package com.linkmart.utils;

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

    public Map<String, String> formDataProcesser(Map<String, String> formData, String[] keys) {
        for (String key : keys) {
            if (formData.get(key) == null) {
                formData.put(key, "");
            }
        }
        return formData;
    }
}
