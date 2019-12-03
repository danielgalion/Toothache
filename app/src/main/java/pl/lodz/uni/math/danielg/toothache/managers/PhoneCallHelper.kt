package pl.lodz.uni.math.danielg.toothache.managers

import android.content.Context
import android.content.Intent
import android.net.Uri

fun call(context: Context, phoneNumber: String?) {
    if (!phoneNumber.isNullOrBlank()) {
        val intent = Intent(Intent.ACTION_DIAL)

        intent.data = Uri.parse("tel:$phoneNumber")
        context.startActivity(intent)
    }
}