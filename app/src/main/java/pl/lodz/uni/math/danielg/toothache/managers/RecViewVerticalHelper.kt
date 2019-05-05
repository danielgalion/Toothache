package pl.lodz.uni.math.danielg.toothache.managers

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

object RecViewVerticalHelper {
    fun attach(context: Context, recyclerView: RecyclerView, adapter: RecyclerView.Adapter<CustomViewHolder>) {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }
}