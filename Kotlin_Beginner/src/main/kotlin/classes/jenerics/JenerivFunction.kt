package classes.jenerics

fun main() {
    seeJenericFunction("someString")
    seeJenericFunction(123123)

}

fun <T> seeJenericFunction(item: T): T {

    println("i'm a jeneric method with some type $item")

    return item;

}

fun <T> List<T>.filter(predicate: (T) -> Boolean): List<T> {}
fun <T> foo(list: List<T>) {
    for (element in list) {

    }
}

fun <T> appender(seq: T) where T : CharSequence,T : Appendable {
    if (!seq.endsWith(".")) {
        seq.append(".")
    }

fun List<Int>.dublicete(): String {}

@JvmName(name = "dublicateWithChars") // to use functions with the same name --> JenericFunction.dublicateWithChars()
fun List<Char>.dublicete(): String {}

}

//+++++++++++++++ filter and Jenerics++++++++++++++++++++++++++++++
fun filterNonZeroGenerated(list: List<Int>): List<Int> = run {
    list.filter { it != 0 }
}
fun main(args: Array<String>) {
    val list = listOf(1, 2, 3)


    filterNonZeroGenerated(list).toString() eq "[1, 2, 3]"
}

fun <T> Iterable<T>.filter(predicate: (T) -> Boolean): List<T> {
    val destination = ArrayList<T>()
    for (element in this) {
        if (predicate(element)) {
            destination.add(element)
        }
    }
    return destination
}


//+++++++++++++++++   build   ++++++++++++++++++++++++++++++++++++++++
fun <K,V>buildMutableMap(build: HashMap<K,V>.() -> Unit): HashMap<K,V> {
    var map = HashMap<K,V>()
    map.build()
    return map
}

//+++++++++++++++++   build   ++++++++++++++++++++++++++++++++++++++++
fun <T> T.myApply(f: T.() -> Unit): T {
    var t = this //this == T
    t.f()
    return t
}

fun createString(): String {
    return StringBuilder().myApply {
        append("Numbers: ")
        for (i in 1..10) {
            append(i)
        }
    }.toString()
}

fun createMap(): Map<Int, String> {
    return hashMapOf<Int, String>().myApply {
        put(0, "0")
        for (i in 1..10) {
            put(i, "$i")
        }
    }
}


//+++++++++++++++++  predicate   ++++++++++++++++++++++++++++++++++++++++++++++++++++

fun <T, C : MutableCollection<T>> Collection<T>.partitionTo(
    first: C,
    second: C,
    predicate: (T) -> Boolean
): Pair<C, C> {

    for (element in this) {
        if (predicate(element)) {
            first.add(element)
        } else second.add(element)
    }
    return Pair(first, second)

}


fun partitionWordsAndLines() {
    val (words, lines) = listOf("a", "a b", "c", "d e")
        .partitionTo(ArrayList(), ArrayList()) { s -> !s.contains(" ") }
    check(words == listOf("a", "c"))
    check(lines == listOf("a b", "d e"))
}

fun partitionLettersAndOtherSymbols() {
    val (letters, other) = setOf('a', '%', 'r', '}')
        .partitionTo(HashSet(), HashSet())
        { c -> c in 'a'..'z' || c in 'A'..'Z' }
    check(letters == setOf('a', 'r'))
    check(other == setOf('%', '}'))
}
