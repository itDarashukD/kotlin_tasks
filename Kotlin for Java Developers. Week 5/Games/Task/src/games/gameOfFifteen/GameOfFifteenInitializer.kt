package games.gameOfFifteen

interface GameOfFifteenInitializer {
    /*
     * Even permutation of numbers 1..15
     * used to initialized the first 15 cells on a board.
     * The last cell is empty.
     */
    val initialPermutation: List<Int>
}

class RandomGameInitializer : GameOfFifteenInitializer {
    /*
     * Generate a random permutation from 1 to 15.
     * `shuffled()` function might be helpful.
     * If the permutation is not even, make it even (for instance,
     * by swapping two numbers).
     */
    override val initialPermutation : List<Int> by lazy {  //(by lazy), чтобы оно было вычислено только при первом обращении к нему.
        val list = (1..15).shuffled().toMutableList()

//        isEven  --> Четная перестановка - это перестановка элементов, в которой количество инверсий (пар элементов, меняющих порядок) является четным числом.
        //[3, 1, 2]. Инверсией будет пара элементов, где более правый элемент меньше левого. В данной перестановке у нас две инверсии: (3, 1) и (3, 2). Поэтому эта перестановка нечетная.
        //что игра будет решаемой, нужно, чтобы начальная перестановка чисел (в нашем случае, чисел от 1 до 15) была четной

        if (isEven(list)) {
            list
        } else {
            val tmp = list[0]
            list[0] = list[1]
            list[1] = tmp
            list
        }

    }
}

