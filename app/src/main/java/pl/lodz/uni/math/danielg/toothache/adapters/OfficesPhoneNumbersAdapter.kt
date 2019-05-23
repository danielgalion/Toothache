package pl.lodz.uni.math.danielg.toothache.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rec_v_offices_phone_numbers.view.*
import pl.lodz.uni.math.danielg.toothache.R
import pl.lodz.uni.math.danielg.toothache.managers.CustomViewHolder

class OfficesPhoneNumbersAdapter(private val context: Context, private val phoneNumbers: ArrayList<String>) :
        RecyclerView.Adapter<CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val row = inflater.inflate(R.layout.rec_v_offices_phone_numbers, parent, false)

        return CustomViewHolder(row, row.rec_v_office_phone_numbers_call_button_id)
    }

    override fun getItemCount(): Int {
        return phoneNumbers.size
    }

//    TODO: Change hardcoded phone number.

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

    }
}
