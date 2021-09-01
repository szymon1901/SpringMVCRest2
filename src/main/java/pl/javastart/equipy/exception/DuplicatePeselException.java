package pl.javastart.equipy.exception;

public class DuplicatePeselException extends RuntimeException{
    public DuplicatePeselException(String message){
        super(message);
    }
}
