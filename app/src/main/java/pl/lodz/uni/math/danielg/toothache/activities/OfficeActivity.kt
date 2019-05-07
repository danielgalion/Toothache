package pl.lodz.uni.math.danielg.toothache.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_office.*
import pl.lodz.uni.math.danielg.toothache.R
import pl.lodz.uni.math.danielg.toothache.managers.TopBarHelper
import pl.lodz.uni.math.danielg.toothache.managers.toStringWithBrs
import pl.lodz.uni.math.danielg.toothache.models.Office

class OfficeActivity : AppCompatActivity() {
    companion object {
        const val OFFICE = "Office"
    }

    private var office: Office? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_office)

        onIntent()
        fillData()
        TopBarHelper.setUp(this, "Gabinet", true)
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