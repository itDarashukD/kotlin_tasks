package nicestring

fun String.isNice(): Boolean {

    var containBuBaBe = !isContainBuBaBe(this)
    var containsMin3wolves = isContainsMin3wolves(this)
    var containsDoubleLetter = isContainsDoubleLetter(this)

    return listOf(containBuBaBe, containsMin3wolves, containsDoubleLetter).count { it == true } >= 2
}
fun isContainBuBaBe(string: String): Boolean {
    val bu: String = "bu"
    val ba: String = "ba"
    val be: String = "be"

//    setOf(bu,ba,be).none { string.contains(it) }

    return string.contains(bu) || string.contains(ba) || string.contains(be)
}

fun isContainsMin3wolves(string: String): Boolean {
    val wolves: List<Char> = listOf('a', 'e', 'i', 'o', 'u')

    return string.filter { char -> wolves.contains(char) }.toList().size >= 3
}

fun isContainsDoubleLetter(string: String): Boolean {
    for (i in 0 until string.length - 1) {
        if (string[i] == string[i + 1])
            return true
    }
    return false
}



