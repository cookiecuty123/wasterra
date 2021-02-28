package com.example.wasterra;

public class ItemModel {
    private int id;
    private String item;
    private int qty;

    public ItemModel(int id, String item, int qty) {
        this.id = id;
        this.item = item;
        this.qty = qty;
    }

    public ItemModel() {
    }

    @Override
    public String toString() {
        return "ItemModel{" +
                "id=" + id +
                ", item='" + item + '\'' +
                ", qty=" + qty +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
