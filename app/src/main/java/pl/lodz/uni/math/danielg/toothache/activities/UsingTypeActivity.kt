package pl.lodz.uni.math.danielg.toothache.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_using_type.*
import pl.lodz.uni.math.danielg.toothache.R
import pl.lodz.uni.math.danielg.toothache.managers.UsingTypeSharedPreferencesManager

class UsingTypeActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "UsingTypeActivity"
        const val SHOULD_EXIT = "shouldExit"
    }

    var isContentViewSet: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (shouldExitApp()) finish()
        else if (isUserTypeAlreadySet()) intentWhenUsingTypeIsAlreadySet()
        else attachContentView()

        title = "Boliząb Polska"
        // TODO: When a dentist is already logged in intent to his dashboard. (Here or in DentistSignIn)
    }

    // When app is showed on the foreground again (e.g. onBackPressed after intenting for a dentist call).
    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "Called onRestart()")

        if (isUserTypeAlreadySet()) intentWhenUsingTypeIsAlreadySet()
        else if (isContentViewSet) using_type_lin_lay_id.visibility = View.VISIBLE
        else attachContentView()
    }

    private fun shouldExitApp(): Boolean {
        if (!intent.hasExtra(SHOULD_EXIT)) return false

        return intent.getBooleanExtra(SHOULD_EXIT, false)
    }

    private fun isUserTypeAlreadySet(): Boolean {
        return UsingTypeSharedPreferencesManager.getUsingType(this) != UsingTypeSharedPreferencesManager.USING_TYPE_NONE
    }

    private fun intentWhenUsingTypeIsAlreadySet() {
        when (UsingTypeSharedPreferencesManager.getUsingType(this)) {
            UsingTypeSharedPreferencesManager.USING_TYPE_PATIENT ->
                startActivity(Intent(this, PatientDashboardActivity::class.java))
            UsingTypeSharedPreferencesManager.USING_TYPE_DENTIST ->
                startActivity(Intent(this, DentistSignInActivity::class.java))
            else                                                 -> {
            }
        }
    }

    private fun attachContentView() {
        setContentView(R.layout.activity_using_type)
        isContentViewSet = true
        setupButtons()
    }

    private fun setupButtons() {
        using_type_patient_button.setOnClickListener {
            UsingTypeSharedPreferencesManager.setUsingType(this, UsingTypeSharedPreferencesManager.USING_TYPE_PATIENT)
            startActivity(Intent(this, PatientDashboardActivity::class.java))
            // Blank the whole layout to be prepared for onBackPressed()
            using_type_lin_lay_id.visibility = View.GONE
        }

        using_type_dentist_button.setOnClickListener {
            UsingTypeSharedPreferencesManager.setUsingType(this, UsingTypeSharedPreferencesManager.USING_TYPE_DENTIST)
            startActivity(Intent(this, DentistSignInActivity::class.java))
            // Blank the whole layout to be prepared for onBackPressed()
            using_type_lin_lay_id.visibility = View.GONE
        }
    }
}