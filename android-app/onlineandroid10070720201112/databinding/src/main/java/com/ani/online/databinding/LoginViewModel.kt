package com.ani.online.databinding

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

//    ?private val _eml = MutableLiveData<String>("Android@gmail.com")
//    private val _phn = MutableLiveData<String>("6546546")
        private val _lgnSts = MutableLiveData<Boolean>(false)

//    val eml : LiveData<String> get() = _eml
//    val phn : LiveData<String> get() = _phn

    val eml : MutableLiveData<String> = MutableLiveData<String>("android@gmail.com")
    val phn : MutableLiveData<String> = MutableLiveData<String>("987979")
    val lgnSts : LiveData<Boolean> get() = _lgnSts

    fun onLogin() {
        this._lgnSts.value = (eml.value == "android") && (phn.value == "1234")

        Log.i("@ani", "Email ${eml.value} Phone ${phn.value}")
    }
}