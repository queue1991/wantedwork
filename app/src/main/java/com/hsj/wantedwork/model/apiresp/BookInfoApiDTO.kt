package com.hsj.wantedwork.model.apiresp

data class BookInfoApiDTO(
    // 정상, 그외 오류메시지
    var totalItems:Int = 0,
    var items:ArrayList<Book>? = null
)