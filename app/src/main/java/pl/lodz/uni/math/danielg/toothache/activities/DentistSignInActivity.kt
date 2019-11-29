package pl.lodz.uni.math.danielg.toothache.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_dentist_sign_in.*
import pl.lodz.uni.math.danielg.toothache.R
import pl.lodz.uni.math.danielg.toothache.managers.intentToUsingTypeChoiceActivity
import pl.lodz.uni.math.danielg.toothache.managers.setUpTopBar

class DentistSignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dentist_sign_in)

        setupButtons()
        setUpTopBar(this, "Logowanie", true, R.drawable.ic_group_white_24dp)
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
        dentist_sign_in_login_button_id.setOnClickListener {
            startActivity(Intent(this, DentistDashboardActivity::class.java))
        }

        dentist_sign_in_sign_up_button_id.setOnClickListener {
            startActivity(Intent(this, DentistSignUpActivity::class.java))
        }
    }
}