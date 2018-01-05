package pl.mkwiecinski.rxcommand

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

class RxCommand<in TParam, TResult>(private val action: (TParam) -> Single<TResult>) : Disposable {
    private val disposeBag = CompositeDisposable()

    private val successSubject = PublishSubject.create<TResult>()
    private val errorSubject = PublishSubject.create<Throwable>()
    private val executingSubject = BehaviorSubject.createDefault(CommandState.Finished)

    val success: Observable<TResult> = successSubject.hide()
    val error: Observable<Throwable> = errorSubject.hide()
    val executing: Observable<CommandState> = executingSubject.hide()

    fun execute(param: TParam) {
        executingSubject.onNext(CommandState.Running)
        val disposable = action(param).doFinally {
            executingSubject.onNext(CommandState.Finished)
        }.subscribe(successSubject::onNext, errorSubject::onNext)

        disposeBag.add(disposable)
    }

    override fun isDisposed() = disposeBag.isDisposed

    override fun dispose() {
        successSubject.onComplete()
        errorSubject.onComplete()
        executingSubject.onComplete()
        disposeBag.dispose()
    }

    fun stop() = disposeBag.clear()
}
