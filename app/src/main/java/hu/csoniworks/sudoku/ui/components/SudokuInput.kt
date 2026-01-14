package hu.csoniworks.sudoku.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import hu.csoniworks.sudoku.ui.theme.SudokuTheme

@Composable
fun SudokuInput(
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    var size by remember { mutableStateOf(IntSize.Zero) }

    BoxWithConstraints(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        val cellSize = minOf(maxWidth, maxHeight) / 3

        Column() {
            Row() {
                SelectableCell(
                    number = 1,
                    onSelect = onSelect,
                    modifier = Modifier.size(cellSize)
                )
                SelectableCell(
                    number = 2,
                    onSelect = onSelect,
                    modifier = Modifier.size(cellSize)
                )
                SelectableCell(
                    number = 3,
                    onSelect = onSelect,
                    modifier = Modifier.size(cellSize)
                )
            }
            Row() {
                SelectableCell(
                    number = 4,
                    onSelect = onSelect,
                    modifier = Modifier.size(cellSize)
                )
                SelectableCell(
                    number = 5,
                    onSelect = onSelect,
                    modifier = Modifier.size(cellSize)
                )
                SelectableCell(
                    number = 6,
                    onSelect = onSelect,
                    modifier = Modifier.size(cellSize)
                )
            }
            Row() {
                SelectableCell(
                    number = 7,
                    onSelect = onSelect,
                    modifier = Modifier.size(cellSize)
                )
                SelectableCell(
                    number = 8,
                    onSelect = onSelect,
                    modifier = Modifier.size(cellSize)
                )
                SelectableCell(
                    number = 9,
                    onSelect = onSelect,
                    modifier = Modifier.size(cellSize)
                )
            }
        }
    }
}

@Composable
private fun SelectableCell(
    number: Int,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = { onSelect(number) },
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
            .padding(2.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = number.toString(),
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Preview
@Composable
fun SudokuInputPreview() {
    SudokuTheme {
        Box(Modifier.width(200.dp)) {
            SudokuInput(
                onSelect = {}
            )
        }
    }
}