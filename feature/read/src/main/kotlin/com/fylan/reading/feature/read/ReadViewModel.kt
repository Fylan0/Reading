package com.fylan.reading.feature.read

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.fylan.reading.core.common.network.Dispatcher
import com.fylan.reading.core.common.network.ReadingDispatchers
import com.fylan.reading.core.data.BookRepository
import com.fylan.reading.core.database.model.BookChapterEntity
import com.fylan.reading.feature.read.navigation.BookArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * @author Create by f.
 * @date 2024/1/8
 * Describe:
 */
@HiltViewModel
class ReadViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val bookRepository: BookRepository,
    @Dispatcher(ReadingDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val bookArgs: BookArgs = BookArgs(savedStateHandle)

    suspend fun getBookChapter(): List<BookChapterEntity> {
        return withContext(ioDispatcher) {
            bookRepository.getBookChapters(bookArgs.bookId)
        }
    }
}