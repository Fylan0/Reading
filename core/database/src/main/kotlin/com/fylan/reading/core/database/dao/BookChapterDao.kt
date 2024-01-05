package com.fylan.reading.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.fylan.reading.core.database.model.BookChapterEntity
import kotlinx.coroutines.flow.Flow

/**
 * @author Create by f.
 * @date 2023/12/28
 * Describe: 书章节dao
 */

@Dao
interface BookChapterDao {

    //基础语句
    @Insert
    fun insertAll(books: List<BookChapterEntity>)

    @Delete
    fun delete(books: BookChapterEntity)

    @Query("SELECT * FROM tb_book_chapter")
    fun getAll(): Flow<List<BookChapterEntity>>
}