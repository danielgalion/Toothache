package pl.lodz.uni.math.danielg.toothache.adapters

import android.content.Context
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rec_v_text_input_row.view.*
import pl.lodz.uni.math.danielg.toothache.R
import pl.lodz.uni.math.danielg.toothache.managers.CustomViewHolder

class TextInputAdapter(
    private val context: Context,
    private val textInputs: ArrayList<String>,
    private val type: String
) : RecyclerView.Adapter<CustomViewHolder>() {

    companion object {
        const val DOCTOR = "lekarz"
        const val PHONE = "nr tel."
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val row = inflater.inflate(R.layout.rec_v_text_input_row, parent, false)

        return CustomViewHolder(row)
    }

    override fun getItemCount(): Int {
        return textInputs.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        if (type == DOCTOR) holder.view.rec_v_text_input_edit_t_id.inputType =
            InputType.TYPE_TEXT_VARIATION_PERSON_NAME
        else if (type == PHONE)
            holder.view.rec_v_text_input_edit_t_id.inputType = InputType.TYPE_CLASS_PHONE

        if (type == DOCTOR || type == PHONE)
            holder.view.rec_v_text_input_layout_id.hint = "${position + 1}. $type"

        setupPopulatingInputs(holder, position)
    }

    private fun setupPopulatingInputs(holder: CustomViewHolder, position: Int) {
        if (position == itemCount - 1)
            holder.view.rec_v_text_input_edit_t_id.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    textInputs[position] = s.toString()

                    if (s?.length ?: 0 > 0 && position == itemCount - 1) {
                        textInputs.add("")
                        notifyItemInserted(itemCount)
                    }
                }

                override fun beforeTextChanged(`_`: CharSequence?, _1: Int, _2: Int, _3: Int) {
                }

                override fun onTextChanged(`_`: CharSequence?, _1: Int, _2: Int, _3: Int) {
                }
            })
    }

    fun getWrittenInputs(): ArrayList<String> {
        if (textInputs.isNotEmpty()) textInputs.removeAt(textInputs.size - 1)

        return textInputs
    }
}