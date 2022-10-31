package com.yudisdwi.submission1.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.yudisdwi.submission1.auth.main.ApiConfig
import com.yudisdwi.submission1.auth.main.ApiService
import com.yudisdwi.submission1.auth.main.LoginResponse
import com.yudisdwi.submission1.auth.main.LoginResult
import com.yudisdwi.submission1.model.UserModel
import com.yudisdwi.submission1.model.UserPreference
import kotlinx.coroutines.launch

class LoginViewModel(private val pref: UserPreference) : ViewModel() {

    fun getUser(): LiveData<UserModel> {
        return pref.getUser().asLiveData()
    }

    fun saveUser(user: UserModel) {
        viewModelScope.launch {
            pref.saveUser(user)
        }
    }

    fun saveState(email: String, password: String) {
        ApiConfig().getApiService().loginAccount(email, password)
    }


    fun login(user: UserModel) {
        viewModelScope.launch {
            pref.login(user)
        }
    }
}