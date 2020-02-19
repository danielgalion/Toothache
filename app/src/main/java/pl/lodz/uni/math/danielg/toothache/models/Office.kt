package pl.lodz.uni.math.danielg.toothache.models

import android.util.Log
import java.io.Serializable

private const val TAG = "Office"

data class Office(
    val name: String,
    val email: String, // po e-mailu dopasowanie gabinetu do użytkownika
    val doctorsNames: ArrayList<String>,
    val availability: Boolean?,
    val address: String,
    val voivodeship: String, // TODO: po tym filtrowanie gabinetów.
    val phoneNumbers: ArrayList<String>,
    val dentalServices: ArrayList<DentalService>,
    var patientName: String, // Pusty oznacza brak zgłoszenia. // TODO: <- W widoku dentysty
    var patientPhone: String,
    var patientCity: String,
    var patientETA: Long
) : Serializable {
    fun generateMapToSend(): HashMap<String, Serializable?> {
        Log.d(TAG, "office: $this")

        return hashMapOf(
            "name" to name,
            "email" to email,
            "doctorsNames" to doctorsNames,
            "availability" to availability,
            "address" to address,
            "phoneNumbers" to phoneNumbers,
            "dentalServices" to dentalServices,
            "patientName" to patientName,
            "patientPhone" to patientPhone,
            "patientCity" to patientCity,
            "patientETA" to patientETA
        )
    }
}

data class DentalService(
    var name: String,
    var price: Int
) : Serializable
