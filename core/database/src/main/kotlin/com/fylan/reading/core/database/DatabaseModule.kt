package com.fylan.reading.core.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author Create by f.
 * @date 2023/12/28
 * Describe: 提供数据库对象方法（使用Hilt）
 *
 * ！！！hilt
 * Hilt 是 Android 的依赖项注入库，可减少在项目中执行手动依赖项注入的样板代码。
 * 执行手动依赖项注入要求您手动构造每个类及其依赖项，并借助容器重复使用和管理依赖项。
 * Hilt 通过为项目中的每个 Android 类提供容器并自动管理其生命周期，提供了一种在应用中使用 DI（依赖项注入）的标准方法。
 * Hilt 在热门 DI 库 Dagger 的基础上构建而成，因而能够受益于 Dagger 的编译时正确性、运行时性能、可伸缩性和 Android Studio 支持。
 * 如需了解详情，请参阅 Hilt 和 Dagger。
 *
 * @Module 注解：
 * 在 Dagger 和 Hilt 中，@Module 注解用于标记一个类，该类负责提供依赖项（dependencies）的创建和提供。
 * 在 @Module 注解的类中，可以定义一些方法（通常以 @Provides 注解标记），这些方法负责提供特定类型的依赖项。
 * 通过使用 @Module 注解，开发者可以告诉 Dagger/Hilt 如何创建和提供某些依赖项的实例。

 * @InstallIn 注解：
 * @InstallIn 注解用于指定 Dagger/Hilt 中的某个组件，告诉 Hilt 在哪个生命周期组件中安装（install）相关的模块。
 * 在上述例子中，@InstallIn(SingletonComponent::class) 意味着 MyModule 将会被安装在应用的单例组件中。
 * 这意味着 MyModule 中提供的依赖项将在应用的整个生命周期内存在，并且将是单例的。
 *
 * 总的来说，@Module 用于定义提供依赖项的模块，而 @InstallIn 用于指定该模块应该安装在哪个 Dagger 组件中，从而确定依赖项的生命周期。
 * 这些注解的结合使得 Dagger/Hilt 能够生成和管理依赖项的实例，并确保它们在适当的生命周期内被创建和管理。
 */

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesReadingDatabase(
        @ApplicationContext context: Context,
    ): ReadingDatabase {
        return Room.databaseBuilder(
            context,
            ReadingDatabase::class.java,
            "reading_database",
        ).build()
    }
}