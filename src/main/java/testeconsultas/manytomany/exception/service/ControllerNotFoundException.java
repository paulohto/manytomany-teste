package testeconsultas.manytomany.exception.service;

public class ControllerNotFoundException extends RuntimeException {

    public ControllerNotFoundException(String message){
        super(message);
    }
}
