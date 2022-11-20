package com.hsj.wantedwork.model.apiresp

data class BookInfo(
    // 정상, 그외 오류메시지
    var title: String? = null,
    var authors:ArrayList<String>? = null,
    var imageLinks:ImageLinks? = null,
    var previewLink:String? = null,
)