package com.fylan.reading.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

/**
 * @author Create by f.
 * @date 2023/12/28
 * Describe:书籍表
 */
@Entity(
    tableName = "tb_book"
)
data class BookEntity(
    @PrimaryKey
    @ColumnInfo(name = "book_id")
    val bookId: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "book_name")
    val bookName: String?,
    @ColumnInfo(name = "book_content")
    val bookContent: String? = "",
    @ColumnInfo(name = "book_author")
    val author: String? = "",
    @ColumnInfo(name = "book_shortIntro")
    val shortIntro: String? = "",
    @ColumnInfo(name = "book_cover")
    val cover: String? = "",
    //章节
) {
}