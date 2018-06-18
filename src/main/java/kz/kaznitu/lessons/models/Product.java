package kz.kaznitu.lessons.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Size(min = 3,max = 20, message = "Error Name: length 3 or 20")
    private String name;
    @Size(min = 3, max = 20, message = "Error Price: length 3 or 20")
    private String price;

    public Product(){
        super(); }

    public Product(int id, String name,String price){
        this.id = id;
        this.name = name;
        this.price = price;
    }
    public Product(String name,String price){
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
