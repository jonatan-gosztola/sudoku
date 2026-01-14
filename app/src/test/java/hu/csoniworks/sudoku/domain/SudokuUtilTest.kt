package hu.csoniworks.sudoku.domain

import hu.csoniworks.sudoku.domain.SudokuSolutionGenerator.getColumn
import hu.csoniworks.sudoku.domain.SudokuSolutionGenerator.getGroup
import hu.csoniworks.sudoku.domain.SudokuSolutionGenerator.getRow
import hu.csoniworks.sudoku.domain.SudokuSolutionGenerator.isValid
import org.junit.Test

class SudokuUtilTest {

    @Test
    fun `isValid should return ture when list has all the numbers`() {
        val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)

        val result = list.isValid()

        assert(result)
    }

    @Test
    fun `isValid should return true when list has null values and there are no numbers duplicated`() {
        val list = listOf(null, 2, 3, 4, 5, null, null, 8, null)

        val result = list.isValid()

        assert(result)
    }

    @Test
    fun `isValid should return true when list has only null values`() {
        val list = listOf(null, null, null, null, null, null, null, null, null)

        val result = list.isValid()

        assert(result)
    }

    @Test
    fun `isValid should return false when list has one duplication`() {
        val list = listOf(1, 2, 2, 4, 5, 6, 7, 8, 9)

        val result = list.isValid()

        assert(!result)
    }

    @Test
    fun `isValid should return false when list has more duplications`() {
        val list = listOf(1, 2, 2, 4, 4, 6, 7, 8, 9)

        val result = list.isValid()

        assert(!result)
    }

    @Test
    fun `generateFullSudoku should generate a full grid`() {
        val table = SudokuSolutionGenerator.generateFullSudokuSolution()

        table.print()

        table.forEach { row ->
            row.forEach { cell ->
                assert(cell != null)
            }
        }
    }

    @Test
    fun `generateFullSudoku should generate a valid grid`() {
        val table = SudokuSolutionGenerator.generateFullSudokuSolution()

        table.print()

        // validation of rows
        for (i in 0..8) {
            assert(table.getRow(i).isValid())
        }

        // validation of columns
        for (i in 0..8) {
            assert(table.getColumn(i).isValid())
        }

        // validation of groups
        for (i in 0..2) {
            for (j in 0..2) {
                assert(table.getGroup(i * 3,j * 3).isValid())
            }
        }
    }

    @Test
    fun `makeEasySudoku should remove some cells`() {
        val solution = SudokuSolutionGenerator.generateFullSudokuSolution()

        val limit = 1
        val grid = EasySudokuPuzzleGenerator.generateEasySudoku(solution, limit)

        val emptyCount = grid.flatten().count { it == null }

        grid.print()
        println("Empt cell count: ${emptyCount}")

        assert(emptyCount <= limit)
    }

    companion object {
        fun List<List<Int?>>.print() {
            this.forEachIndexed { rowIndex, rows ->
                rows.forEachIndexed { cellIndex, cell ->
                    print(" ${cell?.toString() ?: "."}")
                    if ((cellIndex + 1) % 3 == 0) {
                        print(" |")
                    } else {
                        print(" ")
                    }
                }
                println()
                if ((rowIndex + 1) % 3 == 0) {
                    println("------------------------------")
                }
            }
            println()
        }
    }
}