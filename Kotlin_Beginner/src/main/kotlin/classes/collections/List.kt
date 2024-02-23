package classes.collections

val list: List<String> = listOf("apple", "orange", "lime", "apple") // by default List is unmutable!
var mutableList = mutableListOf("apple", "orange", "lime", "apple") // List is mutable

fun main() {

    var s = list[0]
    var indexOf = list.indexOf("apple")
    var lastIndexOf = list.lastIndexOf("apple") //return index of last "apple"

    mutableList.add("penuts")
    println(mutableList)

    mutableList.removeAll(listOf("apple", "lime")) //remove few elements
    println(mutableList)

//    mutableList.clear() //remove all elements
    mutableList.set(0, "melon")  //rewrite position 0
    mutableList.add(
        0,
        "watermelon"
    )  //add watermelon to position 0, else elements will move to the right

    mutableList.addAll(list) //add another collection
    println(mutableList)


//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}
    enum class Gender {MALE, FEMALE}

    data class Hero(val name: String,var age: Int,var gender : Gender?)

fun main() {

    val heroes = listOf(
        Hero("The Captain", 60, Gender.MALE),
        Hero("Frenchy", 42, Gender.MALE),
        Hero("The Kid", 9, null),
        Hero("Lady Lauren", 29, Gender.FEMALE),
        Hero("First Mate", 29, Gender.MALE),
        Hero("Sir Stephen", 37, Gender.MALE))

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

//++++++++++++++++++  filter + create muatable +++++++++++++++++++++++++++
    val tmp = heroes.filterNotNull().toMutableList()

//++++++++++++++++++  shuffle + create muatable +++++++++++++++++++++++++++
    val list = (1..15).shuffled().toMutableList()

//++++++++++++++++  lambda as parameter  +++++++++++++++++++++++++

    fun <T : Any> List<T?>.moveAndMergeEqual(merge: (T) -> T): List<T> {   //(merge: (T) -> T)  == it * 2
        val tmp = this.filterNotNull().toMutableList()
        var i = 1
        while (i < tmp.size) {
            if (tmp[i - 1] == tmp[i]) {
                tmp[i - 1] = merge(tmp[i - 1]) //предыдущий элемент умноэаем на 2
                tmp.removeAt(i)
            }
            i++
        }
        return tmp
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
//++++++++++++++  sorted    +++++++++++++++++++++++++++++++++++++++++++

    people.sortedBy { it.age }.reversed()
    ( people.soredByDescending { it.age} )

//++++++++++++++  sorted    +++++++++++++++++++++++++++++++++++++++++++

    val strings = listOf("bbb", "a", "cc")
    strings.sorted() ==
            listOf("a", "bbb", "cc")

    strings.sortedBy { it.length } ==
            listOf("a", "cc", "bbb")

    strings.sortedDescending() ==
            listOf("cc", "bbb", "a")

    strings.sortedByDescending { it.length } ==
            listOf("bbb", "cc", "a")
    //++++++++++++++  all ,any, count, firstOrNull    +++++++++++++++++++++++++++++++++++++++++++

    // Return true if all customers are from a given city
    fun Shop.checkAllCustomersAreFrom(city: City): Boolean =
        this.customers.all { it.city ==city }

    // Return true if there is at least one customer from a given city
    fun Shop.hasCustomerFrom(city: City): Boolean =
        this.customers.any { it.city ==city }

    // Return the number of customers from a given city
    fun Shop.countCustomersFrom(city: City): Int =
        this.customers.count { it.city == city }

    // Return a customer who lives in a given city, or null if there is none
    fun Shop.findCustomerFrom(city: City): Customer? =
        this.customers.firstOrNull{it.city == city}


    //++++++++++++++  count ,maxOrNull, minOrNull, average  sum  +++++++++++++++++++++++++++++++++++++++++++
    fun main() {
        val numbers = listOf(6, 42, 10, 4)

        println("Count: ${numbers.count()}") // Count: 4
        println("Max: ${numbers.maxOrNull()}") // Max: 42
        println("Min: ${numbers.minOrNull()}") // Min: 4
        println("Average: ${numbers.average()}") // Average: 15.5
        println("Sum: ${numbers.sum()}") // Sum: 62
    }

    //++++++++++++++  loop index  +++++++++++++++++++++++++++++++++++++++++++
    (digits.mapIndexed { i, d -> d * (10 - i)}.sum() + control) % 11 == 0
}