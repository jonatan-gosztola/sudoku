package hu.csoniworks.sudoku.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hu.csoniworks.sudoku.domain.sudokuengine.Cell
import hu.csoniworks.sudoku.ui.theme.SudokuTheme

@Composable
fun SudokuCell(
    number: Cell,
    isSelected: Boolean = false,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {

    val selected = remember(isSelected) { isSelected }
    val backgroundColor =
        if (number is Cell.Editable) {
            MaterialTheme.colorScheme.surface
        } else {
            MaterialTheme.colorScheme.surfaceDim
        }
    val shape = remember { RoundedCornerShape(12.dp) }
    val color = if (selected) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.onSurface
    }
    val borderModifier = if (selected) {
        Modifier.border(4.dp, MaterialTheme.colorScheme.onSurface, shape = shape)
    } else {
        Modifier
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(
                shape = shape,
            )
            .padding(2.dp)
            .background(
                color = backgroundColor
            )
            .clickable(
                enabled = number is Cell.Editable,
                onClick = onClick,
                role = Role.Button,
            )
            .then(borderModifier)
    ) {
        Text(
            text = number.number?.toString() ?: "",
            color = color,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SudokuCellPreview() {
    SudokuTheme {
        SudokuCell(
            number = Cell.Editable(null),
            isSelected = true,
            onClick = {},
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SudokuCellPreview2() {
    SudokuTheme {
        SudokuCell(
            number = Cell.Fixed(8),
            isSelected = false,
            onClick = {},
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
        )
    }
}