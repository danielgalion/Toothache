package pl.lodz.uni.math.danielg.toothache.data

import pl.lodz.uni.math.danielg.toothache.models.OfficeShortened

object Data {
    val sampleOfficeList: ArrayList<OfficeShortened> = arrayListOf(
            OfficeShortened("Something Medical", arrayListOf("Andrzej Rozumny", "Przemek Barszcz"), true, "+48 788 139 685"),
            OfficeShortened("Friendly Dentist", arrayListOf("Paweł Pawłowski", "Mieczysław Albrecht", "Marian Nieboliząb"), false, "+48 788 139 685"),
            OfficeShortened("Teeth Mechanic", arrayListOf("Dominik Burakowski"), null, "+48 788 139 685")
    )
}