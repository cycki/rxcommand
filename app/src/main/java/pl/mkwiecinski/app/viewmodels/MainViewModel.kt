package pl.mkwiecinski.app.viewmodels

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableInt
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pl.mkwiecinski.app.di.ActivityScope
import pl.mkwiecinski.rxcommand.RxCommand
import javax.inject.Inject

@ActivityScope class MainViewModel @Inject constructor() : ViewModel() {
    val selected = ObservableInt()

    val incrementCommand = RxCommand(this::increment)

    private fun increment(param: Unit): Single<Unit> {
        return Single.create<Int> {
            Thread.sleep(2000)
            if (selected.value > 10) {
                it.onError(IllegalArgumentException())
            } else {
                it.onSuccess(selected.value + 1)
            }
        }.subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).doOnSuccess {
            selected.value = selected.value + 1
        }.map {}
    }

    override fun onCleared() {
        incrementCommand.dispose()
        super.onCleared()
    }
}

var ObservableInt.value: Int
    get() = get()
    set(value) = set(value);
