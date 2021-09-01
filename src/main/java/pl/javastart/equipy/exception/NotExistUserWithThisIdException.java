package pl.javastart.equipy.exception;

public class NotExistUserWithThisIdException extends RuntimeException{
    public NotExistUserWithThisIdException(String message){
        super(message);
    }
}
