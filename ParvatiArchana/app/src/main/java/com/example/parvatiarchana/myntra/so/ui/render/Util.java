package com.example.parvatiarchana.myntra.so.ui.render;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

public class Util {
    public static  void displayBitmapImage(Context context, ImageView imageView, String imageName){
       // ImageView imageView = (ImageView) findViewById(R.id.imageView1);
      //  ImageView tv1;
      //  tv1= (ImageView) findViewById(R.id.image);
        System.out.println("Image name 2ndapr"+imageName);
        AssetManager am= context.getAssets();
        try {
        InputStream si1 = am.open("image/" + imageName );
        Bitmap bitmap1 = BitmapFactory.decodeStream(si1);
        imageView.setImageBitmap(bitmap1);


        // load image
/* It ALos work
            // get input stream
            AssetManager am1= context.getAssets();
            InputStream ims = am1.open("avatar.jpg");
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // set image to ImageView
            imageView.setImageDrawable(d);

 */
        }
        catch(IOException ex) {
           // return;
        }
    }
}
