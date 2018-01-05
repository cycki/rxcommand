package pl.mkwiecinski.app.di;

import javax.inject.Scope;

import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.Retention;

@Retention(AnnotationRetention.RUNTIME)
@Scope
public @interface ActivityScope {
}
