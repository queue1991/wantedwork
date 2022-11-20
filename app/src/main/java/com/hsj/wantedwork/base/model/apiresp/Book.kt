package com.hsj.wantedwork.base.model.apiresp

data class Book(
    // 정상, 그외 오류메시지
    var volumeInfo: BookInfo? = null
)