package com.example.parvatiarchana.carddata;

import android.content.ClipData;

import java.util.ArrayList;
import java.util.List;

public class Carddata {
    private void CardData() {

    }
public static double totalPrice=0d;
    public static int count = 0;
    public static int totalCount = 0;
    public static List<CardItem> cardItems = new ArrayList<CardItem>();
public static  void clearCardData(){
    totalPrice=0d;
    count = 0;
    totalCount = 0;
    cardItems.clear();
   

}
    public static void addItem(CardItem item) {
     //   System.out.println("Item:=" + item+" count "+count+1);

        boolean flag =true;
        for (int i = 0; i < count; i++) {
            String itemCode = cardItems.get(i).getItemCode();
            if (itemCode.equals(item.getItemCode())) {
            CardItem ii=    cardItems.get(i);
            ii.setCount(ii.getCount()+1);
            totalPrice=totalPrice+item.getPrice();
            flag=false;
                break;
            }
        }
        if(flag){//first tinme
            item.setCount(1);
            cardItems.add(item);
            totalPrice=totalPrice+item.getPrice();
        }
        count = cardItems.size();
        totalCount +=1;
        System.out.println(" count "+count+" cardItems"+cardItems);
    }

    public static void removeItem(CardItem item) {
        for (int i = 0; i < count; i++) {
            String itemCode = cardItems.get(i).getItemCode();
            if (itemCode.equals(item.getItemCode())) {
                cardItems.remove(i);
                totalPrice=totalPrice- (item.getCount()* item.getPrice());
                totalCount +=-item.getCount();
                break;
            }
        }

        count = cardItems.size();

    }

    // Quantitiy ..
    public static void updateItem(CardItem item) {
        for (int i = 0; i < count; i++) {
            String itemCode = cardItems.get(i).getItemCode();
            if (itemCode.equals(item.getItemCode())) {
                totalPrice=totalPrice+item.getPrice()-cardItems.get(i).getPrice();
                cardItems.remove(i);
                cardItems.add(item);
                break;
            }
            //totalCount +=1;
            count = cardItems.size();

        }
    }
}
