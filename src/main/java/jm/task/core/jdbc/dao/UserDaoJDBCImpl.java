package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

            try (Connection connection = Util.getConnection()) {
                Statement statement = connection.createStatement();
                statement.execute("CREATE TABLE IF NOT EXISTS Users ("
                        + "id BIGINT PRIMARY KEY AUTO_INCREMENT, "
                        + "name VARCHAR(255) NOT NULL, "
                        + "lastName VARCHAR(255) NOT NULL, "
                        + "age TINYINT)");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS Users;";

        try (Connection connection = Util.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

            String sql = "INSERT INTO Users (name, lastName, age) VALUES (?, ?, ?);";
            try (Connection connection = Util.getConnection()) {
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, name);
                statement.setString(2, lastName);
                statement.setByte(3, age);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }


    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM Users WHERE id = ?;";

        try (Connection connection = Util.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM Users;";

        try (Connection connection = Util.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");

                User user = new User(id, name, lastName, age);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


    public void cleanUsersTable() {
        String sql = "DELETE FROM Users;";

        try (Connection connection = Util.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
