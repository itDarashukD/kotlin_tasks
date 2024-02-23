fun main() {

// in Kotlin the String is an array of Chars
    val string: String = "this is my first string"


    var firstChar = string[0]

    //first and last characters
    var first = string.first()
    var last = string.last()

    val startsWith = string.startsWith("this") //true
    val endsWith = string.endsWith("string1") //true

    val name: String = "john"

    val capitalize = name.capitalize() // do first character Capital
    val deCapitalize = name.decapitalize() // do first character litle

    val isEmpty = name.isEmpty()
    val isBlank = name.isBlank()
    val isNotEmpty = name.isNotEmpty()
    val isNullOrBlank = name.isNullOrBlank() //if or null or blank

    val stringWithBlanks = "    |Hello    "
    print(stringWithBlanks.trim())  // remove all blanks
    print(stringWithBlanks.trimStart())  // remove all blanks in start
    print(stringWithBlanks.trimEnd())  // remove all blanks in the end
    print(stringWithBlanks.trimMargin("|"))  // remove till mentioned char

//    val inputFromConsole = readLine()  //read from user console
//    var message = inputFromConsole?.toUpperCase()  // "?" -since inputFromConsole might be null
//    println(message)

    val str : String = " test"
     print( str.repeat(10))  //print 10 times



    val secret = "ABCD"
    val guess = "ANKF"
    var zip :  List<Pair<Char, Char>> = secret.zip(guess)
    println(zip) //[(A, A), (B, N), (C, K), (D, F)]
}

