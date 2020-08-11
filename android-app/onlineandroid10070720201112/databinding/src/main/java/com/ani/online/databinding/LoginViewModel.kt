package com.ani.online.databinding

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    var eml : LiveData<String> = MutableLiveData()
    var phn : LiveData<String> = MutableLiveData()

    fun onLogin() {
        Log.i("@ani", "Email ${eml.value} Phone ${phn.value}")
    }
}