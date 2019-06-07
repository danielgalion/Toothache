package pl.lodz.uni.math.danielg.toothache.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_office.*
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import pl.lodz.uni.math.danielg.toothache.R
import pl.lodz.uni.math.danielg.toothache.adapters.OfficesDentalServicesAdapter
import pl.lodz.uni.math.danielg.toothache.adapters.OfficesPhoneNumbersAdapter
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
        setContentView(R.layout.activity_office)

//        office_mapview_id.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE)
//        office_mapview_id.setExpectedCenter(GeoPoint(51.8478115, 19.4195527))
//        office_mapview_id.minZoomLevel = 8.0
//        office_mapview_id.setUseDataConnection(true)

        onIntent()
        fillData()
        attachRecView()
        TopBarHelper.setUp(this, "Gabinet", true)
    }

    private fun attachRecView() {
        recycler_v_office_phone_numbers_id.layoutManager = LinearLayoutManager(this)
        recycler_v_office_phone_numbers_id.adapter = OfficesPhoneNumbersAdapter(this, arrayListOf("+48 788 139 685", "+48 788 139 685", "+48 788 139 685"))

        recycler_v_office_dental_services_id.layoutManager = LinearLayoutManager(this)
        recycler_v_office_dental_services_id.adapter = OfficesDentalServicesAdapter(this, arrayListOf(DentalService("Borowanie", 200), DentalService("Borowanie", 200), DentalService("Borowanie", 200), DentalService("Borowanie", 200), DentalService("Borowanie", 200)))

        var animator = recycler_v_office_phone_numbers_id.itemAnimator as DefaultItemAnimator

        animator.supportsChangeAnimations = false
        ViewCompat.setNestedScrollingEnabled(recycler_v_office_phone_numbers_id, false)

        animator = recycler_v_office_dental_services_id.itemAnimator as DefaultItemAnimator

        animator.supportsChangeAnimations = false
        ViewCompat.setNestedScrollingEnabled(recycler_v_office_dental_services_id, false)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else              -> super.onOptionsItemSelected(item)
        }
    }

    private fun onIntent() {
        if (!intent.hasExtra(OFFICE)) return

        office = intent.getSerializableExtra(OFFICE) as Office
    }

    private fun fillData() {
        office ?: return

        office_name_txt_v_id.text = office?.name
        office_dr_names_txt_v_id.text = office?.doctorsNames?.toStringWithBrs()
    }
}