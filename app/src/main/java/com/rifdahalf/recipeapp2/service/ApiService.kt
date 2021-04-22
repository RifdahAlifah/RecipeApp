package com.rifdahalf.recipeapp2.service

import android.media.audiofx.AudioEffect
import com.rifdahalf.recipeapp2.model.Category
import com.rifdahalf.recipeapp2.model.ResponseRecipe
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("categories.php")
    fun getRecipesData (
        @Query ("categories") category: String?,
        @Query ("strCategory") strCategory: String?,
        @Query ("strCategoryDescription") strCategoryDescriptor: String?,
        @Query ("idCategory") idCategory: String?
    ): Call<ResponseRecipe>

}