package kz.kaznitu.lessons.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private int phoneNumber;
    private String address;
    @ManyToMany
    private List<Collection> collections;

    public Client() {
        super();
    }
    public Client(int id, String firstName, String lastName, int phoneNumber, String address, List<Collection> collections) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.collections = collections;
    }
    public Client(String firstName, String lastName, int phoneNumber, String address, List<Collection> collections) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.collections = collections;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Collection> getCollections() {
        return collections;
    }

    public void setCollections(List<Collection> collections) {
        this.collections = collections;
    }

    public boolean hasCollection(Collection collection) {
        for (Collection containedCollection : getCollections()) {
            if (containedCollection.getId() == collection.getId()) {
                return true;
            }
        }
        return false;
    }
}
