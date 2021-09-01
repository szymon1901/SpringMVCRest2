package pl.javastart.equipy.exception;

public class DuplicateSerialNumberException extends RuntimeException {
    public DuplicateSerialNumberException(String message){
        super(message);
    }
}
