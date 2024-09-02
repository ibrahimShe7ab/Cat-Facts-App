package com.shehab.applestore.RetrofitInstance

import com.shehab.applestore.Models.CatFact
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("/fact")
   suspend fun GetRoudomFact():Response<CatFact>


}