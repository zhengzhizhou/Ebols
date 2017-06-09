package com.example.administrator.ebols.Login;

import android.text.method.PasswordTransformationMethod;
import android.view.View;

/**
 * Created by Administrator on 2017/6/8.
 */

public class DoNothingTransformation extends PasswordTransformationMethod {
    @Override
    public CharSequence getTransformation(CharSequence source, View view) {
        return new noTransformation(source);
    }

    private class noTransformation implements CharSequence {
        private CharSequence password;
        public noTransformation(CharSequence source) {
            password = source;
        }

        @Override
        public int length() {
            return password.length();
        }

        @Override
        public char charAt(int index) {
            return password.charAt(index);
        }

        @Override
        public CharSequence subSequence(int start, int end) {
            return password.subSequence(start, end);
        }
    }
}
