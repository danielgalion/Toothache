package pl.lodz.uni.math.danielg.toothache.managers

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import me.drakeet.support.toast.ToastCompat
import pl.lodz.uni.math.danielg.toothache.R

private const val TAG = "AlertDialogHelper"

fun showAdequateAlert(activity: Activity?, message: String) {

    try {
        if (android.os.Build.VERSION.SDK_INT == 25 || android.os.Build.VERSION.SDK_INT == 28)
            ToastCompat.makeText(activity, message, Toast.LENGTH_LONG).setBadTokenListener {
                Log.e(TAG, "Failed Toast: $message @showAdequateAlert(..)")
            }.show()
        else showAlert(activity, message)
    } catch (e: WindowManager.BadTokenException) {
        e.printStackTrace()
        Log.e(TAG, "Failed Alert or Toast: $message @showAdequateAlert(..)")
    } catch (e: Exception) {
        e.printStackTrace()
        Log.e(TAG, "Failed Alert or Toast: $message @showAdequateAlert(..)")
    }
}

private fun showAlert(activity: Activity?, message: String) {
    activity ?: return

    val alertDialog = AlertDialog.Builder(activity).create()
    val buttonsColor = ContextCompat.getColor(activity, R.color.blue_medical_selector)

    alertDialog.setMessage(message)
    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK") { dialog, _ ->
        run { dialog.dismiss() }
    }

    alertDialog.show()
    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(buttonsColor)
}

fun openVoivodeshipDialog(context: Context?, voivodeshipTv: TextView) {
    context ?: return

    val voivodeships = context.resources.getStringArray(R.array.voivodeships);

    val builder: AlertDialog.Builder = AlertDialog.Builder(context)
    builder.setTitle("Wybierz województwo")
    builder.setSingleChoiceItems(voivodeships, -1) { dialogInterface, i ->
        voivodeshipTv.text = voivodeships[i]
        dialogInterface.dismiss()
    }

    val dialog: AlertDialog = builder.create()
    dialog.show()
}