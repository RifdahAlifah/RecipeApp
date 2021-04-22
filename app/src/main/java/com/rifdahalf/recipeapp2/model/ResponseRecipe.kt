package com.rifdahalf.recipeapp2.model


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class ResponseRecipe(
    @SerializedName("categories")
    var categories: List<Category>?
) : Parcelable