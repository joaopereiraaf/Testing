package com.example.testingclickablecompose

import com.example.testingclickablecompose.model.Meals
import com.example.testingclickablecompose.model.Something
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface PokeApi {

    @GET("api/json/v1/1/list.php?c=list")
    fun getCategory() : Single<Something>

    @GET("api/json/v1/1/list.php?a=list")
    fun getCountry() : Single<Meals>
}