package pl.lodz.uni.math.danielg.toothache.activities

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_dentist_sign_up.*
import kotlinx.android.synthetic.main.rec_v_office_service_input_row.view.*
import kotlinx.android.synthetic.main.rec_v_text_input_row.view.*
import pl.lodz.uni.math.danielg.toothache.R
import pl.lodz.uni.math.danielg.toothache.adapters.OfficeServiceInputAdapter
import pl.lodz.uni.math.danielg.toothache.adapters.TextInputAdapter
import pl.lodz.uni.math.danielg.toothache.managers.*
import pl.lodz.uni.math.danielg.toothache.models.DentalService
import pl.lodz.uni.math.danielg.toothache.models.Office

class DentistSignUpActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "DentistSignUpActivity"
    }

    private lateinit var auth: FirebaseAuth

    private val doctors = arrayListOf("")
    private val phones = arrayListOf("")
    private val services = arrayListOf(DentalService("", -1))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dentist_sign_up)

        auth = FirebaseAuth.getInstance()

        attachRecViews()
        setupKeyboardVisibility(this, dentist_sign_up_lin_lay_id)
        setUpTopBar(this, "Rejestracja", true)
        setupButtons()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun attachRecViews() {
        attachRecView(
            this,
            recycler_v_dentist_sign_up_dr_id,
            TextInputAdapter(this, doctors, TextInputAdapter.DOCTOR)
        )

        attachRecView(
            this,
            recycler_v_dentist_sign_up_phone_id,
            TextInputAdapter(this, phones, TextInputAdapter.PHONE)
        )

        attachRecView(
            this, recycler_v_dentist_sign_up_services_id, OfficeServiceInputAdapter(this, services)
        )
    }

    private fun setupButtons() {
        dentist_sign_up_button.setOnClickListener { signUp() }
        eye_button_id.setOnClickListener { onClickEyeButton(dentist_sign_up_password_input) }

        dentist_sign_up_choose_voivodeship_btn_id.setOnClickListener {
            openVoivodeshipDialog(this, dentist_sign_up_voivodeship_tv)
        }
    }

    private fun logInputData() {
        Log.d(TAG, "doctors: $doctors")
        Log.d(TAG, "phones: $phones")
        Log.d(TAG, "services: $services")
    }

    private fun createOffice(): Office {
        return Office(
            name = dentist_sign_up_name_edit_t_id.text.toString(),
            email = dentist_sign_up_email_input.text.toString(),
            doctorsNames = getWrittenTexts((recycler_v_dentist_sign_up_dr_id.layoutManager) as LinearLayoutManager),
            availability = null,
            address = dentist_sign_up_address_edit_t_id.text.toString(),
            voivodeship = dentist_sign_up_voivodeship_tv.text.toString(),
            phoneNumbers = getWrittenTexts((recycler_v_dentist_sign_up_phone_id.layoutManager) as LinearLayoutManager),
            dentalServices = getWrittenServices((recycler_v_dentist_sign_up_services_id.layoutManager) as LinearLayoutManager),
            patientName = "",
            patientPhone = "",
            patientCity = "",
            patientETA = -1L
        )
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

    private fun signUp(
        email: String = dentist_sign_up_email_input.text.toString(),
        passwd: String = dentist_sign_up_password_input.text.toString(),
        passwdRep: String = dentist_sign_up_password_rep_input.text.toString()
    ) {
        if (email.isEmpty() || !email.contains('@') || !email.contains('.')) {
            showAdequateAlert(this, "Niepoprawny email")
            Log.d(TAG, "Invalid email.")
            return
        }
        if (passwd != passwdRep) {
            showAdequateAlert(this, "Hasła nie pasują do siebie")
            Log.d(TAG, "Passwords do not match.")
            return
        } else if (passwd.isBlank()) {
            showAdequateAlert(this, "Hasło nie może być puste")
            Log.d(TAG, "Password is blank.")
            return
        }

        auth.createUserWithEmailAndPassword(email, passwd)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")

                    logInputData()

                    val db = FirebaseFirestore.getInstance()
                    val office = createOffice()

                    db.collection("office").document(office.email)
                        .set(office.generateMapToSend())
                        .addOnSuccessListener { _ ->
                            onSignedIn(this)

                            Log.d(TAG, "DocumentSnapshot set: $office")
                        }
                        .addOnFailureListener { e ->
                            Log.w(TAG, "Error adding document", e)
                        }
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    showAdequateAlert(
                        this,
                        "Rejestracja nie powiodła się.\nSprawdź e-mail i pola hasła"
                    )
                }
            }
    }
}