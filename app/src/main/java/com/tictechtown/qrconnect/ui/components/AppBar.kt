package com.tictechtown.qrconnect.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tictechtown.qrconnect.data.QRAccount

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailAppBar(
    account: QRAccount,
    isFullScreen: Boolean,
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
) {
    TopAppBar(modifier = modifier, title = {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = if (isFullScreen) Alignment.CenterHorizontally
            else Alignment.Start
        ) {
            Text(
                text = account.website,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }, navigationIcon = {
        if (isFullScreen) {
            IconButton(
                onClick = onBackPressed,

                ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "back",
                )
            }
        }
    }, actions = {
        IconButton(
            onClick = { /*Click Implementation*/ },
        ) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "delete",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    })
}


