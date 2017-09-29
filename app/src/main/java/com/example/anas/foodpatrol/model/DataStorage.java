package com.example.anas.foodpatrol.model;

/**
 * Created by Anas on 28/09/2017.
 */

public final class DataStorage {
    private static String username;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        DataStorage.username = username;
    }
}
