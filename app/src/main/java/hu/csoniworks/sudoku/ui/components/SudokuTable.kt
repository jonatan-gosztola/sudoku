package hu.csoniworks.sudoku.ui.components

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hu.csoniworks.sudoku.domain.Cell
import hu.csoniworks.sudoku.ui.theme.SudokuTheme

@Composable
fun SudokuTable(
    onCellClick: (row: Int, col: Int) -> Unit,
    selectedCell: Pair<Int, Int>?,
    grid: List<List<Cell>>,
    modifier: Modifier = Modifier,
) {
    val selectedRow = selectedCell?.first
    val selectedCell = selectedCell?.second

    BoxWithConstraints(
        modifier = modifier.fillMaxWidth()
    ) {
        val cellSize = minOf(maxWidth, maxHeight) / grid.size

        Column {
            for (i in 0 until grid.size) {

                if (i == 3 || i == 6) {
                    HorizontalDivider(thickness = 1.dp)
                }

                Row {
                    for (j in 0 until grid.size) {
                        if (j == 3 || j == 6) {
                            VerticalDivider(
                                thickness = 1.dp,
                                modifier = Modifier.height(cellSize)
                            )
                        }

                        SudokuCell(
                            isSelected = i == selectedRow && j == selectedCell,
                            number = grid[i][j],
                            onClick = { onCellClick(i,j) },
                            modifier = Modifier.size(cellSize)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SudokuTablePreview(modifier: Modifier = Modifier) {
    SudokuTheme {
        SudokuTable(
            grid = List(9) {
                List(9) {
                    Cell.Editable(null)
                }
            },
            selectedCell = 1 to 1,
            onCellClick = { _, _ -> },
        )
    }
}
