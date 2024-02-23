package tasks

infix fun <T> T.eq(other: T) {
    if (this == other) println("OK")
    else println("Error: $this != $other")
}

class Words {
    private val list = mutableListOf<String>()

     operator fun   String.unaryPlus(){
       return record()
   }

    override fun toString() = list.toString()

    fun String.record(){
      list +=this
    }
}

fun main(args: Array<String>) {
    val words = Words()
    with(words) {
        // The following two lines should compile:
        "one".record()
        +"two"
    }
    words.toString() eq "[one, two]"
}