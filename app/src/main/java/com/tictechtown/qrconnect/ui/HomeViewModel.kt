package com.tictechtown.qrconnect.ui

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.tictechtown.qrconnect.R
import com.tictechtown.qrconnect.data.LocalQRAccountsDataProvider
import com.tictechtown.qrconnect.data.QRAccount
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.Instant

class HomeViewModel : ViewModel() {

    // UI state exposed to the UI
    private val _uiState = MutableStateFlow(HomeUIState(loading = true))
    val uiState: StateFlow<HomeUIState> = _uiState

    init {
        initList()
    }


    private fun initList() {
        _uiState.value = HomeUIState(
            accounts = LocalQRAccountsDataProvider.allAccounts,
        )
    }

    private fun extractWebsiteFromLink(link: String): String {
        val uri = Uri.parse(link)
        return uri.host ?: link
    }

    fun addNewAccount(account: String, link: String) {

        _uiState.value = HomeUIState(
            accounts = _uiState.value.accounts.plus(
                QRAccount(
                    id = _uiState.value.accounts.count().toLong(),
                    website = extractWebsiteFromLink(link),
                    accountName = account,
                    link = link,
                    createdAt = Instant.now().toString(),
                    websiteLogo = R.drawable.unknown
                )
            )
        )
    }
}

data class HomeUIState(
    val accounts: List<QRAccount> = emptyList(),
    val loading: Boolean = false,
)
