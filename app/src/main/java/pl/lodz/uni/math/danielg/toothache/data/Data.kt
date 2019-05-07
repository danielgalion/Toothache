package pl.lodz.uni.math.danielg.toothache.data

import pl.lodz.uni.math.danielg.toothache.models.DentalService
import pl.lodz.uni.math.danielg.toothache.models.Office
import pl.lodz.uni.math.danielg.toothache.models.OfficeShortened

object Data {
    val sampleOfficeList: ArrayList<OfficeShortened> = arrayListOf(
            OfficeShortened("Something Medical", arrayListOf("Andrzej Rozumny", "Przemek Barszcz"), true, "+48 788 139 685"),
            OfficeShortened("Friendly Dentist", arrayListOf("Paweł Pawłowski", "Mieczysław Albrecht", "Marian Nieboliząb"), false, "+48 788 139 685"),
            OfficeShortened("Teeth Mechanic", arrayListOf("Dominik Burakowski"), null, "+48 788 139 685")
    )

    val sampleFullOffice: Office = Office(
            name = "Something Medical",
            address = "ul. Zamorska 38 m. 3, 77-888 Ur Chaldejskie",
            availability = true,
            dentalServices = arrayListOf(DentalService("Borowanie", 250), DentalService("Wyrywanie", 300)),
            doctorsNames = arrayListOf("Andrzej Rozumny", "Przemek Barszcz"),
            lat = 30.9625,
            lng = 46.103056,
            phoneNumbers = arrayListOf("+48 788 139 685", "+48 7777")
    )
}