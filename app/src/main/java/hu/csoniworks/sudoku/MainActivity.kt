package hu.csoniworks.sudoku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import hu.csoniworks.sudoku.ui.GameViewModel
import hu.csoniworks.sudoku.ui.navigation.Screen
import hu.csoniworks.sudoku.ui.screens.DifficultySelectorScreen
import hu.csoniworks.sudoku.ui.screens.GameScreen
import hu.csoniworks.sudoku.ui.screens.MainScreen
import hu.csoniworks.sudoku.ui.theme.SudokuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val backStack = remember { mutableStateListOf<Screen>(Screen.MainScreen) }

            SudokuTheme {
                NavDisplay(
                    backStack = backStack,
                    onBack = { backStack.removeLastOrNull() },
                    entryDecorators = listOf(
                        rememberSaveableStateHolderNavEntryDecorator(),
                        rememberViewModelStoreNavEntryDecorator()
                    ),
                    entryProvider = { screenKey ->
                        when (screenKey) {
                            Screen.MainScreen -> NavEntry(screenKey) {
                                MainScreen(
                                    onNewGame = {
                                        backStack.add(Screen.DifficultySelectorScreen)
                                    },
                                    onExit = {
                                        finishAndRemoveTask()
                                    }
                                )
                            }

                            Screen.DifficultySelectorScreen -> NavEntry(screenKey) {
                                DifficultySelectorScreen(
                                    onEasySelected = { backStack.add(Screen.GameScreen) },
                                    onMediumSelected = {},
                                    onHardSelected = {},
                                    onBackClicked = {
                                        backStack.removeLastOrNull()
                                    }
                                )
                            }

                            is Screen.GameScreen -> NavEntry(screenKey) {
                                GameScreen(
                                    viewModel = viewModel<GameViewModel>(),
                                    onBackClicked = {
                                        backStack.removeLastOrNull()
                                    },
                                    onBackToMainMenuClicked = {
                                        backStack.removeAll { it != Screen.MainScreen }
                                    }
                                )
                            }
                        }
                    }
                )
            }
        }
    }
}
