package hu.csoniworks.sudoku.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.csoniworks.sudoku.domain.Cell
import hu.csoniworks.sudoku.domain.SudokuEngine
import hu.csoniworks.sudoku.ui.model.GameScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class GameViewModel : ViewModel() {

    private val engine = SudokuEngine(SudokuEngine.Size.Normal)

    private val _selectedCell: MutableStateFlow<Pair<Int, Int>?> = MutableStateFlow(null)
    private val selectedCell: StateFlow<Pair<Int, Int>?> = _selectedCell.asStateFlow()

    private val isDone = MutableStateFlow(false)

    private val table: StateFlow<List<List<Cell>>?> = engine.table
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    val uiState = combine(
        table.filterNotNull(),
        selectedCell,
        isDone
    ) { grid, selectedCell, isDone ->
        GameScreenUiState(
            grid = grid,
            selectedCell = selectedCell,
            isDone = isDone
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    init {
        newGame()
    }

    private fun newGame() {
        isDone.value = false
        viewModelScope.launch {
            engine.generateTable()
        }
    }

    fun onCellClick(row: Int, col: Int) {
        if (!isDone.value) {
            _selectedCell.value = row to col
        }
    }

    fun onInputSelection(number: Int) {
        viewModelScope.launch {
            _selectedCell.value?.let { (row, col) ->
                engine.fillCell(
                    row = row,
                    col = col,
                    number = number
                )
            }

            if (engine.isCompleted()) {
                if (engine.isCompletionValid()) {
                    isDone.value = true
                    _selectedCell.value = null
                }
            }
        }
    }

    override fun onCleared() {
        Log.d(this::class.simpleName, "onCleared")
        super.onCleared()
    }
}
