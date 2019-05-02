package pl.lodz.uni.math.danielg.toothache.managers

import android.content.Context
import android.content.Context.MODE_PRIVATE

object UsingTypeSharedPreferencesManager {
    private const val PREFS_NAME = "UsingTypeSharedPreferences"
    private const val PREF_USING_TYPE = "usingType"
    const val USING_TYPE_NONE: Int = 0
    const val USING_TYPE_PATIENT: Int = 1
    const val USING_TYPE_DENTIST: Int = 2
    private const val DEF_USING_TYPE = USING_TYPE_NONE


    fun setUsingType(context: Context, usingType: Int) {
        if (usingType != USING_TYPE_NONE && usingType != USING_TYPE_PATIENT && usingType != USING_TYPE_DENTIST) return

        val editor = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit()

        editor.putInt(PREF_USING_TYPE, usingType)
        editor.apply()
    }

    fun getUsingType(context: Context): Int {
        return context
            .getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
            .getInt(PREF_USING_TYPE, DEF_USING_TYPE)
    }
}
