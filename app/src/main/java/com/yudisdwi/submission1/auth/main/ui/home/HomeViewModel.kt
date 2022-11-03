package com.yudisdwi.submission1.auth.main.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.yudisdwi.submission1.auth.main.ApiConfig
import com.yudisdwi.submission1.auth.main.StoryResponse
import com.yudisdwi.submission1.model.StoryModel
import com.yudisdwi.submission1.model.UserModel
import com.yudisdwi.submission1.model.UserPreference
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(private val pref: UserPreference) : ViewModel() {

    val storyList = MutableLiveData<List<StoryModel>>()

    fun getUser(): LiveData<UserModel> {
        return pref.getUser().asLiveData()
    }

    fun listStory(token: String) {
        val story = ApiConfig().getApiService().getStorylist(token, 30)
        story.enqueue(object : Callback<StoryResponse> {
            override fun onResponse(
                call: Call<StoryResponse>,
                response: Response<StoryResponse>
            ) {
                if (response.isSuccessful) {
                    storyList.postValue(response.body()?.listStory)
                } else {

                }
            }

            override fun onFailure(call: Call<StoryResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }


        })
    }


}