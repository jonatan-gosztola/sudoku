package hu.csoniworks.sudoku.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import hu.csoniworks.sudoku.R
import hu.csoniworks.sudoku.domain.sudokuengine.Cell
import hu.csoniworks.sudoku.ui.GameViewModel
import hu.csoniworks.sudoku.ui.components.SudokuInput
import hu.csoniworks.sudoku.ui.components.SudokuTable
import hu.csoniworks.sudoku.ui.model.GameScreenUiState
import hu.csoniworks.sudoku.ui.theme.SudokuTheme

@Composable
fun GameScreen(
    viewModel: GameViewModel,
    onBackClicked: () -> Unit,
    onBackToMainMenuClicked: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    uiState?.let { state ->
        GameScreenContent(
            state = state,
            onInputSelection = viewModel::onInputSelection,
            onCellClick = viewModel::onCellClick,
            onBackToMainMenuClicked = onBackToMainMenuClicked,
            onBackClicked = onBackClicked,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreenContent(
    state: GameScreenUiState,
    onCellClick: (row: Int, col: Int) -> Unit,
    onInputSelection: (Int) -> Unit,
    onBackClicked: () -> Unit,
    onBackToMainMenuClicked: () -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.main_screen_title)
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClicked
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_arrow_back_24),
                            contentDescription = stringResource(R.string.back_button)
                        )
                    }
                }
            )
        },
    ) { contentPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp, vertical = 12.dp)
                .padding(contentPadding)
        ) {
            Column(
                modifier = Modifier.weight(2f)
            ) {
//                ButtonRow(
//                    onHintClick = onHintClick
//                )

                Box(
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxWidth()
                ) {
                    SudokuTable(
                        grid = state.grid,
                        selectedCell = state.selectedCell,
                        onCellClick = onCellClick,
                    )
                }
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                if (state.isDone) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(R.string.game_screen_done_solved),
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(bottom = 24.dp)
                        )

                        Text(
                            text = stringResource(R.string.game_screen_done_great_job),
                            modifier = Modifier.padding(bottom = 12.dp)
                        )

                        Button(
                            onClick = onBackToMainMenuClicked
                        ) {
                            Text(text = stringResource(R.string.game_screen_back_to_main_menu_button))
                        }
                    }
                } else {
                    state.selectedCell?.let {
                        SudokuInput(
                            onSelect = onInputSelection
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ButtonRow(
    onHintClick: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = onHintClick
        ) {
            Text(
                text = stringResource(R.string.game_screen_hint)
            )
        }
    }
}

@Preview
@Composable
fun GameScreenContentPreview(
    @PreviewParameter(GameScreenContentPreviewParameterProvider::class)
    state: GameScreenUiState
) {
    SudokuTheme {
        GameScreenContent(
            state = state,
            onInputSelection = {},
            onCellClick = { _, _ -> },
            onBackClicked = {},
            onBackToMainMenuClicked = {}
        )
    }
}

class GameScreenContentPreviewParameterProvider() : PreviewParameterProvider<GameScreenUiState> {
    override val values: Sequence<GameScreenUiState> =
        sequenceOf(
            GameScreenUiState(
                grid = List(9) {
                    List(9) { Cell.Editable(null) }
                },
                selectedCell = 2 to 1,
                isDone = false
            ),
            GameScreenUiState(
                grid = List(9) {
                    List(9) { Cell.Editable(null) }
                },
                selectedCell = null,
                isDone = true
            )
        )
}
