package com.sterling.digicheck.check.exception;

import com.sterling.common.exception.DigiCheckException;

public class CheckException extends DigiCheckException {
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -2071848338582356825L;
	/** Specific customer error*/
	private String customError;
	/** Error code level */
	private String level = null;
	/** Error code action */
	private String action = null;
	/** Code Identifier Module */
	private static final String MODULE_EXCEPTION_CODE = "10";//$NON-NLS-1$
	
	/** Inicialitation Check Exception with Level code and Action Code */
	public CheckException(Exception exception, String level, String action){
		super(exception);
		this.level = level;
		this.action = action;
	}
	
	/** Inicialitation Check Exception with Level code and Action Code */
	public CheckException(String customError, String level, String action){
		super(customError);
		this.customError = customError;
		this.level = level;
		this.action = action;
	}
	@Override
	public String getExceptionCode() {
		StringBuilder exceptionCode = new StringBuilder(0);
		exceptionCode.append(MODULE_EXCEPTION_CODE);
		exceptionCode.append(action);
		exceptionCode.append(level);
		return exceptionCode.toString();
	}
	@Override
	public String getCustomError() {
		return customError;
	}
}
