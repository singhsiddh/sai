package com.example.parvatiarchana.myntra.bo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProdModel {
    private String name= "XL";
    private Double price= 1200d;
    private String         color= "pink";
    private Double discount_amount= 10d;
    private Double         discount_per= 5d;
    private String        bar_code= "12345";
    private Double         max_allowed_qty= 2d;
    private Double         avilable_qty= 10d;
    private String         model_description="" ;
    private Map<String,String> other_attributes;//= [{febric= cotton};{key= value}]

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getDiscount_amount() {
        return discount_amount;
    }

    public void setDiscount_amount(Double discount_amount) {
        this.discount_amount = discount_amount;
    }

    public Double getDiscount_per() {
        return discount_per;
    }

    public void setDiscount_per(Double discount_per) {
        this.discount_per = discount_per;
    }

    public String getBar_code() {
        return bar_code;
    }

    public void setBar_code(String bar_code) {
        this.bar_code = bar_code;
    }

    public Double getMax_allowed_qty() {
        return max_allowed_qty;
    }

    public void setMax_allowed_qty(Double max_allowed_qty) {
        this.max_allowed_qty = max_allowed_qty;
    }

    public Double getAvilable_qty() {
        return avilable_qty;
    }

    public void setAvilable_qty(Double avilable_qty) {
        this.avilable_qty = avilable_qty;
    }

    public String getModel_description() {
        return model_description;
    }

    public void setModel_description(String model_description) {
        this.model_description = model_description;
    }

    public Map<String, String> getOther_attributes() {
        return other_attributes;
    }

    public void setOther_attributes(Map<String, String> other_attributes) {
        this.other_attributes = other_attributes;
    }
/*
    "name": "XL",
        "price": 1200,
        "color": "pink",
        "discount_amount": 10,
        "discount_per": 5,
        "bar_code": "12345",
        "max_allowed_qty": 2,
        "avilable_qty": 10,
        "model_description": "",
        "other_attributes": [{"febric": "cotton"},{"key": "value"}]
     */

    @Override
    public String toString() {
        return "ProdModel{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", color='" + color + '\'' +
                ", discount_amount=" + discount_amount +
                ", discount_per=" + discount_per +
                ", bar_code='" + bar_code + '\'' +
                ", max_allowed_qty=" + max_allowed_qty +
                ", avilable_qty=" + avilable_qty +
                ", model_description='" + model_description + '\'' +
                ", other_attributes=" + other_attributes +
                '}';
    }
}
