package com.yudisdwi.submission1.auth.main

import androidx.lifecycle.*
import com.yudisdwi.submission1.model.UserModel
import com.yudisdwi.submission1.model.UserPreference
import kotlinx.coroutines.launch

class MainViewModel(private val pref: UserPreference) : ViewModel() {
    fun getUser(): LiveData<UserModel> {
        return pref.getUser().asLiveData()
    }

    fun logout() {
        viewModelScope.launch {
            pref.logout()
        }
    }

}