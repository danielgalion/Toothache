package pl.lodz.uni.math.danielg.toothache.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rec_v_office_row.view.*
import pl.lodz.uni.math.danielg.toothache.R
import pl.lodz.uni.math.danielg.toothache.managers.CustomViewHolder
import pl.lodz.uni.math.danielg.toothache.managers.ItemClickListener
import pl.lodz.uni.math.danielg.toothache.managers.toStringWithBrs
import pl.lodz.uni.math.danielg.toothache.models.OfficeShortened

class OfficesAdapter(private val context: Context, private val offices: ArrayList<OfficeShortened>?) :
        RecyclerView.Adapter<CustomViewHolder>() {

    companion object {
        private const val TAG = "OfficesAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val row = inflater.inflate(R.layout.rec_v_office_row, parent, false)

        return CustomViewHolder(row, row.rec_v_office_availability_img_id)
    }

    override fun getItemCount(): Int {
        return offices?.size ?: 0
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        insertTopAndBottomSpaces(holder, position)
        holder.view.rec_v_office_name_txt_id.text = offices?.get(position)?.name
        holder.view.rec_v_office_dr_names_txt_id.text =
                offices?.get(position)?.doctorsNames?.toStringWithBrs()
        insertAvailability(holder, position)
        onClick(holder)
    }

    private fun onClick(holder: CustomViewHolder) {
        holder.itemClickListener = object : ItemClickListener {
            override fun onItemClick(view: View?) {
                val availability = offices?.get(holder.adapterPosition)?.availability

                if (availability == false || availability == null) {
                    OrderVisitHelper.call(context, offices?.get(holder.adapterPosition)?.mainPhoneNumber)
                } else {
//                    TODO: Intent to form for ordering a visit.
                }
            }
        }
    }

    private fun insertTopAndBottomSpaces(holder: CustomViewHolder, position: Int) {
        when (position) {
            0                      -> switchSpacesVisibility(holder, View.VISIBLE, View.GONE)
            offices?.size ?: 0 - 1 -> switchSpacesVisibility(holder, View.GONE, View.VISIBLE)
            else                   -> switchSpacesVisibility(holder, View.GONE, View.GONE)
        }
    }

    // switchSpacesVisibility() setAvailability()

    @SuppressLint("SetTextI18n")
    private fun insertAvailability(holder: CustomViewHolder, position: Int) {
        when (offices?.get(position)?.availability) {
            true  -> setAvailability(
                    holder,
                    context.getString(R.string.availability),
                    R.drawable.circle_green_medical_w_border
            )
            false -> setAvailability(holder, context.getString(R.string.availability), R.drawable.circle_red_w_border)
            null  -> setAvailability(
                    holder,
                    context.getString(R.string.availability) + context.getString(R.string.ask_on_the_phone),
                    R.drawable.ic_perm_phone_msg_dark_green_24dp
            )
        }
    }

    private fun setAvailability(holder: CustomViewHolder, text: String, drawableId: Int) {
        holder.view.rec_v_office_availability_txt_id.text = text
        holder.view.rec_v_office_availability_img_id.setImageResource(drawableId)
    }

    private fun switchSpacesVisibility(holder: CustomViewHolder, topSpaceVisibility: Int, bottomSpaceVisibility: Int) {
        for (visibility in arrayListOf(topSpaceVisibility, bottomSpaceVisibility))
            if (visibility != View.VISIBLE && visibility != View.GONE && visibility != View.INVISIBLE) return

        holder.view.rec_v_office_top_space_id.visibility = topSpaceVisibility
        holder.view.rec_v_office_bottom_space_id.visibility = bottomSpaceVisibility
    }

    object OrderVisitHelper {
        fun call(context: Context, phoneNumber: String?) {
            if (!phoneNumber.isNullOrBlank()) {
                val intent = Intent(Intent.ACTION_DIAL)

                intent.data = Uri.parse("tel:$phoneNumber")
                context.startActivity(intent)
            }
        }
    }
}
