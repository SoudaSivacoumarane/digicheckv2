package com.cpi.sterling.common.exception;
/**
 * This Class is used to Handle all  Exceptions
 *
 */
public abstract class SterlingException extends Exception {
	/** Serialization UID*/
	private static final long serialVersionUID = 8198585080125145310L;
	/** Code Exception for DAO layer */
	public static final String 	LAYER_DAO = "DAO";
	/** Code Exception for Service layer */
	public static final String 	LAYER_SERVICE = "SER";
	/** Code Exception for Action layer */
	public static final String 	LAYER_ACTION = "ACT";
	/** Code Exception for Action layer */
	public static final String 	LAYER_CONVERTER = "CNV";
	/** Code Exception for Insert actions */
	public static final String 	ACTION_INSERT = "101";
	/** Code Exception for Delete actions */
	public static final String 	ACTION_DELETE = "102";
	/** Code Exception for Update actions */
	public static final String 	ACTION_UPDATE = "103";
	/** Code Exception for List Handle */
	public static final String 	ACTION_LISTS = "104";
	/** Code Exception for Select */
	public static final String 	ACTION_SELECT = "105";
	/** Code Exception for Login */
	public static final String ACTION_LOGIN = "106";
	/** Specific error code*/
	private String errorCode;
	/**
	 * Gets specific error code
	 * @return Error code
	 */
	public String getErrorCode() {
		return errorCode;
	}
	/**
	 * Sets specific error code
	 * @param nErrorCode
	 */
	private void setErrorCode(String nErrorCode) {
		this.errorCode = nErrorCode;
	}
	/** Constructor Handling Exception*/
	public SterlingException(Exception exception) {
		super(exception);
	}
	/** Default void Constructor */
	public SterlingException(){
	}
	/** Constructs a new exception with the specified detail message.*/
	public SterlingException(String message) {
		super(message);
		setErrorCode(message);
	}
	/** Abstract method to Gets the Exception Codes */
	public abstract String getExceptionCode();
}
