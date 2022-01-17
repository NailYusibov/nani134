package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDao dao = new UserDaoJDBCImpl();


        dao.createUsersTable();

        dao.saveUser("Nail", "Yusibov", (byte) 36);
        dao.saveUser("Emil", "Yusibov", (byte) 38);
        dao.saveUser("Arzu", "Yusibova", (byte) 21);
        dao.saveUser("Punkhan", "Mamedova", (byte) 30);


        List<User> users = dao.getAllUsers();
        System.out.println("All users:");
        for (User user : users) {
            System.out.println(user);
        }

        dao.removeUserById(1);
        users = dao.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
        dao.cleanUsersTable();
        dao.dropUsersTable();
    }
}





