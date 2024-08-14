package com.aman.foodium

import retrofit2.Response
import retrofit2.http.GET

/**
 * Service to fetch Foodium posts using dummy end point [FOODIUM_API_URL].
 */
interface FoodiumService {

    @GET("/DummyFoodiumApi/api/posts/")
    suspend fun getPosts(): Response<List<Post>>

    companion object {
        const val FOODIUM_API_URL = "https://patilshreyas.github.io/"
    }
}