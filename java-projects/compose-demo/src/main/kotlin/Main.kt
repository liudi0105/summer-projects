import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.*
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
@Preview
fun App() {
    MaterialTheme(colors = darkColors()) {
        login()
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Demo App") {
        App()
    }
}
