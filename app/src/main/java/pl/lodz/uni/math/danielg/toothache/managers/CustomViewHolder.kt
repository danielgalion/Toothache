package pl.lodz.uni.math.danielg.toothache.managers

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CustomViewHolder(val view: View, vararg clickableViews: View)
    : RecyclerView.ViewHolder(view), View.OnClickListener {

    companion object {
        private const val TAG = "CustomViewHolder"
    }

    var itemClickListener: ItemClickListener? = null

    init {
        view.setOnClickListener(this)

        for(index in 0 until clickableViews.size) {
            clickableViews[index].setOnClickListener(this)
        }
    }

    override fun onClick(view: View?) {
        try {
            // Implementation w/o action. TODO: Override it in activities w/ lambda expr.
            itemClickListener?.onItemClick(view)
        } catch (exception: NullPointerException) {
            Log.e(TAG, "Probably itemClickListener, or a view is null. @onClick(..)")
        }
    }
}