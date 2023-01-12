package ultis;

import models.Product;

import java.io.*;
import java.util.ArrayList;

public class CSVultils {
    private static String path;
    public CSVultils(String path){
        this.path = path;
    }
    public void fileWrite(ArrayList<Product> listProduct){
        try {
            FileWriter fr = new FileWriter(this.path);
            BufferedWriter br = new BufferedWriter(fr);
            if(listProduct.size() == 0 ){
                br.write("");
            }
            for(int i = 0; i< listProduct.size(); i++){
                br.write(listProduct.get(i).toString()+"\n");
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void fileRead(ArrayList<Product> listProduct){
        try {
            FileReader fr = new FileReader(this.path);
            BufferedReader br = new BufferedReader(fr);
            String line ="";
            Product.getListProduct().clear();
            while ((line=br.readLine())!=null){
                String[] strArray = line.split(",");
                int id = Integer.parseInt(strArray[0]);
                String name = strArray[1];
                double price  = Double.parseDouble(strArray[2]);
                int quatity = Integer.parseInt(strArray[3]);
                String description= strArray[4];
                listProduct.add(new Product(id, name, price, quatity, description));
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}