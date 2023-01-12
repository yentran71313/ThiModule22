package models;

import java.util.ArrayList;

public class Product {
    private long id;
    private String name;
    private double price;
    private int quantity;
    private String describe;
    private static ArrayList<Product> listProduct;

    public Product(long id, String name, double price, int quantity, String describe) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.describe = describe;
    }

    public Product() {
        listProduct = new ArrayList<>();
    }

    ;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String toViewer() {
        return String.format("%-15s %-15s %-20s %-20s %-20s\n", id, name, price, quantity, describe);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public static void setListUser(ArrayList<Product> listProduct) {
        Product.listProduct = listProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public static ArrayList<Product> getListProduct() {
        return listProduct;
    }

    public static void setListProduct(ArrayList<Product> listProduct) {
        Product.listProduct = listProduct;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s", id, name, price, quantity, describe);
    }
}