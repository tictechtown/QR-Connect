package com.tictechtown.qrconnect.data

import androidx.annotation.DrawableRes

data class QRAccount(
    val id: Long,
    val website: String,
    val accountName: String,
    val link: String,
    val createdAt: String,
    @DrawableRes val websiteLogo: Int,
)
