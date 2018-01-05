package pl.mkwiecinski.app

import android.arch.lifecycle.ViewModel
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection

abstract class BaseActivity<TBinding : ViewDataBinding, TViewModel : ViewModel> : AppCompatActivity() {
    protected lateinit var viewModel: TViewModel
    protected lateinit var binding: TBinding

    protected abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        initViewModel(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        initView(savedInstanceState)
    }

    abstract fun initViewModel(savedInstanceState: Bundle?)

    abstract fun initView(savedInstanceState: Bundle?)
}
