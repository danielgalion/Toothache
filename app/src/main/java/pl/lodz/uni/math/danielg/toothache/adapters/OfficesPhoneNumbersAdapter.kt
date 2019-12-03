package pl.lodz.uni.math.danielg.toothache.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rec_v_offices_phone_numbers_row.view.*
import pl.lodz.uni.math.danielg.toothache.R
import pl.lodz.uni.math.danielg.toothache.managers.CustomViewHolder
import pl.lodz.uni.math.danielg.toothache.managers.ItemClickListener
import pl.lodz.uni.math.danielg.toothache.managers.call

class OfficesPhoneNumbersAdapter(
    private val context: Context,
    private val phoneNumbers: ArrayList<String>?
) :
    RecyclerView.Adapter<CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val row = inflater.inflate(R.layout.rec_v_offices_phone_numbers_row, parent, false)

        return CustomViewHolder(row, row.rec_v_office_phone_numbers_call_button_id)
    }

    override fun getItemCount(): Int {
        return phoneNumbers?.size ?: 0
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.view.rec_v_office_phone_numbers_txt_v_id.text = phoneNumbers?.get(position)
        onClick(holder)
    }

    private fun onClick(holder: CustomViewHolder) {
        holder.itemClickListener = object : ItemClickListener {
            override fun onItemClick(view: View?) {
                when (view?.id) {
                    view?.rec_v_office_phone_numbers_call_button_id?.id -> call(
                        context,
                        phoneNumbers?.get(holder.adapterPosition)
                    )
                }
            }
        }
    }
}
