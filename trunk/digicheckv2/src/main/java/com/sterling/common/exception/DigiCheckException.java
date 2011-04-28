/**
 * 
 */
package com.sterling.common.exception;

/**
 *
 * TODO rioslore:Document this.
 * 
 * @author rioslore
 */
public abstract class DigiCheckException extends Exception {
	/** Serial Version UID */
	private static final long serialVersionUID = 3133152309699982117L;
	/** Code Exception for Converter layer */
	public static final String 	LAYER_CONVERTER = "CNV";//$NON-NLS-1$
	/** Code Exception for Service layer */
	public static final String 	LAYER_SERVICE = "SER";//$NON-NLS-1$
	/** Code Exception for Helper layer */
	public static final String 	LAYER_HELPER = "WEB";//$NON-NLS-1$
	/** Code Exception for Web layer */
	public static final String 	LAYER_WEB = "WEB";//$NON-NLS-1$
	/** Code Exception for Insert actions */
	public static final String 	ACTION_INSERT = "01";//$NON-NLS-1$
	/** Code Exception for Delete actions */
	public static final String 	ACTION_DELETE = "02";//$NON-NLS-1$
	/** Code Exception for Update actions */
	public static final String 	ACTION_UPDATE = "03";//$NON-NLS-1$
	/** Code Exception for List Handle */
	public static final String 	ACTION_LISTS = "04";//$NON-NLS-1$
	/** Code Exception for Delegate actions */
	public static final String 	ACTION_DELEGATE = "05";//$NON-NLS-1$
	/** Code Exception for Service Locator actions */
	public static final String 	ACTION_SERVICE_LOCATOR = "06";//$NON-NLS-1$
	/** Code Exception for Select Handle */
	public static final String 	ACTION_SELECT = "07";//$NON-NLS-1$
	/** Default Constructor */
	public DigiCheckException(){
		
	}
	/** Constructor Handling Exception*/
	public DigiCheckException(Exception exception){
		super(exception);
	}
	/** Constructs a new exception with the specified detail message.*/
	public DigiCheckException(String message) {
		super(message);
	}
	/** Abstract method to Gets the Exception Codes */
	public abstract String getExceptionCode();
	/** Abstract method to Gets the Custom Error*/
	public abstract String getCustomError();
}
