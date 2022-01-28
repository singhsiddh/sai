package com.example.parvatiarchana.so;

import com.example.parvatiarchana.bo.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductSO {

    public List<Product> serachProduct(String keyWord){

        return mock();
    }

    public List<Product> serachProductByCat(String cat){

        return null;
    }

    private List<Product> mock(){

        List<Product> prodL= new ArrayList<>();

        Product prod = new Product();
        prod.setCode("poo1");
        prod.setPrice(1200d);
     String url=  "https://assets.myntassets.com/f_webp,dpr_1.0,q_60,w_210,c_limit,fl_progressive/assets/images/16485962/2021/12/11/efac2f88-8856-4397-9dd2-3d680de385b61639214687618SochWomenBlackYokeDesignLayeredVelvetKurtiwithTrousers1.jpg";

        prod.setDisplayImageURL(url);

        prodL.add(prod);

        prod = new Product();
        url="https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/15813904/2021/11/16/cd84a825-e60e-4ad4-b891-2edaeb59f78d1637050200768TshirtsLevisMenJeansLevisMenJeansLevisMenShirtsArrowSportMen1.jpg";
        prod.setCode("poo2");
        prod.setPrice(2000d);
        prod.setDisplayImageURL(url);

        prodL.add(prod);
    url="https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/13351936/2021/4/23/87651751-7392-4cee-aa11-6b9d406afddd1619163799029-Levis-Women-Shirts-1561619163798318-1.jpg";
        prod = new Product();
        prod.setCode("poo3");
        prod.setPrice(8050d);
        prod.setDisplayImageURL(url);

        prodL.add(prod);
         return prodL;
    }

}
