package pl.lodz.uni.math.danielg.toothache.managers

import android.app.Activity
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import pl.lodz.uni.math.danielg.toothache.R.id.eye_button_id

private const val TAG = "KeyboardHelper"

fun setupKeyboardVisibility(activity: Activity, view: View) {

    if (view !is EditText && view.id != eye_button_id) view.setOnTouchListener { _, _ ->
        hideSoftKeyboard(activity)

        false
    }

    if (view is ViewGroup)
        for (i in 0 until (view).childCount) {
            val innerView = (view).getChildAt(i)
            setupKeyboardVisibility(activity, innerView)
        }
}

private fun hideSoftKeyboard(activity: Activity) {
    val inputMethodManager =
        activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

    if (activity.currentFocus != null) try {
        inputMethodManager.hideSoftInputFromWindow(activity.currentFocus?.windowToken, 0)
    } catch (e: NullPointerException) {
        e.printStackTrace()
        Log.e(TAG, "NPE caused by not safe call of activity.currentFocus @hideSoftKeyboard(..)")
    }
}
