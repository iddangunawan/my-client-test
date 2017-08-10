package com.example.myclient2.model;

/**
 * Created by iddan on 03/08/17.
 */

public class Client {
    private int id;
    private String name;
    private String address;

    public Client() {

    }

    public Client(String name, String address) {
        this.name = name;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
