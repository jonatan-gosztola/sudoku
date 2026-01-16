package hu.csoniworks.sudoku.domain.sudokuengine

sealed class Cell(open val number: Int?) {
    data class Fixed(override val number: Int) : Cell(number)
    data class Editable(override val number: Int?) : Cell(number)

    companion object {
        fun Int.toFixedCell(): Fixed = Fixed(this)
    }
}