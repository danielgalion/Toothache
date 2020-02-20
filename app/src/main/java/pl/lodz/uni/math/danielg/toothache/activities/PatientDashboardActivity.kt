package pl.lodz.uni.math.danielg.toothache.activities

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.QueryDocumentSnapshot
import kotlinx.android.synthetic.main.activity_patient_dashboard.*
import pl.lodz.uni.math.danielg.toothache.R
import pl.lodz.uni.math.danielg.toothache.adapters.OfficesListAdapter
import pl.lodz.uni.math.danielg.toothache.managers.*
import pl.lodz.uni.math.danielg.toothache.models.Office
import pl.lodz.uni.math.danielg.toothache.models.toServicesArray

class PatientDashboardActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "PatientDashboardActivit"
    }

    private var voivodeship = "lubuskie"
    private var listener: ListenerRegistration? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_dashboard)

        attachDbData()
        setUpTopBar(this, "Ekran pacjenta", true, R.drawable.ic_group_white_24dp)
    }

    private fun attachDbData() {
        if (listener != null) listener?.remove()

        val db = FirebaseFirestore.getInstance()

        val query = db.collection("office")
            .whereEqualTo("voivodeship", voivodeship)


        listener = query.addSnapshotListener { value, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                return@addSnapshotListener
            }

            val officeAl = arrayListOf<Office>()

            for (doc in value!!) {
                officeAl.add(createOffice(doc))
            }

            attachVerticalRecView(
                this,
                recycler_v_patient_dashboard_id,
                OfficesListAdapter(this, officeAl)
            )
        }
    }

    private fun createOffice(doc: QueryDocumentSnapshot): Office {
        return Office(
            name = doc.get("name").toString(),
            email = doc.get("email").toString(),
            doctorsNames = doc.get("doctorsNames") as ArrayList<String>,
            availability = doc.get("availability") as Boolean?,
            address = doc.get("address").toString(),
            voivodeship = doc.get("voivodeship").toString(),
            phoneNumbers = doc.get("phoneNumbers") as ArrayList<String>,
            dentalServices = (doc.get("dentalServices") as ArrayList<HashMap<String, Any>>).toServicesArray(),
            patientName = doc.get("patientName").toString(),
            patientPhone = doc.get("patientPhone").toString(),
            patientCity = doc.get("patientCity").toString(),
            patientETA = doc.get("patientETA").toString().toLong()
        )
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
            R.id.patient_dashboard_dolnoslaskie_id -> {
                voivodeship = "dolnośląskie"
                attachDbData()
                true
            }
            R.id.patient_dashboard_kuj_pom_id -> {
                voivodeship = "kujawsko-pomorskie"
                attachDbData()
                true
            }
            R.id.patient_dashboard_lubelskie_id -> {
                voivodeship = "lubelskie"
                attachDbData()
                true
            }
            R.id.patient_dashboard_lubuskie_id -> {
                voivodeship = "lubuskie"
                attachDbData()
                true
            }
            R.id.patient_dashboard_lodzkie_id -> {
                voivodeship = "łódzkie"
                attachDbData()
                true
            }
            R.id.patient_dashboard_malopolskie_id -> {
                voivodeship = "małopolskie"
                attachDbData()
                true
            }
            R.id.patient_dashboard_mazowieckie_id -> {
                voivodeship = "mazowieckie"
                attachDbData()
                true
            }
            R.id.patient_dashboard_opolskie_id -> {
                voivodeship = "opolskie"
                attachDbData()
                true
            }
            R.id.patient_dashboard_podkarpackie_id -> {
                voivodeship = "podkarpackie"
                attachDbData()
                true
            }
            R.id.patient_dashboard_podlaskie_id -> {
                voivodeship = "podlaskie"
                attachDbData()
                true
            }
            R.id.patient_dashboard_pomorskie_id -> {
                voivodeship = "pomorskie"
                attachDbData()
                true
            }
            R.id.patient_dashboard_slaskie_id -> {
                voivodeship = "śląskie"
                attachDbData()
                true
            }
            R.id.patient_dashboard_swietokrzyskie_id -> {
                voivodeship = "świętokrzyskie"
                attachDbData()
                true
            }
            R.id.patient_dashboard_warm_maz_id -> {
                voivodeship = "warmińsko-mazurskie"
                attachDbData()
                true
            }
            R.id.patient_dashboard_wielkopolskie_id -> {
                voivodeship = "wielkopolskie"
                attachDbData()
                true
            }
            R.id.patient_dashboard_zachodniopom_id -> {
                voivodeship = "zachodniopomorskie"
                attachDbData()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}