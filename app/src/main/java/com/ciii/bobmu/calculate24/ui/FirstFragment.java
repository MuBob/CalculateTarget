package com.ciii.bobmu.calculate24.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ciii.bobmu.calculate24.R;
import com.ciii.bobmu.calculate24.library.Calculate;
import com.ciii.bobmu.calculate24.library.Number;
import com.ciii.bobmu.calculate24.settings.SettingBuilder;
import com.ciii.bobmu.calculate24.settings.SettingPreference;
import com.ciii.bobmu.calculate24.ui.widget.InputEditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FirstFragment extends Fragment {

    private LinearLayout contentLayout;
    private TextView resultText;

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        int childCount = contentLayout.getChildCount();
        String[] strings=new String[childCount];
        for (int i = 0; i < childCount; i++) {
            InputEditText childAt = (InputEditText) contentLayout.getChildAt(i);
            strings[i]=childAt.getText().toString();
        }
        outState.putStringArray("edit_array", strings);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState!=null){
            String[] edit_arrays = savedInstanceState.getStringArray("edit_array");
            int childCount = contentLayout.getChildCount();
            if(edit_arrays.length==childCount){
                for (int i = 0; i < childCount; i++) {
                    InputEditText childAt = (InputEditText) contentLayout.getChildAt(i);
                    childAt.setText(edit_arrays[i]);
                }
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        contentLayout=view.findViewById(R.id.linear_content);
        resultText=view.findViewById(R.id.text_result);
        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultText.setText("计算中。。。");
                int childCount = contentLayout.getChildCount();
                Number[] n=new Number[childCount];
                for (int i = 0; i < childCount; i++) {
                    InputEditText childAt = (InputEditText)contentLayout.getChildAt(i);
                    String text = childAt.getText().toString();
                    int thisI=0;
                    try {
                        thisI = Integer.parseInt(text);
                    }catch (NumberFormatException e){
                        e.printStackTrace();
                    }finally {
                        n[i]=new Number(thisI);
                    }
                }
                Calculate calculate=new Calculate(n, SettingPreference.instance(getContext()).getBuilder().getTargetNumber());
                String run = calculate.run();
                resultText.setText(run!=null?run:"无解");
            }
        });

        SettingBuilder builder = SettingPreference.instance(getContext()).getBuilder();
        int count = builder.getnCount();
        for (int i = 0; i < count; i++) {
            InputEditText editView=new InputEditText(getActivity());
            contentLayout.addView(editView);
        }
    }

}
