package pl.lodz.uni.math.danielg.toothache.models

class Office(
        val name: String,
        val doctorsNames: ArrayList<String>,
        val availability: Boolean?,
        val address: String,
        val phoneNumbers: ArrayList<String>,
        val dentalServices: ArrayList<DentalService>,
        val lat: Double,
        val lng: Double
)

class DentalService(
        val name: String,
        val price: Int
)
