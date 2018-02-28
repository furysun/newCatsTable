package catsTable.model.db;

public class CatNotFoundException extends RuntimeException {
    CatNotFoundException(String message) {
        super(message);
    }
}
