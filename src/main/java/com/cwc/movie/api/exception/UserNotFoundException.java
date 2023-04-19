package com.cwc.movie.api.exception;


public class UserNotFoundException extends Exception{

    public UserNotFoundException(){
        super("User with this username is not found!!");
    }

    public UserNotFoundException(String msg){
        super(msg);
    }

}
