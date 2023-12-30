package com.fylan.reading.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fylan.reading.core.database.dao.BookDao
import com.fylan.reading.core.database.model.BookEntity

/**
 * @author Create by f.
 * @date 2023/12/28
 * Describe:主库
 */
@Database(
    version = 1,
    entities = [
        BookEntity::class
    ],
    autoMigrations = [

    ],
    exportSchema = true,
)
abstract class ReadingDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}