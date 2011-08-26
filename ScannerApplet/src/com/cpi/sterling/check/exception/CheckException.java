package com.cpi.sterling.check.exception;

import com.cpi.sterling.common.exception.SterlingException;

public class CheckException extends SterlingException{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3698319338072109598L;
	/** Code Identifier Module */
	private static final String MODULE_EXCEPTION_ERR = "02";//$NON-NLS-1$
	/** Layer Code Level */
	private String prosLevel = null;
	/** Handler Code Action */
	private String prosAction = null;
	/** Code Message constructor */
	StringBuffer eCode;
	
	/** Inicialitation Chek Exception with Level code and Action Code */
	public CheckException(Exception exception, String level, String action){
		super(exception);
		this.prosLevel = level;
		this.prosAction = action;
	}
	
	/** Inicialitation Chek Exception with Level code and Action Code */
	public CheckException(String message, String level, String action){
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
