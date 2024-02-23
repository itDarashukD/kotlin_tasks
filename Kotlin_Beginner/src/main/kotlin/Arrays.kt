import java.util.*

fun main() {

    var stringArray: Array<String> = arrayOf("qqq", "www", "rrr")
    var booleanArray: Array<Boolean> = arrayOf(true, false, false)
    var intArray: Array<Int> = arrayOf(2, 3, 4, 5)
    var intArray2 = arrayOf(2, 3, 4, 5)
    var intArray3 : Array<Int> = emptyArray()


    intArray.get(2) //the same as intArray[2]
    println(intArray.get(2)) //33

    intArray.set(2, 33)
    println(intArray.get(2)) //33

    println(intArray.size)

    intArray3.fill(1,0,5)  //fill array
    println(intArray3)
//+++++++++++++++++  vararg   +++++++++++++++++++++++++++++++++++++++++++++

    var intArray = set.plus(other.set).toIntArray()
    CustomSet(*intArray)  //from list<Int> to vararg

    class CustomSet(vararg elements : Int )
    //+++++++++++++++++  vararg   +++++++++++++++++++++++++++++++++++++++++++++
}