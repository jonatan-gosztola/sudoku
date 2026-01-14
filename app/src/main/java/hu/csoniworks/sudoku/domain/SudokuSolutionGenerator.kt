package hu.csoniworks.sudoku.domain

import hu.csoniworks.sudoku.domain.SudokuCalculationsUtil.isValid

class SudokuSolutionGenerator(
    val size: Int = 9
) {
    fun generateFullSudokuSolution(): MutableList<MutableList<Int?>> {
        val grid = initiateGrid()

        grid.generateSudoku()

        return grid
    }

    private fun initiateGrid(): MutableList<MutableList<Int?>> = MutableList(size) {
        MutableList(size) { null }
    }

    private fun MutableList<MutableList<Int?>>.generateSudoku(): Boolean {
        for (row in 0 until size) {
            for (col in 0 until size) {
                if (this[row][col] == null) {
                    val numbers = (1..9).shuffled()

                    for (n in numbers) {
                        if (isValid(this, row, col, n)) {
                            this[row][col] = n

                            if (this.generateSudoku()) {
                                return true
                            }

                            this[row][col] = null
                        }
                    }
                    return false
                }
            }
        }
        return true
    }

    companion object {

        fun List<Int?>.isValid(): Boolean =
            this.filterNotNull() == this.filterNotNull().distinct()

        fun List<List<Int?>>.getRow(index: Int): List<Int?> =
            this[index]

        fun List<List<Int?>>.getColumn(index: Int): List<Int?> =
            buildList {
                for (row in 0 until this@getColumn.size) {
                    add(this@getColumn[row][index])
                }
            }

        fun List<List<Int?>>.getGroup(row: Int, col: Int): List<Int?> {
            val starRowIndex = when (row) {
                in 0..2 -> 0
                in 3..5 -> 3
                else -> 6
            }

            val startColIndex = when (col) {
                in 0..2 -> 0
                in 3..5 -> 3
                else -> 6
            }

            return buildList {
                for (i in starRowIndex..starRowIndex + 2) {
                    for (j in startColIndex..startColIndex + 2) {
                        add(this@getGroup[i][j])
                    }
                }
            }
        }
    }
}