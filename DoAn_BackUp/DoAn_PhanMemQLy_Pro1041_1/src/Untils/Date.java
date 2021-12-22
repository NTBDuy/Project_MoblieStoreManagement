/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Untils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Duy
 */
public class Date {
    static SimpleDateFormat formater = new SimpleDateFormat();
    public static java.util.Date toDate(String date, String pattern) {
        try {
            formater.applyPattern(pattern);
            return formater.parse(date);
        } 
        catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }
    public static String toString(java.util.Date date, String pattern) {
        formater.applyPattern(pattern);
        return formater.format(date);
    }
}
