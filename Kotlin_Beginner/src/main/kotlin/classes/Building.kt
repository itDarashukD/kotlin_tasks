package classes

open class Building(val floors: Int = 5, var address: String = "vylica") { //constructor, we don't need more create variables, they already in constructor, + i defined defauld value for string
//open - give opportunity to inherited from this class, since by default the extends is forbidden


//    var address: String = ""
//    var floors: Int = 0

//open - give opportunity to inherited (override)from this class
    open fun printHome() {
        println(" hello from Building class")
    }

    open fun clearStairs(){
        print("clear by hands $floors")
    }

}