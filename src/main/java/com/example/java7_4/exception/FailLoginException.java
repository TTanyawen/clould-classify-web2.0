package com.example.java7_4.exception;

public class FailLoginException extends BaseException{
    public FailLoginException(){

    }
    public FailLoginException(String msg){
        super(msg);
    }
}
