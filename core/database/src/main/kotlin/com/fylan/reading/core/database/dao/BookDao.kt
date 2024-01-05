package com.fylan.reading.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.fylan.reading.core.database.model.BookEntity
import kotlinx.coroutines.flow.Flow

/**
 * @author Create by f.
 * @date 2023/12/28
 * Describe: 书dao
 */

@Dao
interface BookDao {

    @Insert
    fun insertAll(vararg books: BookEntity)

    @Delete
    fun delete(books: BookEntity)

    @Query("SELECT * FROM tb_book")
    fun getAll(): Flow<List<BookEntity>>

}