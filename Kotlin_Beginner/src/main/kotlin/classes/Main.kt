package classes

fun main() {

    val building = Building(33, "avenue")

    println(building.address)

    building.address = "street"
    println(building.address)

    building.printHome()
    building.clearStairs()

    var saraj  = Saraj(1,"inventory")
    saraj.printHome()

    saraj.clearStairs()


}

