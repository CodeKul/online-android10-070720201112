package com.ani.app.storage

import android.app.Application
import androidx.room.Room

class StorageApp : Application() {
    val db : AppDb by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDb::class.java, "my-db"
        ).build()
    }
}