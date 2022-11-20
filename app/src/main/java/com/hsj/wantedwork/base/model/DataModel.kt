package com.hsj.wantedwork.base.model

import com.google.gson.JsonObject
import io.reactivex.rxjava3.core.Single
import okhttp3.RequestBody


interface DataModel {
    fun getBookList(suffix : String): Single<JsonObject>
}