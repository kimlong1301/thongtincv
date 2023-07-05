package com.god.store_manager.util.Exception;

public class EntityExistingException extends RuntimeException{
    public EntityExistingException(String message){
        super(message);
    }
}
