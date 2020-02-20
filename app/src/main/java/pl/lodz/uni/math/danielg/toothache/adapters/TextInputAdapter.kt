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
    private val context: Context?,
    val textInputs: ArrayList<String>,
    private val type: String
) : RecyclerView.Adapter<CustomViewHolder>() {

    companion object {
        const val DOCTOR = "lekarz"
        const val PHONE = "nr tel."
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val row = inflater.inflate(R.layout.rec_v_text_input_row, parent, false)

        if (!isFlagsArrayCreated) {
            if (textInputs[textInputs.size - 1].isNotBlank()) textInputs.add("")

            isAlreadyAttached = Array(textInputs.size) { false }
            isFlagsArrayCreated = true
        }

        return CustomViewHolder(row)
    }

    override fun getItemCount(): Int {
        return textInputs.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        if (isFlagsArrayCreated) initInput(holder, position)

        if (type == DOCTOR) holder.view.rec_v_text_input_edit_t_id.inputType =
            InputType.TYPE_TEXT_VARIATION_PERSON_NAME
        else if (type == PHONE)
            holder.view.rec_v_text_input_edit_t_id.inputType = InputType.TYPE_CLASS_PHONE

        if (type == DOCTOR || type == PHONE)
            holder.view.rec_v_text_input_layout_id.hint = "${position + 1}. $type"

        setupPopulatingInputs(holder, position)
    }

    /**
     * Exception is caught not to be logged.
     * Just when the method is useless, inside the 'if' condition,
     * other method wants to check an array value in non existing index.
     */
    private fun initInput(holder: CustomViewHolder, position: Int) {
        try {
            if (!isAlreadyAttached[position] && position != textInputs.size - 1) {
                holder.view.rec_v_text_input_edit_t_id.setText(textInputs[position])
                isAlreadyAttached[position] = true
            }
        } catch (e: ArrayIndexOutOfBoundsException) {
        }
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
}