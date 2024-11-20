package com.SCM.SCM.helpers;

public class ResouceNotFoundException extends RuntimeException{
    public ResouceNotFoundException(String message){
        super();
    }
    public ResouceNotFoundException(){
         super("resource not found");
    }

}
