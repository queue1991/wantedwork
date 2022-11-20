package com.hsj.wantedwork.base.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.hsj.wantedwork.base.viewmodel.BaseKotlinViewModel


/**
 * ViewDataBinding, BaseKotlinViewModel 를 유형매개변수로 받는 abstract class
 * 1. inflate View
 * 2. databinding 준비
 */
abstract class BaseKotlinActivity<R : BaseKotlinViewModel> : AppCompatActivity() {
    /**
     * setContentView로 호출할 Layout의 리소스 Id.
     */
    abstract val layoutResourceId: Int

    /**
     * viewModel 로 쓰일 변수.
     */
    abstract val viewModel: R

    /**
     * fragment를 담을 view
     */
    abstract val fragmentPlace: Int


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResourceId)

        initStartView()
        initAfterStartingView()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    /**
     * 레이아웃을 띄운 직후 호출.
     * 뷰나 액티비티의 속성 등을 초기화.
     * ex) 리사이클러뷰, 툴바, 드로어뷰..
     */
    abstract fun initStartView()

    /**
     * 바인딩 이후에 할 일을 여기에 구현.
     * 그 외에 설정할 것이 있으면 이곳에서 설정.
     * 클릭 리스너도 이곳에서 설정.
     */
    abstract fun initAfterStartingView()

    fun replaceFragment(fragment : Fragment){
        val transaction = supportFragmentManager.beginTransaction()

        transaction.replace(fragmentPlace, fragment)
        transaction.addToBackStack(null)

        transaction.commit()
    }

    override fun onBackPressed() {

    }

}