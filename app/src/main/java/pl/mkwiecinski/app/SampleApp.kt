package pl.mkwiecinski.app

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import pl.mkwiecinski.app.di.DaggerGlobalComponent

class SampleApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<SampleApp>? {
        return DaggerGlobalComponent.builder().create(this)
    }
}
