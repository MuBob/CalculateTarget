package com.ciii.bobmu.calculate24.settings;

import android.content.SharedPreferences;

public class SettingBuilder {

    public static final String TARGET_NUMBER="targetNumber";
    public static final String NUMBER_COUNT="nCount";
    public static final int DEFAULT_TARGET_NUMBER=24;
    public static final int DEFAULT_NUMBER_COUNT=4;

    private int targetNumber;
    private int nCount;

    public SettingBuilder() {
        targetNumber=DEFAULT_TARGET_NUMBER;
        nCount=DEFAULT_NUMBER_COUNT;
    }

    public SettingBuilder setTargetNumber(int targetNumber) {
        if(targetNumber<=0){
            targetNumber=DEFAULT_TARGET_NUMBER;
        }
        this.targetNumber = targetNumber;
        return this;
    }


    public SettingBuilder setNumberCount(int nCount) {
        if(nCount<=0){
            nCount=DEFAULT_NUMBER_COUNT;
        }
        this.nCount = nCount;
        return this;
    }

    public int getTargetNumber() {
        return targetNumber;
    }

    public int getnCount() {
        return nCount;
    }

    void save(SharedPreferences.Editor edit) {
        edit.putInt(TARGET_NUMBER, getTargetNumber());
        edit.putInt(NUMBER_COUNT, getnCount());

    }
}
