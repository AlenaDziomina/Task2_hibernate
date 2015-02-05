package com.epam.testapp.util.query;

public class QueryAppend {
    
    public static void append(StringBuilder sb, String paramName, String delimeter){
        if (sb.length() == 0) {
            sb.append(" WHERE ");
        } else {
            sb.append(delimeter);
        }
        sb.append(paramName);
        sb.append(" = ? ");
    }
    
}
