package com.dankook.parkingjs.extension.databinding;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public interface AutoDisposableOwner {

    Single<Object> autoDisposeSignalSingle();

    Maybe<Object> autoDisposeSignalMaybe();

    Observable<Object> autoDisposeSignalObservable();

    Completable autoDisposeSignalCompletable();
}
