package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userSer = new UserServiceImpl();



        userSer.createUsersTable();


        userSer.saveUser("Nail", "Yusibov", (byte) 36);
        userSer.saveUser("Emil", "Yusibov", (byte) 38);
        userSer.saveUser("Arzu", "Yusibova", (byte) 21);
        userSer.saveUser("Punkhan", "Mamedova", (byte) 30);


        List<User> users = userSer.getAllUsers();
        System.out.println("All users:");
        for (User user : users) {
            System.out.println(user);
        }

        userSer.removeUserById(1);
        users = userSer.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
        userSer.cleanUsersTable();
        userSer.dropUsersTable();
    }
}





