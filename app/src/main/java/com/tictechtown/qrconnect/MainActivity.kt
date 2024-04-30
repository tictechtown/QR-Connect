package com.tictechtown.qrconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import com.tictechtown.qrconnect.ui.App
import com.tictechtown.qrconnect.ui.HomeViewModel
import com.tictechtown.qrconnect.ui.theme.AppTheme
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tictechtown.qrconnect.room.DatabaseProvider

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val database = DatabaseProvider(this).getInstance()
        viewModel = HomeViewModel(database)


        setContent {
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            AppTheme {
                Surface() {
                    App(homeUIState = uiState, addNewAccount = { account, link ->
                        viewModel.addNewAccount(account, link)
                    }, deleteAccount = { viewModel.deleteAccount(it) })
                }
            }
        }
    }
}
