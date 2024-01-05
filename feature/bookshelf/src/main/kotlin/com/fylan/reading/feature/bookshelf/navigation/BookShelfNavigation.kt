package com.fylan.reading.feature.bookshelf.navigation

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.fylan.reading.core.database.model.BookEntity
import com.fylan.reading.feature.bookshelf.HomePage
import com.fylan.reading.feature.bookshelf.R
import kotlin.random.Random

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


fun showToast(context: Context, s: String) {
    Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
}


