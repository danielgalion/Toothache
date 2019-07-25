package pl.lodz.uni.math.danielg.toothache.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rec_v_offices_dental_services_row.view.*
import pl.lodz.uni.math.danielg.toothache.R
import pl.lodz.uni.math.danielg.toothache.managers.CustomViewHolder
import pl.lodz.uni.math.danielg.toothache.models.DentalService

class OfficesDentalServicesAdapter(
    private val context: Context,
    private val dentalServices: ArrayList<DentalService>?
) :
    RecyclerView.Adapter<CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val row = inflater.inflate(R.layout.rec_v_offices_dental_services_row, parent, false)

        return CustomViewHolder(row)
    }

    override fun getItemCount(): Int {
        return dentalServices?.size ?: 0
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.view.rec_v_office_dental_services_name_txt_v_id.text = dentalServices?.get(position)?.name
        holder.view.rec_v_office_dental_services_price_txt_v_id.text =
            context.getString(R.string.no_of_zl, dentalServices?.get(position)?.price)
    }
}