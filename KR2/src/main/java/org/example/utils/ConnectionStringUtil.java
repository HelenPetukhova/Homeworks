package org.example.utils;

import java.io.File;

public class ConnectionStringUtil {
    public static String getConnectionString(){
        File file = new File("src/BankBase");

        if (file.exists()) {
            StringBuilder sb = new StringBuilder();
            sb.append("jdbc");
            sb.append(":");
            sb.append("sqlite");
            sb.append(":");
            sb.append(file.getAbsoluteFile());
            return sb.toString();
        } else {
            System.out.println("no database found");
            return "";
        }
    }
}
