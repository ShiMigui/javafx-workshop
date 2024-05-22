package model.database;

public class DatabaseException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DatabaseException(String title, String msg) {
	super(title + "\n\t" + msg);
    }
}
