package com.example.parvatiarchana;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.parvatiarchana.bo.Product;
import com.example.parvatiarchana.carddata.CardItem;
import com.example.parvatiarchana.carddata.Carddata;
import com.example.parvatiarchana.imageloder.ImageLoader;
import com.example.parvatiarchana.so.ProductSO;

import java.io.InputStream;
import java.util.List;

public class LinerNestedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("LinerNestedActivity 1");
        setContentView(R.layout.activity_liner_nested);
        System.out.println("LinerNestedActivity 2");
        /** Adding multiple Linerlayout in that image */
        LinearLayout cat_linear = (LinearLayout) findViewById(R.id.linernestedlay);


        ProductSO pso = new ProductSO();
       List<Product> prodts=pso.serachProduct("test");
       for( Product pro :prodts){
           System.out.println("LinerNestedActivity 3");
           ImageView imageView23 = new ImageView(LinerNestedActivity.this);
           LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(400 ,400);
           imageView23.setLayoutParams(params);
           imageView23.setImageResource(R.drawable.img2);
           System.out.println("LinerNestedActivity 4");
           TextView tv23 = new TextView(LinerNestedActivity.this);

           System.out.println("LinerNestedActivity 5");
           LinearLayout ll23 = new LinearLayout(LinerNestedActivity.this);
           ll23.setOrientation(LinearLayout.VERTICAL);
           //   ll.setId();
           System.out.println("LinerNestedActivity 6");
           String url23 = pro.getDisplayImageURL();//"https://assets.myntassets.com/f_webp,dpr_1.0,q_60,w_210,c_limit,fl_progressive/assets/images/16485962/2021/12/11/efac2f88-8856-4397-9dd2-3d680de385b61639214687618SochWomenBlackYokeDesignLayeredVelvetKurtiwithTrousers1.jpg";
           setImage(imageView23, url23);

           ll23.addView(imageView23);
           Button bt23 = new Button(LinerNestedActivity.this);
           bt23.setText("Add");
           bt23.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           CardItem it = new CardItem();
                                 // String code=         pro.getCode();
                                           it.setItemCode(""+pro.getCode());
                                           it.setPrice(pro.getPrice());
                                           Carddata.addItem(it);
                                       }
                                   }
           );
           ll23.addView(bt23);
           System.out.println("LinerNestedActivity 7");
           tv23.setText(""+pro.getPrice());
           System.out.println("LinerNestedActivity 8");
            ll23.addView(tv23);
           System.out.println("LinerNestedActivity 9");
           cat_linear.addView(ll23);
       }


        System.out.println("LinerNestedActivity 3");
        ImageView imageView = new ImageView(LinerNestedActivity.this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(400 ,400);
        imageView.setLayoutParams(params);
        imageView.setImageResource(R.drawable.img2);
        System.out.println("LinerNestedActivity 4");
        TextView tv = new TextView(LinerNestedActivity.this);

        System.out.println("LinerNestedActivity 5");
        LinearLayout ll1 = new LinearLayout(LinerNestedActivity.this);
        ll1.setOrientation(LinearLayout.VERTICAL);
        //   ll.setId();
        System.out.println("LinerNestedActivity 6");
        String url1 = "https://assets.myntassets.com/f_webp,dpr_1.0,q_60,w_210,c_limit,fl_progressive/assets/images/16485962/2021/12/11/efac2f88-8856-4397-9dd2-3d680de385b61639214687618SochWomenBlackYokeDesignLayeredVelvetKurtiwithTrousers1.jpg";
        setImage(imageView, url1);
/* Not working
       ImageLoader imgLoader = new ImageLoader(LinerNestedActivity.this);


        int loader = R.drawable.img1;
        imgLoader.DisplayImage(url1, loader,imageView);

        */

        ll1.addView(imageView);
        Button bt1 = new Button(LinerNestedActivity.this);
        bt1.setText("button1");
        ll1.addView(bt1);
        System.out.println("LinerNestedActivity 7");
        tv.setText("url1:");
        System.out.println("LinerNestedActivity 8");
       /* tv[i][i].setLayoutParams(new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f));
*/
        //..    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ll1.addView(tv);
        System.out.println("LinerNestedActivity 9");
        cat_linear.addView(ll1);
        System.out.println("LinerNestedActivity 10");
        ImageView imageView2 = new ImageView(LinerNestedActivity.this);
        //  LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(100, 200);
        imageView2.setLayoutParams(params);
        imageView2.setImageResource(R.drawable.img4);
        TextView tv2 = new TextView(LinerNestedActivity.this);


        LinearLayout ll2 = new LinearLayout(LinerNestedActivity.this);
        ll2.setOrientation(LinearLayout.VERTICAL);
        //   ll.setId();
        Button bt2 = new Button(LinerNestedActivity.this);
        bt2.setText("button 2.");
        ll2.addView(bt2);
        ll2.addView(imageView2);
        tv2.setText("url:2");
       /* tv[i][i].setLayoutParams(new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f));
*/
        //..    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ll2.addView(tv2);

        cat_linear.addView(ll2);
        // ll.addView(view, layoutParams);

        System.out.println("LinerNestedActivity End");
    }

    /**
     * LayoutInflater inflater = (LayoutInflater)   getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
     * View myView = inflater.inflate(R.layout.sub_layout, null);;
     * flContainer = (FrameLayout) v.findViewById(R.id.flContainer);
     * flContainer.addView(myView);
     *
     *
     *
     *   LinearLayout ll = new LinearLayout(mContext);
     *                  ll.setOrientation(LinearLayout.VERTICAL);
     *                  ll.setId(id);
     * LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
     *                 layoutParams.setMargins(25, 20, 25, 10);
     *                 EditText view = new EditText(ViewOnClick.this);
     *                 view.setText(++i+" view");
     *                 ll.addView(view, layoutParams);
     *
     *             }});
     */

    /**
     * Added at 27 jan 2022
     */
    private void setImage(ImageView bmImage, String url) {
        new DownloadImageTask(bmImage).execute(url);


       /* String urldisplay = url;
        Bitmap mIcon11 = null;
        try {
            System.out.println(" Inside ste image..1");
            InputStream in = new java.net.URL(urldisplay).openStream();
            System.out.println(" Inside ste image..2");
            mIcon11 = BitmapFactory.decodeStream(in);
            System.out.println(" Inside ste image..3");
            bmImage.setImageBitmap(mIcon11);
            System.out.println(" Inside ste image..4");
        } catch (Exception e) {
            // Log.e("Error", e.getMessage());
e.printStackTrace();
        }

*/

    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected  Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                // Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}