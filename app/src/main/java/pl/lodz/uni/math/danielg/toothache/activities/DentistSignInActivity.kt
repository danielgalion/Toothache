package pl.lodz.uni.math.danielg.toothache.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_dentist_sign_in.*
import pl.lodz.uni.math.danielg.toothache.R

class DentistSignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dentist_sign_in)

        setupButtons()

        title = "Logowanie"
    }

    private fun setupButtons() {
        dentist_sign_in_login_button_id.setOnClickListener {
            startActivity(Intent(this, DentistDashboardActivity::class.java))
        }

        dentist_sign_in_sign_up_button_id.setOnClickListener {
            startActivity(Intent(this, DentistSignUpActivity::class.java))
        }
    }
}