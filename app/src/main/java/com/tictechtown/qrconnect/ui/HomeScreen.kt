package com.tictechtown.qrconnect.ui

/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import android.app.Activity
import android.content.Context
import android.view.WindowManager
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lightspark.composeqr.DotShape
import com.lightspark.composeqr.QrCodeView
import com.tictechtown.qrconnect.R
import com.tictechtown.qrconnect.data.QRAccount
import com.tictechtown.qrconnect.ui.components.DetailAppBar
import com.tictechtown.qrconnect.ui.components.QRListItem
import com.tictechtown.qrconnect.ui.components.ReplyProfileImage
import com.tictechtown.qrconnect.ui.components.SearchBar
import com.tictechtown.qrconnect.ui.theme.typography

@Composable
fun InboxScreen(
    replyHomeUIState: HomeUIState,
    closeDetailScreen: () -> Unit,
    navigateToDetail: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {

    val emailLazyListState = rememberLazyListState()

    Box(modifier = modifier.fillMaxSize()) {
        ListContent(
            replyHomeUIState = replyHomeUIState,
            emailLazyListState = emailLazyListState,
            modifier = Modifier.fillMaxSize(),
            closeDetailScreen = closeDetailScreen,
            navigateToDetail = navigateToDetail
        )

        if (!replyHomeUIState.isDetailOnlyOpen) {
            FloatingActionButton(
                onClick = { /*Click Implementation*/ },
                containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                contentColor = MaterialTheme.colorScheme.primary,

                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = stringResource(id = R.string.edit),
                    modifier = Modifier.size(24.dp)
                )
            }
        }

    }
}


@Composable
fun ListContent(
    replyHomeUIState: HomeUIState,
    emailLazyListState: LazyListState,
    modifier: Modifier = Modifier,
    closeDetailScreen: () -> Unit,
    navigateToDetail: (Long) -> Unit,
) {
    if (replyHomeUIState.selectedEmail != null && replyHomeUIState.isDetailOnlyOpen) {
        BackHandler {
            closeDetailScreen()
        }
        QRAccountDetail(account = replyHomeUIState.selectedEmail) {
            closeDetailScreen()
        }
    } else {
        QRAccountList(
            emails = replyHomeUIState.emails,
            emailLazyListState = emailLazyListState,
            modifier = modifier,
            navigateToDetail = navigateToDetail
        )
    }
}

@Composable
fun QRAccountList(
    emails: List<QRAccount>,
    emailLazyListState: LazyListState,
    selectedEmail: QRAccount? = null,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        state = emailLazyListState,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 28.dp)
                    .height(152.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    "Social Accounts",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )

            }
        }
        items(items = emails, key = { it.id }) { email ->
            QRListItem(
                account = email,
                isSelected = email.id == selectedEmail?.id
            ) { emailId ->
                navigateToDetail(emailId)
            }
        }
    }
}


@Composable
fun UpdateBrightness() {
    val context = LocalContext.current
    DisposableEffect(Unit) {
        setBrightness(context, isFull = true)
        onDispose {
            setBrightness(context, isFull = false)
        }
    }
}

fun setBrightness(context: Context, isFull: Boolean) {
    val activity = context as? Activity ?: return
    val layoutParams: WindowManager.LayoutParams = activity.window.attributes
    layoutParams.screenBrightness =
        if (isFull) 1.0f else WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_NONE
    activity.window.attributes = layoutParams
}


@Composable
fun QRAccountDetail(
    account: QRAccount,
    isFullScreen: Boolean = true,
    modifier: Modifier = Modifier.fillMaxSize(),
    onBackPressed: () -> Unit = {},
) {
    UpdateBrightness()


    Column(
        modifier = modifier
            .padding(top = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DetailAppBar(account, isFullScreen) {
            onBackPressed()
        }


        Card(
            modifier = Modifier.padding(24.dp),
            colors = CardDefaults.cardColors(
                containerColor =
                MaterialTheme.colorScheme.primaryContainer
            )
        ) {

            Column(
                modifier = Modifier.padding(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ReplyProfileImage(
                        drawableResource = account.websiteLogo,
                        description = "image",
                        size = 30.dp
                    )
                    Text(
                        account.website,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                HorizontalDivider(color = MaterialTheme.colorScheme.inversePrimary)
                Text(
                    account.accountName,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                Color.White,
                                shape = RoundedCornerShape(24.dp)
                            )
                            .padding(16.dp)
                    ) {
                        QrCodeView(
                            data = account.link,
                            dotShape = DotShape.Square,
                            modifier = Modifier
                                .size(300.dp)
                        )
                    }
                    Text(
                        account.link,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )

                }

            }


        }

        Text("Added on ${account.createdAt}", style = MaterialTheme.typography.labelSmall)


    }
}
