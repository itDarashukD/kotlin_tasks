package games.game2048

import board.Cell
import board.GameBoard
import kotlin.random.Random
/*
  * Generate a random value and a random cell among free cells
  * that given value should be added to.
  * The value should be 2 for 90% cases, and 4 for the rest of the cases.
  * Use the 'generateRandomStartValue' function above.
  * If the board is full return null.
  */
interface Game2048Initializer<T> {
    /*
     * Specifies the cell and the value that should be added to this cell.
     */
    fun nextValue(board: GameBoard<T?>): Pair<Cell, T>?
}


/*он генерирует случайное значение (2 с вероятностью 90% и 4 с вероятностью 10%)
 .*/
object RandomGame2048Initializer: Game2048Initializer<Int> {
    private fun generateRandomStartValue(): Int =
            if (Random.nextInt(10) == 9) 4 else 2

    /*  генерирует   случайную пустую ячейку на доске для добавления этого значения.*/
    override fun nextValue(board: GameBoard<Int?>): Pair<Cell, Int>? {
        val v = generateRandomStartValue()
        val empty: List<Cell> = board.filter { it == null }.toList()
        if (empty.isEmpty()) {
            return null
        }
        val r = Random.nextInt(empty.size)
        val cell = empty[r]
        return Pair(cell, v) // random value to cell

    }
}