package pl.lodz.uni.math.danielg.toothache.activities

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_add_dentist_office.*
import pl.lodz.uni.math.danielg.toothache.R
import pl.lodz.uni.math.danielg.toothache.adapters.OfficeServiceInputAdapter
import pl.lodz.uni.math.danielg.toothache.adapters.TextInputAdapter
import pl.lodz.uni.math.danielg.toothache.data.Data
import pl.lodz.uni.math.danielg.toothache.managers.CustomViewHolder
import pl.lodz.uni.math.danielg.toothache.managers.TopBarHelper
import pl.lodz.uni.math.danielg.toothache.models.DentalService

class AddDentistOfficeActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "AddDentistOfficeActivit"
    }

    private val doctors = arrayListOf("")
    private val phones = arrayListOf("")
    private val services = arrayListOf(DentalService("", -1))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_dentist_office)

        attachRecViews()
        setupButtons()
        TopBarHelper.setUp(this, "Nowy gabinet", true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun attachRecViews() {
        attachRecView(
            recycler_v_add_dentist_office_dr_id,
            TextInputAdapter(this, doctors, TextInputAdapter.DOCTOR)
        )

        attachRecView(
            recycler_v_add_dentist_office_phone_id,
            TextInputAdapter(this, phones, TextInputAdapter.PHONE)
        )

        attachRecView(
            recycler_v_add_dentist_office_service_id, OfficeServiceInputAdapter(this, services)
        )
    }

    private fun attachRecView(rv: RecyclerView, adapter: RecyclerView.Adapter<CustomViewHolder>) {
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter

        val animator = rv.itemAnimator as DefaultItemAnimator

        animator.supportsChangeAnimations = false
        ViewCompat.setNestedScrollingEnabled(rv, false)
    }

//    TODO: Next task: Add Office object to database.
//    TODO: Another task: Check how authentication and connecting user with database works.
//    TODO: The other another task: Check how to send information about notification to Firebase from app.
//    TODO: Search on map. Problem: how to resolve this?:
//     New activity? (problem with saving data between views, I mean it's time consuming in implementation),
//     some popup? (It'd impact negatively on performance, cause of drawing layouts one on another. But maybe not and it'll be OK.),
//     some other feature from OSM API? (Maybe on click 'search' a place will be searched in the background + popup on error.)
//    TODO: Deleting offices from DB and refreshing on layout.


    private fun setupButtons() {
        add_dentist_office_button_id.setOnClickListener {
            val office = hashMapOf(
                "name" to "Rwacz",
                "doctorsNames" to arrayListOf("Andrzej Gołota", "Mike Tyson"),
                "availability" to null,
                "address" to "ul. Gdańska 590, 00-000 Miejska Wieś",
                "phoneNumbers" to arrayListOf("+00111222333", "+11000222333", "+55999888777"),
                "dentalServices" to arrayListOf(
                    hashMapOf("name" to "Wyrwanie", "price" to 678),
                    hashMapOf("name" to "Plombowanie", "price" to 876)
                ),
                "lat" to 30.9625,
                "lng" to 46.103056
            )

            val db = FirebaseFirestore.getInstance()

            db.collection("office").add(office)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }
        }
    }
}

//val sampleFullOffice: Office = Office(
//    name = "Something Medical",
//    address = "ul. Zamorska 38 m. 3, 77-888 Ur Chaldejskie",
//    availability = null,
//    dentalServices = arrayListOf(DentalService("Borowanie", 250), DentalService("Wyrywanie", 300)),
//    doctorsNames = arrayListOf("Andrzej Rozumny", "Przemek Barszcz"),
//    lat = 30.9625,
//    lng = 46.103056,
//    phoneNumbers = arrayListOf("+48 788 139 685", "+48 7777")
//
//)

