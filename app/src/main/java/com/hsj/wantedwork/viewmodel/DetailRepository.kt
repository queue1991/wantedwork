package com.hsj.wantedwork.viewmodel

import android.content.Context
import com.google.gson.JsonObject
import com.hsj.wantedwork.model.DataModel
import com.hsj.wantedwork.base.repository.BaseRepository
import io.reactivex.rxjava3.core.Single

class DetailRepository(private val model : DataModel, private val context : Context) : BaseRepository(model,context) {
    fun getBookList(suffix : String) : Single<JsonObject> {
        return model.getBookList(suffix)
    }
}