package com.hsj.wantedwork.fragment

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.hsj.wantedwork.R
import com.hsj.wantedwork.base.view.BaseKotlinFragment
import com.hsj.wantedwork.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_book_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookDetailFragment : BaseKotlinFragment<DetailViewModel>(){
    override val layoutResourceId: Int
        get() = R.layout.fragment_book_detail
    override val viewModel: DetailViewModel by viewModel()

    override val fragmentPlace: Int
        get() = R.id.container

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        webview.settings.loadWithOverviewMode = true
        webview.settings.setSupportZoom(true)
        webview.settings.builtInZoomControls = true
        webview.settings.useWideViewPort = true

        webview.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                if(progress_horizontal != null)
                    progress_horizontal.progress = newProgress
            }
        }

        webview.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                if(progress_horizontal != null)
                    progress_horizontal.visibility = VISIBLE
            }
            override fun onPageFinished(view: WebView?, url: String?) {
                if(progress_horizontal != null)
                    progress_horizontal.visibility = GONE
            }
        }

        webview.loadUrl(viewModel.getPreviewLink())
        super.onViewCreated(view, savedInstanceState)
    }
}