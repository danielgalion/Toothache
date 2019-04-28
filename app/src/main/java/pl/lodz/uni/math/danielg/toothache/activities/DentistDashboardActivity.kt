package pl.lodz.uni.math.danielg.toothache.activities

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_dentist_dashboard.*
import kotlinx.android.synthetic.main.navigation_view.*
import pl.lodz.uni.math.danielg.toothache.R
import pl.lodz.uni.math.danielg.toothache.fragments.DentistEditFragment
import pl.lodz.uni.math.danielg.toothache.fragments.DentistListFragment

class DentistDashboardActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    companion object {
        private const val TAG = "DentistDashbActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dentist_dashboard)

        initNavView()
        setTopBar()
    }

    // TODO: Add office item. Or icon on the right hand side of a TopBar
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.list -> {
                Log.d(TAG, "List item is clicked. @onNavigationItemSelected(..)")
                supportFragmentManager.beginTransaction().replace(R.id.dentist_dashboard_fragment_lin_lay, DentistListFragment()).commit()
                supportActionBar!!.title = "Lista"
            }
            R.id.edit -> {
                Log.d(TAG, "Edit item is clicked. @onNavigationItemSelected(..)")
                supportFragmentManager.beginTransaction().replace(R.id.dentist_dashboard_fragment_lin_lay, DentistEditFragment()).commit()
                supportActionBar!!.title = "Edytuj"
            }
            R.id.patient_dentist -> {
                onBackPressed()
            }
        }

        dentist_dashboard_drawer_lay_id.closeDrawer(GravityCompat.START)

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
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initNavView() {
        navigation_view_id.inflateMenu(R.menu.dentist_drawer_view)
        navigation_view_id.setNavigationItemSelectedListener(this)
    }

    private fun setTopBar() {
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Ekran dentysty"
    }
}