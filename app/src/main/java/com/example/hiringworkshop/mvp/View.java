package com.example.hiringworkshop.mvp;

public interface View<T extends Presenter, E extends PresenterToViewCallback> {

    T getPresenter();

    void initializePresenter();

}
