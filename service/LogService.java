package by.itacademy.gomel.service;

import by.itacademy.gomel.model.User;

import java.io.FileOutputStream;
import java.io.IOException;

public class LogService {

    public static void addLoggedAction(String value, User user) {

        try(FileOutputStream fos = new FileOutputStream(String.format("log_%s.txt", user.getLogin()), true))
        {
            byte[] buffer = value.getBytes();
            fos.write(buffer, 0, buffer.length);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
