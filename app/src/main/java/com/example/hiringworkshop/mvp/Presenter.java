package com.example.hiringworkshop.mvp;


import java.lang.ref.WeakReference;
import java.util.Observer;

import androidx.annotation.Nullable;

public abstract class Presenter<E extends PresenterToViewCallback> implements Observer {

    WeakReference<E> callBackRef;

    protected void onCreate() {
        registerWithModel();
    }

    public void registerCallback(WeakReference<E> callBackRef) {
        this.callBackRef = callBackRef;
    }

    @Nullable
    protected E getCallback() {
        return callBackRef.get();
    }

    protected abstract void registerWithModel();

    protected void onDestroy() {
        unregisterModels();
        this.callBackRef = null;
    }

    protected abstract void unregisterModels();
}