    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author lenovo
 */
public class TableRiwayat {
    private String riwayat;
    private String id_barang;
    private int qty;
    private int total_harga;
    private int banyak;
    
    private String kode_barang;
    private String nama_barang;
    private int stok;
    private int harga;
    
    private String id_diskon;
    private int diskon;
    private String status;
    private int min_belanja;
    
    private String id_kasir;
    private String waktu;   
    private int bayar;
    private int kembalian;
    
    private Connection con;
    private Statement st;
    private String sql;
    private ResultSet rs;
    private String error;
    private Object[] baris;
    private ArrayList<Object[]> listBarang;
    
//    Table Riwayat
    public String getRiwayat(){
        return riwayat;
    }
    public void setRiwayat(String riwayat){
        this.riwayat = riwayat;
    }
    public String getId_Barang(){
        return id_barang;
    }
    public void setId_Barang(String id_barang){
        this.id_barang = id_barang;
    }
    public int getQty(){
        return qty;
    }
    public void setQty(int qty){
        this.qty = qty;
    }
    public int getTotal_Harga(){
        return total_harga;
    }
    public void setTotal_Harga(int total_harga){
        this.total_harga = total_harga;
    }
    public int getBanyak(){
        return banyak;
    }
    public void setBanyak(int banyak){
        this.banyak = banyak;
    }
    
//    Table Barang
    public String getKode_Barang(){
        return kode_barang;
    }
    public void setKode_Barang(String kode_barang){
        this.kode_barang = kode_barang;
    }
    public String getNama_Barang(){
        return nama_barang;
    }
    public void setNama_Barang(String nama_barang){
        this.nama_barang = nama_barang;
    }
    public int getHarga(){
        return harga;
    }
    public void setHarga(int harga){
        this.harga = harga;
    }
    public int getstok(){
        return stok;
    }
    public void setStok(int stok){
        this.stok = stok;
    }
//    Table Diskon
    public String getId_Diskon(){
        return id_diskon;
    }
    public void setId_Diskon(String id_diskon){
        this.id_diskon = id_diskon;
    }
    public int getDiskon(){
        return diskon;
    }
    public void setDiskon(int diskon){
        this.diskon = diskon;
    }
    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public int getMin_Belanja(){
        return min_belanja;
    }
    public void setMin_Belaja(int min_belanja){
        this.min_belanja = min_belanja;
    }
    
//    Table Transaksi
    public String getId_Kasir(){
        return id_kasir;
    }
    public void setId_Kasir(String id_kasir){
        this.id_kasir = id_kasir;
    }
    public String getWaktu(){
        return waktu;
    }
    public void setWaktu(String waktu){
        this.waktu = waktu;
    }
    public int getBayar(){
        return bayar;
    }
    public void setBayar(int bayar){
        this.bayar = bayar;
    }
    public int getKembalian(){
        return kembalian;
    }
    public void setKembalian(int kembalian){
        this.kembalian = kembalian;
    }
    
    
    
    public String generateRandomString(int length) {
        String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; // karakter yang diizinkan
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(allowedChars.length());
            sb.append(allowedChars.charAt(randomIndex));
        }
        return sb.toString();
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
        sql = "insert into riwayat values ('" +riwayat + "', '" + id_barang + "', " +qty + ", " +total_harga+ ")"; 
        return ganti(sql);
    }
    
    public int tambahTransaksi(){
        sql = "insert into transaksi values ('" +riwayat + "', '" + id_kasir + "', '" +waktu + "', '" +id_diskon+ "', '"+total_harga+"', '"+bayar+"', '"+kembalian+"')";
        return ganti(sql);
    }
    
    public boolean cariBarang(String id_barang){
        boolean ketemu = false;
        try {
            con = Koneksi.getKoneksi();
            st = con.createStatement();
            sql = "select * from barang where id_barang = '" + id_barang + "'";
            rs = st.executeQuery(sql);
            if(rs.next()){
                
                this.setId_Barang(rs.getString("id_barang"));
                this.setNama_Barang(rs.getString("nama_barang"));
                this.setStok(rs.getInt("stok"));
                this.setHarga(rs.getInt("harga"));
                ketemu = true;
            }else{
                System.out.println("Tidak menemukan barang yang di cari!!");
                ketemu = false;
            }
            st.close();
            con.close();
        }catch(Exception e){
            System.out.println("Gagal akses database");
        }
        return ketemu;
    }
    
    private ArrayList baca(String where, String order){
        listBarang = new ArrayList<Object[]>();
        error = null;
        try {
            con = Koneksi.getKoneksi();
            st = con.createStatement();

    //            sql = "select * from barang where id_barang = '"+where+"'";
    //            sql = "select * from riwayat where id_riwayat = '"+where+"'";
                sql = "SELECT riwayat.id_riwayat, riwayat.id_barang, barang.nama_barang, barang.harga, barang.stok, riwayat.qty, riwayat.total_harga FROM barang "
                        + "INNER JOIN riwayat ON barang.id_barang = riwayat.id_barang "
                        + "WHERE riwayat.id_riwayat = '"+where+"'";



            rs = st.executeQuery(sql);
            while(rs.next()){

                riwayat = rs.getString("id_riwayat");
                id_barang = rs.getString("id_barang");
                qty = rs.getInt("qty");

                total_harga = rs.getInt("total_harga");

                nama_barang = rs.getString("nama_barang");
                harga = rs.getInt("harga");
                stok = rs.getInt("stok");

    //            baris = new Object[] {riwayat, id_barang, qty, total_harga};
                    baris = new Object[] {riwayat, id_barang, nama_barang, harga, stok, qty, total_harga};

                listBarang.add(baris);
            }
            st.close(); 
            rs.close(); 
            con.close(); 
        }catch(Exception e){
            error = "gagal baca tabel";
        }
        return listBarang;
    }

    public ArrayList<Object[]> getSemua(String where, String orderField){
        return baca(where, orderField);
    }
    
    public int hapus(String kodeBrg, String idRan){
        sql = "delete from riwayat where id_barang = '" + kodeBrg + "' and id_riwayat = '"+idRan+"'";
        return ganti(sql);
    }
    
    public boolean jumHarga(String id_barang){
        boolean ketemu = false;
        try {
            con = Koneksi.getKoneksi();
            st = con.createStatement();
            sql = "select sum(total_harga) as banyak from riwayat where id_riwayat = '" + id_barang + "'";
            rs = st.executeQuery(sql);
            if(rs.next()){
                
                this.setBanyak(rs.getInt("banyak"));

                ketemu = true;
            }else{
                System.out.println("Tidak menemukan barang yang di cari!!");
                ketemu = false;
            }
            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Gagal akses database");
        }
        return ketemu;
    }
    
    public boolean cariDiskon(String id_diskon){
        boolean ketemu = false;
        try {
            con = Koneksi.getKoneksi();
            st = con.createStatement();
            sql = "select * from diskon where id_diskon = '" + id_diskon + "'";
            rs = st.executeQuery(sql);
            if(rs.next()){
                this.setId_Diskon(rs.getString("id_diskon"));
                this.setDiskon(rs.getInt("diskon"));
                this.setStatus(rs.getString("status"));
                this.setMin_Belaja(rs.getInt("min_belanja"));
                ketemu = true;
            }else{
                System.out.println("Tidak menemukan diskon yang di cari!!");
                ketemu = false;
            }
            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Gagal akses database");
        }
        return ketemu;
    }
}
