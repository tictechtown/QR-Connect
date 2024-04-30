package com.tictechtown.qrconnect.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccount(account: Account)

    @Query("SELECT * FROM account")
    fun getAllAccounts(): Flow<List<Account>>

    @Query("SELECT * FROM account WHERE id = :accountId LIMIT 1")

    suspend fun findAccount(accountId: Long): Account


    @Delete
    suspend fun deleteAccount(account: Account)
}
