package com.fylan.reading.ui

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.util.trace
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.fylan.reading.feature.bookshelf.navigation.navigateToBookShelf
import com.fylan.reading.feature.bookstore.navigation.navigateToBookStore
import com.fylan.reading.navigation.TopLevelDestination
import kotlinx.coroutines.CoroutineScope

/**
 * @author Create by f.
 * @date 2023/12/27
 * Describe:
 */


/**
 * ！！！知识点
 * remember{}:
 * 在Jetpack Compose中，remember 是一个用于在 Composable 函数中创建和保留可观察状态的关键字。
 * 它与传统 Android 中的 ViewModel 或 savedInstanceState 类似，但是它是为 Compose 构建的，用于在 Compose 中管理状态。
 * remember 可以用于创建和保留不仅仅是普通的数据，还可以是可观察的数据，例如 MutableState 或 MutableList，
 * 这样当数据发生变化时，Compose 可以自动重新绘制相关的 UI。
 * ！！！
 *
 * @param windowSizeClass WindowSizeClass
 * @param coroutineScope CoroutineScope
 * @param navController NavHostController
 * @return ReadingAppState
 */
@Composable
fun rememberReadingAppState(
    windowSizeClass: WindowSizeClass,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): ReadingAppState {
    return remember(
        navController,
        coroutineScope,
        windowSizeClass,
    ) {
        ReadingAppState(
            navController,
        )
    }
}

/**
 *
 * ！！！@Stable注解含义：
 * Android Compose中的@Stable注解用于标记Compose中的函数或变量，以指示其在不同调用之间具有相同的返回值或引用。
 * 这个注解是为了帮助Compose进行性能优化，因为它允许Compose在重新计算和绘制UI时避免不必要的工作。
 * 具体来说，@Stable注解表示带有此注解的内容是稳定的，即在多次调用之间不会发生变化。
 * 这有助于Compose引擎在重绘UI时避免重新计算和绘制不变的部分，提高性能。
 * ！！！
 *
 */
@Stable
class ReadingAppState(
    val navController: NavHostController,
) {

    /**
     * 底部导航列表
     */
    val topLevelDestination: List<TopLevelDestination> = TopLevelDestination.values().asList()

    /**
     * 当前选中的底部tab
     */
    val currentDestination: NavDestination?
        @Composable get() =
            navController.currentBackStackEntryAsState().value?.destination

    /**
     * 底部导航蓝切换tab
     * @param topLevelDestination TopLevelDestination 点击的tab
     */
    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        //！！！trace（）
        // 在 Jetpack Compose 中，androidx.compose.ui.util.Trace 类提供了用于追踪代码执行时间的工具。
        // 这个工具使用了 Android 中的 android.os.Trace 类。
        //android.os.Trace 是 Android 提供的用于应用程序性能分析的工具，它允许你在代码中插入追踪标记
        // ，以测量代码块的执行时间。在 Compose 中，androidx.compose.ui.util.Trace 类通过对 android.os.Trace 进行包装，提供了更方便的 API。
        trace("Navigation: ${topLevelDestination.name}") {
            //下面才是逻辑代码，trace（）是Compose中提供的测试执行耗时的方法

            //底部导航配置项
            val topLevelNavOptions = navOptions {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }

            //根据点击的item，加载对应tab的页面
            when (topLevelDestination) {
                TopLevelDestination.BOOKSHELF -> {
                    navController.navigateToBookShelf(topLevelNavOptions)
                }

                TopLevelDestination.BOOKSTORE -> {
                    navController.navigateToBookStore(topLevelNavOptions)
                }
            }
        }
    }

}