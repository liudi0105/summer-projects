import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun login() {

    var selectIndex by remember { mutableStateOf(0) }
    val tableTitles = listOf("账号登录", "手机号登录")
    val interactionSource = remember { MutableInteractionSource() }

    Card(
        modifier = Modifier.fillMaxSize(),
        shape = RoundedCornerShape(10.dp),
        elevation = 0.dp,
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxHeight().weight(0.55f)) {
                Button(
                    onClick = {
                        println()
                    },
                ) {
                    Text("aaa")
                }
            }
            Column(
                modifier = Modifier.fillMaxHeight().weight(0.45f).padding(horizontal = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                TabRow(
                    modifier = Modifier.padding(0.dp),
                    selectedTabIndex = selectIndex,
                    divider = { TabRowDefaults.Divider(thickness = 2.dp) }
                ) {
                    tableTitles.forEachIndexed { index, title ->
                        Tab(
                            modifier = Modifier.height(20.dp),
                            selected = selectIndex == index,
                            onClick = { selectIndex = index },
                            text = {
                                Text(
                                    text = title,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}