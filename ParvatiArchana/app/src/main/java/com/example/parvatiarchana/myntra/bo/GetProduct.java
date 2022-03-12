package com.example.parvatiarchana.myntra.bo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetProduct {

    public static List<Cat> getProduct() {
        List<Cat> cats = null;

        try {

            String jsonString = "{\"cat\":\r\n" +
                    "[\r\n" +
                    "{\"name\":\"New Product\"\r\n" +

                    "\r\n" +
                    "},\r\n" +
                    "{\r\n" +
                    "\"name\":\"Men\",\r\n" +
                    "\"subcat\":[\r\n" +
                    "			{\"name\":\" Top Wear\",\r\n" +
                    "				\"subsubcat\":[{\"name\":\"T-SHirt\"},{\"name\":\"Casul shirt\"}]\r\n" +
                    "			},\r\n" +
                    "\r\n" +
                    "			{\r\n" +
                    "			\"name\":\" bottom Wear\", \"subsubcat\":[{\"name\":\"Jeans\"},{\"name\":\"short\"},{\"name\":\"joggers\"}]\r\n" +
                    "			},\r\n" +
                    "			{\r\n" +
                    "			\"name\":\"Shoes\"\r\n" +
                    "			},\r\n" +
                    "			\r\n" +
                    "			{\r\n" +
                    "			\"name\":\"Tie\"\r\n" +
                    "			}\r\n" +
                    "			\r\n" +
                    "			\r\n" +
                    "		]\r\n" +
                    "},\r\n" +
                    "\r\n" +
                    "{\r\n" +
                    "\"name\":\"Women\",\r\n" +
                    "\"subcat\":[\r\n" +
                    "			{\"name\":\" Top Wear\",\r\n" +
                    "				\"subsubcat\":[{\"name\":\"Kurti\"},{\"name\":\"Saree\"},{\"name\":\"Suit Set\"}]\r\n" +
                    "			},\r\n" +
                    "\r\n" +
                    "			{\r\n" +
                    "			\"name\":\" Wester Wear\", \"subsubcat\":[{\"name\":\"Top\"},{\"name\":\"T Shirt\"},{\"name\":\"Skirts\"}]\r\n" +
                    "			},\r\n" +
                    "			{\r\n" +
                    "			\"name\":\"Shoes\"\r\n" +
                    "			},\r\n" +
                    "			\r\n" +
                    "			{\r\n" +
                    "			\"name\":\"makeup\"\r\n" +
                    "			}\r\n" +
                    "			\r\n" +
                    "			\r\n" +
                    "		]\r\n" +
                    "}\r\n" +
                    "\r\n" +
                    "\r\n" +
                    "]\r\n" +
                    "\r\n" +
                    "}\r\n" +
                    "";
            Product saiP = new ObjectMapper().readValue(jsonString, Product.class);
            System.out.println("sai p" + saiP);
            cats = saiP.getCat();
            System.out.println("sai cat" + saiP.getCat());

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return cats;
    }

    public static List<String> getAllCat(List<Cat> cats) {

        List<String> catL = new ArrayList<>();

        for (Cat c : cats) {
            catL.add(c.getName());
        }
        return catL;
    }

    public static Map<String, List<SubCat>> getAllCatMap(List<Cat> cats) {


        Map<String, List<SubCat>> map = new HashMap<>();
        for (Cat c : cats) {
            map.put(c.getName(), c.getSubcat());
        }
        return map;
    }

}
