package com.hsj.wantedwork.common

object CommonValue {

    /**
     *  ------------------- API 호출 URL 관련 -----------------------
     */
    const val REAL_BASE_URL = "https://www.googleapis.com/"

    /**
     * google book path / query
     */
    const val BOOK_PATH = "books/v1/volumes?"
    const val BOOK_SEARCH_WORD_QUERY = "q=%s"
    const val BOOK_START_INDEX_QUERY = "&startIndex=%d"
    const val BOOK_MAX_RESULTS_QUERY = "&maxResults=%d"

    /**
     * max results
     */
    const val MAX_RESULTS = 10


}