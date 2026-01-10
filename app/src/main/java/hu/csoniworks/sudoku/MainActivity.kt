package hu.csoniworks.sudoku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import hu.csoniworks.sudoku.ui.navigation.Screen
import hu.csoniworks.sudoku.ui.screens.DifficultySelectorScreen
import hu.csoniworks.sudoku.ui.screens.GameScreen
import hu.csoniworks.sudoku.ui.screens.MainScreen
import hu.csoniworks.sudoku.ui.theme.SudokuTheme
import kotlin.system.exitProcess

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

                            Screen.GameScreen -> NavEntry(screenKey) {
                                GameScreen(
                                    onBackClicked = {
                                        backStack.removeLastOrNull()
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

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SudokuTheme {
        Greeting("Android")
    }
}