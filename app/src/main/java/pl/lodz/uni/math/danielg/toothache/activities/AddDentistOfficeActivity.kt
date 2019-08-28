package pl.lodz.uni.math.danielg.toothache.activities

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
//import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_add_dentist_office.*
import pl.lodz.uni.math.danielg.toothache.R
import pl.lodz.uni.math.danielg.toothache.adapters.OfficeServiceInputAdapter
import pl.lodz.uni.math.danielg.toothache.adapters.TextInputAdapter
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
        recycler_v_add_dentist_office_dr_id.layoutManager = LinearLayoutManager(this)
        recycler_v_add_dentist_office_dr_id.adapter =
            TextInputAdapter(this, doctors, TextInputAdapter.DOCTOR)

        recycler_v_add_dentist_office_phone_id.layoutManager = LinearLayoutManager(this)
        recycler_v_add_dentist_office_phone_id.adapter =
            TextInputAdapter(this, phones, TextInputAdapter.PHONE)

        recycler_v_add_dentist_office_service_id.layoutManager = LinearLayoutManager(this)
        recycler_v_add_dentist_office_service_id.adapter =
            OfficeServiceInputAdapter(this, services)

        var animator = recycler_v_add_dentist_office_dr_id.itemAnimator as DefaultItemAnimator

        animator.supportsChangeAnimations = false
        ViewCompat.setNestedScrollingEnabled(recycler_v_add_dentist_office_dr_id, false)

        animator = recycler_v_add_dentist_office_phone_id.itemAnimator as DefaultItemAnimator

        animator.supportsChangeAnimations = false
        ViewCompat.setNestedScrollingEnabled(recycler_v_add_dentist_office_phone_id, false)

        animator = recycler_v_add_dentist_office_service_id.itemAnimator as DefaultItemAnimator

        animator.supportsChangeAnimations = false
        ViewCompat.setNestedScrollingEnabled(recycler_v_add_dentist_office_service_id, false)
    }

//    TODO: Next task: Add Office object to database.
//    TODO: Another task: Check how authentication and connecting user with database works.
//    TODO: The other another task: Check how to send information about notification to Firebase from app.

    private fun setupButtons() {
        add_dentist_office_button_id.setOnClickListener {
            //            val db = FirebaseFirestore.getInstance()

            // Create a new user with a first, middle, and last name
//            val user = hashMapOf(
//                "first" to "Alan",
//                "middle" to "Mathison",
//                "last" to "Turing",
//                "born" to 1912
//            )

// Add a new document with a generated ID
//            db.collection("users")
//                .add(user)
//                .addOnSuccessListener { documentReference ->
//                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
//                }
//                .addOnFailureListener { e ->
//                    Log.w(TAG, "Error adding document", e)
//                }

            // Create a new user with a first and last name
//            val user = hashMapOf(
//                "first" to "Ada",
//                "last" to "Lovelace",
//                "born" to 1815
//            )
//
//// Add a new document with a generated ID
//            db.collection("users")
//                .add(user)
//                .addOnSuccessListener { documentReference ->
//                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
//                }
//                .addOnFailureListener { e ->
//                    Log.w(TAG, "Error adding document", e)
//                }

//            val database = FirebaseDatabase.getInstance()
//            val myRef = database.getReference("message")
//
//            myRef.setValue("Elooo")
//            myRef.setValue(
//                    Office(
//                            name = add_dentist_office_name_edit_t_id.text.toString(),
//                            doctorsNames = arrayListOf(add_dentist_office_drs_name_edit_t_id.text.toString(), "Andrew Hardcoded"),
//                            availability = null,
//                            address = add_dentist_office_address_edit_t_id.text.toString(),
//                            phoneNumbers = arrayListOf(add_dentist_office_phone_edit_t_id.text.toString(), "+48000111222"),
//                            dentalServices = arrayListOf(DentalService("Hardcoded service", 404)),
//                            lat = 50.0, lng = 50.0
//                    )
//            )
        }
    }
}

// Write a message to the database
//val database = FirebaseDatabase.getInstance()
//val myRef = database.getReference("message")
//
//myRef.setValue("Hello, World!")