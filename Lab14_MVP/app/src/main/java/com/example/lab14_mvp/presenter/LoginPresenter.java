package com.example.lab14_mvp.presenter;

import com.example.lab14_mvp.constract.ILoginConstract;
import android.os.Handler;
public class LoginPresenter implements ILoginConstract.IPresenter {
    private ILoginConstract.IView mView;

    public LoginPresenter(ILoginConstract.IView view){
        mView = view;
    }
    @Override
    public void login(String email, String password) {

        mView.showLoading();

        new Handler().postDelayed(() -> {

            if(email.isEmpty() || password.isEmpty()){
                mView.hideLoading();
                mView.loginFailed("Không được để trống");
                return;
            }

            if(email.equals("admin@gmail.com") && password.equals("123")){
                mView.hideLoading();
                mView.loginSuccess("Login thành công");
            }else{
                mView.hideLoading();
                mView.loginFailed("Sai tài khoản hoặc mật khẩu");
            }

        }, 2000);
    }
}
