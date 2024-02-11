package board

import board.Direction.*


open class SquareBoardImpl(final override val width: Int) : SquareBoard {

/*Здесь создается двумерный массив cells, представляющий ячейки доски.
Каждая ячейка инициализируется с координатами (i + 1, j + 1),
 чтобы индексация начиналась с 1.*/
    var cells: Array<Array<Cell>> = Array(width) { i ->
        Array(width) { j ->
            Cell(i + 1, j + 1)
        }
    }
/*Эта private функция проверяет, являются ли заданные индексы
i и j допустимыми для доски.*/
    private fun valid(i: Int, j: Int): Boolean =
        (i in 1..width && j in 1..width)

    override fun getCellOrNull(i: Int, j: Int): Cell? {
        return if (!valid(i, j)) {
            null
        } else {
            return cells[i - 1][j - 1]
        }
    }

    override fun getCell(i: Int, j: Int): Cell {
        if (!valid(i, j)) {
            throw IllegalArgumentException()
        } else {
            return cells[i - 1][j - 1]
        }
    }

    override fun getAllCells(): Collection<Cell> {
        val lst = ArrayList<Cell>()
        for (i in 1..width) {
            for (j in 1..width) {
                lst.add(cells[i - 1][j - 1])
            }
        }
        return lst
    }

    /* 1.coerceAtLeast(jRange.first) возвращает значение, которое не меньше
    указанного минимального значения. Если текущее значение больше или
    равно минимальному, то возвращается текущее значение.
    В противном случае возвращается минимальное значение.*/

    /*coerceAtMost() возвращает значение, которое не больше
     указанного максимального значения. Если текущее значение меньше
      или равно максимальному, то возвращается текущее значение.
       В противном случае возвращается максимальное значение.*/
    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
        val lst = ArrayList<Cell>()
        val trueJRange: IntProgression = if (jRange.step == 1) {
            1.coerceAtLeast(jRange.first)..width.coerceAtMost(jRange.last)
        } else {
            width.coerceAtMost(jRange.first) downTo 1.coerceAtLeast(jRange.last)
        }

        for (j in trueJRange) {
            lst.add(cells[i - 1][j - 1])
        }
        return lst
    }

    /*IntProgression представляет собой последовательность целых чисел,
     начиная с одного значения, заканчивая другим и с определенным шагом.

IntProgression имеет три свойства:

first - начальное значение последовательности,
last - конечное значение последовательности,
step - шаг последовательности.*/
    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        val lst = ArrayList<Cell>()
        val trueIRange: IntProgression = if (iRange.step == 1) {
            1.coerceAtLeast(iRange.first)..width.coerceAtMost(iRange.last)
        } else {
            width.coerceAtMost(iRange.first) downTo 1.coerceAtLeast(iRange.last)
        }
        for (i in trueIRange) {
            lst.add(cells[i - 1][j - 1])
        }
        return lst
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? {
        return when (direction) {
            UP -> getCellOrNull(i - 1, j)
            LEFT -> getCellOrNull(i, j - 1)
            DOWN -> getCellOrNull(i + 1, j)
            RIGHT -> getCellOrNull(i, j + 1)
        }
    }

}

fun createSquareBoard(width: Int): SquareBoard {
    return SquareBoardImpl(width)
}

class GameBoardImpl<T>(width: Int) : SquareBoardImpl(width), GameBoard<T> {

    private var map = mutableMapOf<Cell, T?>()
//заполняет мапу null's
    init {
        cells.forEach { row -> row.forEach { cell -> map[cell] = null } }
    }

    override fun get(cell: Cell): T? {
        return map[cell]
    }

    override fun set(cell: Cell, value: T?) {
        map[cell] = value
    }

/*возвращает коллекцию ячеек, удовлетворяющих заданному предикату.*/
    override fun filter(predicate: (T?) -> Boolean): Collection<Cell> {
        return map.filter { (_, value) -> predicate(value) }.keys
    }

/*возвращает первую ячейку, удовлетворяющую заданному предикату.*/
    override fun find(predicate: (T?) -> Boolean): Cell? {
        return map.filter { (_, value) -> predicate(value) }.keys.firstOrNull()
    }

/*возвращает true, если хотя бы одна ячейка удовлетворяет предикату.*/
    override fun any(predicate: (T?) -> Boolean): Boolean {
        return map.filter { (_, value) -> predicate(value) }.isNotEmpty()
    }

/* возвращает true, если все ячейки удовлетворяют предикату.*/
    override fun all(predicate: (T?) -> Boolean): Boolean {
        return map.filter { (_, value) -> !predicate(value) }.isEmpty()
    }

}

fun <T> createGameBoard(width: Int): GameBoard<T> = GameBoardImpl(width)

