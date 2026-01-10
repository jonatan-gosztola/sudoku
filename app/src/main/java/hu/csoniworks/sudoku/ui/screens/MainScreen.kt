package hu.csoniworks.sudoku.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import hu.csoniworks.sudoku.R
import hu.csoniworks.sudoku.ui.theme.SudokuTheme

@Composable
fun MainScreen(
    onNewGame: () -> Unit,
    onExit: () -> Unit,
) {
    MainScreenContent(
        onNewGame = onNewGame,
        onExit = onExit
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenContent(
    onNewGame: () -> Unit,
    onExit: () -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.main_screen_title)
                    )
                },
            )
        },
    ) { contentPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            Spacer(Modifier.weight(1f))

            Button(
                onClick = onNewGame,
            ) {
                Text(
                    text = stringResource(R.string.main_screen_new_game)
                )
            }

            Button(
                onClick = onExit
            ) {
                Text(
                    text = stringResource(R.string.main_screen_exit)
                )
            }

            Spacer(Modifier.weight(2f))
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun MainScreenContentPreview() {
    SudokuTheme {
        MainScreenContent(
            onNewGame = {},
            onExit = {}
        )
    }
}
