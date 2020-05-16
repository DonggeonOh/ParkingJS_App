package com.example.parkingjs.ui;

import androidx.lifecycle.ViewModel;

import com.example.parkingjs.extension.databinding.AutoDisposableOwner;
import com.example.parkingjs.extension.databinding.AutoDisposeException;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.subjects.CompletableSubject;

public class BaseViewModel extends ViewModel implements AutoDisposableOwner {

    @NonNull
    private CompletableSubject autoDisposeSignal = CompletableSubject.create();

    @Override
    protected void onCleared() {
        autoDisposeSignal.onComplete();
        super.onCleared();
    }

    public Single<Object> autoDisposeSignalSingle() {
        return autoDisposeSignal.andThen(Single.error(new AutoDisposeException()));
    }

    public Maybe<Object> autoDisposeSignalMaybe() {
        return autoDisposeSignal.andThen(Maybe.error(new AutoDisposeException()));
    }

    public Observable<Object> autoDisposeSignalObservable() {
        return autoDisposeSignal.andThen(Observable.error(new AutoDisposeException()));
    }

    public Completable autoDisposeSignalCompletable() {
        return autoDisposeSignal.andThen(Completable.error(new AutoDisposeException()));
    }
}
