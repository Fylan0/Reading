package com.fylan.reading.feature.read

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fylan.reading.core.database.model.BookChapterEntity

/**
 * @author Create by f.
 * @date 2023/12/30
 * Describe:
 *
 * 1、获取本地文件
 * 2、专成章节的数据结构
 * 3、左右滑动的view
 *  根据
 * 4、
 */
class ReadingActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                reading()
            }
        }
    }

    @Composable
    private fun reading() {
//        val chapter =
//            LocalPageLoaderUtil.loadChapters(LocalContext.current.assetToFile("剑来.txt")!!)

//        NovelReaderPage(chapter)
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
}
