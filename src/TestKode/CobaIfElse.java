/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestKode;
import Backend.Koneksi;
//import Backend.TableRiwayat;
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
//import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
/**
 *
 * @author lenovo
 */
public class CobaIfElse {
    private Connection con;
    private Statement st;
    private String sql;
    private ResultSet rs;
    private String error;
    
    private String  id;
    private String waktu;
    private String id_diskon;
    private int total;
    private int bayar;
    private int kembalian;
    
    private int harga;
    private int qty;
    private String nama;
    
    private ArrayList<Object[]> listBarang;
    private Object[] baris;
    
//    Table Transaksi
    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getWaktu(){
        return waktu;
    }
    public void setWaktu(String waktu){
        this.waktu = waktu;
    }
    public String getIddiskon(){
        return id_diskon;
    }
    public void setIddiskon(String id){
        this.id_diskon = id;
    }
    public int getBayar(){
        return bayar;
    }
    public void setBayar(int bayar){
        this.bayar = bayar;
    }
    public int getKembali(){
        return kembalian;
    }
    public void setKembali(int kem){
        this.kembalian = kem;
    }
    public int getTotal(){
        return total;
    }
    public void setTotal(int total){
        this.total = total;
    }
    public int getQty(){
        return qty;
    }
    public void setQty(int qty){
        this.qty = qty;
    }
    public int getHarga(){
        return harga;
    }
    public void setHarga(int harga){
        this.harga = harga;
    }
    public String getNama(){
        return nama;
    }
    public void setNama(String nama){
        this.nama = nama;
    }
    
    public String cari(){        
        try {
            con = Koneksi.getKoneksi();
            st = con.createStatement();
            sql = "select id_transaksi, total_harga from transaksi where id_transaksi = '230703001'";
            rs = st.executeQuery(sql);
            if(rs.next()){
                this.setTotal(rs.getInt("total_harga"));
                this.setId(rs.getString("id_transaksi"));
            }else{
                System.out.println("Tidak menemukan barang yang di cari!!");
            }
            st.close();
            con.close();
        }catch(Exception e){
            System.out.println("Gagal akses database");
        }
        return id;
    }
        public void baca1(){
            try {
                FileWriter tul = new FileWriter("filename.txt");
                con = Koneksi.getKoneksi();
                st = con.createStatement();
                sql = "SELECT id_riwayat, qty, total_harga, barang.nama_barang FROM riwayat LEFT JOIN barang ON riwayat.id_barang = barang.id_barang WHERE riwayat.id_riwayat = '230703001';";
                rs = st.executeQuery(sql);
                while(rs.next()){
                    int qty = rs.getInt("qty");
                    tul.write("god");
                    tul.write(qty);
                    this.setQty(rs.getInt("qty"));
                    this.setHarga(rs.getInt("total_harga"));
                    this.setNama(rs.getString("nama_barang"));
                }
                System.out.println("Teks berhasil dicetak ke dalam file ");
            } catch (Exception e) {
                System.out.println("Terjadi kesalahan saat mencetak ke file: " + e.getMessage());
            }
        }
        
        public int baca(){
        try{
            con = Koneksi.getKoneksi();
            st = con.createStatement();
            sql = "SELECT id_riwayat, qty, total_harga, barang.nama_barang FROM riwayat LEFT JOIN barang ON riwayat.id_barang = barang.id_barang WHERE riwayat.id_riwayat = '230703001';";
            rs = st.executeQuery(sql);
            while(rs.next()){
                this.setQty(rs.getInt("qty"));
                this.setHarga(rs.getInt("total_harga"));
                this.setNama(rs.getString("nama_barang"));
            }
        }catch(Exception e){
            System.out.println("Gagal akses database");
        }
        return harga;
    }

    
    public static void main(String[] args) {
        CobaIfElse ci = new CobaIfElse();
        
        String textToPrint, nama;
//        System.out.println(ci.baca1());
//            textToPrint = ci.cari();
            nama = "sat";
        String fileName = "output.txt";
        

        try 
            (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {

            writer.println(ci.cari());
            writer.println(ci.getTotal());
            writer.println(nama);
            System.out.println("Teks berhasil dicetak ke dalam file " + fileName);
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat mencetak ke file: " + e.getMessage());
        }
    }
}
