package pl.lodz.uni.math.danielg.toothache.managers

import android.content.Context
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun attachVerticalRecView(
    context: Context, recyclerView: RecyclerView, adapter: RecyclerView.Adapter<CustomViewHolder>
) {
    recyclerView.layoutManager = LinearLayoutManager(context)
    recyclerView.adapter = adapter
}

fun attachRecView(
    context: Context?,
    rv: RecyclerView,
    adapter: RecyclerView.Adapter<CustomViewHolder>
) {
    rv.layoutManager = LinearLayoutManager(context)
    rv.adapter = adapter

    val animator = rv.itemAnimator as DefaultItemAnimator

    animator.supportsChangeAnimations = false
    ViewCompat.setNestedScrollingEnabled(rv, false)
}