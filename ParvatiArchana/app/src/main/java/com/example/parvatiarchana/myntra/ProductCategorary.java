package com.example.parvatiarchana.myntra;

import java.io.Serializable;
import java.util.List;

public class ProductCategorary implements Serializable {
    private String Id;
    private int seqNo;
    private String dispplayName;
    private String imgSource;
    private List<ProductSubCat> subCat;

    public List<ProductSubCat> getSubCat() {
        return subCat;
    }

    public void setSubCat(List<ProductSubCat> subCat) {
        this.subCat = subCat;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getDispplayName() {
        return dispplayName;
    }

    public void setDispplayName(String dispplayName) {
        this.dispplayName = dispplayName;
    }

    public String getImgSource() {
        return imgSource;
    }

    public void setImgSource(String imgSource) {
        this.imgSource = imgSource;
    }

    @Override
    public String toString() {
        return "ProductCategorary{" +
                "Id='" + Id + '\'' +
                ", seqNo=" + seqNo +
                ", dispplayName='" + dispplayName + '\'' +
                ", imgSource='" + imgSource + '\'' +
                ", subCat=" + subCat +
                '}';
    }
}
