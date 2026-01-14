package hu.csoniworks.sudoku.domain

import hu.csoniworks.sudoku.domain.Cell.Companion.toFixedCell
import hu.csoniworks.sudoku.domain.SudokuCalculationsUtil.isGridValid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext

class SudokuEngine(
    val size: Size = Size.Normal
) {

    enum class Size(val value: Int) {
        Small(3),
        Normal(value = 9)
    }

    private val solutionGenerator = SudokuSolutionGenerator(size.value)
    private val easyPuzzleGenerator = EasySudokuPuzzleGenerator(size.value)

    private val _table: MutableStateFlow<List<List<Cell>>> = MutableStateFlow(
        MutableList(size.value) {
            MutableList(size.value) { Cell.Editable(null) }
        }
    )

    val table: StateFlow<List<List<Cell>>> = _table.asStateFlow()

    suspend fun generateTable() {
        withContext(Dispatchers.Default) {

            val solution = solutionGenerator
                .generateFullSudokuSolution()

            val puzzle = easyPuzzleGenerator
                .generateEasySudoku(solution, 1)

            _table.value = puzzle
                .map { rows ->
                    rows.map { cell ->
                        cell?.toFixedCell() ?: Cell.Editable(null)
                    }
                }
        }
    }

    suspend fun getHint(): Pair<Int, Int> = withContext(Dispatchers.Default) {
        TODO()
    }

    suspend fun fillCell(row: Int, col: Int, number: Int) {
        withContext(Dispatchers.Default) {
            val copy = _table.value.map { it.toMutableList() }.toMutableList()

            copy[row][col] = Cell.Editable(number)

            _table.value = copy
        }
    }

    suspend fun isCompleted(): Boolean = withContext(Dispatchers.Default) {
        _table.value.isComplete()
    }

    suspend fun isCompletionValid(): Boolean = withContext(Dispatchers.Default) {
        _table.value.map { rows ->
            rows.map { cell -> cell.number }
        }.isGridValid()
    }

    private fun List<List<Cell>>.isComplete(): Boolean =
        this.all { row ->
            row.all { cell ->
                cell.number != null
            }
        }

}
