package com.example.java7_4.util;

public class ValidateCode {

    public static boolean UserNameIsValid(String username){
        if(username.length()<3){
            return false;
        }
        for(char c:username.toCharArray()){
            if(!((c>='A'&&c<='z')||(c>='0'&&c<='9'))){
                return false;
            }
        }
        return true;
    }

    public static boolean PasswordIsValid(String password){
        if(password.length()<6){
            return false;
        }
        for(char c:password.toCharArray()){
            if(!((c>='A'&&c<='z')||(c>='0'&&c<='9'))){
                return false;
            }
        }
        return true;
    }
}

