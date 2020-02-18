package pl.lodz.uni.math.danielg.toothache.fragments

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_dentist_edit.*
import pl.lodz.uni.math.danielg.toothache.R
import pl.lodz.uni.math.danielg.toothache.adapters.OfficeServiceInputAdapter
import pl.lodz.uni.math.danielg.toothache.adapters.TextInputAdapter
import pl.lodz.uni.math.danielg.toothache.managers.attachRecView
import pl.lodz.uni.math.danielg.toothache.managers.openVoivodeshipDialog
import pl.lodz.uni.math.danielg.toothache.managers.setupKeyboardVisibility
import pl.lodz.uni.math.danielg.toothache.models.DentalService

class DentistEditFragment : Fragment() {
    //TODO: Real lists could be always from a DB.
    //TODO: Add inside this Fragment data from a contact object (if it'll be convenient)

    private val doctors = arrayListOf("Andrzej Gołota", "Pafnucy Pankracy")
    private val phones = arrayListOf("+777", "+888")
    private val services = arrayListOf(
        DentalService("Ząb", 1000),
        DentalService("Brr", 300),
        DentalService("Szkliwo", 500)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_dentist_edit, container, false)
    }


    // TODO: Add voivodeship choice.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        attachRecViews()
        setupKeyboardVisibility(activity as Activity, dentist_edit_parent_lay_id)
        setUpButtons()
    }

    private fun attachRecViews() {
        attachRecView(
            context,
            recycler_v_dentist_edit_dr_id,
            TextInputAdapter(context, doctors, TextInputAdapter.DOCTOR)
        )

        attachRecView(
            context,
            recycler_v_dentist_edit_phone_id,
            TextInputAdapter(context, phones, TextInputAdapter.PHONE)
        )

        attachRecView(
            context,
            recycler_v_dentist_edit_services_id, OfficeServiceInputAdapter(context, services)
        )
    }

    private fun setUpButtons() {
        dentist_edit_choose_voivodeship_btn_id.setOnClickListener {
            openVoivodeshipDialog(context, dentist_edit_voivodeship_tv)
        }
    }

}