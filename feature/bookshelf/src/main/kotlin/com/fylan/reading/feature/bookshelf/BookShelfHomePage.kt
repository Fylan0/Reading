package com.fylan.reading.feature.bookshelf

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fylan.reading.core.database.model.BookEntity
import com.fylan.reading.feature.bookshelf.navigation.showToast
import kotlin.random.Random

/**
 * @author Create by f.
 * @date 2023/12/27
 * Describe:
 */
@Composable
fun BookShelfHomePage(
    viewModel: BookShelfViewModel = hiltViewModel(),
    onTopicClick: (String) -> Unit,
) {

    // TODO: 这块要查数据库获取书架列表
//    val itemList = listOf(
//        BookEntity(bookName = "斗破苍穹"),
//        BookEntity(bookName = "剑来"),
//        BookEntity(bookName = "凤凰传奇"),
//        BookEntity(bookName = "化身孤岛的蓝鲸"),
//        BookEntity(bookName = "英雄联盟"),
//        BookEntity(bookName = "哈迪斯"),
//        BookEntity(bookName = "饥荒"),
//        BookEntity(bookName = "残酷月光"),
//        BookEntity(bookName = "杭州滨江图书馆"),
//        BookEntity(bookName = "沈阳图书馆"),
//        // Add more items as needed
//    )

    val itemListState = viewModel.getBooks().collectAsState(initial = emptyList())
    val itemList = itemListState.value

    val context: Context = LocalContext.current
    LazyVerticalGrid(columns = GridCells.Fixed(3)) {
        items(itemList.size) { index ->
            BookShelfItemCard(itemList[index]) { item ->
                onTopicClick(item.bookId)
            }
        }
    }
}

@Composable
fun BookShelfItemCard(
    item: BookEntity,
    onItemClick: (BookEntity) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onItemClick(item) },
    ) {
        //测试代码
        val placeholderIdList = listOf(R.drawable.a123, R.drawable.a234, R.drawable.a345)
        val randomIndex = Random.nextInt(placeholderIdList.size)
        Image(
            painter = painterResource(id = placeholderIdList[randomIndex]),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(shape = RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Text(
            text = item.bookName ?: "",
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold
        )
    }
}