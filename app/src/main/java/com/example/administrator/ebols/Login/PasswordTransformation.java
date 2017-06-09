package com.example.administrator.ebols.Login;

import android.text.method.PasswordTransformationMethod;
import android.view.View;

/**
 * Created by Administrator on 2017/6/8.
 */

public class PasswordTransformation extends PasswordTransformationMethod {
    @Override
    public CharSequence getTransformation(CharSequence source, View view) {
        return new PasswordCharSequence(source);
    }
}
