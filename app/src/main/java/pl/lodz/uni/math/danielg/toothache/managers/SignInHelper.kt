package pl.lodz.uni.math.danielg.toothache.managers

import android.content.Context
import android.content.Intent
import pl.lodz.uni.math.danielg.toothache.activities.DentistDashboardActivity

fun onSignedIn(context: Context?) {
    context?.startActivity(Intent(context, DentistDashboardActivity::class.java))
}