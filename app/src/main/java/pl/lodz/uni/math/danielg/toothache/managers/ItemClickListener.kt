package pl.lodz.uni.math.danielg.toothache.managers

import android.view.View

interface ItemClickListener {
    fun onItemClick(view: (View?) -> Unit)
}