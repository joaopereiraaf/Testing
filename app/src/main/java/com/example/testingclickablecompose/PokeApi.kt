package com.example.testingclickablecompose

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface PokeApi {

    @GET("api/json/v1/1/list.php?c=list")
    fun getType() : Single<Something>

//    @GET("pokemon-form/1/")
//    fun getPokemon() : Single<Pokemon>
}