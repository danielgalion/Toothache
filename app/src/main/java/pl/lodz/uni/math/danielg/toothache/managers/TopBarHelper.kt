package pl.lodz.uni.math.danielg.toothache.managers

import androidx.appcompat.app.AppCompatActivity

fun setUpTopBar(
    activity: AppCompatActivity,
    title: String,
    shouldShowLeftButton: Boolean = false,
    leftButtonDrawableId: Int? = null
) {
    if (leftButtonDrawableId != null) activity.supportActionBar!!.setHomeAsUpIndicator(
        leftButtonDrawableId
    )
    activity.supportActionBar!!.setDisplayHomeAsUpEnabled(shouldShowLeftButton)
    activity.supportActionBar!!.title = title
}