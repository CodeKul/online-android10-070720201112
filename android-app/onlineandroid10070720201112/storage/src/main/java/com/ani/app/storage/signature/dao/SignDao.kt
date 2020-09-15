package com.ani.app.storage.signature.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ani.app.storage.signature.domain.Sign

@Dao
interface SignDao {

    @Insert
    fun saveSignature( sign : Sign)

    @Query("select * from sign_user")
    fun listAllSigns() : List<Sign>
}