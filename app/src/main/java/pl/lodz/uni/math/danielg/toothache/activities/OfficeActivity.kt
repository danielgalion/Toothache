package pl.lodz.uni.math.danielg.toothache.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_office.*
import pl.lodz.uni.math.danielg.toothache.R
import pl.lodz.uni.math.danielg.toothache.adapters.OfficesPhoneNumbersAdapter
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
        attachRecView()
        TopBarHelper.setUp(this, "Gabinet", true)
    }

    private fun attachRecView() {
        recycler_v_office_phone_numbers_id.layoutManager = LinearLayoutManager(this)
        recycler_v_office_phone_numbers_id.adapter = OfficesPhoneNumbersAdapter(this, arrayListOf("+48 788 139 685"))

        val animator = recycler_v_office_phone_numbers_id.itemAnimator as DefaultItemAnimator

        animator.supportsChangeAnimations = false
        ViewCompat.setNestedScrollingEnabled(recycler_v_office_phone_numbers_id, false)
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