package classes.collections

fun main() {
    val unmutableSet = setOf<String>("123","234","345")
    println(unmutableSet)

    var mutableSet = mutableSetOf<Int>(1,2,3,4)

    var testes : Set<String> = HashSet()

    var union = mutableSet.union(unmutableSet)  // merge to sets into single

    println(union)

//    var intersect = union.intersect(unmutableSet) // return only values which present in bought sets
//    println(intersect)

    var subtract = union.subtract(unmutableSet)  // return only distinguish between 2 sets, union.values minus unmutable.values
    println(subtract)
}
//+++++++++++++++++  isDisjoint   +++++++++++++++++++++++++++++++++++++++++++++
this.isDisjoint(other) // у двух сетов нет совпадений

//+++++++++++++++++  isDisjoint   +++++++++++++++++++++++++++++++++++++++++++++
