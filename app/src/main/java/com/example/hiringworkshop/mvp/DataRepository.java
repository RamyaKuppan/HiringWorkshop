package com.example.hiringworkshop.mvp;

import java.util.Observable;
import java.util.Observer;

public abstract class DataRepository extends Observable {

    private int observer = 0;

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
        observer++;
    }

    @Override
    public synchronized void deleteObserver(Observer o) {
        super.deleteObserver(o);
        observer--;
        if (observer == 0) {
            clearData();
        }
    }

    protected abstract void clearData();
}
