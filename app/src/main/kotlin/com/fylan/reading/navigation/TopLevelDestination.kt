package com.fylan.reading.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.fylan.reading.core.designsystem.icon.ReadingIcons
import com.fylan.reading.feature.bookshelf.R as bookshelfR
import com.fylan.reading.feature.bookstore.R as bookstoreR

/**
 * @author Create by f.
 * @date 2023/12/20
 * Describe:
 */
enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int,
    val titleTextId: Int,
) {
    /**
     * 书架
     */
    BOOKSHELF(
        selectedIcon = ReadingIcons.Upcoming,
        unselectedIcon = ReadingIcons.UpcomingBorder,
        iconTextId = bookstoreR.string.bookstore,
        titleTextId = bookstoreR.string.bookstore,
    ),

    /**
     * 书店
     */
    BOOKSTORE(
        selectedIcon = ReadingIcons.Upcoming,
        unselectedIcon = ReadingIcons.UpcomingBorder,
        iconTextId = bookshelfR.string.bookshelf,
        titleTextId = bookshelfR.string.bookshelf,
    ),
}