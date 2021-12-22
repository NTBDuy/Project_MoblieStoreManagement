/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Untils;

/**
 *
 * @author Duy
 */
public class Check {
    // Kiểm tra email
    public static boolean KTEmail(String email){
        String regexEmail = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(regexEmail);
    }
    
    // Kiểm tra số điện thoại
    public static boolean KTPhoneNo(String numberPhone){
        String regexSDT = "(\\+84|0)\\d{9,10}";
        return numberPhone.matches(regexSDT);
    }
    
    // Kiểm tra số CMND
    public static boolean KTIDNumber(String IDNo){
        String regexID = "[0-9]{9}|[0-9]{12}";
        return IDNo.matches(regexID);
    }
}
