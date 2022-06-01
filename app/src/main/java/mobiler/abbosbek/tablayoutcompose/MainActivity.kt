package mobiler.abbosbek.tablayoutcompose

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.ContentView
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.LocalOverScrollConfiguration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.ExperimentalPagingApi
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch
import mobiler.abbosbek.tablayoutcompose.ui.theme.TabLayoutComposeTheme
import androidx.paging.PagingState as PagingState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.hide()
        setContent {
            ContentView()
        }
    }

    @OptIn(ExperimentalPagerApi::class)
    @Composable
    private fun ContentView() {

        val pagerState = rememberPagerState()

        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color.White)) {
            TabRow(pagerState = pagerState)
            PagerContent(pagerState = pagerState)
        }

    }

    @OptIn(ExperimentalPagerApi::class)
    @Composable
    fun TabRow(pagerState: PagerState) {
        val tabTextAndIcon = listOf(
            "tab 1" to Icons.Default.Home,
            "tab 2" to Icons.Default.Favorite,
            "tab 3" to Icons.Default.Person,
        )

        val coroutineScope = rememberCoroutineScope()
        
        TabRow(selectedTabIndex = pagerState.currentPage,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            backgroundColor = Color.DarkGray,
            contentColor = Color.DarkGray,
            divider = {
                TabRowDefaults.Divider(
                    thickness = 3.dp,
                    color = Color.Black
                )
            },
            indicator = {tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier.pagerTabIndicatorOffset(pagerState = pagerState,tabPositions = tabPositions),
                    height = 3.dp,
                    color = Color.Yellow
                )
            }
        ){
            tabTextAndIcon.forEachIndexed{index, pair ->
                val selected = pagerState.currentPage == index
                Tab(selected = selected, onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                    enabled = true
                ) {
                    Column(
                        modifier = Modifier
                            .padding(10.dp)
                            .height(50.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(modifier = Modifier
                            .size(30.dp)
                            .align(Alignment.CenterHorizontally)){
                            Image(
                                pair.second,
                                contentDescription = null,
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(CircleShape)
                            )
                        }
                        Text(
                            text = pair.first,
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            fontSize = if (selected){
                                12.sp
                            }else{
                                10.sp
                            },
                            color = if (selected){
                                Color.LightGray
                            }else {
                                Color.DarkGray
                            },
                            fontWeight = if (selected){
                                FontWeight.Bold
                            }else{
                                FontWeight.Normal
                            }
                        )
                    }
                }
            }
        }
    }


    @OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)
    @Composable
    fun PagerContent(pagerState: PagerState) {
        CompositionLocalProvider(LocalOverScrollConfiguration provides null) {
            
            Box(modifier = Modifier.fillMaxSize()){
                HorizontalPager(count = 3, state = pagerState) {page ->
                    when(page){
                        0 -> {ScreenOne()}
                        1 -> {ScreenTwo()}
                        2 -> {ScreenThree()}
                    }
                }
                
                HorizontalPagerIndicator(
                    pagerState = pagerState, modifier = Modifier
                        .align(
                            Alignment.BottomCenter
                        )
                        .padding(bottom = 20.dp),
                    activeColor = Color.Yellow,
                    inactiveColor = Color.DarkGray,
                    indicatorWidth = 8.dp,
                    indicatorHeight = 8.dp,
                    spacing = 5.dp
                )
            }
        }
    }

    @Composable
    fun ScreenOne() {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color.White)) {
            Spacer(modifier = Modifier.padding(vertical = 16.dp))
            
            Text(text = "Coding",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                fontSize = 25.sp
            )
        }
    }
    @Composable
    fun ScreenTwo() {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color.White)) {
            Spacer(modifier = Modifier.padding(vertical = 16.dp))

            Text(text = "With",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                fontSize = 25.sp
            )
        }
    }
    @Composable
    fun ScreenThree() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            verticalArrangement = Arrangement.Center,
        ) {
            Spacer(modifier = Modifier.padding(vertical = 16.dp))

            Text(text = "Abbosbek",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                fontSize = 25.sp
            )
        }
    }
}


