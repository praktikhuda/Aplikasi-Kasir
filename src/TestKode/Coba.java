/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestKode;
import Backend.Koneksi;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
/**
 *
 * @author lenovo
 */
public class Coba {
    private String nama;
    private String id;
    private String user;
    
    private Connection con;
    private Statement st;
    private String sql;
    private ResultSet rs;
    private String error;

    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
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
