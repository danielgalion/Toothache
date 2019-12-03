package pl.lodz.uni.math.danielg.toothache.models

import java.io.Serializable

data class Office(
    val name: String,
    val doctorsNames: ArrayList<String>,
    val availability: Boolean?,
    val address: String,
    val voivodeship: String,
    val phoneNumbers: ArrayList<String>,
    val dentalServices: ArrayList<DentalService>
) : Serializable

data class DentalService(
    var name: String,
    var price: Int
) : Serializable
