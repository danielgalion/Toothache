package pl.lodz.uni.math.danielg.toothache.activities

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_patient_form.*
import pl.lodz.uni.math.danielg.toothache.R
import pl.lodz.uni.math.danielg.toothache.managers.setUpTopBar
import pl.lodz.uni.math.danielg.toothache.managers.showAdequateAlert

class PatientFormActivity : AppCompatActivity() {
    companion object {
        const val EMAIL = "email"
        private const val TAG = "PatientFormActivity"
    }

    private var email = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_form)

        onIntent()
        setUpTopBar(this, "Formularz", true)
        setUpButtons()
    }

    private fun setUpButtons() {
        patient_form_order_button.setOnClickListener {
            if (patient_form_name_edit_t_id.text.toString().isNotBlank()
                && patient_form_phone_edit_t_id.text.toString().isNotBlank()
            )
                makeOrder()
            else showAdequateAlert(this, "Pola imienia i nazwiska oraz nru tel. nie mogą być puste")
        }
    }

    private fun makeOrder() {
        val db = FirebaseFirestore.getInstance()

        db.collection("office").document(email)
            .update("patientName", patient_form_name_edit_t_id.text.toString())
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
            .addOnFailureListener { e ->
                showAdequateAlert(this, "Błąd podczas przesyłania formularza")
                Log.w(TAG, "Error updating document", e)
            }

        db.collection("office").document(email)
            .update("patientPhone", patient_form_phone_edit_t_id.text.toString())
            .addOnSuccessListener {
                showAdequateAlert(
                    this,
                    "Wizyta zamówiona pomyślnie.\nBądź w drodze do gabinetu.\nJeśli chcesz odwołać wizytę – zadzwoń"
                )
                Log.d(TAG, "DocumentSnapshot successfully updated!")
            }
            .addOnFailureListener { e ->
                showAdequateAlert(this, "Błąd podczas przesyłania formularza")
                Log.w(TAG, "Error updating document", e)
            }

        db.collection("office").document(email)
            .update("patientCity", patient_form_location_edit_t_id.text.toString())
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error updating document", e)
            }

        db.collection("office").document(email)
            .update("patientETA", patient_form_eta_edit_t_id.text.toString().toLong())
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error updating document", e)
            }
    }

    private fun onIntent() {
        if (!intent.hasExtra(EMAIL)) return

        email = intent.getStringExtra(EMAIL)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
