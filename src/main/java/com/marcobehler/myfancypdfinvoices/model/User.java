package com.marcobehler.myfancypdfinvoices.model;

public class User {
    //Simple POJO class with two fields id and name, along with their getters and setters. It also has a default constructor and a parameterized constructor to initialize the fields.

    private String id;
    private String name;

    public User() {
    }

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}