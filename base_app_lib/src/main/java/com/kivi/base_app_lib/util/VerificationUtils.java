package com.kivi.base_app_lib.util;

import java.util.regex.Pattern;

/**
 * @description: Created by kivi on 2017/8/17.
 */

public class VerificationUtils {

    //validate phone num
    public static boolean matcherPhoneNum(String value) {
        String regex = "^(\\+?\\d{2}-?)?(1[0-9])\\d{9}$";
        return Pattern.compile(regex)
                .matcher(value)
                .matches();
    }


}
