package com.api.Exception;

public class CatalogException extends Exception{
    String message;
    public CatalogException(String mesg){
        this.message = mesg;
    }
    public CatalogException(){

    }
    public String getMessage(){
        return this.message;
    }

}
