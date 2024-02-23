import tasks.X
import tasks.Y
import tasks.Z
import java.lang.Thread.yield
import java.util.*
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun main() {
    var a: Int? = null
    var c: Int = 1
    val s1 = (a ?: 55) + c  //if a == null, then (55) + 1, if not (a) +1

//____________________________________________________________________________
    fun performOperation(operation: (Int, Int) -> Int) { } //lambda as argument

//____________________________________________________________________________

    inline fun calculateSum(a: Int, b: Int): Int { //increase performance optimization in Kotlin, frequently called functions or lambda expressions.
        return a + b
    }
//____________________________________________________________________________
    class Person(val name: String, var age: Int)

    val people = listOf(Person("Alice", 25), Person("Bob", 30), Person("Charlie", 22))

    val sumOfAges = people.sumBy { it.age }

//____________________________________________________________________________
    val secret = "ABCD"
    val guess = "ANKF"
    var zip: List<Pair<Char, Char>> = secret.zip(guess)
    println(zip) //[(A, A), (B, N), (C, K), (D, F)]
//____________________________________________________________________________


    val createArray = createArray()
    val longestName: String = getLongestName(createArray);

    println(longestName)

    var type: String = getTypeOfMen(createArray)

}

fun createArray(): List<String> {
    val array: List<String> = listOf("dzmitry", "vika", "ulad", "nastia")
    return array


}

fun getLongestName(array: List<String>): String {
    var long: Int = 0
    for (el in array) {
        val length = el.length
        if (length > long) {
            long = length
        }
    }

    for (el in array) {
        if (el.length == long) {
            return el
        }
    }
    return ""
}

fun getTypeOfMen(array: List<String>): String {
    for (man in array)
        return when (man) {
            "dzmitry" -> "man"
            "vika" -> "woman"
            "ulad" -> "young boy"
            "nastia" -> "young girl"

            else -> "there no any man i list"
        }

//    when (man) {
//        "dzmitry" -> return "man"
//        "vika" -> return "woman"
//        "ulad" -> return "young boy"
//        "nastia" -> return "young girl"
//
//        else -> return "there no any man i list"
//    }

    return ""
}
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

enum class Gender { MALE, FEMALE }

data class Hero(val name: String, var age: Int, var gender: Gender?)

fun main() {

    val heroes = listOf(
        Hero("The Captain", 60, Gender.MALE),
        Hero("Frenchy", 42, Gender.MALE),
        Hero("The Kid", 9, null),
        Hero("Lady Lauren", 29, Gender.FEMALE),
        Hero("First Mate", 29, Gender.MALE),
        Hero("Sir Stephen", 37, Gender.MALE)
    )

    heroes.last().name //"Sir Stephen"
    heroes.first().name //"The Captain"
    heroes.firstOrNull()?.name //"The Captain"
    heroes.lastOrNull()?.name //"Sir Stephen"

    heroes.firstOrNull { it.age == 30 }?.name //null, since np any 30 age in the first element
    heroes.map { it.age }.distinct().size    // 5  .it - in this case is ine hero
    heroes.filter { it.age < 30 }.size //3

    val (youngest, oldest) = heroes.partition { it.age < 30 } //devide to 2 list by condition
    oldest.size
    youngest.size

    heroes.maxBy { it.age }?.name //"The Captain"  max by ages
    heroes.all { it.age < 50 }  //false   check if ALL younger then 50
    heroes.any { it.gender == Gender.FEMALE }   //true   check if present any FEMALE

    val mapByAge: Map<Int, List<Hero>> = heroes.groupBy { it.age }
    val (age, group) = mapByAge.maxBy { (_, group) -> group.size }!! //since 2 herous with 29 - it is max size group, with age 29
    println(age) //29

    val mapByName: Map<String, Hero> = heroes.associateBy { it.name }
    mapByName["Frenchy"]?.age  // "Frenchy" is not null return his age

    mapByName.getOrElse("unknown") { 0 } // if not present return 0

}
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
val secret = "ABCD"
val guess = "ANKF"
var zip :  List<Pair<Char, Char>> = secret.zip(guess)
println(zip) //[(A, A), (B, N), (C, K), (D, F)]
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

private var map = mutableMapOf<Cell, T?>()
//заполняет мапу null's
 var cells = listOf(1,2,3,4)
cells.forEach { row -> row.forEach { cell -> map[cell] = null } }

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
fun List<Int>.allNonZero() = all {
    this.count { integer -> integer != 0 } == this.size
}

fun List<Int>.allNonZero1()  = none {
    this.count { integer -> integer != 0 } == this.size
}

fun List<Int>.allNonZero2() = !any {it != 0}


fun List<Int>.containsZero() = any {
//    it==0}
    this.stream().allMatch { it == 0 }
}

fun List<Int>.containsZero1() = all {
    !this.map { it -> return it == 0
    }.toList().contains(false)
}

fun List<Int>.containsZero2() = none {
    this.map { it != 0 }.toList().isEmpty()
}

fun main(args: Array<String>) {
    val list1 = listOf(1, 2, 3)
    list1.allNonZero() eq true
    list1.allNonZero1() eq true
    list1.allNonZero2() eq true

    list1.containsZero() eq false
    list1.containsZero1() eq false
    list1.containsZero2() eq false

    val list2 = listOf(0, 1, 2)
    list2.allNonZero() eq false
    list2.allNonZero1() eq false
    list2.allNonZero2() eq false

    list2.containsZero() eq true
    list2.containsZero1() eq true
    list2.containsZero2() eq true
}
/*
* Вместо обычного вызова sum(10, 20) вы используете инфиксный синтаксис 10 sum 20.

Ключевое слово infix обязательно должно предшествовать объявлению функции. Обратите внимание,
*  что инфиксными могут быть только функции с одним параметром.
*
* Инфиксные функции - это такие функции, которые могут быть вызваны
* с использованием инфиксного синтаксиса,
* то есть без использования точечной нотации или круглых скобок для вызова функции.*/

infix fun <T> T.eq(other: T) {
    if (this == other) println("OK")
    else println("Error: $this != $other")
}

//+++++++++++++++++  extention   ++++++++++++++++++++++++++++++++++++++++++++++++++++
fun String.isNice(): Boolean {

    var containBuBaBe = !isContainBuBaBe(this)
    var containsMin3wolves = isContainsMin3wolves(this)
    var containsDoubleLetter = isContainsDoubleLetter(this)

    return listOf(containBuBaBe, containsMin3wolves, containsDoubleLetter).count { it == true } >= 2
}

fun task(): List<Boolean> {

    val isEven: Int.() -> Boolean = { this % 2 ==0 }
    val isOdd: Int.() -> Boolean = { this % 2 !=0}

    return listOf(42.isOdd(), 239.isOdd(), 294823098.isEven())
}

//+++++++++++++++++  extention   ++++++++++++++++++++++++++++++++++++++++++++++++++++

fun isContainBuBaBe(string: String): Boolean {
    val bu: String = "bu"
    val ba: String = "ba"
    val be: String = "be"

    setOf(bu,ba,be).none { string.contains(it) }

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
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

val number = 42
number.takeIf { it > 10 } // returns 42, -  get it only if { ... }
number.takeUnless { it > 10 } // returns null, -  get it only if { ... }

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
val name: String? = "John"

val length = name?.let {
    println("Name is: $it")
    it.length
}

//    getEmail()?.let {sendEmail(it)} // only if getEmail() returns not null value
//We use let to execute a block of code only if name is not null.
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

val str : String = " test"
print( str.repeat(10))  //print 10 times

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

val secret = "ABCD"
val guess = "ANKF"
var zip :  List<Pair<Char, Char>> = secret.zip(guess)
println(zip) //[(A, A), (B, N), (C, K), (D, F)]
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
/*run { ... } в Kotlin является просто одним из способов организации кода,
 и в данном случае оно не обязательно. Эта конструкция полезна,
 когда нужно выполнить набор операций внутри блока кода и возвратить
  результат этого выполнения.*/

operator fun plus(other: Rational): Rational = run {
    val newNumerator = numerator * other.denominator + denominator * other.numerator
    val newDenominator = denominator * other.denominator
    return Rational(newNumerator, newDenominator)
}

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

/*возвращает коллекцию ячеек, удовлетворяющих заданному предикату.*/
override fun filter(predicate: (T?) -> Boolean): Collection<Cell> {
    return map.filter { (_, value) -> predicate(value) }.keys
}

/*возвращает первую ячейку, удовлетворяющую заданному предикату.*/
override fun find(predicate: (T?) -> Boolean): Cell? {
    return map.filter { (_, value) -> predicate(value) }.keys.firstOrNull()
}

/*возвращает true, если хотя бы одна ячейка удовлетворяет предикату.*/
override fun any(predicate: (T?) -> Boolean): Boolean {
    return map.filter { (_, value) -> predicate(value) }.isNotEmpty()
}

/* возвращает true, если все ячейки удовлетворяют предикату.*/
override fun all(predicate: (T?) -> Boolean): Boolean {
    return map.filter { (_, value) -> !predicate(value) }.isEmpty()
}
//++++++++++++++  Sequence  similar to streams but with lazy +++++++++++++++++++++++++++++++++++++++++++
val numbers = generateSequence(3)

//++++++++++++++  yield    +++++++++++++++++++++++++++++++++++++++++++

fun mySequence() = buildSequence {
    println("yield one element")
    yield(1)
    println("yield a range")
    yieldAll(3..5)
    println("yield a list")
    yieldAll(listOf(7, 9))
}

val numbers = listOf("one", "two", "three", "four")
val numbersSequence = numbers.asSequence()


//++++++++++++++  with` просто заменяет то что в () , чтобы не повторять внутри функции   +++++++++++++++++++++++++++++++++++++++++++

val sb = StringBuilder()
with (sb) {
    sb.appendln("Alphabet: ")
    for (c in 'a'..'z') {
        sb.append(c)
    }
}

//--->  ==

val sb = StringBuilder()
with (sb) {
     appendln("Alphabet: ")
    for (c in 'a'..'z') {
        sb.append(c)
    }
}

//or -->

val sb = StringBuilder()
with (sb) {
    this.appendln("Alphabet: ")
    for (c in 'a'..'z') {
        this.append(c)
    }
}

//+++++++++++++++++   with , run, let, apply   ++++++++++++++++++++++++++++++++++++++++

fun foo(x: X, y: Y?, z: Z) {

    with(x) {
        first = 1
        second = 2
        third = 3
    }

    y?.run{
        start()
        finish()
    }


    val result = z.apply {
        init()
    }
}
//+++++++++++++++++   joinToString   ++++++++++++++++++++++++++++++++++++++++

val numbers = listOf(1, 2, 3, 4, 5, 6)
println(numbers.joinToString()) // 1, 2, 3, 4, 5, 6
println(numbers.joinToString(prefix = "[", postfix = "]")) // [1, 2, 3, 4, 5, 6]
println(numbers.joinToString(prefix = "<", postfix = ">", separator = "•")) // <1•2•3•4•5•6>

//+++++++++++++++++   setter  property getter  ++++++++++++++++++++++++++++++++++++++++


class PropertyExample() {
    var counter = 0
    var propertyWithCounter: Int? = null
        set(someValueToSet) {
            field = someValueToSet
            counter++
        }}

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int)

fun MyDate.toMillis(): Long {
    val c = Calendar.getInstance()
    c.set(year, month, dayOfMonth)
    return c.getTimeInMillis()
}

fun Long.toDate(): MyDate {
    val c = Calendar.getInstance()
    c.setTimeInMillis(this)
    return MyDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE))
}

    class D {
        var date: MyDate by EffectiveDate()
    }

    class EffectiveDate<R> : ReadWriteProperty<R, MyDate> {

        var timeInMillis: Long? = null


        override fun getValue(thisRef: R, property: KProperty<*>): MyDate {
            return timeInMillis!!.toDate()
        }

        override fun setValue(thisRef: R, property: KProperty<*>, value: MyDate) {
            value.toMillis()
        }
    }



}

//+++++++++++++++++   lazy init
// ++++++++++++++++++++++++++++++++++++++++
class LazyProperty(val initializer: () -> Int) {
    val lazyValue: Int by lazy(initializer)
}

//+++++++++++++++++  predicate   ++++++++++++++++++++++++++++++++++++++++++++++++++++
fun task33(): List<Boolean> {

    val isEven: Int.() -> Boolean = { this % 2 ==0 }
    val isOdd: Int.() -> Boolean = { this % 2 !=0}

    return listOf(42.isOdd(), 239.isOdd(), 294823098.isEven())
}

//+++++++++++++++++  Apply   ++++++++++++++++++++++++++++++++++++++++++++++++++++
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

//+++++++++++++++++  lambda as argument   ++++++++++++++++++++++++++++++++++++++++++++++++++++

fun html(init: HTML.() -> Unit): HTML {
    val html = HTML()
    html.init()
    return html
}


//+++++++++++++++++  lambda as argument   +++++++++++++++++++++++++++++++++++++++++++++