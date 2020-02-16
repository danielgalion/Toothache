package pl.lodz.uni.math.danielg.toothache.activities

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
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

    private fun setupButtons() {
        dentist_sign_up_button.setOnClickListener { signUp() }
        eye_button_id.setOnClickListener { onClickEyeButton(dentist_sign_up_password_input) }
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