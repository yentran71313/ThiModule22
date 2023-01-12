package services;

import models.Product;

import java.util.ArrayList;

public class ProductService {
    private Product product;
    private int id;
    private String name;
    private double price;
    private int quantity;
    private String describe;

    public ProductService() {
        product = new Product();
    }

    public void addProduct(Product product) {
        Product.getListProduct().add(product);
    }

    public void removeProduct(int id) {
        ArrayList<Product> listProduct = Product.getListProduct();
        for (int i = 0; i < listProduct.size(); i++) {
            if (id == Product.getListProduct().get(i).getId()) {
                Product.getListProduct().remove(Product.getListProduct().get(i));
            }
        }
    }
    public void showProduct(){
        System.out.printf("%-15s %-15s %-20s %-20s %-20s", "id", "name", "price", "quantity", "describe");
        System.out.println("");
        ArrayList<Product> listProduct = Product.getListProduct();
        for(int i = 0; i< listProduct.size(); i++){
            long id = listProduct.get(i).getId();
            String name = listProduct.get(i).getName();
            double price = listProduct.get(i).getPrice();
            int quantity = listProduct.get(i).getQuantity();
            String describe = listProduct.get(i).getDescribe();
            System.out.printf("%-15s %-15s %-20s %-20s %-20s", id, name, price, quantity, describe);
            System.out.println("");
        }
        System.out.println("");
    }

}