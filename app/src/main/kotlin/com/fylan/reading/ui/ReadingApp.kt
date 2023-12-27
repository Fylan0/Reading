package com.fylan.reading.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import com.fylan.reading.feature.bookshelf.navigation.bookShelfScreen
import com.fylan.reading.feature.bookstore.navigation.bookStoreNavDestinationRoute
import com.fylan.reading.feature.bookstore.navigation.bookStoreScreen
import com.fylan.reading.navigation.TopLevelDestination

/**
 * @author Create by f.
 * @date 2023/12/20
 * Describe:
 */

/**
 *
 *
 * @param appState ReadingAppState
 */
@Composable
fun ReadingApp(
    windowSizeClass: WindowSizeClass,
    appState: ReadingAppState = rememberReadingAppState(
        windowSizeClass = windowSizeClass,
    ),
) {
    Scaffold(
        modifier = Modifier.defaultMinSize(),
        content = { padding ->
            Row(
                Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .consumeWindowInsets(
                        WindowInsets.safeDrawing.only(
                            WindowInsetsSides.Horizontal
                        )
                    )
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    //topBar可以在这块加

                    //主内容
                    NavHost(
                        navController = appState.navController,
                        startDestination = bookStoreNavDestinationRoute,
                        modifier = Modifier,
                        builder = {
                            bookShelfScreen { }
                            bookStoreScreen { }
                        }
                    )
                }
            }
        },
        bottomBar = {
            ReadingBottom(
                modifier = Modifier.testTag("ReadingBottomBar"),
                destinations = appState.topLevelDestination,
                currentDestination = appState.currentTopLevelDestination,
//                onNavigateTopLevelDestination = { topLevelDestination ->
//                    appState.navigateToTopLevelDestination(topLevelDestination)
//                },
                //在 Kotlin 中，:: 表示函数引用（Function Reference）操作符。
                // 它用于引用函数而不调用它，允许你像对待普通值一样处理函数。上面简化后到下面这种写法（不易理解所以保留上面代码）
                onNavigateTopLevelDestination = appState::navigateToTopLevelDestination,
            )
        }
    )
}


/**
 * 首页底部导航栏
 * @param destinations List<TopLevelDestination> BottomBar列表
 * @param modifier Modifier
 * @param currentDestination NavDestination? 当前选中的item
 * @param onNavigateTopLevelDestination Function1<TopLevelDestination, Unit> 点击事件回调
 *
 */
@Composable
fun ReadingBottom(
    modifier: Modifier = Modifier,
    destinations: List<TopLevelDestination>,
    currentDestination: NavDestination?,
    onNavigateTopLevelDestination: (TopLevelDestination) -> Unit,
) {
    NavigationBar(
        modifier = modifier,
//        contentColor = ,
//        tonalElevation = 0.dp,
        content = {
            destinations.forEach { destination ->
                //判断当前选中的item
                val selected = currentDestination?.hierarchy?.any {
                    it.route?.contains(destination.name, true) ?: false
                } ?: false
                //创建底部tab
                NavigationRailItem(
                    selected = selected,
                    onClick = {
                        onNavigateTopLevelDestination(destination)
                    },
                    icon = {
                        if (selected) {
                            Icon(
                                imageVector = destination.selectedIcon,
                                contentDescription = null
                            )
                        } else {
                            Icon(
                                imageVector = destination.unselectedIcon,
                                contentDescription = null
                            )
                        }
                    },
                    label = {
                        Text(text = stringResource(id = destination.titleTextId))
                    },
                    modifier = modifier,
                )

            }
        }
    )
}