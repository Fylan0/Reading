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
    val bookId: String?,
    @ColumnInfo(name = "chapter_name")
    var chapterName: String?,
    @ColumnInfo(name = "chapter_number")
    var chapterNumber: Int?,
    @ColumnInfo(name = "chapter_content")
    var bookContent: String? = "",
)