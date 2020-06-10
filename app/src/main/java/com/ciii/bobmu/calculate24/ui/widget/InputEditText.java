package com.ciii.bobmu.calculate24.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.ciii.bobmu.calculate24.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import androidx.annotation.Nullable;

public class InputEditText extends FrameLayout {
    private Context mContext;
    private TextInputLayout layoutInput;
    private TextInputEditText textEdit;

    public InputEditText(Context context) {
        this(context, null);
    }

    public InputEditText(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InputEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs){
        mContext=context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_input_edit_text, this, true);
        layoutInput = view.findViewById(R.id.input_layout);
        textEdit = view.findViewById(R.id.input_edit);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.InputEditText);
        layoutInput.setHint(typedArray.getString(R.styleable.InputEditText_hint));
        setText(typedArray.getString(R.styleable.InputEditText_text));
        setInputType(typedArray.getInteger(R.styleable.InputEditText_android_inputType, InputType.TYPE_CLASS_NUMBER));
        textEdit.setTextSize(typedArray.getFloat(R.styleable.InputEditText_textSize, 12));
    }

    public Editable getText(){
        return textEdit.getText();
    }

    public void setText(CharSequence text){
        textEdit.setText(text);
    }

    public void setInputType(int type){
        textEdit.setInputType(type);
    }
}
