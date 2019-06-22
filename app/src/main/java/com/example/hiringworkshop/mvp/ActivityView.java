package com.example.hiringworkshop.mvp;

import android.os.Bundle;

import java.lang.ref.WeakReference;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class ActivityView<T extends Presenter, E extends PresenterToViewCallback> extends AppCompatActivity implements View<T, E> {

    private T presenter;

    private String viewKey = String.valueOf(System.identityHashCode(this));

    private String viewKeyStr = "view_key";

    private String createKeyStr = "create_key";


    protected boolean isCreating = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            viewKey = savedInstanceState.getString(viewKeyStr);
            if (viewKey == null) {
                viewKey = String.valueOf(System.identityHashCode(this));
            }
            if (savedInstanceState.containsKey(createKeyStr)) {
                isCreating = savedInstanceState.getBoolean(createKeyStr);
            } else {
                isCreating = true;
            }
        }

        initializePresenter();
    }

    public void initializePresenter() {
        //TODO check type.
        presenter = (T) PresenterProvider.getInstance().attachPresenter(viewKey, new FactoryCallback() {
            @Override
            public Presenter generatePresenter() {
                return createPresenter();
            }
        });
        presenter.registerCallback(new WeakReference(getCallback()));
        presenter.onCreate();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (outState != null) {
            outState.putString(viewKeyStr, viewKey);
            outState.putBoolean(createKeyStr, false);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (isFinishing()) {
            presenter.onDestroy();
            PresenterProvider.getInstance().remove(viewKey);
        }
    }

    @Override
    public T getPresenter() {
        return presenter;
    }

    protected abstract E getCallback();

    protected abstract T createPresenter();

}
