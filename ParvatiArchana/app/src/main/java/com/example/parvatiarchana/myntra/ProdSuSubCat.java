package com.example.parvatiarchana.myntra;

public class ProdSuSubCat {
    private String Id;
    private int seqNo;
    private String dispplayName;

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

    @Override
    public String toString() {
        return "ProdSuSubCat{" +
                "Id='" + Id + '\'' +
                ", seqNo=" + seqNo +
                ", dispplayName='" + dispplayName + '\'' +
                '}';
    }
}
