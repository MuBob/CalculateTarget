package com.ciii.bobmu.calculate24.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ciii.bobmu.calculate24.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivityTAG";
    private FirstFragment firstFragment;
    private SecondFragment secondFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().show(secondFragment).hide(firstFragment).commit();
            }
        });
    }

    boolean secondShow;

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        firstFragment = (FirstFragment) getSupportFragmentManager().getFragment(savedInstanceState, FirstFragment.class.getName());
        secondFragment = (SecondFragment) getSupportFragmentManager().getFragment(savedInstanceState, SecondFragment.class.getName());
        secondShow=savedInstanceState.getBoolean("secondShow", false);
        firstFragment.onViewStateRestored(savedInstanceState);
        secondFragment.onViewStateRestored(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        if (firstFragment != null) {
            firstFragment.onSaveInstanceState(outState);
            getSupportFragmentManager().putFragment(outState, FirstFragment.class.getName(), firstFragment);
        }
        if (secondFragment != null) {
            secondFragment.onSaveInstanceState(outState);
            getSupportFragmentManager().putFragment(outState, SecondFragment.class.getName(), secondFragment);
        }
        outState.putBoolean("secondShow", secondFragment.isVisible());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onResume() {
        if (firstFragment == null) {
            firstFragment = new FirstFragment();
            Log.i(TAG, "MainActivity.onResume: init firstFragment");
            getSupportFragmentManager().beginTransaction().add(R.id.frame_content, firstFragment).commit();
        }
        if (secondFragment == null) {
            secondFragment = new SecondFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.frame_content, secondFragment).commit();
        }
        Log.i(TAG, "MainActivity.onResume: first fragment is visible="+firstFragment.isVisible());
        if(secondShow){
            getSupportFragmentManager().beginTransaction().show(secondFragment).hide(firstFragment).commit();
        }else{
            getSupportFragmentManager().beginTransaction().show(firstFragment).hide(secondFragment).commit();
        }
        super.onResume();
    }


    @Override
    public void onBackPressed() {
        if (firstFragment.isVisible()) {
            super.onBackPressed();
        } else {
            getSupportFragmentManager().beginTransaction().show(firstFragment).hide(secondFragment).commit();
        }
    }

    private void showFragment(int index){
        getSupportFragmentManager().beginTransaction().show(firstFragment).hide(secondFragment).commit();
        getSupportFragmentManager().beginTransaction().show(secondFragment).hide(firstFragment).commit();
    }
}
