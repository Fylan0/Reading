package com.fylan.reading.feature.read

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fylan.reading.core.database.model.BookChapterEntity
import kotlin.math.min

/**
 * @author Create by f.
 * @date 2024/1/8
 * Describe:阅读页面
 */
@Composable
fun ReadPage(
    viewModel: ReadViewModel = hiltViewModel(),
) {
    var bookChapter by remember { mutableStateOf<List<BookChapterEntity>>(emptyList()) }
    LaunchedEffect(viewModel) {
//        viewModel.viewModelScope.launch {
        val bookChapter1 = viewModel.getBookChapter()

        bookChapter1.forEach{
            // TODO:  item字数过多有异常，
            it.chapterContent=it.chapterContent?.take(100)
        }
        bookChapter = bookChapter1
//        }
    }
    NovelReaderPage(bookChapter = bookChapter)
}

@Composable
fun NovelReaderPage(bookChapter: List<BookChapterEntity>) {
    val fontSize: TextUnit = 26.sp
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
//                .pointerInput(Unit) {
//                    detectTransformGestures { _, pan, _, _ ->
//                        val deltaX = pan.x
//                        if (deltaX > 0) {
//                            // 向右滑动，翻到上一页
//                            onPageChange(currentPage - 1)
//                        } else {
//                            // 向左滑动，翻到下一页
//                            onPageChange(currentPage + 1)
//                        }
//                    }
//                }
    ) {
        // 使用LazyColumn显示小说文本
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(bookChapter.size) { index ->
                Text(
                    text = bookChapter[index].chapterContent ?: "",
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = fontSize,
                    lineHeight = 36.sp, // Set the line height to 24sp
                    letterSpacing = 1.sp // Set the letter spacing to 0.1sp
                )
            }
        }
    }
}