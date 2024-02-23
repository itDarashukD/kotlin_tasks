import java.lang.IllegalArgumentException
import kotlin.random.Random

fun main() {

//    null - empty value
//    var setNullValue: Int = null   can't set null value
    var setNullValue: Int? = null  //Int?  - ?  can set null value

//    list<String?>()   //one of the values in lins might be a null
//    list<String>?   //the whole list might be a null
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    val foo: Int
    get() = 1       //the getter
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    val string: String by lazy {   //lazy initialization
        println("!!!!")
        "hello"
    }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    lateinit var myString: String // can't be val, can't be a primitive, late initializations of this field

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    val name: String? = "John"

    val length = name?.let {
        println("Name is: $it")
        it.length
    }

//    getEmail()?.let {sendEmail(it)} // only if getEmail() returns not null value
    //We use let to execute a block of code only if name is not null.
//+++++++++++++++ multiple lines strings ++++++++++++++++++++++++
    """ adsasf
        asdfafsd
        asdfaf
        """
//+++++++++++++++ trimMargin  ++++++++++++++++++++++++

    const val question = "life, the universe, and everything"
    const val answer = 42

    val tripleQuotedString = """
    #question = "$question"
    #answer = $answer""".trimMargin(marginPrefix = "#")  //remove any sign
//+++++++++++++++  ++++++++++++++++++++++++

//+++++++++++++++  ++++++++++++++++++++++++
    // Using !! to assert that nullableValue is not null
    var guess = readLine()!!

    val answer2: String? = null
    var toUpperCase2 = answer2?.toUpperCase()  // ? -  can be null

    val answer: String = ""
    var toUpperCase = answer!!.toUpperCase()  // !! -  we 100% sure that in answer can't be null


    var myAge: Int = 37
    var test1 = 5
    var myShort: Short = 33
    var long: Long = 35
    var myFloat: Float = 34.34f
    var myDouble: Double = 43.54

    var myString: String = "first kotlin string"
    myString = "23"

    var test = "wer"
    var myChar: Char = 'V'

    var myBoolean: Boolean = false

    val constant: Int = 100 //VAL - value can't be changed

//    vararg trips: Trip --> [Trip]  vararg - array of smth

    println(myString + myChar)
    println("Program arguments: ${test1} ${test1} ")
    println("Program arguments: $test1 and $myChar ")

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    IntRange
    val range1 = 1..5 // Creates a range from 1 to 5 (inclusive)
    val range2 = 10 downTo 1 // Creates a range from 10 down to 1 (inclusive)
    val range3 = 1 until 10 // Creates a range from 1 to 9 (inclusive on 1, exclusive on 10)
    val range = 1..10
    println(range.contains(5)) // true
    println(range.isEmpty())   // false
    println(range.first)       // 1
    println(range.last)        // 10
    println(range.step)        // 1 (default step)

    if (myAge in 21..38) {                // in  - diapason
        println("more then 21, but less then 38")
    }
//+++++++++++ if - elase  ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 fun generateRandomStartValue(): Int =
        if (Random.nextInt(10) == 9) 4 else 2


//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    val day: String = "Monday"
    when (day) {
        "Monday" -> println("yeap it is Monday")
        "Friday" -> println("yeap it is Friday")
        "Wednesday" -> println("yeap it is Wednesday")

        else -> println("no one from mentioned days")
    }

    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // CAST
    fun main(args: Array<String>) {
        val s = "string"
        println(s as? Int)    // cast if s is Integer -->return s , else return Null
        println(s as Int?)    // cast if s is Integer -->return s , else thrown exception
    }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    val someNotStaticField: String VAriables.getSomeStaticField() //getter under the hood

    @JvmField
    val someStaticFielld: String   VAriables.someStaticFielld

    const val someSecondStaticFielld: String  VAriables.someSecondStaticFielld

    class Foo {
        companion object {
            fun bar(): String = "someStaticField"
        }
    }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    object Singleton : // singleton class
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    class Student(name: String, age: Int) {
        constructor(name: String) : this(name, 18) { //the second constructor in class
            // ...
        }
    }
//++++++++++++++++++++++   Nothing  type +++++++++++++++++++++++++++++++++++++++++++++++
fun failWithWrongAge(age: Int?): Nothing {
    throw IllegalArgumentException("Wrong age: $age")
}

//++++++++++++++++++++++   require +++++++++++++++++++++++++++++++++++++++++++++++

    init {
        require(number[0] !in "01") //if false  (number[0] !in "01") --> throw exception
        require(number[3] !in "01")
        require(number.length == 10)
    }


}