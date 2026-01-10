package hu.csoniworks.sudoku.ui.navigation

import androidx.navigation3.runtime.NavEntry
import hu.csoniworks.sudoku.ui.screens.DifficultySelectorScreen
import hu.csoniworks.sudoku.ui.screens.GameScreen
import hu.csoniworks.sudoku.ui.screens.MainScreen

sealed class Screen {
    data object MainScreen : Screen()
    data object DifficultySelectorScreen : Screen()
    data object GameScreen : Screen()
}
