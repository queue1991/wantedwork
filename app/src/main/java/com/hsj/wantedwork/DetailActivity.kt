package com.hsj.wantedwork

import com.hsj.wantedwork.base.view.BaseKotlinActivity
import com.hsj.wantedwork.fragment.BookListFragment
import com.hsj.wantedwork.viewmodel.DetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailActivity : BaseKotlinActivity<DetailViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_main
    override val viewModel: DetailViewModel by viewModel()
    override val fragmentPlace: Int
        get() = R.id.container

    override fun initStartView() {

    }

    override fun initAfterStartingView() {
        replaceFragment(BookListFragment())
    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount > 1)
            supportFragmentManager.popBackStack()
        else
            finish()
    }
}