package com.example.administrator.ebols.Fragment.NewOrderFragments;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/7/18.
 */

public class AddNewView {
    private EditText editText;
    private TextView textView;
    private LinearLayout linearLayout;
    private TableLayout.LayoutParams tlp = new TableLayout.LayoutParams();
    private Context context;
    public AddNewView(Context context){
        this.context = context;
    }
    public EditText buildNewEditText(EditText editText, int left, int right, int top, int bottom, int backroundResource){
        editText = new EditText(context);
        this.editText = editText;
        tlp.setMargins(left, top, right, bottom);
        this.editText.setLayoutParams(tlp);
        this.editText.setBackgroundResource(backroundResource);
        return this.editText;
    }
    public TextView buildNewTextView(TextView textView, String text, int color){
        textView = new TextView(context);
        this.textView = textView;
        this.textView.setText(text);
        this.textView.setTextColor(color);
        return this.textView;
    }
    public LinearLayout buildNewLinearLayout(LinearLayout linearLayout, int aspect, int width, int height, float weight){
        linearLayout = new LinearLayout(context);
        this.linearLayout = linearLayout;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height, weight);
        this.linearLayout.setOrientation(aspect);
        this.linearLayout.setLayoutParams(params);
        return this.linearLayout;
    }
    public LinearLayout buildNewLinearLayout(LinearLayout linearLayout, int aspect, int width, int height){
        linearLayout = new LinearLayout(context);
        this.linearLayout = linearLayout;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        this.linearLayout.setOrientation(aspect);
        this.linearLayout.setLayoutParams(params);
        return this.linearLayout;
    }
    public void addToLayout(LinearLayout layout, View ... views){
        for(int i = 0; i<views.length; i++){
            layout.addView(views[i]);
        }
    }
}
