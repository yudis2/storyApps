package com.yudisdwi.submission1.auth.login

import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.yudisdwi.submission1.auth.main.ApiConfig
import com.yudisdwi.submission1.auth.main.LoginResponse
import com.yudisdwi.submission1.auth.main.LoginResult
import com.yudisdwi.submission1.model.UserModel
import com.yudisdwi.submission1.model.UserPreference
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class LoginViewModel(private val pref: UserPreference) : ViewModel() {
    private val _loginResponse = MutableLiveData<LoginResult?>()
    val loginResponse: LiveData<LoginResult?> = _loginResponse
    fun getUser(): LiveData<UserModel> {
        return pref.getUser().asLiveData()
    }

    fun saveUser(user: UserModel) {
        viewModelScope.launch {
            pref.saveUser(user)
        }
    }

    fun login() {
        viewModelScope.launch {
            pref.login()
        }
    }

    fun service(email: String, password: String) {
        val token = ApiConfig().getApiService().loginAccount(email, password)
        token.enqueue(object : retrofit2.Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null && !responseBody.error) {
                        _loginResponse.value = responseBody.loginResult
                    }
                } else {

                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }


        })
    }
}