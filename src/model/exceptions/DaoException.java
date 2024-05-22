package model.exceptions;

public class DaoException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DaoException(String msg) {
	super(msg);
    }

}
