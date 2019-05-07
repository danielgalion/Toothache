package pl.lodz.uni.math.danielg.toothache.models

import java.io.Serializable

class Office(
        val name: String,
        val doctorsNames: ArrayList<String>,
        val availability: Boolean?,
        val address: String,
        val phoneNumbers: ArrayList<String>,
        val dentalServices: ArrayList<DentalService>,
        val lat: Double,
        val lng: Double
) : Serializable

class DentalService(
        val name: String,
        val price: Int
) : Serializable
