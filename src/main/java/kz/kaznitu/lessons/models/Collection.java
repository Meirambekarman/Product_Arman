package kz.kaznitu.lessons.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Size(min = 3,max = 20, message = "Error TYPE: length 3 or 20")
    private String type;
    @Size(min = 3, max = 20, message = "Error KINDS: length 3 or 20")
    private String kinds;

    public Collection(){
        super(); }

    public Collection(int id, String type,String kinds){
        this.id = id;
        this.type = type;
        this.kinds = kinds;
    }
    public Collection(String type,String kinds){
        this.type = type;
        this.kinds = kinds;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKinds() {
        return kinds;
    }

    public void setKinds(String kinds) {
        this.kinds = kinds;
    }
}
