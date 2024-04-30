package com.tictechtown.qrconnect.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.tictechtown.qrconnect.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailAppBar(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    onDeletePressed: () -> Unit,
    onPrintPressed: () -> Unit,
) {

    var expanded by remember { mutableStateOf(false) }

    TopAppBar(modifier = modifier, title = {
        Column(
            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {}
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
            onClick = { expanded = true },
        ) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "delete",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            DropdownMenuItem(text = { Text("Print") },
                onClick = { expanded = false; onPrintPressed() },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_print),
                        contentDescription = null
                    )
                }
            )

            DropdownMenuItem(text = { Text("Delete") },
                onClick = { expanded = false; onDeletePressed() },
                leadingIcon = {
                    Icon(
                        Icons.Outlined.Delete, contentDescription = null
                    )
                }
            )
        }

    })
}


