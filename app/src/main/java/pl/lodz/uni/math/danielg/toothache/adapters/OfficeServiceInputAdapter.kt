package pl.lodz.uni.math.danielg.toothache.adapters

import android.content.Context
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rec_v_office_service_input_row.view.*
import pl.lodz.uni.math.danielg.toothache.R
import pl.lodz.uni.math.danielg.toothache.managers.CustomViewHolder
import pl.lodz.uni.math.danielg.toothache.models.DentalService

class OfficeServiceInputAdapter(
    private val context: Context?,
    val serviceInputs: ArrayList<DentalService>
) : RecyclerView.Adapter<CustomViewHolder>() {

    companion object {
        private const val TAG = "OfficeServiceInputAdapt"
    }

    /**
     * When the array below will be already created, the flag will be set to true.
     * So, it prevents of creating flag array multiple times.
     */
    private var isFlagsArrayCreated = false

    /**
     * What it does (How should it be handled):
     * After onBindVH is called first time on each row it'll be set to true.
     *
     * What's the purpose:
     * This is the flag, that allows for the first 'binding' attach to EditText old contact data to edit.
     * Later it should be disabled, because data in row in EditText could be changed.
     * So it'd be not convenient to attach the old data again.
     */
    private lateinit var isAlreadyAttached: Array<Boolean>

    /**
     * Simultaneously when creating flags array,
     * add the additional empty input if the initial last one element is not an empty string.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val row = inflater.inflate(R.layout.rec_v_office_service_input_row, parent, false)

        if (!isFlagsArrayCreated) {
            if (serviceInputs[serviceInputs.size - 1].name.isNotBlank())
                serviceInputs.add(DentalService("", -1L))

            isAlreadyAttached = Array(serviceInputs.size) { false }
            isFlagsArrayCreated = true
        }

        return CustomViewHolder(row)
    }

    override fun getItemCount(): Int {
        return serviceInputs.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        if (isFlagsArrayCreated) initInput(holder, position)

        holder.view.rec_v_office_services_input_name_edit_t_id.inputType = InputType.TYPE_CLASS_TEXT
        holder.view.rec_v_office_services_input_price_edit_t_id.inputType =
            InputType.TYPE_CLASS_NUMBER

        holder.view.rec_v_office_services_input_name_text_id.hint =
            context?.getString(R.string.no_service, position + 1)

        setupPopulatingInputs(holder, position)
        onPriceChange(holder, position)
    }

    /**
     * Exception is caught not to be logged.
     * Just when the method is useless, inside the 'if' condition,
     * other method wants to check an array value in non existing index.
     */
    private fun initInput(holder: CustomViewHolder, position: Int) {
        try {
            if (!isAlreadyAttached[position] && position != serviceInputs.size - 1) {
                holder.view.rec_v_office_services_input_name_edit_t_id.setText(serviceInputs[position].name)
                holder.view.rec_v_office_services_input_price_edit_t_id.setText(serviceInputs[position].price.toString())
                isAlreadyAttached[position] = true
            }
        } catch (e: ArrayIndexOutOfBoundsException) {
        }
    }

    private fun setupPopulatingInputs(holder: CustomViewHolder, position: Int) {
        if (position == itemCount - 1)
            holder.view.rec_v_office_services_input_name_edit_t_id.addTextChangedListener(object :
                TextWatcher {

                override fun afterTextChanged(s: Editable?) {
                    serviceInputs[position].name = s.toString()

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

    private fun onPriceChange(holder: CustomViewHolder, position: Int) {
        holder.view.rec_v_office_services_input_price_edit_t_id.addTextChangedListener(object :
            TextWatcher {

            override fun afterTextChanged(s: Editable?) {
                serviceInputs[position].price = s.toString().toLongOrNull() ?: -1L
            }

            override fun beforeTextChanged(`_`: CharSequence?, _1: Int, _2: Int, _3: Int) {
            }

            override fun onTextChanged(`_`: CharSequence?, _1: Int, _2: Int, _3: Int) {
            }
        })
    }
}