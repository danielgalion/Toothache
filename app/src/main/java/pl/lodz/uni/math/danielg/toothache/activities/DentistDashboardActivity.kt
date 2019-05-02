package pl.lodz.uni.math.danielg.toothache.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_dentist_dashboard.*
import kotlinx.android.synthetic.main.navigation_view.*
import pl.lodz.uni.math.danielg.toothache.R
import pl.lodz.uni.math.danielg.toothache.fragments.DentistEditFragment
import pl.lodz.uni.math.danielg.toothache.fragments.DentistListFragment
import pl.lodz.uni.math.danielg.toothache.managers.TopBarHelper
import pl.lodz.uni.math.danielg.toothache.managers.UsingTypeSharedPreferencesManager

class DentistDashboardActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    companion object {
        private const val TAG = "DentistDashbActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dentist_dashboard)

        initNavView()
        TopBarHelper.setUp(this, "Ekran dentysty", true, R.drawable.ic_menu_white_24dp)
    }

    override fun onBackPressed() {
        finish()
        Handler().postDelayed({ System.exit(0) }, 500)
    }

    // TODO: Add office item. Or icon on the right hand side of a TopBar
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.list -> {
                Log.d(TAG, "List item is clicked. @onNavigationItemSelected(..)")
                supportFragmentManager.beginTransaction()
                        .replace(R.id.dentist_dashboard_fragment_lin_lay, DentistListFragment()).commit()
                supportActionBar!!.title = "Lista"
            }
            R.id.edit -> {
                Log.d(TAG, "Edit item is clicked. @onNavigationItemSelected(..)")
                supportFragmentManager.beginTransaction()
                        .replace(R.id.dentist_dashboard_fragment_lin_lay, DentistEditFragment()).commit()
                supportActionBar!!.title = "Edytuj"
            }
            R.id.patient_dentist -> {
                val intent = Intent(this, UsingTypeActivity::class.java)

                // UsingTypeActivity is in launchMode "singleTask". So there won't be opened multiple windows of this activity.
                UsingTypeSharedPreferencesManager.setUsingType(this, UsingTypeSharedPreferencesManager.USING_TYPE_NONE)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
            }
            R.id.sign_out -> {
                UsingTypeSharedPreferencesManager.setUsingType(this, UsingTypeSharedPreferencesManager.USING_TYPE_NONE)
                startActivity(Intent(this, DentistSignInActivity::class.java))
            }
        }

        dentist_dashboard_drawer_lay_id.closeDrawer(GravityCompat.START)

        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.dentist_dashboard, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                if (dentist_dashboard_drawer_lay_id.isDrawerOpen(GravityCompat.START)) {
                    dentist_dashboard_drawer_lay_id.closeDrawer(GravityCompat.START)
                } else {
                    dentist_dashboard_drawer_lay_id.openDrawer(GravityCompat.START)
                }
                true
            }
            R.id.dentist_dashboard_add_office_id -> {
                startActivity(Intent(this, AddDentistOfficeActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initNavView() {
        navigation_view_id.inflateMenu(R.menu.dentist_drawer_view)
        navigation_view_id.setNavigationItemSelectedListener(this)
    }
}