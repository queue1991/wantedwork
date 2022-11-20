package com.hsj.wantedwork.base.model.apiresp

data class ImageLinks(
    // 정상, 그외 오류메시지
    var smallThumbnail: String? = null,
    var thumbnail: String? = null

)