package pl.lodz.uni.math.danielg.toothache.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pl.lodz.uni.math.danielg.toothache.R

class PatientDashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_dashboard)

        title = "Ekran pacjenta"
    }

    override fun onBackPressed() {}
}