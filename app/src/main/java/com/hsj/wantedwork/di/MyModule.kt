package com.hsj.wantedwork.di

import com.hsj.wantedwork.model.DataModel
import com.hsj.wantedwork.model.DataModelImpl
import com.hsj.wantedwork.common.CommonValue.REAL_BASE_URL
import com.hsj.wantedwork.model.service.RetrofitService
import com.hsj.wantedwork.viewmodel.DetailRepository
import com.hsj.wantedwork.viewmodel.DetailViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * single : 앱이 실행되는 동안 계속 유지되는 싱글톤 객체를 생성합니다.
 * factory : 요청할 때마다 매번 새로운 객체를 생성합니다.
 * get() : 컴포넌트 내에서 알맞은 의존성을 주입 받습니다.
 */
var retrofitPart = module {
    single<RetrofitService> {
        Retrofit.Builder().baseUrl(REAL_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(RetrofitService::class.java)
    }
}

var repositoryPart = module{

    single{
        DetailRepository(get(),androidContext())
    }
}

var modelPart = module {
    factory<DataModel> {
        DataModelImpl(get())
    }
}

var viewModelPart = module {
    single {
        DetailViewModel(get())
    }
}


var myDiModule = listOf(retrofitPart, modelPart, viewModelPart,repositoryPart)