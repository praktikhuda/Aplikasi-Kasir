/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestKode;
import Backend.TableRiwayat;
import java.util.*;
/**
 *
 * @author lenovo
 */
public class CobaIfElse {
    public static void main(String agrs []){
        Scanner in = new Scanner (System.in);
        TableRiwayat ran = new TableRiwayat();
        System.out.print("Masukan id diskon : ");
        String KodeDiskon = in.next();
        
        boolean ketemu = ran.cariDiskon(KodeDiskon);
        String status = ran.getStatus();
        if(ketemu){
            if(status.equalsIgnoreCase("belum")){
                System.out.println("Diskon Belum dipakai!!");
            }else{
                System.out.println("Diskon Sudah dipakai!!");
            }
        }else{
            System.out.println("Tidak menemukan Kode Diskon!!");
        }
    }
}
