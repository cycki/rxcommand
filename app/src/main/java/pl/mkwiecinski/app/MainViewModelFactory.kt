package pl.mkwiecinski.app

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import pl.mkwiecinski.app.viewmodels.MainViewModel
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(private val mainViewModel: MainViewModel) :
        ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return mainViewModel as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


