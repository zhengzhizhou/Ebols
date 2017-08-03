package com.example.administrator.circleimageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.lang.reflect.Array;

/**
 * Created by Administrator on 2017/6/28.
 */

public class CircleImageView extends android.support.v7.widget.AppCompatImageView {
    private ScaleType scaleType = ScaleType.CENTER_CROP;
    private AttributeSet attributeSet;
    public CircleImageView(Context context) {
        super(context);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Array typedArray = getContext().obtainStyledAttributes(attrs, );
    }
}
