package testeconsultas.manytomany.exception.service;

public class DatabaseException extends  RuntimeException {

    public DatabaseException(String msg) {
        super(msg);
    }
}
