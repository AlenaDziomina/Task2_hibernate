package com.epam.testapp.util.converter;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DataConverter {
    
    private static final String DATE_FORMAT = "MM/dd/yyyy";
    private static final SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
    
    public static String toFormatString(java.sql.Date date){
        if (date == null) {
            date = new Date(new java.util.Date().getTime());
        }
        return formatter.format(date);
    }
    
    public static Date toSqlDate(String strDate) throws ParseException {
        java.util.Date date = formatter.parse(strDate);
        return new Date(date.getTime());
    }
    
    public static Date toSqlDate() {
        java.util.Date currDate = new java.util.Date();
        return new Date(currDate.getTime());
    }
    
    
}
