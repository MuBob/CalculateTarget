package com.ciii.bobmu.calculate24.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ciii.bobmu.calculate24.R;
import com.ciii.bobmu.calculate24.settings.SettingBuilder;
import com.ciii.bobmu.calculate24.settings.SettingPreference;
import com.ciii.bobmu.calculate24.ui.widget.InputEditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SecondFragment extends Fragment {

    private InputEditText numberCount, numberTarget;

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("count", numberCount.getText().toString());
        outState.putString("target", numberTarget.getText().toString());
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState!=null){
            numberCount.setText(savedInstanceState.getString("count"));
            numberTarget.setText(savedInstanceState.getString("target"));
        }
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int nCount=0;
                int numberTargett=0;
                try {
                    nCount=Integer.parseInt(numberCount.getText().toString());
                    numberTargett=Integer.parseInt(numberTarget.getText().toString());
                }catch (NumberFormatException e){
                    e.printStackTrace();
                }finally {
                    SettingBuilder builder=new SettingBuilder()
                            .setNumberCount(nCount)
                            .setTargetNumber(numberTargett);
                    SettingPreference.instance(getContext()).save(builder);
                    getActivity().onBackPressed();
                }
            }
        });
        numberCount=view.findViewById(R.id.input_setting_number_count);
        numberTarget=view.findViewById(R.id.input_setting_number_target);

        numberCount.setText(String.valueOf(SettingPreference.instance(getContext()).getBuilder().getnCount()));
        numberTarget.setText(String.valueOf(SettingPreference.instance(getContext()).getBuilder().getTargetNumber()));
    }


}
