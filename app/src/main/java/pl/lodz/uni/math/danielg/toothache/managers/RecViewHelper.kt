package pl.lodz.uni.math.danielg.toothache.managers

import android.content.Context
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pl.lodz.uni.math.danielg.toothache.models.DentalService

fun attachVerticalRecView(
    context: Context, recyclerView: RecyclerView, adapter: RecyclerView.Adapter<CustomViewHolder>
) {
    recyclerView.layoutManager = LinearLayoutManager(context)
    recyclerView.adapter = adapter
}

fun attachRecView(
    context: Context?,
    rv: RecyclerView?,
    adapter: RecyclerView.Adapter<CustomViewHolder>
) {
    rv?.layoutManager = LinearLayoutManager(context)
    rv?.adapter = adapter

    val animator = rv?.itemAnimator as DefaultItemAnimator

    animator.supportsChangeAnimations = false
    ViewCompat.setNestedScrollingEnabled(rv, false)
}

/**
 * Input RecViews have one additional empty element that is for enter the next value.
 * Returns array of elements w/ a given type w/o the last element.
 */
fun ArrayList<String>.getWrittenInputs(): ArrayList<String> {
    if (this.isNotEmpty()) this.removeAt(this.size - 1)

    for (i in 0 until this.size) if (this[i].isBlank()) this.removeAt(i)

    return this
}

fun ArrayList<DentalService>.getWrittenServices(): ArrayList<DentalService> {
    if (this.isNotEmpty()) this.removeAt(this.size - 1)

    for (i in 0 until this.size) if (this[i].name.isBlank()) this.removeAt(i)

    return this
}