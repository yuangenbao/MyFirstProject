package com.example.ygb.myfirstproject.entity;

/**
 * Created by YuanBuyuan on 2017/11/9 0009.
 */

public class ItemData {
    private  String itemName;
    private String itemDesc;
    private  String itemThird;
    private String itemAuthor;
    private String id;
    private int itemImageId;

    public ItemData(String itemName, String itemDesc, String itemAuthor, String itemThird, String id) {
        this.itemName = itemName;
        this.itemDesc = itemDesc;
        this.itemThird = itemThird;
        this.itemAuthor=itemAuthor;
        this.id=id;
    }

    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemAuthor() {
        return itemAuthor;
    }

    public void setItemAuthor(String author) {
        this.itemAuthor = author;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public String getItemThird() {
        return itemThird;
    }

    public void setItemThird(String itemThird) {
        this.itemThird = itemThird;
    }

    public int getItemImageId() {
        return itemImageId;
    }

    public void setItemImageId(int itemImageId) {
        this.itemImageId = itemImageId;
    }


}
