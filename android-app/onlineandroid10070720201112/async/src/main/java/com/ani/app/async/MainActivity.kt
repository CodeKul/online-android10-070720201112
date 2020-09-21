package com.ani.app.async

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener {

            counterObservable()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { txt.text = "$it" }
                .doOnError { Log.i("@ani", "${it.message}") }
                .doOnComplete { Log.i("@ani", "Completed") }
                .subscribe()

            /*CoroutineScope(Dispatchers.IO).launch {
                // heavy ops -> can be written here
                for (i in 0..1000) {
                    delay(1000)
                    updateUi(i)
                }
            }*/
        }
    }

    /*private suspend fun updateUi(i : Int) {
        // update the UI
        CoroutineScope(Dispatchers.Main).launch {
            txt.text = "$i"
        }
    }*/

    private fun counterObservable() : Observable<Int> {
        return  Observable.create { em : ObservableEmitter<Int> ->
            for (i in 0..1000) {
                try {
                    Thread.sleep(1000)
                    em.onNext(i) // partial completion
                }catch (e : Exception) {
                    em.onError(e) // error result
                }
            }
            em.onComplete() // completion
        }
    }
}
