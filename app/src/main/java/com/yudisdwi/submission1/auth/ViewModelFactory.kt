package com.yudisdwi.submission1.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yudisdwi.submission1.auth.login.LoginViewModel
import com.yudisdwi.submission1.auth.main.ui.addstory.AddstoryViewModel
import com.yudisdwi.submission1.auth.main.ui.home.HomeViewModel
import com.yudisdwi.submission1.auth.main.ui.profile.ProfileViewModel
import com.yudisdwi.submission1.auth.signup.SignupViewModel
import com.yudisdwi.submission1.model.UserPreference

class ViewModelFactory(private val pref: UserPreference) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(pref) as T
            }
            modelClass.isAssignableFrom(SignupViewModel::class.java) -> {
                SignupViewModel(pref) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(pref) as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                ProfileViewModel(pref) as T
            }

            modelClass.isAssignableFrom(AddstoryViewModel::class.java) -> {
                AddstoryViewModel(pref) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}