package com.example.lab14_mvp.constract;

public interface ILoginConstract {
    interface IView{
        void loginSuccess(String message);
        void loginFailed(String error);
        void showLoading();
        void hideLoading();
    }

    interface IPresenter{
        void login(String email, String password);
    }
}
