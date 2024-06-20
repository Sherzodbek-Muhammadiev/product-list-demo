package uz.gita.task.ui.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.size.Size
import uz.gita.task.R
import uz.gita.task.data.model.ProductData
import uz.gita.task.util.PreviewUtil
import uz.gita.task.util.theme.TaskTheme

@Composable
fun ProductItem(
    productData: ProductData,
    onClick: (ProductData) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .height(260.dp),
        elevation = CardDefaults.cardElevation(1.5.dp),
        colors = CardDefaults.outlinedCardColors(Color(0xFFFCFAFA))
    ) {
        Column(modifier = Modifier.clickable { onClick(productData) }, horizontalAlignment = Alignment.CenterHorizontally) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(productData.imageUrl)
                    .crossfade(true)
                    .size(Size.ORIGINAL)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .build(),
                contentDescription = null,
                placeholder = painterResource(R.drawable.placeholder),
                error = painterResource(R.drawable.no_network),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentScale = ContentScale.Crop
            )
            Row(
                modifier = Modifier
                    .background(Color(0xFFFCFAFA))
                    .padding(horizontal = 8.dp, vertical = 16.dp)
            ) {
                Text(
                    text = productData.name,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Text(
                    text = productData.price,
                    color = Color.Gray,
                )
            }

            Text(
                text = "Add",
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .clickable(interactionSource = remember { MutableInteractionSource() }, indication = rememberRipple(bounded = false), onClick={})
                    .background(Color(0xFFFF9800), shape = RoundedCornerShape(16.dp)).padding(horizontal = 8.dp, vertical = 2.dp),
            color = Color.White,
            fontSize = 12.sp
            )
        }
    }
}

@Composable
@Preview
fun PreviewProductItem() {
    TaskTheme {
        Column {
            Row {
                Box(modifier = Modifier.weight(1f)) {
                    ProductItem(PreviewUtil.testProductData, {})
                }
                Box(modifier = Modifier.weight(1f)) {
                    ProductItem(PreviewUtil.testProductData, {})
                }
            }
            Row {
                Box(modifier = Modifier.weight(1f)) {
                    ProductItem(PreviewUtil.testProductData, {})
                }
                Box(modifier = Modifier.weight(1f)) {
                    ProductItem(PreviewUtil.testProductData, {})
                }
            }
        }
    }
}