/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

/**
 *
 * @author lenovo
 */
public class TableDashboard {
    private int totalBarang;
    private int totalDiskon;
    private int totalRiwayat;
    private int totalPenghasilan;
    private String nama;
    
    private Connection con;
    private Statement st;
    private String sql;
    private ResultSet rs;
    private String error;
    
    public int getTotalBarang(){
        return totalBarang;
    }
    public void setTotalBarang(int total){
        this.totalBarang = total;
    }
    public int getTotalDiskon(){
        return totalDiskon;
    }
    public void setTotalDiskon(int total){
        this.totalDiskon = total;
    }
    public int getTotalRiwayat(){
        return totalRiwayat;
    }
    public void setTotalRiwayat(int total){
        this.totalRiwayat = total;
    }
    public int getTotalPenghasilan(){
        return totalPenghasilan;
    }
    public void setTotalPenghasilan(int total){
        this.totalPenghasilan = total;
    }
    
    
    public int totalBarang(){
        try {
            con = Koneksi.getKoneksi();
            st = con.createStatement();
            sql = "select count(*) from barang";
            rs = st.executeQuery(sql);
            if(rs.next()){
                this.setTotalBarang(rs.getInt(1));
                return totalBarang;
            }else{
                System.out.println("Tidak menemukan barang yang di cari!!");
            }
            st.close();
            con.close();
        }catch(Exception e){
            System.out.println("Gagal akses database");
        }
        return totalBarang;
    }
    
        public int totalDiskon(){
        try {
            con = Koneksi.getKoneksi();
            st = con.createStatement();
            sql = "select count(*) from diskon";
            rs = st.executeQuery(sql);
            if(rs.next()){
                this.setTotalDiskon(rs.getInt(1));
                return totalDiskon;
            }else{
                System.out.println("Tidak menemukan barang yang di cari!!");
            }
            st.close();
            con.close();
        }catch(Exception e){
            System.out.println("Gagal akses database");
        }
        return totalDiskon;
    }
        
        public int totalRiwayat(){
        try {
            con = Koneksi.getKoneksi();
            st = con.createStatement();
            sql = "select count(*) from riwayat";
            rs = st.executeQuery(sql);
            if(rs.next()){
                this.setTotalRiwayat(rs.getInt(1));
                return totalRiwayat;
            }else{
                System.out.println("Tidak menemukan barang yang di cari!!");
            }
            st.close();
            con.close();
        }catch(Exception e){
            System.out.println("Gagal akses database");
        }
        return totalRiwayat;
    }

    public int totalPenghasilan(int bulan, int tahun){
        try {
            con = Koneksi.getKoneksi();
            st = con.createStatement();
            sql = "SELECT SUM(total_harga) AS harga, MONTH(waktu)  FROM `transaksi` WHERE  MONTH(waktu) = '"+bulan+"' and YEAR(waktu) = '"+tahun+"';";
            rs = st.executeQuery(sql);
            if(rs.next()){
                this.setTotalPenghasilan(rs.getInt(1));
                return totalPenghasilan;
            }else{
                System.out.println("Tidak menemukan barang yang di cari!!");
            }
            st.close();
            con.close();
        }catch(Exception e){
            System.out.println("Gagal akses database");
        }
        return totalPenghasilan;
    }
    
    public int hitungBulan(){
        LocalDate localDate = LocalDate.now(); 
        int bulan = localDate.getMonthValue();
        int tahun = localDate.getYear();
        
        TableDashboard td = new TableDashboard();
        int a = td.totalBarang();
        int b = td.totalDiskon();
        int c = td.totalRiwayat();
        
        totalPenghasilan = td.totalPenghasilan(bulan, tahun);
        return totalPenghasilan;
    }
    public String bulan(){
        LocalDate localDate = LocalDate.now();
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

        
}
