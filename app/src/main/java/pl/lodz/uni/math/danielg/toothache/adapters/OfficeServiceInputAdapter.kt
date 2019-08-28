package pl.lodz.uni.math.danielg.toothache.adapters

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rec_v_office_service_input_row.view.*
import pl.lodz.uni.math.danielg.toothache.R
import pl.lodz.uni.math.danielg.toothache.managers.CustomViewHolder
import pl.lodz.uni.math.danielg.toothache.models.DentalService

class OfficeServiceInputAdapter(
    private val context: Context,
    private val serviceInputs: ArrayList<DentalService>
) : RecyclerView.Adapter<CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val row = inflater.inflate(R.layout.rec_v_office_service_input_row, parent, false)

        return CustomViewHolder(row)
    }

    override fun getItemCount(): Int {
        return serviceInputs.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.view.rec_v_office_services_input_name_text_id.hint =
            context.getString(R.string.no_service, position + 1)

        setupPopulatingInputs(holder, position)
    }

    private fun setupPopulatingInputs(holder: CustomViewHolder, position: Int) {
        if (position == itemCount - 1)
            holder.view.rec_v_office_services_input_name_edit_t_id.addTextChangedListener(object :
                TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    if (s?.length ?: 0 > 0 && position == itemCount - 1) {
                        serviceInputs.add(DentalService("", -1))
                        notifyItemInserted(itemCount)
                    }
                }

                override fun beforeTextChanged(`_`: CharSequence?, _1: Int, _2: Int, _3: Int) {
                }

                override fun onTextChanged(`_`: CharSequence?, _1: Int, _2: Int, _3: Int) {
                }
            })
    }

}