package com.example.parvatiarchana.carddata;

public class CardItem {
    private String itemCode="";
    private int count;
    private Double price;
    private Double discount;

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "CardItem{" +
                "itemCode='" + itemCode + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", discount=" + discount +
                '}';
    }
}
