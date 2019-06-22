package com.example.hiringworkshop.threads;

import android.os.AsyncTask;

public class AppAsyncTask<T> extends AsyncTask<Void, Void, T> {

    //TODO need to improve the desing and switch to handler or something else....
    private AppAsyncCallback<T> appAsyncCallback;

    public AppAsyncTask(AppAsyncCallback<T> appAsyncCallback) {
        this.appAsyncCallback = appAsyncCallback;
    }

    @Override
    protected T doInBackground(Void... voids) {
        return appAsyncCallback.doInBackGround();
    }

    @Override
    protected void onPostExecute(T t) {
        super.onPostExecute(t);
        appAsyncCallback.onPost(t);
    }

    public interface AppAsyncCallback<T> {

        T doInBackGround();

        void onPost(T t);
    }
}
