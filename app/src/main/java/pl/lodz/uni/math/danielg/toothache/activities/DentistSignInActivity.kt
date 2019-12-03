package pl.lodz.uni.math.danielg.toothache.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_dentist_sign_in.*
import pl.lodz.uni.math.danielg.toothache.R
import pl.lodz.uni.math.danielg.toothache.managers.intentToUsingTypeChoiceActivity
import pl.lodz.uni.math.danielg.toothache.managers.setUpTopBar

class DentistSignInActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "DentistSignInActivity"
    }

    //    TODO: Implement signing-in w/ Firebase.
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dentist_sign_in)

        auth = FirebaseAuth.getInstance()
        setupButtons()
        setUpTopBar(this, "Logowanie", true, R.drawable.ic_group_white_24dp)
    }

    override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser

        if (currentUser != null) onSignIn(currentUser)
        // Check if user is signed in (non-null) and update UI accordingly.
    }

    private fun onSignIn(user: FirebaseUser) {
//        TODO: Depend it w/ user specific info if it'd be needed.
        startActivity(Intent(this, DentistDashboardActivity::class.java))
    }

    override fun onBackPressed() {
        intentToUsingTypeChoiceActivity(this)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                intentToUsingTypeChoiceActivity(this)
                true
            }
            else -> super.onOptionsItemSelected(item)

        }
    }

    private fun setupButtons() {
//        TODO: Test implementation of signing-in and signing-out.
//        TODO: Make an intent on sign-up and test it.
        dentist_sign_in_login_button_id.setOnClickListener {
            val email = dentist_sign_in_email_input_id.text.toString()
            val passwd = dentist_sign_in_password_input_id.text.toString()

            auth.signInWithEmailAndPassword(email, passwd)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        val user = auth.currentUser

//                        TODO: Make code cleaning.
//                        updateUI(user)
                        startActivity(Intent(this, DentistDashboardActivity::class.java))
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
//                        updateUI(null)
                    }

                    // ...
                }

        }

        dentist_sign_in_sign_up_button_id.setOnClickListener {
            startActivity(Intent(this, DentistSignUpActivity::class.java))
        }
    }
}