package com.hsj.wantedwork.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hsj.wantedwork.R
import com.hsj.wantedwork.adapter.BookListAdapter
import com.hsj.wantedwork.base.view.BaseKotlinFragment
import com.hsj.wantedwork.common.CommonValue.BOOK_MAX_RESULTS_QUERY
import com.hsj.wantedwork.common.CommonValue.BOOK_PATH
import com.hsj.wantedwork.common.CommonValue.BOOK_SEARCH_WORD_QUERY
import com.hsj.wantedwork.common.CommonValue.BOOK_START_INDEX_QUERY
import com.hsj.wantedwork.common.CommonValue.MAX_RESULTS
import com.hsj.wantedwork.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_book_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookListFragment : BaseKotlinFragment<DetailViewModel>(){
    override val layoutResourceId: Int
        get() = R.layout.fragment_book_list
    override val viewModel: DetailViewModel by viewModel()
    override val fragmentPlace: Int
        get() = R.id.container

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObserver()
        setListener()
    }

    private fun setObserver(){
        viewModel.bookList.observe(this.viewLifecycleOwner) {
            if(it.items != null) {
                tv_count.text = String.format(getString(R.string.book_results_cnt), it.totalItems)
                rv_booklist.adapter = BookListAdapter(this.requireContext(), it.items!!, object : BookListAdapter.ClickBookListener{
                    override fun onClicked(index: Int) {
                        viewModel.clickedBookNo = index
                        replaceFragment(BookDetailFragment())
                    }
                })
            }
        }

        viewModel.bookListMore.observe(this.viewLifecycleOwner) {
            if(it.items != null) {
                for(book in it.items!!) {
                    viewModel.bookList.value?.items?.add(book)
                }
                rv_booklist.adapter?.notifyItemRangeChanged(viewModel.getPositionStart(), MAX_RESULTS)

                viewModel.isLoadMore = true
            }
        }
    }

    private fun setListener(){
        rv_booklist.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    var visibleItemCount = recyclerView.layoutManager!!.childCount
                    var totalItemCount = recyclerView.layoutManager!!.itemCount
                    var pastVisiblesItems =
                        (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                    if (visibleItemCount + pastVisiblesItems >= totalItemCount && viewModel.isLoadMore) {
                        viewModel.getBookListMore()
                    }
                }
            }
        })

        btn_search.setOnClickListener {
            viewModel.searchedWord = edit_search.text.toString()

            viewModel.getBookList()

            edit_search.setText("")
        }
    }

}