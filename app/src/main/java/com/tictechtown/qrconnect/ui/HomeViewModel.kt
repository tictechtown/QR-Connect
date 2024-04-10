package com.tictechtown.qrconnect.ui

import androidx.lifecycle.ViewModel
import com.tictechtown.qrconnect.data.LocalQRAccountsDataProvider
import com.tictechtown.qrconnect.data.QRAccount
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel : ViewModel() {

    // UI state exposed to the UI
    private val _uiState = MutableStateFlow(HomeUIState(loading = true))
    val uiState: StateFlow<HomeUIState> = _uiState

    init {
        initEmailList()
    }

    private fun initEmailList() {
        val emails = LocalQRAccountsDataProvider.allEmails
        _uiState.value = HomeUIState(
            emails = emails,
            selectedEmail = emails.first()
        )
    }

    fun setSelectedEmail(emailId: Long) {
        /**
         * We only set isDetailOnlyOpen to true when it's only single pane layout
         */
        val email = uiState.value.emails.find { it.id == emailId }
        _uiState.value = _uiState.value.copy(
            selectedEmail = email,
            isDetailOnlyOpen = true
        )
    }

    fun closeDetailScreen() {
        _uiState.value = _uiState
            .value.copy(
                isDetailOnlyOpen = false,
                selectedEmail = _uiState.value.emails.first()
            )
    }
}

data class HomeUIState(
    val emails: List<QRAccount> = emptyList(),
    val selectedEmail: QRAccount? = null,
    val isDetailOnlyOpen: Boolean = false,
    val loading: Boolean = false,
    val error: String? = null,
)
