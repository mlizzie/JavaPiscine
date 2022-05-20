package edu.school21.models;

public class Product {

    private Integer id;
    private String name;
    private  Integer price;

    public Product(int id, String name, int price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product: {id = " + id + ", name = " + name + ", price = " + price + " }";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return  true;
        if(obj == null || this.getClass() != obj.getClass()){
            return false;
        }
        Product o = (Product) obj;
        return name.equals(o.getName()) && id.equals(o.getId()) && price.equals(o.getPrice());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
