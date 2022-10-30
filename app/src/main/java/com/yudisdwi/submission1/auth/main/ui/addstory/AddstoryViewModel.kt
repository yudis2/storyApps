package com.yudisdwi.submission1.auth.main.ui.addstory

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.yudisdwi.submission1.model.UserModel
import com.yudisdwi.submission1.model.UserPreference

class AddstoryViewModel(private val pref: UserPreference) : ViewModel() {

    fun getUser(): LiveData<UserModel> {
        return pref.getUser().asLiveData()
    }
}