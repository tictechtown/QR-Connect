package com.tictechtown.qrconnect.ui

import android.app.Activity
import android.content.Context
import android.view.WindowManager
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.lightspark.composeqr.DotShape
import com.lightspark.composeqr.QrCodeView
import com.tictechtown.qrconnect.data.QRAccount
import com.tictechtown.qrconnect.ui.components.DetailAppBar
import com.tictechtown.qrconnect.ui.components.ProfileImage
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle


@Composable
fun DetailScreen(
    account: QRAccount,
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit = {},
    onDeletePressed: () -> Unit = {},
) {
    BackHandler {
        onBackPressed()
    }

    UpdateBrightness()

    QRAccountDetail(
        account = account,
        modifier = modifier.fillMaxSize(),
        onBackPressed = onBackPressed,
        onDeletePressed = onDeletePressed
    )
}

@Composable
fun QRAccountDetail(
    account: QRAccount,
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit = {},
    onDeletePressed: () -> Unit = {},
) {

    Column(
        modifier = modifier.padding(top = 8.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DetailAppBar(
            onBackPressed = onBackPressed,
            onDeletePressed = onDeletePressed,
            onPrintPressed = {
                /* TODO */
            })


        Card(
            modifier = Modifier.padding(24.dp),
            shape = MaterialTheme.shapes.large,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
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
                    ProfileImage(
                        drawableResource = account.websiteLogo, description = "image", size = 30.dp
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
                                Color.White, shape = RoundedCornerShape(24.dp)
                            )
                            .padding(16.dp)
                    ) {
                        QrCodeView(
                            data = account.link,
                            dotShape = DotShape.Square,
                            modifier = Modifier.size(300.dp)
                        )
                    }
                    if (account.link.startsWith("https")) {
                        Text(
                            account.link,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }

                }

            }


        }


        Text(
            "Added on ${
                DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withZone(ZoneId.of("UTC"))
                    .format(account.createdAt)
            }",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )


    }
}

// utils
@Composable
private fun UpdateBrightness() {
    val context = LocalContext.current
    DisposableEffect(Unit) {
        setBrightness(context, isFull = true)
        onDispose {
            setBrightness(context, isFull = false)
        }
    }
}

private fun setBrightness(context: Context, isFull: Boolean) {
    val activity = context as? Activity ?: return
    val layoutParams: WindowManager.LayoutParams = activity.window.attributes
    layoutParams.screenBrightness =
        if (isFull) 1.0f else WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_NONE
    activity.window.attributes = layoutParams
}
