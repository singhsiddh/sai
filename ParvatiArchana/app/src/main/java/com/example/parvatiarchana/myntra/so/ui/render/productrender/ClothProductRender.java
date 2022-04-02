package com.example.parvatiarchana.myntra.so.ui.render.productrender;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.parvatiarchana.R;
import com.example.parvatiarchana.bo.Product;
import com.example.parvatiarchana.carddata.CardItem;
import com.example.parvatiarchana.carddata.Carddata;
import com.example.parvatiarchana.myntra.MyntraMainActivity;
import com.example.parvatiarchana.myntra.so.ui.render.Util;
import com.example.parvatiarchana.so.ProductSO;

import java.io.InputStream;
import java.util.List;

public class ClothProductRender implements BaseProductRender {
    @Override
    public void render(Activity myntraMainActivity, LinearLayout layout, String[] catDet) {
        LinearLayout cat_linear = layout;
        // RelativeLayout cat_linear = (RelativeLayout) findViewById(R.id.linernestedlay);
        ProductSO pso = new ProductSO();
        List<Product> prodts = pso.serachProduct(catDet);
        LinearLayout.LayoutParams params = null;
        LinearLayout ll23_0 = null;
        LinearLayout ll23 = null;
        for (Product pro : prodts) {
            {
                System.out.println("LinerNestedActivity 3");
                ImageView imageView23 = new ImageView(myntraMainActivity);
                params = new LinearLayout.LayoutParams(400, 400);
                imageView23.setLayoutParams(params);

               //.. imageView23.setImageResource(R.drawable.img2);
                System.out.println("LinerNestedActivity 4");
                TextView tv23 = new TextView(myntraMainActivity);

                System.out.println("LinerNestedActivity 5");


                ll23_0 = new LinearLayout(myntraMainActivity);
                System.out.println("LinerNestedActivity 5 0");
                ll23_0.setOrientation(LinearLayout.HORIZONTAL);

                ll23 = new LinearLayout(myntraMainActivity);
                System.out.println("LinerNestedActivity 5 1");
                ll23.setOrientation(LinearLayout.VERTICAL);

                //   ll.setId();
                System.out.println("LinerNestedActivity 6");
                String url23 = pro.getDisplayImageURL();//"https://assets.myntassets.com/f_webp,dpr_1.0,q_60,w_210,c_limit,fl_progressive/assets/images/16485962/2021/12/11/efac2f88-8856-4397-9dd2-3d680de385b61639214687618SochWomenBlackYokeDesignLayeredVelvetKurtiwithTrousers1.jpg";
            //    Util.displayBitmapImage(myntraMainActivity, imageView23, url23);
                setImage(imageView23, url23);
                System.out.println("LinerNestedActivity 6 0");
                ll23_0.addView(imageView23);
                System.out.println("LinerNestedActivity 6 1");

                ll23_0.addView(ll23);
                System.out.println("LinerNestedActivity 6 2");
                Button addButton = new Button(myntraMainActivity);
                addButton.setText("Add");
                addButton.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                CardItem it = new CardItem();
                                                // String code=         pro.getCode();
                                                it.setItemCode("" + pro.getCode());
                                                it.setPrice(pro.getPrice());
                                                Carddata.addItem(it);

                                                String totaldesc = " Total Type of  Item :" + Carddata.count + "\n Total Price :" + Carddata.totalPrice + " Total count" + Carddata.totalCount;
                                                //TODO ..siddharth 19 march
                                                ( (MyntraMainActivity)myntraMainActivity).communicateH(totaldesc);

                                                ( (MyntraMainActivity)myntraMainActivity).  communicateButtom(); // This will refresh bell cion total item based on card
                                           /* TextView totalitemsummary =(TextView) findViewById(R.id.totalitemsummary);
                                            totalitemsummary.setText(totaldesc);

                                            */
                                            }
                                        }
                );
                System.out.println("LinerNestedActivity 6 3");
                ll23.addView(addButton);
                System.out.println("LinerNestedActivity 7");
                tv23.setText("Price : " + pro.getPrice());
                System.out.println("LinerNestedActivity 8");
                TextView desc = new TextView(myntraMainActivity);
                desc.setText(pro.getShortDesc());
                ll23.addView(desc);
                ll23.addView(tv23);
                System.out.println("LinerNestedActivity 9");
                cat_linear.addView(ll23_0);
                System.out.println("LinerNestedActivity 9 0 .");
            }
        }
    }

    private void setImage(ImageView bmImage, String url) {
        new ClothProductRender.DownloadImageTask(bmImage).execute(url);
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
