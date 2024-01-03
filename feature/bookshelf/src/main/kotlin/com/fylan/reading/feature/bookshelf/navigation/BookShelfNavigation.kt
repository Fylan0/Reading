package com.fylan.reading.feature.bookshelf.navigation

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

const val bookShelfNavDestinationRoute = "bookshelf_route"

fun NavController.navigateToBookShelf(navOptions: NavOptions? = null) {
    this.navigate(bookShelfNavDestinationRoute, navOptions)
}


fun NavGraphBuilder.bookShelfScreen(
    onTopicClick: (String) -> Unit,
) {
    composable(route = bookShelfNavDestinationRoute) {
        HomePage()
    }
}

@Composable
fun HomePage() {
    Text(text = "书架")
}

