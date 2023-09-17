/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestKode;

import java.text.DateFormat;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.Calendar;  

/**
 *
 * @author lenovo
 */
public class tes {
    public static String polindromeGod(String kata){
       kata = kata.replaceAll("[\\s\\p{Punct}]", "");
        String kata1 = kata;
        return kata1;
    }
    public static void main(String [] agrs){

        Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("yy-dd-MM hh:dd:ss");  
        String strDate = dateFormat.format(date);  
        System.out.println("Converted String: " + strDate);  
        System.out.println("Hasil : "+polindromeGod(strDate));
    }
}
