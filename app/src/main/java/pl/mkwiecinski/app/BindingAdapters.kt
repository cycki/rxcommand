package pl.mkwiecinski.app

import android.databinding.BindingAdapter
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import pl.mkwiecinski.rxcommand.CommandState
import pl.mkwiecinski.rxcommand.RxCommand

@BindingAdapter("android:onClick") fun <T> bindOnClick(view: Button, command: RxCommand<Unit, T>?) {
    view.setOnClickListener {
        command?.execute(Unit)
    }
}

@BindingAdapter("android:enabled") fun <T> bindEnabled(view: View, command: RxCommand<Unit, T>?) {
    command?.executing?.subscribe {
        view.isEnabled = it == CommandState.Finished
    }
}

@BindingAdapter("android:visibility") fun <T> bindVisibility(view: View,
                                                             command: RxCommand<Unit, T>?) {
    command?.executing?.subscribe {
        view.visibility = when (it) {
            CommandState.Finished -> View.VISIBLE
            else -> View.GONE
        }
    }
}

@BindingAdapter("android:visibility") fun <T> bindProgressbarVisibility(view: ProgressBar,
                                                             command: RxCommand<Unit, T>?) {
    command?.executing?.subscribe {
        view.visibility = when (it) {
            CommandState.Finished -> View.GONE
            else -> View.VISIBLE
        }
    }
}

