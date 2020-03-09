package it.pizzastore.model.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	private final static SimpleDateFormat sqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
	private final static SimpleDateFormat fullCETDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z");

	/**
	 * @return una stringa yyyy-MM-dd
	 */
	public static String convertDateToSqlDateString(Date date) {
		return sqlDateFormat.format(date);
	}

	public static Date convertSqlStringToDate(String sqlDate) {
		try {
			return simpleDateFormat.parse(sqlDate);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * @return una stringa dd-MM-yyyy
	 */
	public static String convertDateToSimpleDateString(Date date) {
		return simpleDateFormat.format(date);
	}

	/**
	 * 
	 * @param simpleDate una stringa dd-MM-yyyy
	 * @return oggetto Date
	 */
	public static Date convertSimpleDateStringToDate(String simpleDate) {
		try {
			return simpleDateFormat.parse(simpleDate);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * @return una stringa dd-MM-yyyy HH:mm:ss z
	 */
	public static String convertDateToFullCETDateString(Date date) {
		return fullCETDateFormat.format(date);
	}

	/**
	 * 
	 * @param fullCETString una stringa dd-MM-yyyy HH:mm:ss z
	 * @return oggetto Date
	 */
	public static Date ConvertFullCETStringToDate(String fullCETString) {
		try {
			return fullCETDateFormat.parse(fullCETString);
		} catch (Exception e) {
		}
		return null;
	}

}
