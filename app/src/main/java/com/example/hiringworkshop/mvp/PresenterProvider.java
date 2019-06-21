package com.example.hiringworkshop.mvp;

import java.util.HashMap;
import java.util.Map;

public class PresenterProvider {

    private static final PresenterProvider instance = new PresenterProvider();

    public static PresenterProvider getInstance() {
        return instance;
    }

    private Map<String, Presenter> viewPresenterMap = new HashMap<>();

    Presenter attachPresenter(String key, FactoryCallback factoryCallback) {
        Presenter presenter = viewPresenterMap.get(key);

        if (presenter == null) {
            presenter = factoryCallback.generatePresenter();
            viewPresenterMap.put(key, presenter);
        }

        return presenter;
    }

    void remove(String key) {
        viewPresenterMap.remove(key);
    }
}

interface FactoryCallback<T extends Presenter> {

    T generatePresenter();
}
