/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestKode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Locale;

/**
 *
 * @author lenovo
 */
public class TestKode {
    private String nama;
    
    public String bulan(){
        LocalDateTime ldt = LocalDateTime.now();
        LocalDate localDate = LocalDate.now();
        System.out.println(ldt);
        int bul = localDate.getMonthValue(); 
        int tahun = localDate.getYear();
        System.out.println(tahun);
        if(bul ==1){
            nama = "Januari";
        }else if(bul == 2){
            nama = "Febuari";
        }else if(bul ==3){
            nama = "Maret";
        }else if(bul == 4){
            nama = "April";
        }else if(bul == 5){
            nama = "Mei";
        }else if(bul == 6){
            nama = "Juni";
        }else if(bul == 7){
            nama = "Juli";
        }else if(bul == 8){
            nama = "Agustus";
        }else if(bul == 9){
            nama = "September";
        }else if(bul == 10){
            nama = "Oktober";
        }else if(bul == 11){
            nama = "November";
        }else{
            nama = "Desember";
        }
        return nama;
    }
    public static void main(String[] args) {
        TestKode tk = new TestKode();
        System.out.println(tk.bulan());
        
//        String namaBulan = Month.of(bul).name().toLowerCase();
//        System.out.println(namaBulan);
    }
}
