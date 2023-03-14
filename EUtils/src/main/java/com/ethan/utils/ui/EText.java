package com.ethan.utils.ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Ethan 2023/3/14
 */
public class EText {

    public static boolean isEmailValid(String email) {
        String regEx = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(email);
        return m.matches();
    }
}
