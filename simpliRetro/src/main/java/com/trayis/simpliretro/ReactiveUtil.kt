package com.trayis.simpliretro

import io.reactivex.BackpressureStrategy
import io.reactivex.Completable
import io.reactivex.CompletableOnSubscribe
import io.reactivex.Flowable
import io.reactivex.FlowableOnSubscribe
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Single
import io.reactivex.SingleOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object ReactiveUtil {

    fun <T> prepareObservable(subscribe: ObservableOnSubscribe<T>): Observable<T> {
        return Observable.create(subscribe).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    fun <T> prepareObservable(observable: Observable<T>): Observable<T> {
        return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    fun <T> prepareSingle(subscribe: SingleOnSubscribe<T>): Single<T> {
        return Single.create(subscribe).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    fun <T> prepareSingle(single: Single<T>): Single<T> {
        return single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    fun prepareCompletable(subscribe: CompletableOnSubscribe): Completable {
        return Completable.create(subscribe).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    fun prepareCompletable(completable: Completable): Completable {
        return completable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    fun <T> prepareFlowable(flowable: Flowable<T>): Flowable<T> {
        return flowable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    fun <T> prepareFlowable(subscribe: FlowableOnSubscribe<T>): Flowable<T> {
        return prepareFlowable(subscribe, BackpressureStrategy.BUFFER)
    }

    fun <T> prepareFlowable(subscribe: FlowableOnSubscribe<T>, strategy: BackpressureStrategy): Flowable<T> {
        return Flowable.create(subscribe, strategy).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
}
