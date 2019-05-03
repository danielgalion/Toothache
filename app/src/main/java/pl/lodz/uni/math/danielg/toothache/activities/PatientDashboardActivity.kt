package pl.lodz.uni.math.danielg.toothache.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import pl.lodz.uni.math.danielg.toothache.R
import pl.lodz.uni.math.danielg.toothache.managers.ExitAppHelper
import pl.lodz.uni.math.danielg.toothache.managers.TopBarHelper
import pl.lodz.uni.math.danielg.toothache.managers.UsingTypeHelper
import pl.lodz.uni.math.danielg.toothache.managers.UsingTypeSharedPreferencesManager

class PatientDashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_dashboard)

        TopBarHelper.setUp(this, "Ekran pacjenta", true, R.drawable.ic_group_white_24dp)
    }

    override fun onBackPressed() {
        ExitAppHelper.exit(this)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                UsingTypeHelper.intentToChoiceActivity(this)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}