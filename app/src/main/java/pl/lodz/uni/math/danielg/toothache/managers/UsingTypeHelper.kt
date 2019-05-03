package pl.lodz.uni.math.danielg.toothache.managers

import android.app.Activity
import android.content.Intent
import pl.lodz.uni.math.danielg.toothache.activities.UsingTypeActivity

object UsingTypeHelper {
    fun intentToChoiceActivity(activity: Activity) {
        val intent = Intent(activity, UsingTypeActivity::class.java)

        UsingTypeSharedPreferencesManager.setUsingType(activity, UsingTypeSharedPreferencesManager.USING_TYPE_NONE)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        activity.startActivity(intent)
    }
}