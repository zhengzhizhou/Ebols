package com.example.administrator.ebols.Login;

/**
 * Created by Administrator on 2017/6/8.
 */

class PasswordCharSequence implements CharSequence {
    private CharSequence password;
    public PasswordCharSequence(CharSequence source){
        password = source;
    }
    @Override
    public int length() {
        return password.length();
    }

    @Override
    public char charAt(int index) {
        return '*';
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return password.subSequence(start, end);
    }
}
