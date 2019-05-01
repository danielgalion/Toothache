package pl.lodz.uni.math.danielg.toothache.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import pl.lodz.uni.math.danielg.toothache.R

class UsingTypeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupButtons()
        title = "Boliząb Polska"
    }

    override fun onBackPressed() {}

    private fun setupButtons() {
        main_patient_button.setOnClickListener {
            startActivity(Intent(this, PatientDashboardActivity::class.java))
        }

        main_dentist_button.setOnClickListener {
            startActivity(Intent(this, DentistSignInActivity::class.java))
        }
    }
}