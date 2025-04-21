package org.example;

import org.example.utils.ConnectionStringUtil;
import org.example.utils.CreateTables;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection(ConnectionStringUtil.getConnectionString());) {
            CreateTables createTables = new CreateTables(connection);
            createTables.createTables();
            UI ui = new UI(connection);
            ui.bankInterface();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

