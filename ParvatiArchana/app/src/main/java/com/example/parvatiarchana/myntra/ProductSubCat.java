package com.example.parvatiarchana.myntra;

import java.util.List;

public class ProductSubCat {
    private String Id;
    private int seqNo;
    private String dispplayName;
    List<ProdSuSubCat> subSubCats;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public int getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(int seqNo) {
        this.seqNo = seqNo;
    }

    public String getDispplayName() {
        return dispplayName;
    }

    public void setDispplayName(String dispplayName) {
        this.dispplayName = dispplayName;
    }

    public List<ProdSuSubCat> getSubSubCats() {
        return subSubCats;
    }

    public void setSubSubCats(List<ProdSuSubCat> subSubCats) {
        this.subSubCats = subSubCats;
    }

    @Override
    public String toString() {
        return "ProductSubCat{" +
                "Id='" + Id + '\'' +
                ", seqNo=" + seqNo +
                ", dispplayName='" + dispplayName + '\'' +
                ", subSubCats=" + subSubCats +
                '}';
    }
}
