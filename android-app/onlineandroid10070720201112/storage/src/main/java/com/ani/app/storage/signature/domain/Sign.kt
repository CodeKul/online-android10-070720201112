package com.ani.app.storage.signature.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sign_user")
data class Sign(
    @PrimaryKey
    val id : Long,
    val userName : String,
    val datetime : Long,
    val signFilePath: String
)