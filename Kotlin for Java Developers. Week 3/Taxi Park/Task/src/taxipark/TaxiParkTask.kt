package taxipark

/*
 * Task #1. Find all the drivers who performed no trips.
 */
fun TaxiPark.findFakeDrivers(): Set<Driver> {
    val allDriversInPark = this.allDrivers
    val realDrivers = this.trips.map { it.driver }.toSet()

    return allDriversInPark.subtract(realDrivers);
}

/*
 * Task #2. Find all the clients who completed at least the given number of trips.
 */
fun TaxiPark.findFaithfulPassengers(minTrips: Int): Set<Passenger> {

    var passengerTripCountMap = this.trips.flatMap { it.passengers }.groupBy { it }
        .mapValues { it.value.size } as MutableMap<Passenger, Int>

    this.allPassengers.subtract(passengerTripCountMap.keys)
        .forEach { failPassenger -> passengerTripCountMap.put(failPassenger, 0) }

    return passengerTripCountMap.filter { it.value >= minTrips }.keys.toSet()
}

/*
 * Task #3. Find all the passengers, who were taken by a given driver more than once.
 */
fun TaxiPark.findFrequentPassengers(driver: Driver): Set<Passenger> {

    var toList = this.trips.filter { it.driver == driver }.toList()

    //map<Passenger, countOfTrips>
    var mutableMap =
        toList.map { it.passengers }.flatMap { it }.groupBy { it }.mapValues { it.value.size }

    return mutableMap.filter { it.value > 1 }.keys
}


/*
 * Task #4. Find the passengers who had a discount for majority of their trips.
 */
fun TaxiPark.findSmartPassengers(): Set<Passenger> {
    // Count trips with and without discount for each passenger

    val tripsWithDiscount = mutableMapOf<Passenger, Int>()
    val tripsWithOutDiscount = mutableMapOf<Passenger, Int>()

    this.trips.forEach {
        for (passenger in it.passengers) {
            if (it.discount != 0.0 && it.discount != null) {
                tripsWithDiscount[passenger] = (tripsWithDiscount[passenger] ?: 0) + 1
            } else {
                tripsWithOutDiscount[passenger] = (tripsWithOutDiscount[passenger] ?: 0) + 1
            }
        }
    }

    // Filter passengers who had strictly more trips with discount than trips without discount
    return tripsWithDiscount.filter { (passenger, withDiscountCount) ->
        val withoutDiscountCount = tripsWithOutDiscount[passenger] ?: 0
        withDiscountCount > withoutDiscountCount
    }.keys
}

/*
 * Task #5. Find the most frequent trip duration among minute periods 0..9, 10..19, 20..29, and so on.
 * Return any period if many are the most frequent, return `null` if there're no trips.
 */
fun TaxiPark.findTheMostFrequentTripDurationPeriod(): IntRange? {

    // Group trips by their duration period
    val tripsByDurationPeriod =
        trips.groupBy { it.duration / 10 * 10 until (it.duration / 10 + 1) * 10 }

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


/*
 * Task #6.
 * Check whether 20% of the drivers contribute 80% of the income.
 */
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

fun main() {
    val number = 42
    var takeIf = number.takeIf { it > 10 } // returns 42, -  get it only if { ... }
    var takeUnless = number.takeUnless { it > 10 }
    println(takeUnless)

}

data class Value(val s: String)

fun equals1(v1: Value?, v2: Value?): Boolean {
    return v1 == v2
}
