package com.example.parvatiarchana.myntra.mainfragment.recycleview;
//http://www.devexchanges.info/2017/02/android-recyclerview-dynamically-load.html
import java.io.Serializable;
import java.util.List;

public class MyntraProducts implements Serializable {
    /*
    categorary
    subCat
    subSbuCat
     */
    private String cat = "";
    private String subCat = "";
    private String subSubCat = "";
    private String sizeUnit = "";
    private List<String> size = null;
    private String code = "cc";
    private String shortDesc = "ss";
    private String displayImageURL = "::";
    private List<String> allImageURL = null;
    private Double price = 0d;
    private String description = "...";
    private String prodAttributeJSON = "";
    private String currencyType="Euro";

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public String getSizeUnit() {
        return sizeUnit;
    }

    public void setSizeUnit(String sizeUnit) {
        this.sizeUnit = sizeUnit;
    }

    public List<String> getSize() {
        return size;
    }

    public void setSize(List<String> size) {
        this.size = size;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDisplayImageURL() {
        return displayImageURL;
    }

    public void setDisplayImageURL(String displayImageURL) {
        this.displayImageURL = displayImageURL;
    }

    public List<String> getAllImageURL() {
        return allImageURL;
    }

    public void setAllImageURL(List<String> allImageURL) {
        this.allImageURL = allImageURL;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProdAttributeJSON() {
        return prodAttributeJSON;
    }

    public void setProdAttributeJSON(String prodAttributeJSON) {
        this.prodAttributeJSON = prodAttributeJSON;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getSubCat() {
        return subCat;
    }

    @Override
    public String toString() {
        return "Product{" +
                "cat='" + cat + '\'' +
                ", subCat='" + subCat + '\'' +
                ", subSubCat='" + subSubCat + '\'' +
                ", code='" + code + '\'' +
                ", displayImageURL='" + displayImageURL + '\'' +
                ", allImageURL=" + allImageURL +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", prodAttributeJSON='" + prodAttributeJSON + '\'' +
                '}';
    }

    public void setSubCat(String subCat) {
        this.subCat = subCat;
    }

    public String getSubSubCat() {
        return subSubCat;
    }

    public void setSubSubCat(String subSubCat) {
        this.subSubCat = subSubCat;
    }

}
