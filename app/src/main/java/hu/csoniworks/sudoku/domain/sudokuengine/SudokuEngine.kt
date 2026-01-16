package hu.csoniworks.sudoku.domain.sudokuengine

import hu.csoniworks.sudoku.domain.sudokuengine.Cell.Companion.toFixedCell
import hu.csoniworks.sudoku.domain.sudokuengine.calc.EasySudokuPuzzleGenerator
import hu.csoniworks.sudoku.domain.sudokuengine.calc.SudokuCalculationsUtil.isGridValid
import hu.csoniworks.sudoku.domain.sudokuengine.calc.SudokuSolutionGenerator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext

class SudokuEngine(
    val size: Size = Size.Medium
): SudokuEngineInterface {

    enum class Size(val value: Int) {
        Small(3),
        Medium(6),
        Normal(value = 9)
    }

    private val solutionGenerator = SudokuSolutionGenerator(size.value)
    private val easyPuzzleGenerator = EasySudokuPuzzleGenerator(size.value)

    private val _table: MutableStateFlow<List<List<Cell>>> = MutableStateFlow(
        MutableList(size.value) {
            MutableList(size.value) { Cell.Editable(null) }
        }
    )

    override val table: StateFlow<List<List<Cell>>> = _table.asStateFlow()

    override suspend fun generateTable() {
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

    override suspend fun fillCell(row: Int, col: Int, number: Int) {
        withContext(Dispatchers.Default) {
            val copy = _table.value.map { it.toMutableList() }.toMutableList()

            copy[row][col] = Cell.Editable(number)

            _table.value = copy
        }
    }

    override suspend fun isCompleted(): Boolean = withContext(Dispatchers.Default) {
        _table.value.isComplete()
    }

    override suspend fun isCompletionValid(): Boolean = withContext(Dispatchers.Default) {
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
