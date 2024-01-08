package com.fylan.reading.core.data

import com.fylan.reading.core.database.model.BookChapterEntity
import com.fylan.reading.core.database.model.BookEntity
import kotlinx.coroutines.flow.Flow

/**
 * @author Create by f.
 * @date 2024/1/4
 * Describe:
 */
interface BookRepository {

    fun insertBook(vararg books: BookEntity)

    fun insertBookChapter(bookChapters: List<BookChapterEntity>)

    fun getBooks(): Flow<List<BookEntity>>

    fun getBookChapters(bookId: String): List<BookChapterEntity>

}