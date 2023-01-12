package views;

import models.Product;
import pattern.PatternString;
import services.ProductService;
import ultis.CSVultils;
import ultis.CompareASC;
import ultis.CompareDESC;
//import ultis.SortASC;
//import ultis.SortDESC;

import java.util.List;
import java.util.Scanner;

public class ProductView {
    private ProductService productService;
    private CSVultils csVultils;
    private Scanner scanner;
    private boolean isContinue = true;
    private String name;
    private double price;
    private int quantity;
    private String describe;
    private PatternString patternString;
    private String idUpdate;
    private int id;

    public ProductView(){
        productService = new ProductService();
        scanner = new Scanner(System.in);
        patternString = new PatternString();
        csVultils = new CSVultils("D:\\ThiModule2\\ThiModule2\\src\\data\\products.csv");
    }

    public void showHead(){
       System.out.println("[1]: Xem danh sách");
       System.out.println("[2]: Thêm mới");
       System.out.println("[3]: Cập nhật");
       System.out.println("[4]: Xóa");
       System.out.println("[5]: Sắp xếp");
       System.out.println("[6]: Tìm sản phẩm có giá đắt nhất");
       System.out.println("[7]: Đọc từ file");
       System.out.println("[8]: Ghi vào file");
       System.out.println("[9]: Thoát");
    }
    public void findMaxPrice(){
        double max = Product.getListProduct().get(0).getPrice();
        for (int i =1; i< Product.getListProduct().size();i++){
            if (Product.getListProduct().get(i).getPrice()>= max){
                max = Product.getListProduct().get(i).getPrice();
            }
        }
        System.out.println("Sản phẩm có giá cao nhất:");
        System.out.printf("%-15s %-15s %-20s %-20s %-20s\n", "id", "name", "price", "quantity", "describe");
        for (int i =0; i< Product.getListProduct().size();i++){
            if (max == Product.getListProduct().get(i).getPrice()){
                long id = Product.getListProduct().get(i).getId();
                 name = Product.getListProduct().get(i).getName();
                 price = Product.getListProduct().get(i).getPrice();
                 quantity = Product.getListProduct().get(i).getQuantity();
                 describe = Product.getListProduct().get(i).getDescribe();
                System.out.printf("%-15s %-15s %-20s %-20s %-20s\n", id, name, price, quantity, describe);
            }
        }
    }

    private void showProductsPaggingView() {
        int numberOfPage = 5;
        List<Product> products = Product.getListProduct();
        int pageSize = (int) Math.ceil(((double) products.size())/numberOfPage);
        System.out.println("Số trang: " + pageSize);
        for (int i = 1; i <= pageSize; i++) {
            System.out.println("Page: " + i);
            int fromIndex = (i-1)*numberOfPage;
            int toIndex = fromIndex + numberOfPage;
            if (i == pageSize) {
                toIndex = products.size();
            }
            showProductsView(products.subList( fromIndex, toIndex));
            scanner.nextLine();
        }
    }



    private void showProductsView(List<Product> products) {
        System.out.printf("%-15s %-15s %-20s %-20s %-20s\n", "ID", "Name", "Price", "Quantity", "Describe");
        for (Product p : products) {
            System.out.println(p.toViewer());
        }
    }
    public void showBody(){
        boolean flag = true;
        int number = 0;
        do{
            try{
                System.out.print("Nhập lựa chọn: ");
                number = Integer.parseInt(scanner.nextLine());
                flag = false;
            }catch (Exception e){
                flag = true;
                scanner.reset();
            }
        }while (flag);
        switch (number){
            case 1:{
                showProductsPaggingView();
                break;
            }
            case 2:{
                addProduct();
                break;
            }
            case 3:{
                editProduct();
                break;
            }
            case 4:{
                removeProduct();
                break;
            }
            case 5:
                System.out.println("5. Sắp xếp the giá");
                System.out.println("-- Chọn kiểu Sap xep --");
                System.out.println("1 : Tang dan / ASC");
                System.out.println("2 : Giam dan / DESC");
                String select2 = scanner.nextLine();
                switch (select2) {
                    case "2":
                        System.out.println("5.1 : Giảm dần / DESC");
                        CompareDESC compareDESC = new CompareDESC();
                        compareDESC.descPrice();
//
                        break;
                    case "1":
                        System.out.println("5.2 : Tăng dần / ASC");
                        CompareASC compareASC = new CompareASC();
                        compareASC.ascPrice();
                        break;
                    case "0":
                        System.out.println("exited!");
                        flag = true;
                        break;
                    default:
                        System.out.println("invalid! please choose action in below menu:");
                        break;
                }
                break;
            case 6:
                findMaxPrice();
                break;
            case 7:
                System.out.println("Khi thực hiện sẽ cập nhật bộ nhớ! Nhập Y để tiếp tục or N để quay lại menu");
                boolean checkRead = false;
                do {
                    String choiceWrite = "";
                    choiceWrite = scanner.nextLine();
                    switch (choiceWrite) {
                        case "Y":
                            csVultils.fileRead(Product.getListProduct());
                            showProductsPaggingView();
                            checkRead = false;
                            break;
                        case "N":
//                            showHead();
                            break;
                        default:
                            System.out.println("Nhập sai, vui lòng nhập lại Y or N");
                            checkRead = true;
                            break;
                    }
                }while (checkRead);
                break;
            case 8:
                System.out.println("Khi thực hiện sẽ cập nhật bộ nhớ! Nhập Y để tiếp tục or N để quay lại menu");
                boolean checkWrite = false;
                do {
                    String choiceWrite = "";
                    choiceWrite = scanner.nextLine();
                    switch (choiceWrite) {
                        case "Y":
                            csVultils.fileWrite(Product.getListProduct());
                            checkWrite = false;
                            break;
                        case "N":
//                            showHead();
                            break;
                        default:
                            System.out.println("Nhập sai, vui lòng nhập lại Y or N");
                            checkWrite = true;
                            break;
                    }
                }while (checkWrite);
                break;
            case 9:
                isContinue = false;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + number);
        }
    }
    public void show(){
        do{
            showHead();
            showBody();
        }while (isContinue);
    }
    public void inputProduct(){
        boolean flag = true;
        do{
            try{
                System.out.print("Nhập tên sản phẩm: ");
                name = scanner.nextLine();
                flag = false;
            }catch (Exception e){
                flag = true;
                scanner.reset();
            }
            System.out.println("");
        }while (flag);
        do{
            try{
                System.out.print("Nhập giá sản phẩm: ");
                price = Double.parseDouble(scanner.nextLine());
                if(price>0){
                    flag = false;
                }else{
                    System.out.println("Giá của sản phẩm không thể nhỏ hơn 0");
                    flag=true;
                }
                System.out.println("");
            }catch (Exception e){
                System.out.println("Nhập đúng định dạng, ví dụ: 25000");
                flag = true;
                scanner.reset();
            }
        }while (flag);
        do{
            try{
                System.out.print("Nhập số lượng sản phẩm: ");
                quantity = Integer.parseInt(scanner.nextLine());
                flag = false;
                if(quantity>0){
                    flag = false;
                }else{
                    System.out.println("Số lượng sản phẩm không thể nhỏ hơn 0");
                    flag=true;
                }
            }catch (Exception e){
                System.out.println("Nhập đúng định dạng, ví dụ: 25");
                flag = true;
                scanner.reset();
            }
        }while (flag);
        do{
            System.out.println("Nhập mô tả sản phẩm: ");
            describe = scanner.nextLine();
        }while (flag);
    }
    public void addProduct(){
        long id = System.currentTimeMillis()/1000;
        boolean checkAdd = true;
        inputProduct();
        for (Product product: Product.getListProduct()){
            if (product.getName().equals(name)){
                checkAdd = false;
                product.setQuantity(quantity+product.getQuantity());
                System.out.println("Thêm sản phẩm thành công!!");
                break;
            }
        }
        if(checkAdd){
            Product product = new Product(id ,name, price, quantity, describe);
            productService.addProduct(product);
            System.out.println("Thêm sản phẩm thành công!!");
        }
    }
    public void removeProduct(){
        boolean flag = true;
        int id = 0;
        do{
            try{
                System.out.print("Nhap id: ");
                id = Integer.parseInt(scanner.nextLine());
                if(checkId(id) == false){
                    System.out.println("Id sản phẩm không tồn tại, vui lòng nhập lại");
                    flag = true;
                }else{
                    flag = false;
                }

            }catch (Exception e){
                System.out.println("Nhập đúng định dạng, ví dụ: 345435");
                flag = true;
                scanner.reset();
            }
        }while (flag);
        for(int i = 0; i< Product.getListProduct().size(); i++){
            if(id== Product.getListProduct().get(i).getId()){
                Product.getListProduct().remove(i);
                System.out.println("Xóa sản phẩm thành công!!");
            }
        }
    }
    public void editProduct(){
        boolean flag = true;
        int id = 0;
        do{
            try{
                System.out.print("Nhap id: ");
                id = Integer.parseInt(scanner.nextLine());
                if(checkId(id) == false ){
                    System.out.println("Id sản phẩm không tồn tại, vui lòng nhập lại");
                    flag = true;

                }else{

                    flag = false;
                }
            }catch (Exception e){
                System.out.println("Nhập đúng định dạng, ví dụ: 345435");
                flag = true;
                scanner.reset();
            }
        }while (flag);

        inputEditProduct(id);
        System.out.println("Sửa sản phẩm thành công");
    }
    public void inputEditProduct(int id){
        boolean flag = true;
        boolean isContinue = true;
        int number = 0;
        do{
            System.out.println("[1]: Mã sản phẩm");
            System.out.println("[2]: Tên sản phẩm");
            System.out.println("[3]: Giá");
            System.out.println("[4]: Số lượng");
            System.out.println("[5]: Mô tả");
            System.out.println("[6]: Exit");
            do{
                try{
                    number = Integer.parseInt(scanner.nextLine());
                    flag = false;
                }catch (Exception e){
                    flag = true;
                    scanner.reset();
                }
            }while (flag);
            switch (number){
                case 1:{
                    optionEdit("idUpdate", id);
                    break;
                }

                case 2:{
                    optionEdit("name", id);
                    break;
                }
                case 3:{
                    optionEdit("price", id);
                    break;
                }
                case 4:{
                    optionEdit("quantity", id);
                    break;
                }
                case 5:{
                    optionEdit("describe", id);
                    break;
                }
                case 6:{
                    isContinue = false;
                }
            }
        }while (isContinue);
    }
    public void optionEdit(String str, int id){
        boolean flag = true;
        switch (str){
            case "idUpdate": {
                do {
                    try {
                        System.out.print("Nhap ID: ");
                        idUpdate = scanner.nextLine();
                        if (name.equals("")) {
                            flag = true;
                        } else {
                            flag = false;
                        }
                    } catch (Exception e) {
                        flag = true;
                        scanner.reset();
                    }
                } while (flag);
                for (int i = 0; i < Product.getListProduct().size(); i++) {
                    if (id == Product.getListProduct().get(i).getId()) {
                        Product.getListProduct().get(i).setId(Integer.parseInt(idUpdate));
                    }
                }
                break;
            }
            case "name":{
                do{
                    try{
                        System.out.print("Nhap name: ");
                        name = scanner.nextLine();
                        if(name.equals("")){
                            flag = true;
                        }else{
                            flag= false;
                        }
                    }catch (Exception e){
                        flag = true;
                        scanner.reset();
                    }
                }while (flag);
                for(int i = 0; i< Product.getListProduct().size(); i++){
                    if(id == Product.getListProduct().get(i).getId()){
                        Product.getListProduct().get(i).setName(name);
                    }
                }
                break;
            }
            case "price":{
                do{
                    try{
                        System.out.print("Nhập giá: ");
                        price = Double.parseDouble(scanner.nextLine());
                        flag=false;
//                        if(!patternString.valindateEmail(email)){
//                            flag = true;
//                        }else{
//                            flag= false;
//                        }
                    }catch (Exception e){
                        flag = true;
                        scanner.reset();
                    }
                }while (flag);
                for(int i = 0; i< Product.getListProduct().size(); i++){
                    if(id == Product.getListProduct().get(i).getId()){
                        Product.getListProduct().get(i).setPrice(price);
                    }
                }
                break;
            }
            case "quantity":{
                do{
                    try{
                        System.out.print("Nhập số lượng: ");
                        quantity = Integer.parseInt(scanner.nextLine());
                        flag=false;
//                        if(!patternString.validatePhone(phone)){
//                            flag = true;
//                        }else{
//                            flag= false;
//                        }
                    }catch (Exception e){
                        flag = true;
                        scanner.reset();
                    }
                }while (flag);
                for(int i = 0; i< Product.getListProduct().size(); i++){
                    if(id == Product.getListProduct().get(i).getId()){
                        Product.getListProduct().get(i).setQuantity(quantity);
                    }
                }
                break;
            }
            case "describe":{
                do{
                    try{
                        System.out.print("Nhập mô tả: ");
                        describe = scanner.nextLine();
                        if(describe.equals("")){
                            flag = true;
                        }else{
                            flag= false;
                        }
                    }catch (Exception e){
                        flag = true;
                        scanner.reset();
                    }
                }while (flag);
                for(int i = 0; i< Product.getListProduct().size(); i++){
                    if(id == Product.getListProduct().get(i).getId()){
                        Product.getListProduct().get(i).setDescribe(describe);
                    }
                }
                break;
        }
        }

    }

    public boolean checkId (int id){
        for(int i = 0; i< Product.getListProduct().size(); i++){
            if(id == Product.getListProduct().get(i).getId()){
                return true;
            }
        }
        return false;
    }
}
