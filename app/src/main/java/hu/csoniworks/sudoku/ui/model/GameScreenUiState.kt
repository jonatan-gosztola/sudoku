package hu.csoniworks.sudoku.ui.model

import hu.csoniworks.sudoku.domain.sudokuengine.Cell

data class GameScreenUiState(
    val grid: List<List<Cell>>,
    val selectedCell: Pair<Int, Int>?,
    val isDone: Boolean = false
)