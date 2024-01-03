package com.fylan.reading.core.database.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

/**
 * @author Create by f.
 * @date 2023/12/28
 * Describe: 章节表
 */
data class BookChapter(
    @PrimaryKey
    @ColumnInfo(name = "book_id")
    val bookId: String,
    //章节名称
    @ColumnInfo(name = "chapter_name")
    var chapterName: String?,
    //第几章
    @ColumnInfo(name = "chapter_number")
    var chapterNumber: Int?,
    //章节内容
    @ColumnInfo(name = "chapter_content")
    var chapterContent: String? = "",
    //在书籍文件中的起始位置
    val start: Long = 0,
    //在书籍文件中的终止位置
    val end: Long = 0,
)