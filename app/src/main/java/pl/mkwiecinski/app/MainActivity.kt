package pl.mkwiecinski.app

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.Menu
import android.view.MenuItem
import pl.mkwiecinski.app.databinding.ActivityMainBinding
import pl.mkwiecinski.app.viewmodels.MainViewModel
import javax.inject.Inject


class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    @Inject lateinit var viewModelFactory: MainViewModelFactory
    override val layoutId = R.layout.activity_main

    override fun initViewModel(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.incrementCommand.success.subscribe {
            Snackbar.make(binding.root, "Incremented", Snackbar.LENGTH_LONG).setAction("Action",
                                                                                       null).show()
        }
        viewModel.incrementCommand.error.subscribe {
            Snackbar.make(binding.root, "Error", Snackbar.LENGTH_LONG).setAction("Action",
                                                                                 null).show()
        }
    }

    override fun initView(savedInstanceState: Bundle?) {
        binding.model = viewModel
        setSupportActionBar(binding.toolbar)
        binding.fab.setOnClickListener {
            viewModel.incrementCommand.execute(Unit)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
