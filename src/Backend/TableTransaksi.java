/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import TestKode.TestId;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author lenovo
 */
public class TableTransaksi {
    private Connection con;
    private Statement st;
    private String sql;
    private ResultSet rs;    
    private String error;
    
    private int angka;
    private int jum;
    private int ban;
    private int id;
    
    public void setBan(int ban){
        this.ban = ban;
    }
    public void setAngka(int angka){
        this.angka = angka;
    }
    public void setJum(int jum){
        this.jum = jum;
    }
    public int banding(){
        try{
            con = Koneksi.getKoneksi();
            st = con.createStatement();
            sql = "SELECT id_transaksi, LEFT(id_transaksi, 6) AS ban FROM transaksi ORDER BY waktu DESC;";
            rs = st.executeQuery(sql);
            if(rs.next()){
                this.setBan(rs.getInt("ban"));
            }else{
                System.out.println("Tidak menemukan barang yang di cari!!");
            }
            st.close();
            con.close();
        }catch(Exception e){
            e.getMessage();
            System.out.println("Gagal akses database");
        }
        return ban;
    }
    public int cari1(){
        try {
            con = Koneksi.getKoneksi();
            st = con.createStatement();
            sql = "SELECT RIGHT(id_transaksi, 2) as idb FROM transaksi ORDER BY waktu DESC;";
            rs = st.executeQuery(sql);
            if(rs.next()){
                this.setAngka(rs.getInt("idb"));
            }else{
                System.out.println("Tidak menemukan barang yang di cari!!");
            }
            st.close();
            con.close();
        }catch(Exception e){
            e.getMessage();
            System.out.println("Gagal akses database");
        }
        return angka;
    }
    public int cari2(){
        try {
            con = Koneksi.getKoneksi();
            st = con.createStatement();
            sql = "SELECT RIGHT(id_transaksi, 2) as idb FROM transaksi ORDER BY waktu DESC;";
            rs = st.executeQuery(sql);
            if(rs.next()){
                this.setAngka(rs.getInt("idb"));
            }else{
                System.out.println("Tidak menemukan barang yang di cari!!");
            }
            st.close();
            con.close();
        }catch(Exception e){
            e.getMessage();
            System.out.println("Gagal akses database");
        }
        return angka;
    }
    
    private  int nomorUrut(){
        return angka+1;
    }
    
    public int jumlahHuruf(){
        try {
            con = Koneksi.getKoneksi();
            st = con.createStatement();
            sql = "SELECT id_transaksi, MAX(length(id_transaksi)) AS jumlah FROM transaksi;";
            rs = st.executeQuery(sql);
            if(rs.next()){
                this.setJum(rs.getInt("jumlah"));
            }else{
                System.out.println("Tidak menemukan barang yang di cari!!");
            }
            st.close();
            con.close();
        }catch(Exception e){
            e.getMessage();
            System.out.println("Gagal akses database");
        }
        return jum;
    }
    
    private String getFormattedNomorUrut() {
        TableTransaksi tt = new TableTransaksi();
        int no = nomorUrut();
        String formattedNomorUrut = String.format("%03d", no);
        no++;

        return formattedNomorUrut;
    }
    
    public int GodId(){
        TableTransaksi ti = new TableTransaksi();
//        Kode Convert Tanggal Sekarang 
        LocalDate currentTime = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
        String id2 = currentTime.format(formatter);
        String id;
        int c1 = ti.cari1();
        int c2 = ti.cari2();
//        Kode untuk perbandingan apakah tanggal sekarang sudah dipakai untuk id transaksi
        if(ti.banding() == Integer.parseInt(id2)){
            System.out.println("Uwu");
            
//            Kode untuk menambahkan urutan id
            if(c1 > 10){
                id = id2 + ti.getFormattedNomorUrut();
                System.out.println("ID: " + id);
                System.out.println("Lebih Besar");
            }else{
                id = id2 + ti.getFormattedNomorUrut();
                System.out.println("ID: " + id);
                System.out.println("Lebih Kecil");
            }
        }else{
            id = id2 + "001";
            System.out.println(id);
        }
        int id3 = Integer.parseInt(id);
        return Integer.parseInt(id);
    }
    public static void main(String []agrs){
        TableTransaksi tt = new TableTransaksi();
        tt.GodId();
        System.out.println("hehe");
        tt.banding();
    }
    
    private String nama;
    private String idUser;
    private String user;
    
    public String getId(){
        return idUser;
    }
    public void setId(String id){
        this.idUser = id;
    }
    public String getUser(){
        return user;
    }
    public void setUser(String user){
        this.user = user;
    }
    
    public String getNama(){
        return nama;
    }
    public void setNama(String nama){
        this.nama = nama;
    }
    public void tampil(){
        System.out.println(nama);
    }
    
    public int ganti(String sql){
        int n = 0;
        try {
            con = Koneksi.getKoneksi();
            st = con.createStatement();
            n = st.executeUpdate(sql);
            st.close(); 
            rs.close(); 
            con.close(); 
        }catch(Exception e){
            error = "gagal baca tabel";
        }
        System.out.println("sql: "+sql);
        System.out.println("n: "+n);
        return n;
    }
    public int tambah(){
        LocalDateTime ldt = LocalDateTime.now();
        sql = "insert into log values ('"+id+"', '"+user+"', '"+ldt+"')"; 
        return ganti(sql);
    }
    public String cari(){
        try {
            con = Koneksi.getKoneksi();
            st = con.createStatement();
            sql = "select user from log ORDER BY tanggal desc";
            rs = st.executeQuery(sql);
            if(rs.next()){
                this.setNama(rs.getString("user"));
            }else{
                System.out.println("Tidak menemukan barang yang di cari!!");
            }
            st.close();
            con.close();
        }catch(Exception e){
            System.out.println("Gagal akses database");
        }
        return nama;
    }
    
}
