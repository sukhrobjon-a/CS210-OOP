package com.carrental.service;

import com.carrental.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private static final String FILE_PATH = "users.txt";

    static {
        createFileIfMissing();
        createDefaultAdminIfMissing();
    }

    private static void createFileIfMissing() {
        try {
            File file = new File(FILE_PATH);

            if (!file.exists()) {
                file.createNewFile();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createDefaultAdminIfMissing() {
        if (!usernameExists("admin")) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
                bw.write("admin,admin123,ADMIN");
                bw.newLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }




    public static boolean validateLogin(String username, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {

            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length == 3 &&
                        parts[0].equals(username) &&
                        parts[1].equals(password)) {
                    return true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }




    public static String getUserRole(String username) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {

            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length == 3 && parts[0].equals(username)) {
                    return parts[2];
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }




    public static boolean registerUser(User user) {
        if (usernameExists(user.getUsername())) {
            return false;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {

            bw.write(user.getUsername() + "," + user.getPassword() + ",RENTER");
            bw.newLine();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



    public static boolean createReceptionist(String username, String password) {
        if (usernameExists(username)) {
            return false;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {

            bw.write(username + "," + password + ",RECEPTIONIST");
            bw.newLine();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



    public static List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {

            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length == 3) {
                    users.add(new User(parts[0], parts[1], parts[2]));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }


    private static boolean usernameExists(String username) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {

            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length >= 1 && parts[0].equals(username)) {
                    return true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}