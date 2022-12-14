package com.hsj.wantedwork.model

import com.google.gson.JsonObject
import io.reactivex.rxjava3.core.Single


interface DataModel {
    fun getBookList(suffix : String): Single<JsonObject>
}