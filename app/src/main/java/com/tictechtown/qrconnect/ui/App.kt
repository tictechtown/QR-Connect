package com.tictechtown.qrconnect.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun App(
    replyHomeUIState: HomeUIState,
    closeDetailScreen: () -> Unit = {},
    navigateToDetail: (Long) -> Unit = {},
) {
    Surface {
        AppContent(
            replyHomeUIState = replyHomeUIState,
            closeDetailScreen = closeDetailScreen,
            navigateToDetail = navigateToDetail
        )
    }
}


@Composable
fun AppContent(
    modifier: Modifier = Modifier,
    replyHomeUIState: HomeUIState,
    closeDetailScreen: () -> Unit,
    navigateToDetail: (Long) -> Unit,
) {

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {

        InboxScreen(
            replyHomeUIState = replyHomeUIState,
            closeDetailScreen = closeDetailScreen,
            navigateToDetail = navigateToDetail,
            modifier = Modifier.weight(1f)
        )
    }
}
