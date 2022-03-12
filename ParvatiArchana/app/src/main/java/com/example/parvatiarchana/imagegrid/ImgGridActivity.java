package com.example.parvatiarchana.imagegrid;
//https://www.youtube.com/watch?v=5UCPa54V1cI

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.parvatiarchana.R;

import java.io.InputStream;

public class ImgGridActivity extends AppCompatActivity {
    private int image[] = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img6, R.drawable.img1};
    private GridView gv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_grid);
        gv = (GridView) findViewById(R.id.imggridview);
        SaiGridAdapater adpter = new SaiGridAdapater(this, image);
        gv.setAdapter(adpter);
        // Events
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                      @Override
                                      public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                          System.out.println(" img click");
                                          Toast.makeText(getApplicationContext(), "Images " + i, Toast.LENGTH_SHORT).show();
                                      }
                                  }
        );
    }


}