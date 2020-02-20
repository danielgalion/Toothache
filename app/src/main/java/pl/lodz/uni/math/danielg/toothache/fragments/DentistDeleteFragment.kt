package pl.lodz.uni.math.danielg.toothache.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_dentist_delete.*
import me.drakeet.support.toast.ToastCompat
import pl.lodz.uni.math.danielg.toothache.R
import pl.lodz.uni.math.danielg.toothache.activities.DentistSignInActivity
import pl.lodz.uni.math.danielg.toothache.managers.UsingTypeSharedPreferencesManager

class DentistDeleteFragment : Fragment() {
    companion object {
        private const val TAG = "DentistDeleteFragment"
    }

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_dentist_delete, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        setUpButtons()
    }

    private fun setUpButtons() {
        dentist_delete_yes_btn_id.setOnClickListener {
            auth.currentUser?.delete()?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "User account deleted.")
                    context ?: return@addOnCompleteListener

                    val intent = Intent(context, DentistSignInActivity::class.java)

                    UsingTypeSharedPreferencesManager.setUsingType(
                        context!!,
                        UsingTypeSharedPreferencesManager.USING_TYPE_NONE
                    )
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(intent)
                }
            }?.addOnFailureListener {
                ToastCompat.makeText(
                    context,
                    "Błąd usuwania użytkownika\nSpróbuj ponownie",
                    ToastCompat.LENGTH_LONG
                )
                it.printStackTrace()
                Log.e(TAG, "User deletion failed.")
            }
        }
    }
}