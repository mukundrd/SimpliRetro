package com.trayis.simpliretro;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Flowable;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ReactiveUtil {

    public static <T> Observable<T> prepareObservable(ObservableOnSubscribe<T> subscribe) {
        return Observable.create(subscribe).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> Observable<T> prepareObservable(Observable<T> observable) {
        return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> Single<T> prepareSingle(SingleOnSubscribe<T> subscribe) {
        return Single.create(subscribe).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> Single<T> prepareSingle(Single<T> single) {
        return single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public static Completable prepareCompletable(CompletableOnSubscribe subscribe) {
        return Completable.create(subscribe).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public static Completable prepareCompletable(Completable completable) {
        return completable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> Flowable<T> prepareFlowable(Flowable<T> flowable) {
        return flowable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> Flowable<T> prepareFlowable(FlowableOnSubscribe<T> subscribe) {
        return prepareFlowable(subscribe, BackpressureStrategy.BUFFER);
    }

    public static <T> Flowable<T> prepareFlowable(FlowableOnSubscribe<T> subscribe, BackpressureStrategy strategy) {
        return Flowable.create(subscribe, strategy).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
