package pl.lodz.uni.math.danielg.toothache.activities

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_dentist_sign_up.*
import pl.lodz.uni.math.danielg.toothache.R
import pl.lodz.uni.math.danielg.toothache.managers.onClickEyeButton
import pl.lodz.uni.math.danielg.toothache.managers.setUpTopBar
import pl.lodz.uni.math.danielg.toothache.managers.setupKeyboardVisibility

class DentistSignUpActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "DentistSignUpActivity"
    }

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dentist_sign_up)

        auth = FirebaseAuth.getInstance()

        setupButtons()
        setupKeyboardVisibility(this, dentist_sign_up_lin_lay_id)
        setUpTopBar(this, "Rejestracja", true)
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

    private fun setupButtons() {
        dentist_sign_up_button.setOnClickListener { signUp() }
        eye_button_id.setOnClickListener { onClickEyeButton(dentist_sign_up_password_input) }

        dentist_sign_up_choose_voivodeship_btn_id.setOnClickListener { openVoivodeshipDialog() }
    }

    private fun openVoivodeshipDialog() {
        val voivodeships = resources.getStringArray(R.array.voivodeships);

        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Wybierz województwo")
        builder.setSingleChoiceItems(voivodeships, -1) { dialogInterface, i ->
            dentist_sign_up_voivodeship_tv.text = voivodeships[i]
            dialogInterface.dismiss()
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    // TODO: Attach inputs' RecViews w/ adapters.
    //  Firstly, make them populating during typing.

    // TODO: Add one office to DB. Then make a method that takes whole list of offices and take
    //  an ID for the next one to insert.

    // TODO: Make contact object on 'Signup' click.

    // TODO: Make hashMap of contact object for Firebase Cloud Firestore.

    // TODO: Check signing up. Attach email to office object.
    private fun signUp(
        email: String = dentist_sign_up_email_input.text.toString(),
        passwd: String = dentist_sign_up_password_input.text.toString(),
        passwdRep: String = dentist_sign_up_password_rep_input.text.toString()
    ) {
        if (email.isEmpty() || !email.contains('@') || !email.contains('.')) {
            Log.d(TAG, "Invalid email.")
            return
        }
        if (passwd != passwdRep) {
            Log.d(TAG, "Passwords do not match.")
            return
        } else if (passwd.isBlank()) {
            Log.d(TAG, "Password is blank.")
            return
        }

        auth.createUserWithEmailAndPassword(email, passwd)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser


//                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()


//                    updateUI(null)
                }

                // ...
            }
    }
}