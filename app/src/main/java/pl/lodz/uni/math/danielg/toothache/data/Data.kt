package pl.lodz.uni.math.danielg.toothache.data

import pl.lodz.uni.math.danielg.toothache.models.DentalService
import pl.lodz.uni.math.danielg.toothache.models.Office

object Data {
    val sampleOfficeList: ArrayList<Office> = arrayListOf(
        Office(
            name = "Something Medical",
            address = "ul. Zamorska 38 m. 3, 77-888 Ur Chaldejskie",
            voivodeship = "łódzkie",
            doctorsNames = arrayListOf("Andrzej Rozumny", "Przemek Barszcz"),
            availability = true,
            dentalServices = arrayListOf(
                DentalService("Borowanie", 250),
                DentalService("Wyrywanie", 300)
            ),
            phoneNumbers = arrayListOf("+48 788 139 685, + 48 3333")
        ),
        Office(
            name ="Friendly Dentist",
            address = "ul. Zamorska 38 m. 3, 77-888 Ur Chaldejskie",
            voivodeship = "łódzkie",
            doctorsNames = arrayListOf("Paweł Pawłowski", "Mieczysław Albrecht", "Marian Nieboliząb"),
            availability = false,
            dentalServices = arrayListOf(
                DentalService("Borowanie", 250),
                DentalService("Wyrywanie", 300)
            ),
            phoneNumbers = arrayListOf("+48 788 139 685", "+48 2222")
        ),
        Office(
            name = "Teeth Mechanic",

            address = "ul. Zamorska 38 m. 3, 77-888 Ur Chaldejskie",
            voivodeship = "łódzkie",
            availability = null,
            dentalServices = arrayListOf(
                DentalService("Borowanie", 250),
                DentalService("Wyrywanie", 300)
            ),
            doctorsNames = arrayListOf("Dominik Burakowski"),

            phoneNumbers = arrayListOf("+48 788 139 685", "+48 7777")
        )
    )

    val sampleFullOffice: Office = Office(
        name = "Something Medical",
        address = "ul. Zamorska 38 m. 3, 77-888 Ur Chaldejskie",
        voivodeship = "łódzkie",
        availability = true,
        dentalServices = arrayListOf(
            DentalService("Borowanie", 250),
            DentalService("Wyrywanie", 300)
        ),
        doctorsNames = arrayListOf("Andrzej Rozumny", "Przemek Barszcz"),
        phoneNumbers = arrayListOf("+48 788 139 685", "+48 7777")
    )
}