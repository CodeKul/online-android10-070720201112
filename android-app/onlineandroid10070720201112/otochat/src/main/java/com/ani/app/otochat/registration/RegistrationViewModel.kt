package com.ani.app.otochat.registration

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegistrationViewModel : ViewModel() {

    private val _reg : MutableLiveData<Int> = MutableLiveData()

    val reg : LiveData<Int> = _reg

    val userName : MutableLiveData<String> = MutableLiveData()
    val password : MutableLiveData<String> = MutableLiveData()

    fun onReg() {
        Log.i("@ani", "Vm Clicked")
        _reg.value?.plus(1)
    }
}