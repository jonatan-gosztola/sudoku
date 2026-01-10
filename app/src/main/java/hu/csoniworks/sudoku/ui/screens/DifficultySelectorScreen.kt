package hu.csoniworks.sudoku.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import hu.csoniworks.sudoku.R
import hu.csoniworks.sudoku.ui.theme.SudokuTheme

@Composable
fun DifficultySelectorScreen(
    onEasySelected: () -> Unit,
    onMediumSelected: () -> Unit,
    onHardSelected: () -> Unit,
    onBackClicked: () -> Unit,
) {
    DifficultySelectorScreenContent(
        onEasySelected = onEasySelected,
        onMediumSelected = onMediumSelected,
        onHardSelected = onHardSelected,
        onBackClicked = onBackClicked,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DifficultySelectorScreenContent(
    onEasySelected: () -> Unit,
    onMediumSelected: () -> Unit,
    onHardSelected: () -> Unit,
    onBackClicked: () -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.difficulty_selector_screen_title)
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
                .padding(contentPadding)
        ) {
            Spacer(Modifier.weight(1f))

            Button(
                onClick = onEasySelected
            ) {
                Text(
                    text = stringResource(R.string.difficulty_selector_screen_easy)
                )
            }

            Button(
                onClick = onMediumSelected,
                enabled = false
            ) {
                Text(
                    text = stringResource(R.string.difficulty_selector_screen_medium)
                )
            }

            Button(
                onClick = onHardSelected,
                enabled = false,
            ) {
                Text(
                    text = stringResource(R.string.difficulty_selector_screen_hard)
                )
            }

            Spacer(Modifier.weight(2f))
        }
    }
}

@Preview
@Composable
fun DifficultySelectorScreenContentPreview() {
    SudokuTheme {
        DifficultySelectorScreenContent(
            onEasySelected = {},
            onMediumSelected = {},
            onHardSelected = {},
            onBackClicked = {},
        )
    }
}
