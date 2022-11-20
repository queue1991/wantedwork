package com.hsj.wantedwork.base.model.service

import com.google.gson.JsonObject
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*


interface RetrofitService {
    /**
     * call Retrofit Service With Suffix
     */
    @GET
    fun callRetrofitServiceWithSuffix(@Url url : String): Single<JsonObject>
}