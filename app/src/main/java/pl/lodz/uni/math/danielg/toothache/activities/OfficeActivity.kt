package pl.lodz.uni.math.danielg.toothache.activities

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_office.*
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import pl.lodz.uni.math.danielg.toothache.R
import pl.lodz.uni.math.danielg.toothache.adapters.OfficesDentalServicesAdapter
import pl.lodz.uni.math.danielg.toothache.adapters.OfficesPhoneNumbersAdapter
import pl.lodz.uni.math.danielg.toothache.data.Data
import pl.lodz.uni.math.danielg.toothache.managers.TopBarHelper
import pl.lodz.uni.math.danielg.toothache.managers.toStringWithBrs
import pl.lodz.uni.math.danielg.toothache.models.DentalService
import pl.lodz.uni.math.danielg.toothache.models.Office

class OfficeActivity : AppCompatActivity() {
    companion object {
        const val OFFICE = "Office"
    }

    private var office: Office? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // OpenStreetMap configuration:
        Configuration.getInstance()
            .load(applicationContext, PreferenceManager.getDefaultSharedPreferences(applicationContext))

        setContentView(R.layout.activity_office)

        setupMap()
        onIntent()
        fillData()
        attachRecView()
        TopBarHelper.setUp(this, "Gabinet", true)
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

    private fun setupMap() {
        office_mapview_id.setTileSource(TileSourceFactory.MAPNIK)
//        office_mapview_id.setExpectedCenter(GeoPoint(51.8478115, 19.4195527))
//        office_mapview_id.minZoomLevel = 8.0
        office_mapview_id.isTilesScaledToDpi = true
        office_mapview_id.setMultiTouchControls(false)
        office_mapview_id.setUseDataConnection(true)
        office_mapview_id.controller.setCenter(GeoPoint(51.8478115, 19.4195527))
        office_mapview_id.controller.setZoom(17.0)
    }

    private fun onIntent() {
        if (!intent.hasExtra(OFFICE)) return

        office = intent.getSerializableExtra(OFFICE) as Office
    }

    private fun fillData() {
        office ?: return

        office_name_txt_v_id.text = office?.name
        office_dr_names_txt_v_id.text = office?.doctorsNames?.toStringWithBrs()
        office_address_txt_v_id.text = office?.address

        setAvailability()
    }

    private fun setAvailability() {
        office ?: return

        when (office?.availability) {
            true -> office_availability_img_v_id.setImageResource(R.drawable.circle_green_medical_w_border)
            false -> office_availability_img_v_id.setImageResource(R.drawable.circle_red_w_border)
            null -> office_availability_img_v_id.setImageResource(R.drawable.ic_perm_phone_msg_dark_green_24dp)
        }
    }

    private fun attachRecView() {
        office ?: return

        recycler_v_office_phone_numbers_id.layoutManager = LinearLayoutManager(this)
        recycler_v_office_phone_numbers_id.adapter =
            OfficesPhoneNumbersAdapter(this, office?.phoneNumbers)
//                arrayListOf("+48 788 139 685", "+48 788 139 685", "+48 788 139 685"))

        recycler_v_office_dental_services_id.layoutManager = LinearLayoutManager(this)
        recycler_v_office_dental_services_id.adapter = OfficesDentalServicesAdapter(this, office?.dentalServices)

//            arrayListOf(
//                DentalService("Borowanie", 200),
//                DentalService("Borowanie", 200),
//                DentalService("Borowanie", 200),
//                DentalService("Borowanie", 200),
//                DentalService("Borowanie", 200)
//            )

        var animator = recycler_v_office_phone_numbers_id.itemAnimator as DefaultItemAnimator

        animator.supportsChangeAnimations = false
        ViewCompat.setNestedScrollingEnabled(recycler_v_office_phone_numbers_id, false)

        animator = recycler_v_office_dental_services_id.itemAnimator as DefaultItemAnimator

        animator.supportsChangeAnimations = false
        ViewCompat.setNestedScrollingEnabled(recycler_v_office_dental_services_id, false)
    }
}