package com.fylan.reading.feature.settings.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.fylan.reading.feature.settings.FilePickPage

/**
 * @author Create by f.
 * @date 2024/1/4
 * Describe:
 */

const val filePickNavDestinationRoute = "file_pick_route"

fun NavController.navigateToFilePick(navOptions: NavOptions? = null) {
    this.navigate(filePickNavDestinationRoute, navOptions)
}

fun NavGraphBuilder.filePickScreen(
    onClick: (String) -> Unit,
) {
    composable(route = filePickNavDestinationRoute) {
        FilePickPage(onClick = onClick)
    }
}
