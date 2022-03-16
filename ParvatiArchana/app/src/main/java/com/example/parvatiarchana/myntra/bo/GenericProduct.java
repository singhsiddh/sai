package com.example.parvatiarchana.myntra.bo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GenericProduct {
    private String cat="";
    private String           subcat="";
    private String          subsubcat="";
    private String          base_itemcode="";
    private String          base_image="";
    private String          description="";
    private String          description_en="";
    private String         description_ch="";
    private Double          base_model_price=0d;
    private Double          discount_amount=0d;
    private Double          discount_per= 0d;
    private String []        other_images;
    private String          model_desc="size";
    private List<ProdModel> models;

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getSubcat() {
        return subcat;
    }

    public void setSubcat(String subcat) {
        this.subcat = subcat;
    }

    public String getSubsubcat() {
        return subsubcat;
    }

    public void setSubsubcat(String subsubcat) {
        this.subsubcat = subsubcat;
    }

    public String getBase_itemcode() {
        return base_itemcode;
    }

    public void setBase_itemcode(String base_itemcode) {
        this.base_itemcode = base_itemcode;
    }

    public String getBase_image() {
        return base_image;
    }

    public void setBase_image(String base_image) {
        this.base_image = base_image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription_en() {
        return description_en;
    }

    public void setDescription_en(String description_en) {
        this.description_en = description_en;
    }

    public String getDescription_ch() {
        return description_ch;
    }

    public void setDescription_ch(String description_ch) {
        this.description_ch = description_ch;
    }

    public Double getBase_model_price() {
        return base_model_price;
    }

    public void setBase_model_price(Double base_model_price) {
        this.base_model_price = base_model_price;
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

    public String[] getOther_images() {
        return other_images;
    }

    public void setOther_images(String[] other_images) {
        this.other_images = other_images;
    }

    public String getModel_desc() {
        return model_desc;
    }

    public void setModel_desc(String model_desc) {
        this.model_desc = model_desc;
    }

    public List<ProdModel> getModels() {
        return models;
    }

    public void setModels(List<ProdModel> models) {
        this.models = models;
    }

    @Override
    public String toString() {
        return "GenericProduct{" +
                "cat='" + cat + '\'' +
                ", subcat='" + subcat + '\'' +
                ", subsubcat='" + subsubcat + '\'' +
                ", base_itemcode='" + base_itemcode + '\'' +
                ", base_image='" + base_image + '\'' +
                ", description='" + description + '\'' +
                ", description_en='" + description_en + '\'' +
                ", description_ch='" + description_ch + '\'' +
                ", base_model_price=" + base_model_price +
                ", discount_amount=" + discount_amount +
                ", discount_per=" + discount_per +
                ", other_images=" + Arrays.toString(other_images) +
                ", model_desc='" + model_desc + '\'' +
                ", models=" + models +
                '}';
    }
}
