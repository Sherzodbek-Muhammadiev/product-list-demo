package uz.gita.task.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getScreenModel
import kotlinx.coroutines.flow.emptyFlow
import uz.gita.task.data.model.ProductData
import uz.gita.task.ui.home.component.HomeBar
import uz.gita.task.ui.home.component.ProductItem
import uz.gita.task.ui.home.viewmodel.HomeContract
import uz.gita.task.ui.home.viewmodel.HomeViewModel
import uz.gita.task.util.theme.TaskTheme

class HomeScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel: HomeContract.ViewModel = getScreenModel<HomeViewModel>()
        val uiState = viewModel.uiState.collectAsState()
        HomeScreenContent(uiState, viewModel::onEventDispatcher)
    }
}

@Composable
private fun HomeScreenContent(uiState: State<HomeContract.UiState>, eventDispatcher: (HomeContract.Intent) -> Unit) {
    val productListItems: LazyPagingItems<ProductData> = uiState.value.products.collectAsLazyPagingItems()
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        HomeBar()
    }) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Text(
                text = "Products & Fruits", modifier = Modifier.padding(16.dp), color = Color.Black, fontWeight = FontWeight.Bold
            )
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                productListItems.apply {
                    when {
                        loadState.refresh is LoadState.Loading -> {
                            Column(modifier = Modifier.align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally) {
                                CircularProgressIndicator(color = Color(0xFFFF9800))
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(text = "Loading...")
                            }
                        }
                    }
                }
                LazyVerticalGrid(
                    modifier = Modifier, columns = GridCells.Fixed(2), contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp)
                ) {
                    items(productListItems.itemCount) {
                        ProductItem(
                            productData = productListItems[it]!!,
                            onClick = { eventDispatcher(HomeContract.Intent.OpenDetails(it)) }
                        )
                    }
                    productListItems.apply {
                        when {
                            loadState.append is LoadState.Loading -> {
                                item(span = { GridItemSpan(2) }) {
                                    Box(modifier = Modifier.height(80.dp)) {
                                        CircularProgressIndicator(color = Color(0xFFFF9800), modifier = Modifier.align(Alignment.Center))
                                    }
                                }
                            }

                            loadState.append is LoadState.Error -> {
                                item(span = { GridItemSpan(2) }) {
                                    Text(text = "Error")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun ScreenPreview() {
    TaskTheme {
        val mockUiState = remember { mutableStateOf(HomeContract.UiState(emptyFlow())) }
        HomeScreenContent(mockUiState, {})
    }
}