package hu.csoniworks.sudoku.domain.sudokuengine

import kotlinx.coroutines.flow.StateFlow

interface SudokuEngineInterface {

    val table: StateFlow<List<List<Cell>>>

    suspend fun generateTable()

    suspend fun fillCell(row: Int, col: Int, number: Int)

    suspend fun isCompleted(): Boolean

    suspend fun isCompletionValid(): Boolean
}