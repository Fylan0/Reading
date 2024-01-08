package com.fylan.reading.feature.read.navigation

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.fylan.reading.feature.read.ReadPage
import java.net.URLDecoder
import java.net.URLEncoder

private val URL_CHARACTER_ENCODING = Charsets.UTF_8.name()

@VisibleForTesting
internal const val bookIdArg = "bookId"

internal class BookArgs(val bookId: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(
                URLDecoder.decode(
                    checkNotNull(savedStateHandle[bookIdArg]),
                    URL_CHARACTER_ENCODING
                )
            )
}

const val readNavDestinationRoute = "read_route"

fun NavController.navigateToRead(
    navOptions: NavOptions? = null,
    bookId: String,
) {
    val encodedId = URLEncoder.encode(bookId, URL_CHARACTER_ENCODING)
    this.navigate("topic_route/$encodedId", navOptions)
}

fun NavGraphBuilder.readScreen(
    onTopicClick: (String) -> Unit,
) {
    composable(
        route = "{$readNavDestinationRoute}/{$bookIdArg}",
        arguments = listOf(
            navArgument(bookIdArg) { type = NavType.StringType },
        ),
    ) {
        ReadPage()
    }
}

