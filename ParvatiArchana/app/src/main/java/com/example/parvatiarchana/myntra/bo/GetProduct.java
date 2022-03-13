package com.example.parvatiarchana.myntra.bo;

import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetProduct {

    public static List<Cat> getProduct(Context context) {
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
/*
            File file = new File(
                    GetProduct.class.getClass().getClassLoader().getResource("cat.json").getFile()
            );
*/
            jsonString=  readJSONFromAsset(context,"cat.json");
            Product saiP = new ObjectMapper().readValue(jsonString, Product.class);
           // Product saiP = new ObjectMapper().readValue(file, Product.class);
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
    public static String readJSONFromAsset(Context context,String jsonFile) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(jsonFile);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private static String readFileFromResources(String fileName) throws IOException {
        URL resource = GetProduct.class.getClassLoader().getResource(fileName);

        if (resource == null)
            throw new IllegalArgumentException("file is not found!");

        StringBuilder fileContent = new StringBuilder();

        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(new File(resource.getFile())));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fileContent.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return fileContent.toString();
    }
}
