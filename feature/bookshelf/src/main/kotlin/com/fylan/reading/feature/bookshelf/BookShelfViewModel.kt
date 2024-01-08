package com.fylan.reading.feature.bookshelf

import androidx.lifecycle.ViewModel
import com.fylan.reading.core.common.network.Dispatcher
import com.fylan.reading.core.common.network.ReadingDispatchers
import com.fylan.reading.core.data.BookRepository
import com.fylan.reading.core.database.model.BookEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author Create by f.
 * @date 2024/1/8
 * Describe:
 */
@HiltViewModel
class BookShelfViewModel @Inject constructor(
    private val bookRepository: BookRepository,
    @Dispatcher(ReadingDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    fun getBooks(): Flow<List<BookEntity>> {
        return bookRepository.getBooks()
    }

}