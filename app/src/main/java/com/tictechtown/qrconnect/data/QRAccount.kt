package com.tictechtown.qrconnect.data

import android.net.Uri
import androidx.annotation.DrawableRes
import com.tictechtown.qrconnect.R
import com.tictechtown.qrconnect.room.Account
import java.time.Instant

data class QRAccount(
    val id: Long,
    val website: String,
    val accountName: String,
    val link: String,
    val createdAt: Instant,
    @DrawableRes val websiteLogo: Int,
) {
    companion object {
        fun fromDB(account: Account): QRAccount {

            val website = getWebsiteFromLink(account.link)
            val logo = getWebsiteLogo(website)

            return QRAccount(
                id = account.id,
                website = website,
                accountName = account.name,
                link = account.link,
                createdAt = Instant.parse(account.createdAt),
                websiteLogo = logo
            )
        }
    }
}


fun getWebsiteLogo(host: String): Int {

    if (host.contains("linkedin.com")) {
        return R.drawable.linkedin
    }
    if (host.contains("wellfound.com")) {
        return R.drawable.wellfound
    }
    if (host.contains("github.com")) {
        return R.drawable.github
    }
    if (host.contains("twitter.com") || host.contains("x.com")) {
        return R.drawable.twitter
    }
    if (host.contains("venmo")) {
        return R.drawable.venmo
    }
    if (host.contains("WIFI")) {
        return R.drawable.wifi
    }

    return R.drawable.unknown
}

fun getWebsiteFromLink(link: String): String {
    if (link.startsWith("http")) {
        val uri = Uri.parse(link)
        return uri.host ?: link
    }
    if (link.startsWith("WIFI")) {
        return "WIFI"
    }
    if (link.startsWith("BEGIN:VCARD")) {
        return "VCard"
    }
    return "VCard"
}