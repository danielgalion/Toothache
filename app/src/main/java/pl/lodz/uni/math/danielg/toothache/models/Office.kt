package pl.lodz.uni.math.danielg.toothache.models

import java.io.Serializable

data class Office(
    val id: Int,
    val name: String,
    val email: String, // TODO: po e-mailu dopasowanie gabinetu do użytkownika
    val doctorsNames: ArrayList<String>,
    val availability: Boolean?,
    val address: String,
    val voivodeship: String, // TODO: po tym filtrowanie gabinetów.
    val phoneNumbers: ArrayList<String>,
    val dentalServices: ArrayList<DentalService>,
    var patientName: String, // Pusty oznacza brak zgłoszenia. // TODO: <- W widoku dentysty
    var patientPhone: String,
    var patientCity: String,
    var patientETA: Int
) : Serializable

data class DentalService(
    var name: String,
    var price: Int
) : Serializable


// TODO: Identyfikacja gabinetu (musi być coś unikatowego)
//  Prosty pomysł: brać 0, gdy to jest 1. element.
//                 brać najwyższy z id + 1, gdy są już elementy na liście
//                 brać brakujący pomiędzy, gdy jest "przerwa" po usunięciu elementu listy.

