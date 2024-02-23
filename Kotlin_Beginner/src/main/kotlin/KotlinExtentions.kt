
import java.util.Random

fun main() {

    val someString: String = "some test string"
    val someArray: Array<Int> = arrayOf(1, 2, 3, 4, 5)

    println(someString.countOfWords())

    println((someArray.swapDigits(0, 4)))

    someArray.printElements()
    someArray.fill(Random().nextInt(),0,5)
    someArray.printElements()


}

//did additional mettod to String class,
// we can invoke this method in any place of the app and from any String!
fun String.countOfWords(): Int {
    var count = 0;
    for (i in this) {                // "this" - in this method will be a Sting
        if (i.isWhitespace()) {
            count++;
        }
    }
    return count + 1
}


fun Array<Int>.swapDigits(ind1: Int, ind2: Int): Array<Int> {
    var digit1 = this[ind1]
    var digit2 = this[ind2]

    this[ind1] = digit2
    this[ind2] = digit1

    return this //in this case "this" is an Array
}


fun Array<Int>.printElements() {
    this.forEach { int -> print(" $int") } //"this" is an Array
}

fun <T> Array<T>.printElements() {   //Typazing array
    this.forEach { int -> print(" $int") }
}


fun main(args: Array<String>) {
    val s1: String? = null
    val s2: String? = ""
    s1?.isEmptyOrNull(s1) eq true
    s2?.isEmptyOrNull(s2) eq true

    val s3 = "   "
    s3.isEmptyOrNull(s3) eq false
}

fun String.isEmptyOrNull(string: String) : Boolean{
    if (string.isNullOrEmpty() ) {
        return true
    }

    fun main(args: Array<String>) {
        val s = "string"
        println(s as? Int)    // null
        println(s as Int?)    // exception
    }

    //++++++++++++++++++++++   Extention +++++++++++++++++++++++++++++++++++++++++++++++
    data class RationalNumber(val numerator: Int, val denominator: Int)

    fun Int.r(): RationalNumber =

        RationalNumber(this,1)

    fun Pair<Int, Int>.r(): RationalNumber =
        RationalNumber(this.first,this.second)
    //++++++++++++++++++++++    +++++++++++++++++++++++++++++++++++++++++++++++

}


