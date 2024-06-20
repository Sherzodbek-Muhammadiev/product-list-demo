package uz.gita.task.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.size.Size
import uz.gita.task.R
import uz.gita.task.data.model.ProductData
import uz.gita.task.util.PreviewUtil
import uz.gita.task.util.theme.TaskTheme

data class DetailsScreen(val productData: ProductData) : Screen {
    @Composable
    override fun Content() {
        DetailsScreenContent(productData)
    }
}

@Composable
private fun DetailsScreenContent(productData: ProductData) {
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            DetailsBar()
        }) {
        Column(
            modifier = Modifier
                .padding(it)
                .verticalScroll(rememberScrollState())
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(productData.imageUrl)
                    .crossfade(true)
                    .size(Size.ORIGINAL)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .build(),
                contentDescription = null,
                placeholder = painterResource(R.drawable.placeholder),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Inside
            )
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Row {
                    Text(
                        text = productData.name,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        modifier = Modifier.weight(1f)
                    )

                    Text(
                        text = productData.price,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        modifier = Modifier
                            .background(Color(0xFFFF9800), RoundedCornerShape(16.dp))
                            .padding(horizontal = 8.dp)
                    )
                }
                Text(
                    text = PreviewUtil.testDescription,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    }
}


@Preview
@Composable
private fun PreviewDetailsScreenContent() {
    TaskTheme {
        DetailsScreenContent(PreviewUtil.testProductData)
    }
}