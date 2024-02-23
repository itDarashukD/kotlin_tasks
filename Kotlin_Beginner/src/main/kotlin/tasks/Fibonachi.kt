package tasks

fun main() {

    fun fibonacci(): Sequence<Int> = sequence {
        var pair = Pair(0, 1)
        while (true) {
            this.yield(pair.first)
            pair = Pair(pair.second, pair.first + pair.second)
        }
    }

    fun main(args: Array<String>) {
        infix fun <T> T.eq(other: T) {
            if (this == other) println("OK")
            else println("Error: $this != $other")
        }

        fibonacci().take(4).toList().toString() eq
                "[0, 1, 1, 2]"

        fibonacci().take(10).toList().toString() eq
                "[0, 1, 1, 2, 3, 5, 8, 13, 21, 34]"
    }


}