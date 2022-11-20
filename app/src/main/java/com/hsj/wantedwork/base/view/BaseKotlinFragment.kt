package com.hsj.wantedwork.base.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hsj.wantedwork.base.viewmodel.BaseKotlinViewModel

abstract class BaseKotlinFragment<R : BaseKotlinViewModel> :  Fragment(){

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


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutResourceId, container, false)
    }

    fun replaceFragment(fragment : Fragment){
        val transaction = parentFragmentManager.beginTransaction()

        transaction.replace(fragmentPlace, fragment)
        transaction.addToBackStack(null)

        transaction.commit()
    }

}