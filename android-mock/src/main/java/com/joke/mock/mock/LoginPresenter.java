package com.joke.mock.mock;

public class LoginPresenter {
    public UserManager mUserManager ;
    public PasswordValidator mPasswordValidator ;

    //step 1
    //basic mock object  验证方法调用
    public void login(String username, String password) {
        if (username == null || username.length() == 0) return;
        if (password == null || password.length() < 6) return;

        mUserManager.performLogin(username, password);
    }

    public void setUserManager(UserManager user){
        this.mUserManager = user;
    }

    public void setPasswordValidator(PasswordValidator passwordValidator) {
        mPasswordValidator = passwordValidator;
    }

    //step_2
    //指定mock对象的某些方法的行为
    //需要测试的方法是singIn，因此我们不必要关心相应的mPasswordValidator的相应的方法细节，
    //只需要指定特定的值即可
    public void singIn(String username,String password) {
        if (username == null || username.length() == 0) return;
        if (mPasswordValidator.verifyPassword(password)) return;

        mUserManager.singIn(username, password);
    }


    //step_3
    //存在callback的情况
    public void validate(String username,String password){
        if (username == null || username.length() == 0) return;
        //假设我们对密码强度有一定要求，使用一个专门的validator来验证密码的有效性
        if (mPasswordValidator.verifyPassword(password)) return;

        //login的结果将通过callback传递回来。
        mUserManager.validate(username, password, new NetworkCallback() {  //<==
            @Override
            public void onSuccess(Object data) {
            }

            @Override
            public void onFailure(int code, String msg) {
            }
        });
    }
}