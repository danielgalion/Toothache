package pl.lodz.uni.math.danielg.toothache.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.lodz.uni.math.danielg.toothache.R
import pl.lodz.uni.math.danielg.toothache.managers.CustomViewHolder
import pl.lodz.uni.math.danielg.toothache.models.DentalService

class OfficesDentalServicesAdapter(private val context: Context, private val dentalServices: ArrayList<DentalService>) :
        RecyclerView.Adapter<CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val row = inflater.inflate(R.layout.rec_v_offices_dental_services_row, parent, false)

        return CustomViewHolder(row)
    }

    override fun getItemCount(): Int {
        return dentalServices.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

    }
}