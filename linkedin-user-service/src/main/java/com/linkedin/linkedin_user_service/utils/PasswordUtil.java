package com.linkedin.linkedin_user_service.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    // hash a password for a first time
    public static String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }
    // check that a plain text password matches a previously hashed one
    public static boolean checkPassword(String plainTextPassword, String hashedPassword) {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }
}
