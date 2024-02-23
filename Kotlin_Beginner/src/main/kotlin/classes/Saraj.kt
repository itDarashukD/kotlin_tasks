package classes

import kotlin.math.floor


// : - extends from class Building
class Saraj(floors: Int, address: String) : Building(floors, address) {

    // override - extends from class Building
    override fun printHome() {
        println("hello from Saraj class  in address  $address")
    }

    override fun clearStairs() {
        println(" clear by hoover stair $floors")
    }
}