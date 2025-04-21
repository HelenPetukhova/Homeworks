package org.example.daoClasses;

import org.example.models.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private final Connection connection;

    public static final String ADD_USER_QUERY =
            "INSERT INTO users (name, address) VALUES (?, ?)";

    public static final String FIND_USER_BY_USER_ID_QUERY =
            "SELECT * FROM users WHERE id = ?";

    public static final String FIND_ALL_USERS_IN_USERS_TABLE_QUERY =
            "SELECT * FROM users";

    public static final String DELETE_USER_FROM_USER_TABLE_QUERY =
            "DELETE FROM users WHERE id = ?";

    public UserDao(Connection connection) {
        this.connection = connection;
    }  // передаем в конструктор connection, чтобы использовать один connection при работе приложения

    // Создание пользователя

    public void createUser(User user) {
       try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER_QUERY)) {
            preparedStatement.setString(1, user.getName());   // вместо первого ? передаю name
            preparedStatement.setString(2, user.getAddress());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUserByUserId(Integer userId) {

        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_USER_ID_QUERY)) {
            preparedStatement.setInt(1, userId);                                                 // вместо первого ? передаю user_id интересующего меня пользователя

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {                          // проходим по всем строкам результата
                return new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("address"));
            } else {
                return null;
                //  throw new SQLException("Пользователь с user_id = " + userId + "не существует");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();  // cоздаем пустой список, куда будем сохранять пользовательские данные

        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_USERS_IN_USERS_TABLE_QUERY)) {
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {                          // проходим по всем строкам результата
                users.add(new User(                             // каждый раз добавляем новый объект User с id, name, address из результатов запроса
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("address"))
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;                                           // возвращаем список пользователей
    }



    public void printAllUsers() {
        List<User> users = getAllUsers();

        System.out.println("Список пользователей из users таблицы");
        for (User user : users) {
            System.out.println("userId: " + user.getUserId() + "|" + "name: " + user.getName() + "|" + "address: " + user.getAddress());

        }
    }



//    public Integer deleteUserFromUserTable(Integer userId) {      // можно потом вывести сколько строк удалено
//
//        try (//Connection connection = DriverManager.getConnection(ConnectionStringUtil.getConnectionString());
//             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_FROM_USER_TABLE_QUERY)) {
//            preparedStatement.setInt(1, userId);                                                 // вместо первого ? передаю user_id интересующего меня пользователя
//            return preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//    }


}
