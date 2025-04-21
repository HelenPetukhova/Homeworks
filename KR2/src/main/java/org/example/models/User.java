package org.example.models;

public class User {
    private Integer userId;
    private final String name;
    private final String address;

    // Конструктор на случай создания пользователя, когда user's id не известен
    public User(String name, String address) {
        this.name = name;
        this.address = address;
    }

    // Конструктор на случай создания пользователя, когда user's id не известен и адрес не заполнен
    public User(String name) {
        this.name = name;
        this.address = null;
    }

    // Конструктор на случай операций с пользователем, когда user's id известен
    public User(Integer userId, String name, String address) {
        this.userId = userId;
        this.name = name;
        this.address = address;
    }

    // Конструктор на случай операций с пользователем, когда user's id известен, но адрес не заполнен --?? нужен??
    public User(Integer userId, String name) {
        this.userId = userId;
        this.name = name;
        this.address = null;
    }


    public Integer getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }


    public void printUser(){
        System.out.println("[USERS] userId = " + userId + "\t" + "name = " + name + "\t" + "address = " + address);
    }
    }
