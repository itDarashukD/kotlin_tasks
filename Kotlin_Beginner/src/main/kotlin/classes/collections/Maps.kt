package classes.collections

import java.sql.Driver

fun main() {

    var immutableFirstMap = mapOf<String, String>(
        "1" to "1",
        "2" to "2",
        "3" to "3"
    )

    var mutableMap = mutableMapOf<String, String>(
        "1" to "34",
        "2" to "2",
        "3" to "3"
    )

    var mutableEmptyMap = mutableMapOf<String, String>()

    var valueForKey = mutableMap["1"] //return first element
    var get = mutableMap.get("1")
    println(valueForKey)

    mutableMap.replace("2", "5")
    println(mutableMap)

    mutableList.add(2, "6")
    println(mutableMap)


    mutableMap.put("4", "234")

    mutableEmptyMap.put("4", "6")

    val secret: String = "ABDC"
    val guess: String = "ABDC"
//fill the map (dictionary calculator)
    val secretOccurrences: MutableMap<Char, Int> =
        secret.groupBy { it: Char -> it }.mapValues { it.value.size } as MutableMap<Char, Int>
    val guessOccurrences: MutableMap<Char, Int> =
        guess.groupBy { it }.mapValues { it.value.size } as MutableMap<Char, Int>

    // reduce count of values for each entry:
    for (i in secret.indices) {
        val secretKey = secret[i]
        val guessKey = guess[i]

        secretOccurrences[secretKey] = secretOccurrences.getValue(secretKey) - 1
        guessOccurrences[guessKey] = guessOccurrences.getValue(guess[i]) - 1

    }
    // sort map by values
//    driverCostMap.toList().sortedBy { it.second }.toMap()


    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}
    data class TaxiPark(
        val allDrivers: Set<Driver>,
        val allPassengers: Set<Passenger>,
        val trips: List<Trip>)

    data class Driver(val name: String)
    data class Passenger(val name: String)

    data class Trip(
        val driver: Driver,
        val passengers: Set<Passenger>,
        // the trip duration in minutes
        val duration: Int,
        // the trip distance in km
        val distance: Double,
        // the percentage of discount (in 0.0..1.0 if not null)
        val discount: Double? = null
    ) {
        // the total cost of the trip
        val cost: Double
            get() = (1 - (discount ?: 0.0)) * (duration + distance)
    }
//+++++++++++++++++++++  flatMap  maxByOrNull ++++++++++++++++++++++++++++++++++++++++++++++++
fun TaxiPark.findFaithfulPassengers(minTrips: Int): Set<Passenger> {
    var passengerTripCountMap = this.trips.flatMap { it.passengers }.groupBy { it }
        .mapValues { it.value.size } as MutableMap<Passenger, Int>

    this.allPassengers.subtract(passengerTripCountMap.keys)
        .forEach { failPassenger -> passengerTripCountMap.put(failPassenger, 0) }

    return passengerTripCountMap.filter { it.value >= minTrips }.keys.toSet()

    customer.orders.flatMap { order -> order.products}.sumOf { product -> product.price }


    // Count the amount of times a product was ordered.
// Note that a customer may order the same product several times.
    fun Shop.getNumberOfTimesProductWasOrdered(product: Product): Int {
        return this.customers.flatMap { it.orders }
            .filter { it.products.contains(product) }.count()
    }

    fun findMostExpensiveProductBy(customer: Customer): Product? {
        return customer.orders
            .filter { it.isDelivered}
            .flatMap { it.products }
            .maxByOrNull { it.price }
    }
}

// Return all products the given customer has ordered
fun Customer.getOrderedProducts(): List<Product> =
    this.orders.flatMap { it.products }

// Return all products that were ordered by at least one customer
fun Shop.getOrderedProducts(): Set<Product> {
    return this.customers
        .flatMap { customer -> customer.orders
            .flatMap { order -> order.products } }
        .toSet()

}

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    fun TaxiPark.findSmartPassengers(): Set<Passenger> {
        // Count trips with and without discount for each passenger

        val tripsWithDiscount = mutableMapOf<Passenger, Int>()
        val tripsWithOutDiscount = mutableMapOf<Passenger, Int>()

        this.trips.forEach{
            for (passenger in it.passengers) {
                if (it.discount != 0.0 && it.discount != null ) {
//                    fill map  values
                    tripsWithDiscount[passenger] = (tripsWithDiscount[passenger] ?: 0 ) +1
                }else {
                    tripsWithOutDiscount[passenger] = (tripsWithOutDiscount[passenger] ?:0) +1
                }
            }
        }

        // Filter passengers who had strictly more trips with discount than trips without discount
        return tripsWithDiscount.filter { (passenger, withDiscountCount) ->
            val withoutDiscountCount = tripsWithOutDiscount[passenger] ?: 0
            withDiscountCount > withoutDiscountCount
        }.keys
    }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

fun TaxiPark.findTheMostFrequentTripDurationPeriod(): IntRange? {

    // Group trips by their duration period
    val tripsByDurationPeriod = trips.groupBy { it.duration / 10 * 10 until (it.duration / 10 + 1) * 10 }

    if (tripsByDurationPeriod.isEmpty()) {
        return null // Return null if there are no trips
    }

    // Count the number of trips in each duration period
    val tripCountByPeriod = tripsByDurationPeriod.mapValues { it.value.size }

    // Determine the maximum frequency among all duration periods
    val maxFrequency = tripCountByPeriod.maxByOrNull { it.value }?.value

    // Filter the duration periods that have the maximum frequency
    val mostFrequentPeriods = tripCountByPeriod.filterValues { it == maxFrequency }

    // Return any suitable duration period if many are the most frequent
    // Otherwise, return null if there are no trips
    return if (mostFrequentPeriods.isNotEmpty())
        mostFrequentPeriods.keys.first()
    else null
}
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

fun TaxiPark.checkParetoPrinciple(): Boolean {
    if (this.trips.isEmpty()) return false // Return false if the taxi park contains no trips

    // Calculate the total income
    val totalIncome = trips.sumOf { it.cost }

    // Sort the drivers by their total income in descending order
    val driversByIncome = this.allDrivers.map { driver ->
        driver to trips.filter { it.driver == driver }.sumOf { it.cost }
    }.sortedByDescending { it.second }

    // Calculate the threshold income representing 80% of the total income
    val thresholdIncome = totalIncome * 0.8

    // Calculate the number of drivers representing the top 20%
    val top20PercentDrivers = (allDrivers.size * 0.2).toInt()

    // Sum up the incomes of the top 20% drivers
    val top20PercentIncome = driversByIncome.take(top20PercentDrivers).sumOf { it.second }

    // Check if the sum of the top 20% incomes is greater than or equal to the threshold income
    return top20PercentIncome >= thresholdIncome
}
//+++++++++++++++++   mapNotNull   ++++++++++++++++++++++++++++++++++++++++

people.map { person ->
    person.takeIf { it.isPublicProfile }?.name
}
.filterNotNull()

people.mapNotNull { person ->
    person.takeIf { it.isPublicProfile }?.name
}

//+++++++++++++++++   filter  map   ++++++++++++++++++++++++++++++++++++++++

// Find the customers living in a given city
fun Shop.getCustomersFrom(city: City): List<Customer> =
    this.customers.filter { customer ->  customer.city == city }.toList()

fun Shop.getCustomerCities(): Set<City> =
    this.customers.map { it: Customer -> it.city }.toSet()


//+++++++++++++++++   associateWith  associateBy   ++++++++++++++++++++++++++++++++++++++++
fun main() {
    val numbers = listOf("one", "two", "three", "four")
    println(numbers.associateWith { it.length }) // {one=3, two=3, three=5, four=4}
}

fun main() {
    val numbers = listOf("one", "two", "three", "four")

    println(numbers.associateBy { it.first().uppercaseChar() }) // {O=one, T=three, F=four}
    println(numbers.associateBy(
        keySelector = { it.first().uppercaseChar() },
        valueTransform = { it.length }
    )) // {O=3, T=5, F=4}


    // Build a map from the customer name to the customer
    fun Shop.nameToCustomerMap(): Map<String, Customer> =
        this.customers.associateBy { it.name }.toMap()

    // Build a map from the customer to their city
    fun Shop.customerToCityMap(): Map<Customer, City> =
        this.customers.associateWith { it.city }

    // Build a map from the customer name to their city
    fun Shop.customerNameToCityMap(): Map<String, City> =
        this.customers.associateBy(keySelector = {it.name}

}


//+++++++++++++++++   groupingBy  groupBy   ++++++++++++++++++++++++++++++++++++++++
val groupsByLength: Map<Int, List<String>> = collection.groupBy { s -> s.length }

fun main() {
    val numbers = listOf("one", "two", "three", "four", "five", "six")
    println(numbers.groupingBy { it.first() }.eachCount()) // {o=1, t=2, f=2, s=1}
}

fun main() {
    val numbers = listOf("one", "two", "three", "four", "five")

    println(numbers.groupBy { it.first().uppercase() }) // {O=[one], T=[two, three], F=[four, five]}

    println(numbers.groupBy(
        keySelector = { it.first() },
        valueTransform = { it.uppercase() }
    )) // {o=[ONE], t=[TWO, THREE], f=[FOUR, FIVE]}
}

//+++++++++++++++++   groupingBy  groupBy   ++++++++++++++++++++++++++++++++++++++++
init {
    require(string.all { it in "ACGT" }) { "Invalid nucleotide in: $string" }
}
val nucleotideCounts = ("ACGT" + string).groupBy { it }.mapValues { it.value.size.dec() }


//    val nucleotideCounts: Map<Char, Int>
//        get() {
//            return   string
//                .groupingBy { it }.eachCount()
//        }


//+++++++++++++++++   partition  разбивает на 2 листа   ++++++++++++++++++++++++++++++++++++++++


fun Shop.getCustomersWithMoreUndeliveredOrders(): Set<Customer> {
    var associateBy: Map<Customer, Pair<List<Order>, List<Order>>> =
        this.customers.associateBy(keySelector = { it },
            valueTransform = { it.orders.partition { it.isDelivered == true } })

    var mapValues: Map<Customer, Boolean> =
        associateBy.mapValues { (_, value) -> value.first.count() < value.second.count() }

    return mapValues.mapValues { (_, value) -> value == true }.keys

}

// Return customers who have more undelivered orders than delivered
fun Shop.getCustomersWithMoreUndeliveredOrders(): Set<Customer> {

    return this.customers.filter {
        val (delivered, undelivered) = it.orders.partition { it.isDelivered == true }

        delivered.size < undelivered.size

    }.toSet()


}
//+++++++++++++++++   reduce  + intersect работают вместе собирают в одну коллекцию только их общие елементы   ++++++++++++++++++++++++++++++++++++++++
fun Customer.getOrderedProducts(): Set<Product> =
    this.orders.flatMap { it.products }.

fun Shop.getProductsOrderedByAll(): Set<Product> =
    this.customers.map { customer -> customer.getOrderedProducts() }
        .reduce { accumulatorCollection, secondCollection ->
            accumulatorCollection.intersect(secondCollection)
        }

val maximumSizeOfGroup: Int? = groupsByLength.values.map { group -> group.size }.maxOrNull()
//+++++++++++++++++   firstOrNull   ++++++++++++++++++++++++++++++++++++++++

var firstOrNull: List<String>? = groupsByLength.values.firstOrNull { group ->group != null }

//+++++++++++++++++   build   ++++++++++++++++++++++++++++++++++++++++
fun <K,V>buildMutableMap(build: HashMap<K,V>.() -> Unit): HashMap<K,V> {
    var map = HashMap<K,V>()
    map.build()
    return map
}

//+++++++++++++++++   flatMap   ++++++++++++++++++++++++++++++++++++++++
/*input: [1,[2,3,null,4],[null],5]

output: [1,2,3,4,5]
*/
fun flatten(source: Collection<Any?>): List<Any> {
    return source.flatMap { it ->
        when {
            it is Collection<*> -> flatten(it)
            else -> listOf(it)
        }
    }.filterNotNull()
}

//+++++++++++++++++   flatMap   ++++++++++++++++++++++++++++++++++++++++


