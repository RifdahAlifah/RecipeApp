package com.rifdahalf.recipeapp2.model


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class Category(
    @SerializedName("idCategory")
    var idCategory: String?, // 1
    @SerializedName("strCategory")
    var strCategory: String?, // Beef
    @SerializedName("strCategoryDescription")
    var strCategoryDescription: String?, // Beef is the culinary name for meat from cattle, particularly skeletal muscle. Humans have been eating beef since prehistoric times.[1] Beef is a source of high-quality protein and essential nutrients.[2]
    @SerializedName("strCategoryThumb")
    var strCategoryThumb: String? // https://www.themealdb.com/images/category/beef.png
) : Parcelable