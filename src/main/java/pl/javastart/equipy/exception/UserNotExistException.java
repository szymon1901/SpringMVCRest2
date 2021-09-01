package pl.javastart.equipy.exception;

public class UserNotExistException extends RuntimeException{
    UserNotExistException(String message){
        super(message);
    }
}
