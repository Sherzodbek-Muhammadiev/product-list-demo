package uz.gita.task

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import uz.gita.task.navigation.AppNavigatorHandler
import uz.gita.task.ui.home.HomeScreen
import uz.gita.task.util.theme.TaskTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigatorHandler: AppNavigatorHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskTheme {
                Navigator(HomeScreen()) { navigator ->
                    LaunchedEffect(key1 = navigator) {
                        navigatorHandler.navigation
                            .onEach { it(navigator) }
                            .collect()
                    }
                    SlideTransition(navigator)
                }
            }
        }
    }
}