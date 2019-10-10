package com.example.indi_kart.Model;

public class Cart {
    String pid,pname,description,quantity,price,discount;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public Cart()
    {

    }
    public Cart(String pid, String pname, String description, String quantity, String price, String discount) {
        this.pid = pid;
        this.pname = pname;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
    }


}
