package com.example.weiyue

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.weiyue.component.ApplicationComponent
import com.example.weiyue.ui.base.BaseActivity
import com.example.weiyue.ui.base.BaseContract
import com.example.weiyue.utils.ImageLoaderUtil
import com.example.weiyue.widget.SimpleMultiStateView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import kotlinx.android.synthetic.main.activity_welcome.*
import pl.droidsonroids.gif.GifDrawable
import java.util.concurrent.TimeUnit

class WelcomeActivity : BaseActivity<BaseContract.BasePresenter>() {


    //必应每日壁纸 来源于 https://www.dujin.org/fenxiang/jiaocheng/3618.html.
    private val picUrl = "http://api.dujin.org/bing/1920.php"

    private var mCompositeDisposable: CompositeDisposable? = CompositeDisposable()

    override fun getContentLayout(): Int = R.layout.activity_welcome

    override fun getSimpleMultiStateView(): SimpleMultiStateView? = null

    override fun initInjector(appComponent: ApplicationComponent) {
    }

    override fun initData() {
    }

    override fun bindView(view: View, savedInstanceState: Bundle?) {
        val gifDrawable = gifImageView.drawable as GifDrawable
        gifDrawable.loopCount = 1
        gifImageView.postDelayed({ gifDrawable.start() }, 100)

        ImageLoaderUtil.LoadImage(this, picUrl, iv_ad)
        mCompositeDisposable?.add(countDown(3)
                .doOnSubscribe({ tv_skip.text = "跳过 4" })
                .subscribeWith(object : DisposableObserver<Int>() {
                    override fun onNext(t: Int) {
                        tv_skip.text = "跳过${t + 1}"
                    }

                    override fun onError(e: Throwable) {
                    }

                    override fun onComplete() {
                        toMain()
                    }
                }))
        fl_ad.setOnClickListener { toMain() }
    }

    private fun countDown(time: Int): Observable<Int> {
        return Observable.interval(0, 1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .map { t -> time - t.toInt() }
                .take((time + 1).toLong())
    }

    private fun toMain() {
        mCompositeDisposable?.dispose()
        with(Intent()) {
            setClass(this@WelcomeActivity, MainActivity::class.java)
            startActivity(this)
        }
        finish()
    }

    override fun onDestroy() {
        mCompositeDisposable?.dispose()
        super.onDestroy()
    }

}