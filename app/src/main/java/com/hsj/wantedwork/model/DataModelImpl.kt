package com.hsj.wantedwork.model

import com.google.gson.JsonObject
import com.hsj.wantedwork.model.service.RetrofitService
import io.reactivex.rxjava3.core.Single

class DataModelImpl(private val service: RetrofitService):DataModel{
    override fun getBookList(
        suffix: String
    ): Single<JsonObject> {
        return service.callRetrofitServiceWithSuffix(suffix)
    }
}