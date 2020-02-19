package pl.lodz.uni.math.danielg.toothache.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_dentist_availability.*
import pl.lodz.uni.math.danielg.toothache.R
import pl.lodz.uni.math.danielg.toothache.managers.showAdequateAlert

class DentistAvailabilityFragment : Fragment() {
    companion object {
        private const val TAG = "DentistAvailabilityFrag"
    }

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_dentist_availability, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        setUpViewChanges()
        setUpButtons()
    }

    private fun setUpViewChanges() {
        val db = FirebaseFirestore.getInstance()
        val docRef =
            db.collection("office").document(auth.currentUser?.email ?: "")
        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                showAdequateAlert(
                    activity,
                    "Błąd pobierania danych gabinetu.\nSprawdź połączenie internetowe"
                )
                Log.w(TAG, "Listen failed.", e)
                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.exists()) {
                onAvailabilityChange(snapshot.data?.get("availability") as Boolean?)
                onVisitRequest(snapshot.data)

                Log.d(TAG, "Current data: ${snapshot.data}")
            } else {
                showAdequateAlert(activity, "Błąd pobierania danych gabinetu")
                Log.d(TAG, "Current data: null")
            }
        }
    }

    private fun onAvailabilityChange(availability: Boolean?) {
        try {
            when (availability) {
                true -> dentist_availability_iv_id.setImageResource(R.drawable.circle_green_medical_w_border)
                false -> dentist_availability_iv_id.setImageResource(R.drawable.circle_red_w_border)
                null -> dentist_availability_iv_id.setImageResource(R.drawable.ic_perm_phone_msg_dark_green_24dp)
            }
        } catch (e: NullPointerException) {
            e.printStackTrace()
            Log.e(TAG, "NPE @onAvailabilityChange")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "Exception @onAvailabilityChange")
        }
    }

    private fun onVisitRequest(data: Map<String, Any>?) {
        try {
            val name = data?.get("patientName") as String?

            if (name.isNullOrBlank()) {
                dentist_availability_visit_ended_button_id.visibility = View.GONE
                dentist_availability_patient_lin_lay_id.visibility = View.GONE
            } else {
                dentist_availability_patient_lin_lay_id.visibility = View.VISIBLE
                dentist_availability_visit_ended_button_id.visibility = View.VISIBLE

                dentist_availability_name_tv_id.text = name
                dentist_availability_phone_tv_id.text = data?.get("patientPhone") as String? ?: ""
                dentist_availability_location_tv_id.text = data?.get("patientCity") as String? ?: ""
                dentist_availability_eta_tv_id.text =
                    getString(R.string.eta_in_minutes, data?.get("patientETA") as Long? ?: 30L)
            }
        } catch (e: NullPointerException) {
            e.printStackTrace()
            Log.e(TAG, "NPE @onVisitRequest")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "Exception @onVisitRequest")
        }
    }

    private fun setUpButtons() {
        dentist_availability_phone_orders_btn_id.setOnClickListener { changeToPhoneOrders() }

        dentist_availability_iv_id.setOnClickListener { changeAvailability() }

        dentist_availability_visit_ended_button_id.setOnClickListener { endVisit() }
    }

    private fun changeToPhoneOrders() {
        val db = FirebaseFirestore.getInstance()

        db.collection("office").document(auth.currentUser?.email ?: "")
            .update("availability", null)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
    }

    private fun changeAvailability() {
        val db = FirebaseFirestore.getInstance()

        db.collection("office").document(auth.currentUser?.email ?: "")
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {

                    when (document.data?.get("availability") as Boolean?) {
                        true -> changeToInactive()
                        null -> changeToInactive()
                        // Pozwól na włączenie dostępności tylko jeśli nie ma pacjenta w drodze.
                        false -> if ((document.data?.get("patientName") as String?).isNullOrBlank())
                            changeToActive()
                    }

                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }
    }

    private fun changeToActive() {
        val db = FirebaseFirestore.getInstance()

        db.collection("office").document(auth.currentUser?.email ?: "")
            .update("availability", true)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
    }

    private fun changeToInactive() {
        val db = FirebaseFirestore.getInstance()

        db.collection("office").document(auth.currentUser?.email ?: "")
            .update("availability", false)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
    }

    // TODO: Firstly show dialog w/ yes and no
    private fun endVisit() {
        val db = FirebaseFirestore.getInstance()
        val doc = db.collection("office").document(auth.currentUser?.email ?: "")

        doc.update("availability", false)
            .addOnSuccessListener {
                Log.d(TAG, "DocumentSnapshot's availability successfully updated!")
            }
            .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }

        doc.update("patientName", "")
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot's name successfully updated!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }

        doc.update("patientPhone", "")
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot's phone successfully updated!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }

        doc.update("patientCity", "")
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot's city successfully updated!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }

        doc.update("patientETA", 0)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot's ETA successfully updated!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
    }
}