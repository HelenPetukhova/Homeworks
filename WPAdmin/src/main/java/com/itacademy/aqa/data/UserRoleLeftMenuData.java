package com.itacademy.aqa.data;

import org.testng.annotations.DataProvider;

import java.util.Arrays;

public class UserRoleLeftMenuData {

    @DataProvider(name = "userRoleLeftMenuData")
    public static Object[][] userRoleLeftMenuData(){
        return new Object[][]{
                {"kladmin","OZ%h*i5Bv*0w89%JgEugD$1V", Arrays.asList("Dashboard", "Posts", "Media", "Pages", "Comments", "Appearance", "Plugins", "Users",
                        "Tools", "Settings", "Performance", "Smush")},
                {"kleditor","OIwJEqB8F1Xa4J@z@&5gN2AI", Arrays.asList("Dashboard", "Posts", "Media", "Pages", "Comments", "Profile", "Tools")},
                {"klauthor","qBNKScpmqG15QgpJSa2YNimX", Arrays.asList("Dashboard", "Posts", "Media", "Comments", "Profile", "Tools")},
                {"klone","C(17oLf9q)bRGC)4w&3Xkoin", Arrays.asList("Dashboard", "Posts", "Comments", "Profile", "Tools")},
                {"kltestuser","BT905MYP)^3j2%zFxh@sc)kU", Arrays.asList("Dashboard", "Profile")}
        };
    }
}
