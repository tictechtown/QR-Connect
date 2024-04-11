package com.tictechtown.qrconnect.ui.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tictechtown.qrconnect.data.QRAccount

@Composable
fun QRListItem(
    account: QRAccount,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
) {
    Card(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .clickable { navigateToDetail(account.id) },
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            MaterialTheme.colorScheme.surfaceContainerHigh
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ProfileImage(
                drawableResource = account.websiteLogo,
                description = account.accountName,
            )
            Column(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = account.website,
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    text = account.accountName,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
