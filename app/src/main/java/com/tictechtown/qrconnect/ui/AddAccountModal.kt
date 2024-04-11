package com.tictechtown.qrconnect.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import com.tictechtown.qrconnect.ui.theme.AppTheme

@Composable
fun AddAccountModal(onAdd: (account: String, link: String) -> Unit) {
    var accountText by remember { mutableStateOf("") }
    var linkText by remember { mutableStateOf("") }


    val saveEnabled by remember { derivedStateOf { accountText.isNotEmpty() && linkText.isNotEmpty() } }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(vertical = 24.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Add Account", style = MaterialTheme.typography.titleLarge)
            Button(onClick = { onAdd(accountText, linkText) }, enabled = saveEnabled) {
                Text("Add")
            }

        }
        OutlinedTextField(
            value = accountText,
            onValueChange = { accountText = it },
            label = { Text("Account") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = linkText,
            onValueChange = { linkText = it },
            label = { Text("Link") },
            modifier = Modifier.fillMaxWidth()
        )

    }
}


@Preview(
    device = "id:pixel_6a", showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    wallpaper = Wallpapers.RED_DOMINATED_EXAMPLE
)
@Preview(
    device = "id:pixel_6a", showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    wallpaper = Wallpapers.RED_DOMINATED_EXAMPLE
)
@Composable
fun AddAccountModalPreview() {
    AppTheme {
        Surface {
            AddAccountModal(onAdd = { accountText, linkText -> })
        }
    }
}