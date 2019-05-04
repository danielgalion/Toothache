package pl.lodz.uni.math.danielg.toothache.managers

fun ArrayList<String>.toStringWithBrs(): String {
    var stringWithBrs = ""

    for (index in 0 until this.size) {
        stringWithBrs += this[index]
        if (index != this.size - 1) stringWithBrs += "\n"
    }

    return stringWithBrs
}