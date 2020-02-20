package pl.lodz.uni.math.danielg.toothache.data

import pl.lodz.uni.math.danielg.toothache.models.DentalService
import pl.lodz.uni.math.danielg.toothache.models.Office

//3.1	województwo dolnośląskie
//3.2	województwo kujawsko-pomorskie
//3.3	województwo lubelskie
//3.4	województwo lubuskie
//3.5	województwo łódzkie
//3.6	województwo małopolskie
//3.7	województwo mazowieckie
//3.8	województwo opolskie
//3.9	województwo podkarpackie
//3.10	województwo podlaskie
//3.11	województwo pomorskie
//3.12	województwo śląskie
//3.13	województwo świętokrzyskie
//3.14	województwo warmińsko-mazurskie
//3.15	województwo wielkopolskie
//3.16	województwo zachodniopomorskie

val sampleOfficeList: ArrayList<Office> = arrayListOf(
    Office(
        name = "Something Medical",
        email = "andrzej.golota@bokser.pl",
        address = "ul. Zamorska 38 m. 3, 77-888 Ur Chaldejskie",
        voivodeship = "łódzkie",
        doctorsNames = arrayListOf("Andrzej Rozumny", "Przemek Barszcz"),
        availability = true,
        dentalServices = arrayListOf(
            DentalService("Borowanie", 250),
            DentalService("Wyrywanie", 300)
        ),
        phoneNumbers = arrayListOf("+48 788 139 685, + 48 3333"),
        patientName = "Gerwazy Piastowski",
        patientCity = "Zgierz",
        patientPhone = "+48 111 222 333",
        patientETA = 25
    ),
    Office(
        name = "",
        email = "anzelm@dentysta.pl",
        address = "ul. Zamorska 38 m. 3, 77-888 Ur Chaldejskie",
        voivodeship = "łódzkie",
        doctorsNames = arrayListOf(
            "Paweł Pawłowski",
            "Mieczysław Albrecht",
            "Marian Nieboliząb"
        ),
        availability = false,
        dentalServices = arrayListOf(
            DentalService("Borowanie", 250),
            DentalService("Wyrywanie", 300)
        ),
        phoneNumbers = arrayListOf("+48 788 139 685", "+48 2222"),
        patientName = "",
        patientCity = "",
        patientPhone = "",
        patientETA = 0
    ),
    Office(
        name = "Teeth Mechanic",
        email = "abraham@udarowa.pl",
        address = "ul. Zamorska 38 m. 3, 77-888 Ur Chaldejskie",
        voivodeship = "łódzkie",
        availability = null,
        dentalServices = arrayListOf(
            DentalService("Borowanie", 250),
            DentalService("Wyrywanie", 300)
        ),
        doctorsNames = arrayListOf("Dominik Burakowski"),
        phoneNumbers = arrayListOf("+48 788 139 685", "+48 7777"),
        patientName = "",
        patientCity = "",
        patientPhone = "",
        patientETA = 0
    ),
    Office(
        name = "Something Medical",
        email = "andrzej.golota@bokser.pl",
        address = "ul. Zamorska 38 m. 3, 77-888 Ur Chaldejskie",
        voivodeship = "łódzkie",
        doctorsNames = arrayListOf("Andrzej Rozumny", "Przemek Barszcz"),
        availability = true,
        dentalServices = arrayListOf(
            DentalService("Borowanie", 250),
            DentalService("Wyrywanie", 300)
        ),
        phoneNumbers = arrayListOf("+48 788 139 685, + 48 3333"),
        patientName = "Gerwazy Piastowski",
        patientCity = "Zgierz",
        patientPhone = "+48 111 222 333",
        patientETA = 25
    ),
    Office(
        name = "",
        email = "anzelm@dentysta.pl",
        address = "ul. Zamorska 38 m. 3, 77-888 Ur Chaldejskie",
        voivodeship = "łódzkie",
        doctorsNames = arrayListOf(
            "Paweł Pawłowski",
            "Mieczysław Albrecht",
            "Marian Nieboliząb"
        ),
        availability = false,
        dentalServices = arrayListOf(
            DentalService("Borowanie", 250),
            DentalService("Wyrywanie", 300)
        ),
        phoneNumbers = arrayListOf("+48 788 139 685", "+48 2222"),
        patientName = "",
        patientCity = "",
        patientPhone = "",
        patientETA = 0
    ),
    Office(
        name = "Teeth Mechanic",
        email = "abraham@udarowa.pl",
        address = "ul. Zamorska 38 m. 3, 77-888 Ur Chaldejskie",
        voivodeship = "łódzkie",
        availability = null,
        dentalServices = arrayListOf(
            DentalService("Borowanie", 250),
            DentalService("Wyrywanie", 300)
        ),
        doctorsNames = arrayListOf("Dominik Burakowski"),
        phoneNumbers = arrayListOf("+48 788 139 685", "+48 7777"),
        patientName = "",
        patientCity = "",
        patientPhone = "",
        patientETA = 0
    )
)

val sampleOffice: Office = Office(
    name = "",
    email = "",
    address = "",
    voivodeship = "",
    availability = null,
    dentalServices = arrayListOf(),
    doctorsNames = arrayListOf(),
    phoneNumbers = arrayListOf(),
    patientName = "",
    patientCity = "",
    patientPhone = "",
    patientETA = 0
)
