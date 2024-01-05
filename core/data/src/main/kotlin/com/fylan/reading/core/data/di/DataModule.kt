package com.fylan.reading.core.data.di

import com.fylan.reading.core.data.BookRepository
import com.fylan.reading.core.data.DefaultBookRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * @author Create by f.
 * @date 2024/1/4
 * Describe:
 */
@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindBookRepository(
        bookRepository: DefaultBookRepository,
    ): BookRepository

}