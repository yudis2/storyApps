package com.yudisdwi.submission1.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yudisdwi.submission1.model.UserModel
import com.yudisdwi.submission1.model.UserPreference
import kotlinx.coroutines.launch

class SignupViewModel(private val pref: UserPreference) : ViewModel() {
    fun saveUser(user: UserModel) {
        viewModelScope.launch {
            pref.saveUser(user)
        }
    }
}