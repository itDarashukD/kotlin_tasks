fun main() {

    var intArray: Array<Int> = arrayOf(2, 3, 4, 5)

    for (i in 1..10) {
        println("$i loop for")
    }

    for (i in 1..10 step 2) {
        println("$i loop for")
    }

    for (i in 10 downTo 1 step 2) { //reverse order
        println("$i loop for")
    }

    for (element in intArray) {
        println(element)
    }

    intArray.forEach { element -> println(element) }

    intArray.forEachIndexed { index, element ->     //can loop not only element, but also index
        println("$index has element $element")
    }

    intArray.forEachIndexed{index,el -> println("$index has an element $el") }

    intArray.forEach { el-> println("some $el") }


    //++++++++++++++  loop index  +++++++++++++++++++++++++++++++++++++++++++
    (digits.mapIndexed { i, d -> d * (10 - i)}.sum() + control) % 11 == 0
}