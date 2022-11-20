package com.hsj.wantedwork.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.hsj.wantedwork.model.apiresp.BookInfoApiDTO
import com.hsj.wantedwork.base.viewmodel.BaseKotlinViewModel
import com.hsj.wantedwork.common.CommonValue
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class DetailViewModel(private val detailRepository : DetailRepository) : BaseKotlinViewModel(detailRepository) {

    private var _bookList = MutableLiveData<BookInfoApiDTO>()
    val bookList: LiveData<BookInfoApiDTO> get() = _bookList

    private var _bookListMore = MutableLiveData<BookInfoApiDTO>()
    val bookListMore: LiveData<BookInfoApiDTO> get() = _bookListMore

    var isLoadMore : Boolean = true
    var searchedWord : String? = null

    var clickedBookNo : Int = 0

    private fun setBookList(list : BookInfoApiDTO){
        _bookList.postValue(list)
    }

    private fun setBookListMore(list : BookInfoApiDTO){
        _bookListMore.postValue(list)
    }

    private fun setIsLoading(isLoading: Boolean){
        _isLoading.postValue(isLoading)
    }

    fun getPreviewLink() : String{
        return bookList.value?.items?.get(clickedBookNo)?.volumeInfo?.previewLink!!
    }

    fun getPositionStart() : Int{
        return bookList.value?.items?.size!! - CommonValue.MAX_RESULTS
    }

    fun getQuery(loadMore : Boolean) : String{
        var startIdx = 0
        if(loadMore){
            startIdx = bookList.value?.items?.size!!
        }

        var query = StringBuilder()
        query.append(String.format(CommonValue.BOOK_PATH))
        query.append(String.format(CommonValue.BOOK_SEARCH_WORD_QUERY, searchedWord))
        query.append(String.format(CommonValue.BOOK_START_INDEX_QUERY, startIdx))
        query.append(String.format(CommonValue.BOOK_MAX_RESULTS_QUERY, CommonValue.MAX_RESULTS))

        return query.toString()
    }


    /**
     * search button Click 시 getBookList
     */
    fun getBookList(){
        setIsLoading(true)

        addDisposable(detailRepository.getBookList(getQuery(false))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run{
                    val bookInfoApiDTO = Gson().fromJson(it.toString(), BookInfoApiDTO::class.java)
                    setBookList(bookInfoApiDTO)

                    setIsLoading(false)
                }
            },{

            })
        )
    }

    /**
     * search button Click 시 getBookList
     */
    fun getBookListMore(){
        setIsLoading(true)
        isLoadMore = false

        addDisposable(detailRepository.getBookList(getQuery(true))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run{
                    val bookInfoApiDTO = Gson().fromJson(it.toString(), BookInfoApiDTO::class.java)
                    setBookListMore(bookInfoApiDTO)

                    setIsLoading(false)

                }
            },{

            })
        )
    }
}