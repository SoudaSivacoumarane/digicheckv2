package com.sterling.digicheck.batch.exception;

import com.sterling.common.exception.DigiCheckException;
/**
 * Batch Exception
 * @author Edgar Joao
 *
 */
public class BatchException extends DigiCheckException{
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 5445159431731874914L;
	/** Specific customer error*/
	private String customError;
	/** Error code level */
	private String level = null;
	/** Error code action */
	private String action = null;
	/** Code Identifier Module */
	private static final String MODULE_EXCEPTION_CODE = "10";//$NON-NLS-1$
	
	/** Inicialitation User Exception with Level code and Action Code */
	public BatchException(Exception exception, String level, String action){
		super(exception);
		this.level = level;
		this.action = action;
	}
	
	/** Inicialitation User Exception with Level code and Action Code */
	public BatchException(String customError, String level, String action){
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
