package mobiler.abbosbek.tablayoutcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.ExperimentalPagingApi
import mobiler.abbosbek.tablayoutcompose.ui.theme.TabLayoutComposeTheme
import androidx.paging.PagingState as PagingState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TabLayoutComposeTheme {
                TabScreen()
            }
        }
    }
}

@Composable
fun TabScreen() {
    val pagerState = rememberPagerState(pageCount = 3)
    Column(modifier = Modifier.background(Color.White)) {
        Tabs(pagerState = pagerState)
    }
}

@ExperimentalPagingApi
@Composable
fun Tabs(pagerState : PagingState) {

    var list = listOf("MIE1","MIE2","MIE3")
    var scope = rememberCoroutineScope()
    TabRow(selectedTabIndex = pagerState) {

    }
}
