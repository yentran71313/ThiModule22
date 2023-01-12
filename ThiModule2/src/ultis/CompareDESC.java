package ultis;

import models.Product;

import java.util.Comparator;

public class CompareDESC implements Comparator<Product> {

    @Override
    public int compare(Product o1, Product o2) {
        if(o1.getPrice() < o2.getPrice()){
            return 1;
        }else if(o1.getPrice() > o2.getPrice()){
            return -1;
        }
        return 0;
    }
    public CompareDESC(){}
    public void descPrice(){
        Product.getListProduct().sort(new CompareDESC());
    }
}