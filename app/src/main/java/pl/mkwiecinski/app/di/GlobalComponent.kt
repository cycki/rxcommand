package pl.mkwiecinski.app.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import pl.mkwiecinski.app.SampleApp
import javax.inject.Singleton


@Singleton @Component(modules = [AndroidSupportInjectionModule::class, GlobalComponentInjectors::class]) interface GlobalComponent :
        AndroidInjector<SampleApp> {

    @Component.Builder abstract class Builder : AndroidInjector.Builder<SampleApp>()
}

