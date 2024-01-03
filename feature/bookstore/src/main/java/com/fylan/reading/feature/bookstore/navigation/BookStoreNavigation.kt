package com.fylan.reading.feature.bookstore.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

/**
 * @author Create by f.
 * @date 2023/12/27
 * Describe:
 */

const val bookStoreNavDestinationRoute = "bookstore_route"

fun NavController.navigateToBookStore(navOptions: NavOptions? = null) {
    this.navigate(bookStoreNavDestinationRoute, navOptions)
}

fun NavGraphBuilder.bookStoreScreen(
    onTopicClick: (String) -> Unit,
) {
    composable(route = bookStoreNavDestinationRoute) {
        HomePage()
    }
}

@Composable
fun HomePage() {
    Text(text = "书店")
}

