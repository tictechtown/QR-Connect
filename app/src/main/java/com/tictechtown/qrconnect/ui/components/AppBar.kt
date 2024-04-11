package com.tictechtown.qrconnect.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailAppBar(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
) {
    TopAppBar(modifier = modifier, title = {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
        }
    }, navigationIcon = {
        IconButton(
            onClick = onBackPressed,

            ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "back",
            )
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


