package com.ani.app.webservices

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {

    private val _btn : MutableLiveData<Int> = MutableLiveData()
    val btn : LiveData<Int> = _btn

    private val _email : MutableLiveData<String> = MutableLiveData()
    val email : LiveData<String> = _email

    private val _firstName : MutableLiveData<String> = MutableLiveData()
    val firstName : LiveData<String> = _firstName

    private val _lastName : MutableLiveData<String> = MutableLiveData()
    val lastName : LiveData<String> = _lastName

    private val _avatar : MutableLiveData<String> = MutableLiveData()
    val avatar : LiveData<String> = _avatar

    fun okay() {
        _btn.value = _btn.value?.plus(1)
    }

    fun setData(domain : Domain) {
        _email.value = domain.data.email
        _firstName.value = domain.data.first_name
        _lastName.value = domain.data.last_name
        _avatar.value = domain.data.avatar
    }
}