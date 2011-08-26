package com.cs.common.pool.exception;

public class PoolException extends Exception {
	/** Serial ID */
	private static final long serialVersionUID = 4349134722561812464L;
	/** Exception code */
	private static final String MODULE_EXCEPTION_ERR = "00";
	/** Pool action */
	private static final String POOL_ACTION = "00";
	/** Pool level */
	private static final String POOL_LEVEL = "POO";
	/** Constructor Handling Exception*/
	public PoolException(Exception exception) {
		super(exception);
	}
	/** Default void Constructor */
	public PoolException(){
	}
	/** Constructs a new exception with the specified detail message.*/
	public PoolException(String message) {
		super(message);
	}
	/** Gets the Code Exception */
	public String getExceptionCode() {
		StringBuffer eCode = new StringBuffer(0);
		eCode.append(MODULE_EXCEPTION_ERR);
		eCode.append(POOL_ACTION);
		eCode.append(POOL_LEVEL);
		return eCode.toString();
	}
}