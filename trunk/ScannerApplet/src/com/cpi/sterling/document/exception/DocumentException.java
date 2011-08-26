package com.cpi.sterling.document.exception;

import com.cpi.sterling.common.exception.SterlingException;

public class DocumentException extends SterlingException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2247064999037686831L;
	/** Code Identifier Module */
	private static final String MODULE_EXCEPTION_ERR = "03";//$NON-NLS-1$
	/** Layer Code Level */
	private String prosLevel = null;
	/** Handler Code Action */
	private String prosAction = null;
	/** Code Message constructor */
	StringBuffer eCode;
	
	/** Inicialitation Document Exception with Level code and Action Code */
	public DocumentException(Exception exception, String level, String action){
		super(exception);
		this.prosLevel = level;
		this.prosAction = action;
	}
	
	/** Inicialitation Document Exception with Level code and Action Code */
	public DocumentException(String message, String level, String action){
		super(message);
		this.prosLevel = level;
		this.prosAction = action;
	}
	
	/** Sets the Code Action */
	public void setProsAction(String prosAction) {
		this.prosAction = prosAction;
	}
	/** Sets the Code Level */
	public void setProsLevel(String prosLevel) {
		this.prosLevel = prosLevel;
	}
	
	/** Gets the Code Exception */
	public String getExceptionCode() {
		eCode = new StringBuffer(0);
		eCode.append(MODULE_EXCEPTION_ERR);
		eCode.append(this.prosAction);
		eCode.append(this.prosLevel);
		return eCode.toString();	
	}
}
