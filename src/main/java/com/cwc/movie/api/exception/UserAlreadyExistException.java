package com.cwc.movie.api.exception;

public class UserAlreadyExistException extends Exception {

	    public UserAlreadyExistException(){
	        super("User with this username is already there in database!! Try with another one!!");
	    }

	    public UserAlreadyExistException(String msg){
	        super(msg);
	    }
}
