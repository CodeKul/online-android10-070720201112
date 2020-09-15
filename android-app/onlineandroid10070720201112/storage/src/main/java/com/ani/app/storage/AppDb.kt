package com.ani.app.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ani.app.storage.signature.dao.SignDao
import com.ani.app.storage.signature.domain.Sign

@Database(version = 1, entities = [Sign::class])
abstract class AppDb : RoomDatabase() {
    abstract fun signDao() : SignDao
}