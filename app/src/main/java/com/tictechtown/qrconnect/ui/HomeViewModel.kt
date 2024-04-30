package com.tictechtown.qrconnect.ui

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tictechtown.qrconnect.R
import com.tictechtown.qrconnect.data.LocalQRAccountsDataProvider
import com.tictechtown.qrconnect.data.QRAccount
import com.tictechtown.qrconnect.room.Account
import com.tictechtown.qrconnect.room.AppDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.launch
import java.time.Instant

class HomeViewModel(private val database: AppDatabase) : ViewModel() {


    // UI state exposed to the UI
    private val _uiState = MutableStateFlow(HomeUIState(loading = true))
    val uiState: StateFlow<HomeUIState> = _uiState


    init {
        initList()
    }


    private fun initList() {

        viewModelScope.launch {
            database.accountDao().getAllAccounts().collect() { accountList ->
                _uiState.value = HomeUIState(
                    accounts = LocalQRAccountsDataProvider.allAccounts + accountList.map {
                        QRAccount.fromDB(
                            it
                        )
                    },
                )
            }
        }
    }

    fun addNewAccount(account: String, link: String) {
        viewModelScope.launch {
            database.accountDao().insertAccount(
                Account(
                    id = 0,
                    name = account,
                    link = link,
                    createdAt = Instant.now().toString(),
                )
            )
        }
    }

    fun deleteAccount(accountId: Long) {
        viewModelScope.launch {
            val account = database.accountDao().findAccount(accountId)
            database.accountDao().deleteAccount(account)
        }
    }
}

data class HomeUIState(
    val accounts: List<QRAccount> = emptyList(),
    val loading: Boolean = false,
)
