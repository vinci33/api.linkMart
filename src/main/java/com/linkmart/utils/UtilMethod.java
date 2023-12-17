package com.linkmart.utils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Random;

public class UtilMethod {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    public static String generateRandomString(int length) {
        // Create an instance of Random
        Random random = new Random();

        // Create a StringBuilder to store the random string
        StringBuilder stringBuilder = new StringBuilder(length);

        // Generate random characters and append them to the StringBuilder
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }

        // Get the final random string
        return stringBuilder.toString();
    }


    public static String Now(LocalTime currentTime) {
        // Create a LocalTime object representing the current time

        // Format the LocalTime object using a DateTimeFormatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timeString = currentTime.format(formatter);

        // Print the time string
        System.out.println("Current time: " + timeString);
        return timeString;
    }

//    public <T>Map<String, T> formDataProcessor(Map<String, T> formData, String[] keys, T defaultValue) {
//        for (String key : keys) {
//            if (!formData.containsKey(key)) {
//                if (formData.get(key) instanceof String) {
//                    formData.put(key, (T) "");
//                } else if (formData.get(key) instanceof Integer) {
//                    formData.put(key, (T) Integer.valueOf(0));
//                }
//            }
//        }
//        return formData;
//    }
}
