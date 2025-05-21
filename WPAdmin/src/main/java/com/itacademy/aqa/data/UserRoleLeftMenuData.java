package com.itacademy.aqa.data;

import org.testng.annotations.DataProvider;

import java.util.Arrays;

public class UserRoleLeftMenuData {

    @DataProvider(name = "userRoleCredentials")
    public static Object[][] userRoleCredentials(){
        return new Object[][]{
                {"Admin","kladmin","OZ%h*i5Bv*0w89%JgEugD$1V"},
                {"Editor","kleditor","OIwJEqB8F1Xa4J@z@&5gN2AI"},
                {"Author","klauthor","qBNKScpmqG15QgpJSa2YNimX"},
                {"Contributor","klone","C(17oLf9q)bRGC)4w&3Xkoin"},
                {"Subscriber","kltestuser","BT905MYP)^3j2%zFxh@sc)kU"}
        };
    }



    @DataProvider(name = "userRoleLeftMenuData")
    public static Object[][] userRoleLeftMenuData(){
        return new Object[][]{
                {"Admin","kladmin","OZ%h*i5Bv*0w89%JgEugD$1V", Arrays.asList("Dashboard", "Posts", "Media", "Pages", "Comments", "Appearance", "Plugins", "Users",
                        "Tools", "Settings", "Performance", "Smush")},
                {"Editor","kleditor","OIwJEqB8F1Xa4J@z@&5gN2AI", Arrays.asList("Dashboard", "Posts", "Media", "Pages", "Comments", "Profile", "Tools")},
                {"Author","klauthor","qBNKScpmqG15QgpJSa2YNimX", Arrays.asList("Dashboard", "Posts", "Media", "Comments", "Profile", "Tools")},
                {"Contributor","klone","C(17oLf9q)bRGC)4w&3Xkoin", Arrays.asList("Dashboard", "Posts", "Comments", "Profile", "Tools")},
                {"Subscriber","kltestuser","BT905MYP)^3j2%zFxh@sc)kU", Arrays.asList("Dashboard", "Profile")}
        };
    }

    @DataProvider(name = "userRoleCredentialsPostCreators")
    public static Object[][] userRoleCredentialsPostCreators(){
        return new Object[][]{
                {"Admin","kladmin","OZ%h*i5Bv*0w89%JgEugD$1V", "KL ADMIN NEW POST TITLE TEST"},
                {"Editor","kleditor","OIwJEqB8F1Xa4J@z@&5gN2AI", "KL EDITOR NEW POST TITLE TEST"},
                {"Author","klauthor","qBNKScpmqG15QgpJSa2YNimX", "KL AUTHOR NEW POST TITLE TEST"}

        };
    }

    @DataProvider(name = "userRoleCredentialsPageCreators")
    public static Object[][] userRoleCredentialsPageCreators(){
        return new Object[][]{
                {"Admin","kladmin","OZ%h*i5Bv*0w89%JgEugD$1V", "KL ADMIN NEW PAGE TITLE TEST"},
                //  {"Editor","kleditor","OIwJEqB8F1Xa4J@z@&5gN2AI", "KL EDITOR NEW PAGE TITLE TEST"},

        };
    }
}
