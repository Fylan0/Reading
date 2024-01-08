package com.fylan.reading.feature.bookshelf.navigation

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.fylan.reading.core.database.model.BookEntity
import com.fylan.reading.feature.bookshelf.BookShelfHomePage

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
        BookShelfHomePage(onTopicClick=onTopicClick)
    }
}


fun showToast(context: Context, s: String) {
    Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
}


