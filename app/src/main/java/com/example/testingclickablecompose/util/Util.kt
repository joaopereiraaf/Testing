package com.example.testingclickablecompose.util

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.MutableLiveData
import com.example.testingclickablecompose.DataModule
import com.example.testingclickablecompose.model.Meals
import com.example.testingclickablecompose.model.Something
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.internal.jdk8.FlowableCollectWithCollector
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

private val disposable = CompositeDisposable()
private val repoLoadError = MutableLiveData<Boolean>()
private val loading = MutableLiveData<Boolean>()
private val repositoryResponse: MutableList<Something> = mutableListOf()
private val repositoryResponseTwo: MutableList<Meals> = mutableListOf()

var someList = mutableListOf<Meals>()

fun types() {

    val data = DataModule.providePokeApi().getCountry()

    data.subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            { types ->
                repositoryResponseTwo.add(types)
                println(repositoryResponseTwo)
                println("Country")

                someList = saveData(types)
            },
            {
                println("DIND'T WORK")
            }
        )
        .addTo(disposable)

    val dataTwo = DataModule.providePokeApi().getCategory()

    dataTwo.subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            { types ->
                repositoryResponse.add(types)
                println(repositoryResponse)
                println("Category")
            },
            {
                println("DIND'T WORK")
            }
        )
        .addTo(disposable)
}

fun saveData(meals: Meals) : MutableList<Meals> {
    val mealsList: MutableList<Meals> = mutableListOf()
    mealsList.add(meals)
    return mealsList
}

@Composable
fun Show() {

    Column() {
        Row() {
            Text(text = someList.toString())
        }
        Row() {
            Text(text = "Something")
        }
    }
}