package pl.mkwiecinski.app.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.mkwiecinski.app.MainActivity

@Module abstract class GlobalComponentInjectors {
    @ActivityScope @ContributesAndroidInjector abstract fun main(): MainActivity
}