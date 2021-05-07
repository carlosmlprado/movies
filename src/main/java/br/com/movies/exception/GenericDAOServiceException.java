package br.com.movies.exception;

public class GenericDAOServiceException extends RuntimeException {

	/**
	 * Serial Version ID.
	 */
	private static final long serialVersionUID = -2015941923249488184L;

	/**
	 * Constructor.
	 */
	public GenericDAOServiceException() {
		super();
	}

	/**
	 * @param message
	 *            the error message.
	 */
	public GenericDAOServiceException(String message) {
		super(message);
	}

	/**
	 * @param message
	 *            the error message.
	 * @param cause
	 *            the root an exception.
	 */
	public GenericDAOServiceException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 *            the error message.
	 * @param cause
	 *            the root an exception.
	 */
	public GenericDAOServiceException(String message, Throwable cause) {
		super(message, cause);
	}


}
