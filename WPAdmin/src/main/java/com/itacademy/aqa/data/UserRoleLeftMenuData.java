package com.itacademy.aqa.data;

import com.itacademy.aqa.config.Configuration;
import org.testng.annotations.DataProvider;

import java.util.Arrays;

public class UserRoleLeftMenuData {

    @DataProvider(name = "userRoleCredentials")
    public static Object[][] userRoleCredentials(){
        return new Object[][]{
                {"Admin", Configuration.getProperties().getProperty("adminUserName"),Configuration.getProperties().getProperty("adminPassword")},
                {"Editor", Configuration.getProperties().getProperty("editorUserName"),Configuration.getProperties().getProperty("editorPassword")},
                {"Author", Configuration.getProperties().getProperty("authorUserName"),Configuration.getProperties().getProperty("authorPassword")},
                {"Contributor", Configuration.getProperties().getProperty("contributorUserName"),Configuration.getProperties().getProperty("contributorPassword")},
                {"Subscriber", Configuration.getProperties().getProperty("subscriberUserName"),Configuration.getProperties().getProperty("subscriberPassword")}
        };
    }



    @DataProvider(name = "userRoleLeftMenuData")
    public static Object[][] userRoleLeftMenuData(){
        return new Object[][]{
                {"Admin",Configuration.getProperties().getProperty("adminUserName"),Configuration.getProperties().getProperty("adminPassword"), Arrays.asList("Dashboard", "Posts", "Media", "Pages", "Comments", "Appearance", "Plugins", "Users",
                        "Tools", "Settings", "Performance", "Smush")},
                {"Editor",Configuration.getProperties().getProperty("editorUserName"),Configuration.getProperties().getProperty("editorPassword"), Arrays.asList("Dashboard", "Posts", "Media", "Pages", "Comments", "Profile", "Tools")},
                {"Author",Configuration.getProperties().getProperty("authorUserName"),Configuration.getProperties().getProperty("authorPassword"), Arrays.asList("Dashboard", "Posts", "Media", "Comments", "Profile", "Tools")},
                {"Contributor",Configuration.getProperties().getProperty("contributorUserName"),Configuration.getProperties().getProperty("contributorPassword"), Arrays.asList("Dashboard", "Posts", "Comments", "Profile", "Tools")},
                {"Subscriber",Configuration.getProperties().getProperty("subscriberUserName"),Configuration.getProperties().getProperty("subscriberPassword"), Arrays.asList("Dashboard", "Profile")}
        };
    }

    @DataProvider(name = "userRoleCredentialsPostCreators")
    public static Object[][] userRoleCredentialsPostCreators(){
        return new Object[][]{
                {"Admin",Configuration.getProperties().getProperty("adminUserName"),Configuration.getProperties().getProperty("adminPassword"), "KL ADMIN NEW POST TITLE TEST"},
                {"Editor",Configuration.getProperties().getProperty("editorUserName"),Configuration.getProperties().getProperty("editorPassword"), "KL EDITOR NEW POST TITLE TEST"},
                {"Author",Configuration.getProperties().getProperty("authorUserName"), Configuration.getProperties().getProperty("authorPassword"), "KL AUTHOR NEW POST TITLE TEST"}

        };
    }

    @DataProvider(name = "userRoleCredentialsPageCreators")
    public static Object[][] userRoleCredentialsPageCreators(){
        return new Object[][]{
                {"Admin",Configuration.getProperties().getProperty("adminUserName"),Configuration.getProperties().getProperty("adminPassword"), "KL ADMIN NEW PAGE TITLE TEST"},
                {"Editor",Configuration.getProperties().getProperty("editorUserName"),Configuration.getProperties().getProperty("editorPassword"), "KL EDITOR NEW PAGE TITLE TEST"},

        };
    }
}
