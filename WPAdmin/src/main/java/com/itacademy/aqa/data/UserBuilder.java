package com.itacademy.aqa.data;

import com.itacademy.aqa.elements.NameBar;
import com.itacademy.aqa.enums.RolesEnum;
import com.itacademy.aqa.utils.RandomUtil;
import org.apache.log4j.Logger;

public class UserBuilder {
    private String username = "kluser_" + RandomUtil.generateRandomString(8);
    private String email = RandomUtil.generateEmail(6);
    private String password = "klpass_" + RandomUtil.generateRandomString(8);
    private RolesEnum roleEnum =  RandomUtil.generateRole();
    private String firstName = "kl" + RandomUtil.generateRandomString(8);
    private String lastName  = "kl" + RandomUtil.generateRandomString(8);
    private String website  = "kl" + RandomUtil.generateRandomString(8) + ".ts";
    private static Logger logger = Logger.getLogger(NameBar.class);


    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public UserBuilder withUserName(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder withRole(RolesEnum roleEnum) {
        this.roleEnum = roleEnum;
        return this;
    }

    public UserBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder withWebsite(String website) {
        this.website = website;
        return this;
    }




    public User build() {
        logger.info("Creating user object with random credentials and role");

        return new User(username, email, password, roleEnum, firstName, lastName, website);
    }


}
