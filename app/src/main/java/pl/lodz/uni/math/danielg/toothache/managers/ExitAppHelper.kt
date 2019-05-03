package pl.lodz.uni.math.danielg.toothache.managers

import android.app.Activity
import android.content.Intent
import pl.lodz.uni.math.danielg.toothache.activities.UsingTypeActivity

object ExitAppHelper {
    fun exit(activity: Activity) {
        val intent = Intent(activity, UsingTypeActivity::class.java)

        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
        intent.putExtra(UsingTypeActivity.SHOULD_EXIT, true)
        activity.startActivity(intent)
        activity.finish()
    }
}