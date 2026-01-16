package hu.csoniworks.sudoku.domain.sudokuengine.calc

import hu.csoniworks.sudoku.domain.sudokuengine.calc.SudokuSolutionGenerator.Companion.getColumn
import hu.csoniworks.sudoku.domain.sudokuengine.calc.SudokuSolutionGenerator.Companion.getGroup
import hu.csoniworks.sudoku.domain.sudokuengine.calc.SudokuSolutionGenerator.Companion.getRow
import hu.csoniworks.sudoku.domain.sudokuengine.calc.SudokuSolutionGenerator.Companion.isValid

object SudokuCalculationsUtil {

    /**
     * Deep copy
     */
    fun copyGrid(
        grid: List<List<Int?>>
    ): MutableList<MutableList<Int?>> =
        grid.map { it.toMutableList() }.toMutableList()


    /**
     * Returns whether the given number is a valid fill for the given position (row, col) on the given grid.
     */
    fun isValid(
        grid: List<List<Int?>>,
        row: Int,
        column: Int,
        number: Int
    ): Boolean {

        for (i in 0 until grid.size) {
            if (grid[row][i] == number) return false
            if (grid[i][column] == number) return false
        }

        val startRow = (row / 3) * 3
        val startCol = (column / 3) * 3

        for (r in startRow until startRow + 3)
            for (c in startCol until startCol + 3)
                if (grid[r][c] == number) return false

        return true
    }

    /**
     * Returns whether all the given grid's cells are filled.
     */
    fun isComplete(grid: List<List<Int?>>) = grid.all { row -> row.all { it != null } }

    /**
     * Returns whether the grid does not have an invalid cell.
     */
    fun List<List<Int?>>.isGridValid(): Boolean {
        try {
            // row
            for (i in 0 until size) {
                assert(this.getRow(i).isValid())
            }

            // col
            for (i in 0 until size) {
                assert(this.getColumn(i).isValid())
            }

            // group
            val groups = if (size == 9) 3 else 1
            for (i in 0 until groups) {
                for (j in 0 until groups) {
                    assert(this.getGroup(i * 3, j * 3).isValid())
                }
            }
        } catch (_: AssertionError) {
            return false
        }

        return true
    }
}
