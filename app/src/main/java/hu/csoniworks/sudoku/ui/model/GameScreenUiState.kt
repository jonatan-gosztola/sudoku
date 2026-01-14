package hu.csoniworks.sudoku.ui.model

import hu.csoniworks.sudoku.domain.Cell

data class GameScreenUiState(
    val grid: List<List<Cell>>,
    val selectedCell: Pair<Int, Int>?,
    val isDone: Boolean = false
)