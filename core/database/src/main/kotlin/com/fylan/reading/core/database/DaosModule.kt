package com.fylan.reading.core.database

import com.fylan.reading.core.database.dao.BookDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * @author Create by f.
 * @date 2023/12/29
 * Describe: 向外部提供各表dao对象
 */
@Module
@InstallIn(SingletonComponent::class)
object DaosModule {

    @Provides
    fun providesBookDao(database: ReadingDatabase): BookDao {
        return database.bookDao()
    }


}