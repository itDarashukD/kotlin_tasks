package games.game2048

import board.Cell
import board.Direction
import board.GameBoard
import board.createGameBoard
import games.game.Game

/*
 * Your task is to implement the game 2048 https://en.wikipedia.org/wiki/2048_(video_game).
 * Implement the utility methods below.
 *
 * After implementing it you can try to play the game running 'PlayGame2048'.
 */
fun newGame2048(initializer: Game2048Initializer<Int> = RandomGame2048Initializer): Game =
        Game2048(initializer)

class Game2048(private val initializer: Game2048Initializer<Int>) : Game {
    private val board: GameBoard<Int?> = createGameBoard<Int?>(4)


// add two new elements to the board
    override fun initialize() {
        repeat(2) {
            board.addNewValue(initializer)
        }
    }
// проверка есть ли свободные клетки на доске для след хода
    override fun canMove() = board.any { it == null }

//метод проверки победы, который возвращает true, если на доске есть значение 2048.
    override fun hasWon() = board.any { it == 2048 }

/*метод обработки хода, который перемещает значения на доске в
указанном направлении и добавляет новое значение, если ход выполнен успешно.*/
    override fun processMove(direction: Direction) {
        if (board.moveValues(direction)) {
            board.addNewValue(initializer)
        }
    }
//метод для получения значения из ячейки доски по координатам.
    override fun get(i: Int, j: Int): Int? = board.run { get(getCell(i, j)) }
}

/*
 * Add a new value produced by 'initializer' to a specified cell in a board.
 */
fun GameBoard<Int?>.addNewValue(initializer: Game2048Initializer<Int>) {
    val p = initializer.nextValue(this)
    p?.let {
        this[p.first] = p.second
    }
}

/*
 * Update the values stored in a board,
 * so that the values were "moved" in a specified rowOrColumn only.
 * Use the helper function 'moveAndMergeEqual' (in Game2048Helper.kt).
 * The values should be moved to the beginning of the row (or column),
 * in the same manner as in the function 'moveAndMergeEqual'.
 * Return 'true' if the values were moved and 'false' otherwise.
 */
fun GameBoard<Int?>.moveValuesInRowOrColumn(rowOrColumn: List<Cell>): Boolean {
    var values = rowOrColumn.map { it -> this[it] }  //которая содержит значения ячеек из указанной строки или столбца.
    values = values.moveAndMergeEqual { it: Int -> it * 2 } //Эта функция объединяет соседние одинаковые элементы, удваивая их значения.
    rowOrColumn.forEachIndexed { index, cell -> this[cell] =
        if (index < values.size) {
            values[index]
        } else {
            null
        }
    }
/*Функция возвращает true, если в результате перемещения в строке или столбце произошли изменения, и false в противном случае.*/
    return values.isNotEmpty() && values.size < rowOrColumn.size
}



/*
 * Update the values stored in a board,
 * so that the values were "moved" to the specified direction
 * following the rules of the 2048 game .
 * Use the 'moveValuesInRowOrColumn' function above.
 * Return 'true' if the values were moved and 'false' otherwise.
 */
fun GameBoard<Int?>.moveValues(direction: Direction): Boolean {
    var success = false
    (1..this.width).forEach { x -> run {
        val list = when (direction) {
            Direction.DOWN -> this.getColumn(this.width downTo 1, x)
            Direction.UP -> this.getColumn(1..this.width, x)
            Direction.RIGHT -> this.getRow(x, this.width downTo 1)
            Direction.LEFT -> this.getRow(x, 1..this.width)
        }
        val current = this.moveValuesInRowOrColumn(list)
        success = success || current
    } }
    return success
}
