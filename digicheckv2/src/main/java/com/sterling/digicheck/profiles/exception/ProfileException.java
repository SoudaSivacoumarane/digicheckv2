package com.sterling.digicheck.profiles.exception;

import com.sterling.common.exception.DigiCheckException;
/**
 * Profile Exception
 * @author rioslore
 *
 */
public class ProfileException extends DigiCheckException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1598568719445446924L;
	
	/** Specific customer error*/
	private String customError;
	/** Error code level */
	private String level = null;
	/** Error code action */
	private String action = null;
	/** Code Identifier Module */
	private static final String MODULE_EXCEPTION_CODE = "08";//$NON-NLS-1$
	
	/** Inicialitation Profile Exception with Level code and Action Code */
	public ProfileException(Exception exception, String level, String action){
		super(exception);
		this.level = level;
		this.action = action;
	}
	
	/** Inicialitation Profile Exception with Level code and Action Code */
	public ProfileException(String customError, String level, String action){
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
