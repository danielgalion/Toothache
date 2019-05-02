package pl.lodz.uni.math.danielg.toothache.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_dentist_sign_in.*
import pl.lodz.uni.math.danielg.toothache.R
import pl.lodz.uni.math.danielg.toothache.managers.UsingTypeSharedPreferencesManager

class DentistSignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dentist_sign_in)

        setupButtons()

        title = "Logowanie"
    }

    override fun onBackPressed() {
        val intent = Intent(this, UsingTypeActivity::class.java)

        UsingTypeSharedPreferencesManager.setUsingType(this, UsingTypeSharedPreferencesManager.USING_TYPE_NONE)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
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