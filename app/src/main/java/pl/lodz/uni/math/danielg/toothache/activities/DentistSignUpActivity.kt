package pl.lodz.uni.math.danielg.toothache.activities

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_dentist_sign_up.*
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
            doctorsNames = doctors.getWrittenInputs(),
            availability = null,
            address = dentist_sign_up_address_edit_t_id.text.toString(),
            voivodeship = dentist_sign_up_voivodeship_tv.text.toString(),
            phoneNumbers = phones.getWrittenInputs(),
            dentalServices = services.getWrittenInputs(),
            patientName = "",
            patientPhone = "",
            patientCity = "",
            patientETA = -1
        )
    }

    // TODO: CHECK THIS
    //  Add one office to DB.

    // TODO: CHECK THIS
    //  Make hashMap of contact object for Firebase Cloud Firestore.

    // TODO: Check signing up.
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
//                    val user = auth.currentUser

                    logInputData()

                    val db = FirebaseFirestore.getInstance()
                    val office = createOffice()

                    db.collection("office").document(office.email)
                        .set(office.generateMapToSend())
                        .addOnSuccessListener { _ ->
                            Log.d(TAG, "DocumentSnapshot set: $office")
                        }
                        .addOnFailureListener { e ->
                            Log.w(TAG, "Error adding document", e)
                        }

//                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    showAdequateAlert(
                        this,
                        "Rejestracja nie powiodła się.\nSprawdź e-mail i pola hasła"
                    )


//                    updateUI(null)
                }

                // ...
            }
    }
}