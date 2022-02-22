package com.example.parvatiarchana.myntra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.parvatiarchana.ParvatiFragment;
import com.example.parvatiarchana.R;

import java.util.ArrayList;
import java.util.List;

public class MyntraMainActivity extends AppCompatActivity implements MyntrMainFragment.InterFragCommuncation,MyntraHeaderFragment.InterFragCommuncation {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myntra_activity_main);
        initializeAll3fragments();
    }
    private MyntrMainFragment fragmentMain;


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
                this.fragmentMain=fragment;
                manager120.beginTransaction().add(R.id.myntra_mid, fragment).commit();
            }
            if (manager120.findFragmentById(R.id.myntra_btm) == null) {
                MyntraButtomFragment  fragment = new MyntraButtomFragment();
                manager120.beginTransaction().add(R.id.myntra_btm, fragment).commit();
            }
        }
    }





    private  void  debug(String msg){
        System.out.println(""+msg);
    }

    @Override
    public void communicateM(String input) {
        System.out.println("Sai Communicate M is called  ");
        FragmentManager manager120 = getSupportFragmentManager();
        try {
            if (manager120.findFragmentById(R.id.myntra_mid) != null) {
                System.out.println("Sai Communicate M is called going toremove ");

                Fragment fr = manager120.findFragmentById(R.id.myntra_mid);
                System.out.println("Sai Communicate M is called going toremove 111");
                ViewGroup vg = fr.getView().findViewById(R.id.main_liner_lay);
                System.out.println("Sai Communicate M is called going toremove 222");
                vg.removeAllViews();
                System.out.println("Sai Communicate M is called going toremove 333 ");
                Button bt = null;
                bt = new Button(this);
                System.out.println("Sai Communicate M is called going toremove444 ");
                bt.setText("Main Section");
                vg.addView(bt);
                System.out.println("Sai Communicate M is called going toremove 555");
            } else {
                System.out.println("Sai Communicate M is called going toremove ELSE ");
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void communicateH(String input) {

    }
}