package com.example.parvatiarchana.imagegrid;
//https://www.youtube.com/watch?v=5UCPa54V1cI

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.net.URI;

public class SaiGridAdapater extends BaseAdapter {
    private Context contex;
    private int[] images;

    public SaiGridAdapater(Context context, int[] images) {
        this.contex = context;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int i) {
        return images[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageV = new ImageView(contex);
        imageV.setImageResource(images[i]);
        imageV.setMinimumHeight(400);
        imageV.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageV.setLayoutParams(new GridView.LayoutParams(115, 115));
        return imageV;
    }
}
