package com.fylan.reading.core.data

import com.fylan.reading.core.database.dao.BookChapterDao
import com.fylan.reading.core.database.dao.BookDao
import com.fylan.reading.core.database.model.BookChapterEntity
import com.fylan.reading.core.database.model.BookEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author Create by f.
 * @date 2024/1/4
 * Describe:
 */
class DefaultBookRepository @Inject constructor(
    private val bookDao: BookDao,
    private val bookChapterDao: BookChapterDao,
) : BookRepository {
    override fun insertBook(vararg books: BookEntity) {
        bookDao.insertAll(*books)
    }

    override fun insertBookChapter(bookChapters: List<BookChapterEntity>) {
        bookChapterDao.insertAll(bookChapters)
    }

    override fun getBooks(): Flow<List<BookEntity>> {
        return bookDao.getAll()
    }

    override fun getBookChapters(bookId: String): List<BookChapterEntity> {
        return bookChapterDao.getAll()
    }

}