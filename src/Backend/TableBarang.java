/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author lenovo
 */
public class TableBarang {
    private Connection con;
    private Statement st;
    private String sql;
    private ResultSet rs;
    private String error;
    
    private String id_barang;
    private String nama;
    private int harga;
    private int stok;
    
    private Object[] baris;
    private ArrayList<Object[]> listBarang;
    
    public String getId_barang(){
        return id_barang;
    }
    public void setId_barang(String id_barang){
        this.id_barang = id_barang;
    }
    public String getNama(){
        return nama;
    }
    public void setNama(String nama){
        this.nama = nama;
    }
    public int getHarga(){
        return harga;
    }
    public void setHarga(int harga){
        this.harga = harga;
    }
    public int getStok(){
        return stok;
    }
    public void setStok(int stok){
        this.stok = stok;
    }
    
    private ArrayList baca(){
        listBarang = new ArrayList<Object[]>();
        error = null;
        try {
            con = Koneksi.getKoneksi();
            st = con.createStatement();
            sql = "select * from barang";
            rs = st.executeQuery(sql);
            
            while(rs.next()){
                id_barang = rs.getString("id_barang");
                nama = rs.getString("nama_barang");
                harga = rs.getInt("harga");
                stok = rs.getInt("stok");
                baris = new Object[] {id_barang, nama, harga, stok};

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
    public ArrayList<Object[]> getSemua(){
        return baca();
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
        sql = "insert into barang values ('" +id_barang + "', '" + nama + "', '" +harga + "', '" +stok+ "')"; 
        return ganti(sql);
    }
    
    public int hapus(String id){
        sql = "delete from barang where id_barang = '" + id +"'";
        return ganti(sql);
    }
    public boolean cari(String id){
        boolean ketemu = false;
        try {
            con = Koneksi.getKoneksi();
            st = con.createStatement();
            sql = "select * from barang where id_barang = '" + id + "'";
            rs = st.executeQuery(sql);
            if(rs.next()){
                this.setId_barang(rs.getString("id_barang"));
                this.setNama(rs.getString("nama_barang"));
                this.setHarga(rs.getInt("harga"));
                this.setStok(rs.getInt("stok"));
                ketemu = true;
            }else{
                ketemu = false;
            }
                st.close();//Close statement
                con.close(); //Close database Connection
            } catch (Exception e) {
                System.out.println("Gagal akses database");
            }
        return ketemu;
    }
    public int ubah(String id, String nama, int harga, int stok){
        sql="update barang set id_barang = '"+id+"', nama_barang = '"+nama+"', harga = '"+harga+"', stok = '"+stok+"' where id_barang = '"+id+"'";
        return ganti(sql);
    }
}
