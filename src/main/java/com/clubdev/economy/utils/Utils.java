package com.clubdev.economy.utils;

public class Utils {
    
    public static boolean isValidNumber(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }

        double num;
        try {
            num = Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return false;
        }
        
        if (num < 1) {
            return false;
        }

        if (str.contains(".")) {

            String[] parts = str.split("\\.");
            if (parts.length != 2) {
                return false;
            }

            if (parts[1].length() > 2 || Integer.parseInt(parts[1]) > 99) {
                return false;
            }
        }

        return true;
    }
}
