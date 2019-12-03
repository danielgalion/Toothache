package pl.lodz.uni.math.danielg.toothache.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_patient_dashboard.*
import pl.lodz.uni.math.danielg.toothache.R
import pl.lodz.uni.math.danielg.toothache.adapters.OfficesListAdapter
import pl.lodz.uni.math.danielg.toothache.data.Data
import pl.lodz.uni.math.danielg.toothache.managers.*

class PatientDashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_dashboard)

//        TODO: Later attach real data list
        attachVerticalRecView(this, recycler_v_patient_dashboard_id, OfficesListAdapter(this, Data.sampleOfficeList))
        setUpTopBar(this, "Ekran pacjenta", true, R.drawable.ic_group_white_24dp)
    }

    override fun onBackPressed() {
        exitApp(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.patient_dashboard, menu)

        return true
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
}