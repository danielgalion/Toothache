package pl.lodz.uni.math.danielg.toothache.fragments

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_dentist_edit.*
import kotlinx.android.synthetic.main.rec_v_office_service_input_row.view.*
import kotlinx.android.synthetic.main.rec_v_text_input_row.view.*
import me.drakeet.support.toast.ToastCompat
import pl.lodz.uni.math.danielg.toothache.R
import pl.lodz.uni.math.danielg.toothache.adapters.OfficeServiceInputAdapter
import pl.lodz.uni.math.danielg.toothache.adapters.TextInputAdapter
import pl.lodz.uni.math.danielg.toothache.managers.*
import pl.lodz.uni.math.danielg.toothache.models.DentalService
import pl.lodz.uni.math.danielg.toothache.models.toServicesArray

class DentistEditFragment : Fragment() {
    //TODO: Real lists could be always from a DB.
    //TODO: Add inside this Fragment data from a contact object (if it'll be convenient)

    companion object {
        private const val TAG = "DentistEditFragment"
    }

    private var doctors = arrayListOf("")
    private var phones = arrayListOf("")
    private var services = arrayListOf(DentalService("", -1))

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_dentist_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        initData()

        setupKeyboardVisibility(activity as Activity, dentist_edit_parent_lay_id)
        setUpButtons()
    }

    private fun initData() {
        val db = FirebaseFirestore.getInstance()

        val docRef =
            db.collection("office").document(auth.currentUser?.email ?: "")
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    onDocumentFetched(document)

                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                } else {
                    showAdequateAlert(activity, "Błąd pobierania dotychczasowych danych")
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                showAdequateAlert(activity, "Błąd pobierania dotychczasowych danych")
                Log.d(TAG, "get failed with ", exception)
            }
    }

    private fun onDocumentFetched(document: DocumentSnapshot?) {
        dentist_edit_name_edit_t_id.setText(document?.get("name").toString())

        try {
            doctors = document?.get("doctorsNames") as ArrayList<String>
            services =
                (document.get("dentalServices") as ArrayList<HashMap<String, Any>>).toServicesArray()
            phones = document.get("phoneNumbers") as ArrayList<String>
        } catch (e: Exception) {
            showAdequateAlert(activity, "Błąd pobierania dotychczasowych danych")
            Log.e(TAG, "Cast exception @onDocumentFetched")
        }

        dentist_edit_address_edit_t_id.setText(document?.get("address").toString())
        dentist_edit_voivodeship_tv.text = document?.get("voivodeship").toString()

        attachRecViews()
    }

    private fun attachRecViews() {
        attachRecView(
            context,
            recycler_v_dentist_edit_dr_id,
            TextInputAdapter(context, doctors, TextInputAdapter.DOCTOR)
        )

        attachRecView(
            context,
            recycler_v_dentist_edit_phone_id,
            TextInputAdapter(context, phones, TextInputAdapter.PHONE)
        )

        attachRecView(
            context,
            recycler_v_dentist_edit_services_id, OfficeServiceInputAdapter(context, services)
        )
    }

    private fun setUpButtons() {
        dentist_edit_choose_voivodeship_btn_id.setOnClickListener {
            openVoivodeshipDialog(context, dentist_edit_voivodeship_tv)
        }

        dentist_edit_button_id.setOnClickListener { saveChanges() }
    }

    private fun saveChanges() {
        val db = FirebaseFirestore.getInstance()

        val doc =
            db.collection("office").document(auth.currentUser?.email ?: "")

        doc.update("name", dentist_edit_name_edit_t_id.text.toString())
            .addOnFailureListener { e ->
                ToastCompat.makeText(
                    context,
                    "Błąd zapisywania danych. Spróbuj ponownie później",
                    ToastCompat.LENGTH_LONG
                ).setBadTokenListener { Log.e(TAG, "Error showing a toast") }.show()
                Log.w(TAG, "Error updating document", e)
            }

        doc.update("address", dentist_edit_address_edit_t_id.text.toString())
            .addOnFailureListener { e ->
                ToastCompat.makeText(
                    context,
                    "Błąd zapisywania danych. Spróbuj ponownie później",
                    ToastCompat.LENGTH_LONG
                ).setBadTokenListener { Log.e(TAG, "Error showing a toast") }.show()
                Log.w(TAG, "Error updating document", e)
            }

        doc.update("voivodeship", dentist_edit_voivodeship_tv.text.toString())
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
            .addOnFailureListener { e ->
                ToastCompat.makeText(
                    context,
                    "Błąd zapisywania danych. Spróbuj ponownie później",
                    ToastCompat.LENGTH_LONG
                ).setBadTokenListener { Log.e(TAG, "Error showing a toast") }.show()
                Log.w(TAG, "Error updating document", e)
            }

        doc.update(
            "doctorsNames",
            getWrittenTexts((recycler_v_dentist_edit_dr_id.layoutManager) as LinearLayoutManager)
        )
            .addOnSuccessListener {
                doctors =
                    getWrittenTexts((recycler_v_dentist_edit_dr_id.layoutManager) as LinearLayoutManager)
                doctors.add("")

                attachRecView(
                    context,
                    recycler_v_dentist_edit_dr_id,
                    TextInputAdapter(context, doctors, TextInputAdapter.DOCTOR)
                )
                recycler_v_dentist_edit_dr_id.adapter?.notifyDataSetChanged()
                Log.d(TAG, "DocumentSnapshot successfully updated!")
            }
            .addOnFailureListener { e ->
                ToastCompat.makeText(
                    context,
                    "Błąd zapisywania danych. Spróbuj ponownie później",
                    ToastCompat.LENGTH_LONG
                ).setBadTokenListener { Log.e(TAG, "Error showing a toast") }.show()
                Log.w(TAG, "Error updating document", e)
            }

        doc.update(
            "phoneNumbers",
            getWrittenTexts((recycler_v_dentist_edit_phone_id.layoutManager) as LinearLayoutManager)
        )
            .addOnSuccessListener {
                phones =
                    getWrittenTexts((recycler_v_dentist_edit_phone_id.layoutManager) as LinearLayoutManager)
                phones.add("")

                attachRecView(
                    context,
                    recycler_v_dentist_edit_phone_id,
                    TextInputAdapter(context, phones, TextInputAdapter.PHONE)
                )
                recycler_v_dentist_edit_phone_id.adapter?.notifyDataSetChanged()
                Log.d(TAG, "DocumentSnapshot successfully updated!")
            }
            .addOnFailureListener { e ->
                ToastCompat.makeText(
                    context,
                    "Błąd zapisywania danych. Spróbuj ponownie później",
                    ToastCompat.LENGTH_LONG
                ).setBadTokenListener { Log.e(TAG, "Error showing a toast") }.show()
                Log.w(TAG, "Error updating document", e)
            }

        doc.update(
            "dentalServices",
            getWrittenServices((recycler_v_dentist_edit_services_id.layoutManager) as LinearLayoutManager)
        )
            .addOnSuccessListener {
                services =
                    getWrittenServices((recycler_v_dentist_edit_services_id.layoutManager) as LinearLayoutManager)
                services.add(DentalService("", -1))

                attachRecView(
                    context,
                    recycler_v_dentist_edit_services_id,
                    OfficeServiceInputAdapter(context, services)
                )
                recycler_v_dentist_edit_services_id.adapter?.notifyDataSetChanged()
                Log.d(TAG, "DocumentSnapshot successfully updated!")
            }
            .addOnFailureListener { e ->
                ToastCompat.makeText(
                    context,
                    "Błąd zapisywania danych. Spróbuj ponownie później",
                    ToastCompat.LENGTH_LONG
                ).setBadTokenListener { Log.e(TAG, "Error showing a toast") }.show()
                Log.w(TAG, "Error updating document", e)
            }


    }

    private fun getWrittenTexts(layoutManager: LinearLayoutManager): ArrayList<String> {
        val count = layoutManager.childCount
        val textsAl = arrayListOf<String>()

        for (i in 0 until count) {
            val lay = layoutManager.findViewByPosition(i) as LinearLayout
            val s = lay.rec_v_text_input_edit_t_id.text.toString()

            if (s.isNotBlank()) textsAl.add(s)
        }

        return textsAl
    }

    private fun getWrittenServices(layoutManager: LinearLayoutManager): ArrayList<DentalService> {
        val count = layoutManager.childCount
        val servicesAl = arrayListOf<DentalService>()

        for (i in 0 until count) {
            val lay = layoutManager.findViewByPosition(i) as LinearLayout
            val name = lay.rec_v_office_services_input_name_edit_t_id.text.toString()
            val price =
                lay.rec_v_office_services_input_price_edit_t_id.text.toString().toLongOrNull()

            if (name.isNotBlank() && price != null) servicesAl.add(DentalService(name, price))
        }

        return servicesAl
    }
}