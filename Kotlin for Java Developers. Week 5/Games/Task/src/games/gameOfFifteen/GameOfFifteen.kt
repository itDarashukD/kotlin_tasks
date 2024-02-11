package games.gameOfFifteen

import board.Cell
import board.Direction
import board.GameBoard
import board.createGameBoard
import games.game.Game

/*
 * Implement the Game of Fifteen (https://en.wikipedia.org/wiki/15_puzzle).
 * When you finish, you can play the game by executing 'PlayGameOfFifteen'.
 */
fun newGameOfFifteen(initializer: GameOfFifteenInitializer = RandomGameInitializer()): Game =
    GameOfFifteen(initializer)

class GameOfFifteen(private val initializer: GameOfFifteenInitializer) : Game {
    private val board: GameBoard<Int?> = createGameBoard<Int?>(4)


    /*Переменная initialPermutation содержит список чисел от 1 до 15,
    представляющих начальную перестановку. Для каждой ячейки доски определяется значение из этого списка в соответствии
     с их позицией. Последняя ячейка остается пустой.*/
    override fun initialize() {
        val initialPermutation: List<Int> = initializer.initialPermutation //shuffled list of ints
        for (i in 1..board.width) {
            for (j in 1..board.width) {
                val index = (i - 1) * board.width + (j - 1)
                val cell: Cell = board.getCell(i, j)                      //get cell by coordinates
                board[cell] = if (index < board.width * board.width - 1) {
                    initialPermutation[index]
                } else {
                    null
                }
            }
        }
    }

    override fun canMove(): Boolean = true


    /*
    * Этот метод проверяет, выиграли ли вы в игре. Он проверяет, находятся ли числа на доске в порядке от 1 до
    * 15 (или до предпоследнего числа), а также проверяет, что последняя ячейка пуста.*/
    override fun hasWon(): Boolean {
        var won = true
        for (i in 1..board.width) {
            for (j in 1..board.width) {
                val index = (i - 1) * board.width + (j - 1) + 1
                val cell = board.getCell(i, j)
                won = won && (if (index < board.width * board.width) {
                    board[cell] == index
                } else {
                    board[cell] == null
                })
            }
        }
        return won
    }
/*Этот метод обрабатывает ход игрока в указанном направлении.
Он перемещает числа по доске, чтобы создать эффект движения. Для этого он ищет пустую ячейку
, затем находит соседнюю ячейку в указанном направлении и меняет их значения местами.*/
    override fun processMove(direction: Direction) {
        val empty = board.find { it -> it == null } ?: return

        board.apply {
            val operator = when (direction) {
                Direction.DOWN -> {
                    empty.getNeighbour(Direction.UP)
                }
                Direction.UP -> {
                    empty.getNeighbour(Direction.DOWN)
                }
                Direction.RIGHT -> {
                    empty.getNeighbour(Direction.LEFT)
                }
                Direction.LEFT -> {
                    empty.getNeighbour(Direction.RIGHT)
                }
            } ?: return
            val tmp = board[empty]
            board[empty] = board[operator]
            board[operator] = tmp
        }

    }

    override fun get(i: Int, j: Int): Int? {
        val cell = board.getCell(i, j)
        return board[cell]
    }

}




