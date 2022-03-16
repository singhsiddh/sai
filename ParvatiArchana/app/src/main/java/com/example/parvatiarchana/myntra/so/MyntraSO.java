package com.example.parvatiarchana.myntra.so;

import android.content.Context;

import com.example.parvatiarchana.myntra.bo.GenericProduct;
import com.example.parvatiarchana.myntra.bo.GetProduct;
import com.example.parvatiarchana.myntra.bo.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class MyntraSO {

    public static List<GenericProduct> loadGenPruducts(Context context) throws IOException {
        String json = GetProduct.readJSONFromAsset(context, "product.json");
        List<GenericProduct> saiP = new ObjectMapper().readValue(json, new TypeReference<List<GenericProduct>>() {
        });

        //  List<MyClass> myObjects = mapper.readValue(jsonInput, new TypeReference<List<MyClass>>(){});
        System.out.println("16march  genraicproduct " + saiP);
        return saiP;
    }
    public static List<GenericProduct> loadGenPruductsByCat(Context context,String[] cats) throws IOException {
        String json = GetProduct.readJSONFromAsset(context, "product.json");
        List<GenericProduct> saiP = new ObjectMapper().readValue(json, new TypeReference<List<GenericProduct>>() {
        });

        //  List<MyClass> myObjects = mapper.readValue(jsonInput, new TypeReference<List<MyClass>>(){});
        System.out.println("16march  genraicproduct " + saiP);
        return saiP;
    }

}
