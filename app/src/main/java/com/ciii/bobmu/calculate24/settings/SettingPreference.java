package com.ciii.bobmu.calculate24.settings;

import android.content.Context;
import android.content.SharedPreferences;

public class SettingPreference {
    private  SharedPreferences sp;
    private static SettingPreference preference;
    private SettingBuilder builder;

    private SettingPreference(Context context) {
       sp=context.getSharedPreferences("setting", Context.MODE_PRIVATE);
    }

    public static SettingPreference instance(Context context){
       if(preference==null){
           preference=new SettingPreference(context);
       }
       return preference;
    }

    public void save(SettingBuilder builder){
        SharedPreferences.Editor edit = sp.edit();
        this.builder=builder;
        builder.save(edit);
        edit.apply();
    }

    public SettingBuilder getBuilder(){
        if(builder==null){
            builder=new SettingBuilder();
            save(builder);
        }
        return builder;
    }

}
