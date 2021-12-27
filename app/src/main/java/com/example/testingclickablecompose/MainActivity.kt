package com.example.testingclickablecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : ComponentActivity() {

    private val disposable = CompositeDisposable()
    private val repoLoadError = MutableLiveData<Boolean>()
    private val loading = MutableLiveData<Boolean>()
    private val repositoryResponse: MutableList<List<Something>> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        types()

    }

    private fun types() {

        val data = DataModule.providePokeApi().getType()
        println("here")
        println(data)

        data.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { types ->
                    println(types)
                    repositoryResponse.add(listOf(types))
                    println(repositoryResponse)
                    println("WORKED")
                },
                {
                    println("DIND'T WORK")
                }
            )
            .addTo(disposable)
    }
}

