package com.example.parvatiarchana.myntra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.parvatiarchana.ParvatiFragment;
import com.example.parvatiarchana.R;

public class MyntraMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myntra_activity_main);
        initializeAll3fragments();
    }

    private void initializeAll3fragments() {
        FragmentManager manager120 = getSupportFragmentManager();


        if (manager120 != null) {

            //If a fragment is not already loaded into your container, then add one...
            if (manager120.findFragmentById(R.id.myntra_top) == null) {
                MyntraHeaderFragment  fragment = new MyntraHeaderFragment();
                manager120.beginTransaction().add(R.id.myntra_top, fragment).commit();
            }
            if (manager120.findFragmentById(R.id.myntra_mid) == null) {
                MyntrMainFragment fragment = new MyntrMainFragment();
                manager120.beginTransaction().add(R.id.myntra_mid, fragment).commit();
            }
            if (manager120.findFragmentById(R.id.myntra_btm) == null) {
                MyntraButtomFragment  fragment = new MyntraButtomFragment();
                manager120.beginTransaction().add(R.id.myntra_btm, fragment).commit();
            }
        }
    }
}