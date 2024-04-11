package com.tictechtown.qrconnect.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ProfileImage(
    drawableResource: Int,
    description: String,
    modifier: Modifier = Modifier,
    size: Dp = 60.dp,
) {

    Image(
        modifier = modifier
            .size(size)
            .background(color = Color.White, shape = RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp)),
        painter = painterResource(id = drawableResource),
        contentDescription = description,
    )
}
