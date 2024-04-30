package com.tictechtown.qrconnect.room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "account")
data class Account(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    val link: String,
    val createdAt: String,
)

