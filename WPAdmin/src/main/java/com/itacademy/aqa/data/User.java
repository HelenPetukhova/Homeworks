package com.itacademy.aqa.data;

import com.itacademy.aqa.elements.NameBar;
import com.itacademy.aqa.enums.RolesEnum;
import org.apache.log4j.Logger;

public class User {  private String username;
    private String email;
    private String password;
    private RolesEnum roleEnum;
    private String firstName;
    private String lastName;
    private String website;

    private static Logger logger = Logger.getLogger(NameBar.class);

    public User(String username, String email, String password, RolesEnum roleEnum) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roleEnum = roleEnum;
    }

    public User(String username, String email, String password, RolesEnum roleEnum, String firstName, String lastName, String website) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roleEnum = roleEnum;
        this.firstName = firstName;
        this.lastName = lastName;
        this.website = website;
    }


    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public RolesEnum getRoleEnum() {
        return roleEnum;
    }

    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public String getWebsite() {
        return website;
    }

}

