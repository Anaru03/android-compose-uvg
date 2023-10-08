package com.zezzi.eventzezziapp.data.repository

import com.zezzi.eventzezziapp.data.networking.MealsWebService
import com.zezzi.eventzezziapp.data.networking.response.MealsCategoriesResponse
import com.zezzi.eventzezziapp.data.networking.response.MealsRecipesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealsRepository(private val webService: MealsWebService = MealsWebService()) {
    suspend fun getMeals(): MealsCategoriesResponse? {
        return suspendCancellableCoroutine { continuation ->
            webService.getMeals().enqueue(object : Callback<MealsCategoriesResponse> {
                override fun onResponse(
                    call: Call<MealsCategoriesResponse>,
                    response: Response<MealsCategoriesResponse>
                ) {
                    if (response.isSuccessful)
                        successCallback(response.body())
                }

                override fun onFailure(call: Call<MealsCategoriesResponse>, t: Throwable) {
                    // TODO treat failure
                }
            })
        }
    }
}