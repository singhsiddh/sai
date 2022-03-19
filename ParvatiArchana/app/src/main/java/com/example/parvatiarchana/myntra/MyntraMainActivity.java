package com.example.parvatiarchana.myntra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.parvatiarchana.LinerNestedActivity;
import com.example.parvatiarchana.ParvatiFragment;
import com.example.parvatiarchana.R;
import com.example.parvatiarchana.bo.Product;
import com.example.parvatiarchana.carddata.CardItem;
import com.example.parvatiarchana.carddata.Carddata;
import com.example.parvatiarchana.myntra.so.ui.render.productrender.BaseProductRender;
import com.example.parvatiarchana.myntra.so.ui.render.productrender.GrocessyRender;
import com.example.parvatiarchana.so.ProductSO;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MyntraMainActivity extends AppCompatActivity implements MyntrMainFragment.InterFragCommuncation, MyntraHeaderFragment.InterFragCommuncation,MyntraButtomFragment.ButtonFragmentInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myntra_activity_main);
        initializeAll3fragments();
    }

    private MyntrMainFragment fragmentMain;
    private MyntraButtomFragment buttomFragment ;


    private void initializeAll3fragments() {
        FragmentManager manager120 = getSupportFragmentManager();


        if (manager120 != null) {

            //If a fragment is not already loaded into your container, then add one...
            if (manager120.findFragmentById(R.id.myntra_top) == null) {
                MyntraHeaderFragment fragment = new MyntraHeaderFragment();

                manager120.beginTransaction().add(R.id.myntra_top, fragment).commit();


            }
            if (manager120.findFragmentById(R.id.myntra_mid) == null) {
                MyntrMainFragment fragment = new MyntrMainFragment();
                this.fragmentMain = fragment;
                manager120.beginTransaction().add(R.id.myntra_mid, fragment).commit();
            }
            if (manager120.findFragmentById(R.id.myntra_btm) == null) {
                MyntraButtomFragment fragment = new MyntraButtomFragment();
                this.buttomFragment =fragment;
                manager120.beginTransaction().add(R.id.myntra_btm, fragment).commit();
            }
        }
    }


    private void debug(String msg) {
        System.out.println("" + msg);
    }

    @Override
    public void communicateM(String[] input) {
        System.out.println("Sai Communicate M is called Input Data   " + input);
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
                displayDatainMainFragment((LinearLayout) vg, input);
                System.out.println("Sai Communicate M is called going toremove 666-kaliji");
            } else {
                System.out.println("Sai Communicate M is called going toremove ELSE ");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displayDatainMainFragment(LinearLayout layout, String[] catDet) {
        //.. LinearLayout cat_linear = (LinearLayout) findViewById(R.id.linernestedlay);
        if("Grocessary".equals(catDet[0])){
System.out.println("19thMarch going to render Grocessory");
            BaseProductRender ren = new GrocessyRender();

          ren.render(MyntraMainActivity.this,layout, catDet);
            return;
        }else{
            System.out.println("19thMarch NOT NNNNNN going to render Grocessory");
        }
        LinearLayout cat_linear = layout;
        // RelativeLayout cat_linear = (RelativeLayout) findViewById(R.id.linernestedlay);
        ProductSO pso = new ProductSO();
        List<Product> prodts = pso.serachProduct(catDet);
        LinearLayout.LayoutParams params = null;
        LinearLayout ll23_0 = null;
        LinearLayout ll23 = null;
        for (Product pro : prodts) {
            System.out.println("LinerNestedActivity 3");
            ImageView imageView23 = new ImageView(MyntraMainActivity.this);
            params = new LinearLayout.LayoutParams(400, 400);
            imageView23.setLayoutParams(params);
            imageView23.setImageResource(R.drawable.img2);
            System.out.println("LinerNestedActivity 4");
            TextView tv23 = new TextView(MyntraMainActivity.this);

            System.out.println("LinerNestedActivity 5");


            ll23_0 = new LinearLayout(MyntraMainActivity.this);
            System.out.println("LinerNestedActivity 5 0");
            ll23_0.setOrientation(LinearLayout.HORIZONTAL);

            ll23 = new LinearLayout(MyntraMainActivity.this);
            System.out.println("LinerNestedActivity 5 1");
            ll23.setOrientation(LinearLayout.VERTICAL);

            //   ll.setId();
            System.out.println("LinerNestedActivity 6");
            String url23 = pro.getDisplayImageURL();//"https://assets.myntassets.com/f_webp,dpr_1.0,q_60,w_210,c_limit,fl_progressive/assets/images/16485962/2021/12/11/efac2f88-8856-4397-9dd2-3d680de385b61639214687618SochWomenBlackYokeDesignLayeredVelvetKurtiwithTrousers1.jpg";
            setImage(imageView23, url23);
            System.out.println("LinerNestedActivity 6 0");
            ll23_0.addView(imageView23);
            System.out.println("LinerNestedActivity 6 1");

            ll23_0.addView(ll23);
            System.out.println("LinerNestedActivity 6 2");
            Button bt23 = new Button(MyntraMainActivity.this);
            bt23.setText("Add");
            bt23.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            CardItem it = new CardItem();
                                            // String code=         pro.getCode();
                                            it.setItemCode("" + pro.getCode());
                                            it.setPrice(pro.getPrice());
                                            Carddata.addItem(it);

                                            String totaldesc = " Total Type of  Item :" + Carddata.count + "\n Total Price :" + Carddata.totalPrice + " Total count" + Carddata.totalCount;
                                            communicateH(totaldesc);
                                           /* TextView totalitemsummary =(TextView) findViewById(R.id.totalitemsummary);
                                            totalitemsummary.setText(totaldesc);

                                            */
                                        }
                                    }
            );
            System.out.println("LinerNestedActivity 6 3");
            ll23.addView(bt23);
            System.out.println("LinerNestedActivity 7");
            tv23.setText("Price : " + pro.getPrice());
            System.out.println("LinerNestedActivity 8");
            TextView desc = new TextView(MyntraMainActivity.this);
            desc.setText(pro.getShortDesc());
            ll23.addView(desc);
            ll23.addView(tv23);
            System.out.println("LinerNestedActivity 9");
            cat_linear.addView(ll23_0);
            System.out.println("LinerNestedActivity 9 0 .");
        }

    }

    @Override
    public void communicateH(String input) {
        System.out.println(" Inside method communicateH() -1 ");
        System.out.println(" Inside method communicateH() -2 " + input);
        int id = 2307;
        TextView totalitemsummary = (TextView) findViewById(R.id.my_edit_text_1);//R.id.totalitemsummary); dynamic id added in dynamic text view
        totalitemsummary.setText(input);

    }

    private void setImage(ImageView bmImage, String url) {
        new MyntraMainActivity.DownloadImageTask(bmImage).execute(url);


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

    @Override
    public void communicateButtom() {
        System.out.println(" Inside communicateButtom ....");

        // TODO Set Total Item on bell Icon text
        try {

            Button totalitemsummary =  this.buttomFragment.getView().findViewById(R.id.notif_count);//R.id.totalitemsummary); dynamic id added in dynamic text view


            totalitemsummary.setText(""+Carddata.cardItems.size());
        }catch( Exception e){
            System.out.println(" Inside communicateButtom  ERROR EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE....");
            e.printStackTrace();
        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
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