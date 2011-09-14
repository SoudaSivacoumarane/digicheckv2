package com.sterling.common.util;

import java.text.DecimalFormat;
import java.util.Arrays;

public class NumberUtil {
	/** Formato para cantidades */
	private final static String quantityPattern = "###,###,##0.00";//$NON-NLS-1$
	/** Formato para precios */
	private final static String pricePattern = "###,###,##0.0000";//$NON-NLS-1$	
	/** Formato para enteros */
	private final static String intPattern = "###,###,###";//$NON-NLS-1$
	/** Caracter COMA */
	public static final String COMMA_LABEL = ",";//$NON-NLS-1$
	/** Cadena vacia */
	public static final String EMPTY_STRING = "";//$NON-NLS-1$
	/** Clase que formatea las cantidades */
	private static DecimalFormat quantityFormater = new DecimalFormat(quantityPattern);
	/** Clase que formatea los precios */
	private static DecimalFormat priceFormater = new DecimalFormat(pricePattern);
	/** Clase que formatea las enteros */
	private static DecimalFormat integerFormater = new DecimalFormat(intPattern);	
	
	/**
	 * Metodo para formatear cantidades con el formato establecido ###,###,##0.00 
	 * @param quantity cantidad
	 * @return cantidad formateada
	 */
	public static String floorQuantity( double quantity ){
		String formatedQuantity =  null;
		formatedQuantity = integerFormater.format(((int)(quantity+.99)));
		return formatedQuantity;
	}
	
	
	public static String convertTon( Double tons ){
		return convertTon(tons.doubleValue());
	}
	
	/**
	 * Metodo para formatear cantidades con el formato establecido ###,###,##0.00 
	 * @param quantity cantidad
	 * @return cantidad formateada
	 */
	public static String convertQuantity( double quantity ){
		String formatedQuantity =  null;
		formatedQuantity = quantityFormater.format(quantity);
		return formatedQuantity;
	}
	
	public static String convertPrice( double price ){
		String formatedQuantity =  null;
		formatedQuantity = priceFormater.format(price);
		return formatedQuantity;
	}
	
	public static String convertPrice( Double price ){
		return convertPrice(price.doubleValue());
	}
	/**
	 * Metodo para formatear enteros con el formato establecido ###,###,### 
	 * @param quantity cantidad
	 * @return cantidad formateada
	 */
	public static String convertInteger( int integer ){
		String formatedQuantity =  null;
		formatedQuantity = integerFormater.format(integer);
		return formatedQuantity;
	}
	
	public static String convertInteger( Integer integer ){
		return convertInteger(integer.intValue());
	}
	
	/**
	 * Metodo para formatear cantidades con el formato establecido ###,###,##0.00
	 * @param quantity Clase Double a convertir
	 * @return cantidad formateada
	 */
	public static String convertQuantity(Double quantity) {
		return convertQuantity(quantity.doubleValue());
	}
	/**
	 * Convierte a entero un objeto de tipo String
	 * @param object
	 * @return
	 */
	public static int getInt(Object object){
		return Integer.parseInt((String)object);
	}
	/**
	 * Convierte una cadena a su double correspondiente
	 * @param doubleToParse cadena a convertir
	 * @return double convertido
	 */
	public static double parseDouble(String doubleToParse){
		double parsedDouble = -1;
		if(null!=doubleToParse){
			String validDouble = doubleToParse.replaceAll(COMMA_LABEL, EMPTY_STRING);
			if(!validDouble.equals(EMPTY_STRING)){
				parsedDouble = Double.parseDouble(validDouble);
			}
		}
		return parsedDouble;
	}
	/**
	 * Convierte una cadena a su entero correspondiente
	 * @param intToParse cadena a convertir
	 * @return int convertido
	 */
	public static int parseInt(String intToParse){
		int parsedInt = -1;
		if(null!=intToParse){
			parsedInt = Integer.parseInt(intToParse.replaceAll(COMMA_LABEL, EMPTY_STRING));
		}
		return parsedInt;
	}
	
	public static String fill(int numberToFill, char withChar, int length){
		char[] fill = null;
		StringBuffer stringFilled = null;
		String strNumberToFill = null;
		
		strNumberToFill = Integer.toString(numberToFill);
		fill = new char[length-strNumberToFill.length()];
		stringFilled = new StringBuffer(0);
		
		Arrays.fill(fill, withChar);
		stringFilled.append(fill);
		stringFilled.append(strNumberToFill);
		
		return stringFilled.toString();
	}
}
