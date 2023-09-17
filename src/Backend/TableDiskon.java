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
import java.util.Random;

/**
 *
 * @author lenovo
 */
public class TableDiskon {
    private String id_diskon;
    private int diskon;
    private String status;
    private int min_belanja;
    
    private Connection con;
    private Statement st;
    private String sql;
    private ResultSet rs;
    private String error;
    private Object[] baris;
    private ArrayList<Object[]> listBarang;
    
    public String getId_diskon(){
        return id_diskon;
    }
    public void setId_diskon(String id_diskon){
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
    public int getMin_belanja(){
        return min_belanja;
    }
    public void setMin_belanja(int min_belanja){
        this.min_belanja = min_belanja;
    }
        private ArrayList baca(){
        listBarang = new ArrayList<Object[]>();
        error = null;
        try {
            con = Koneksi.getKoneksi();
            st = con.createStatement();

            sql = "select * from diskon";



            rs = st.executeQuery(sql);
            while(rs.next()){
                id_diskon = rs.getString("id_diskon");
                diskon = rs.getInt("diskon");
                status = rs.getString("status");
                min_belanja = rs.getInt("min_belanja");
                
                baris = new Object[] {id_diskon, diskon, status, min_belanja};

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
        sql = "insert into diskon values ('" +id_diskon + "', " + diskon + ", '" +status + "', " +min_belanja+ ")"; 
        return ganti(sql);
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
    
    public int hapus(String IdDiskon){
        sql = "delete from diskon where id_diskon = '" + IdDiskon +"'";
        return ganti(sql);
    }
    public boolean cari(String id){
        boolean ketemu = false;
        try {
            con = Koneksi.getKoneksi();
            st = con.createStatement();
            sql = "select * from diskon where id_diskon = '" + id + "'";
            rs = st.executeQuery(sql);
            if(rs.next()){
                this.setId_diskon(rs.getString("id_diskon"));
                this.setDiskon(rs.getInt("diskon"));
                this.setStatus(rs.getString("status"));
                this.setMin_belanja(rs.getInt("min_belanja"));
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
    public int ubah(String id, int diskon, String status, int min){
        sql="update diskon set id_diskon = '"+id+"', diskon = '"+diskon+"', status = '"+status+"', min_belanja = '"+min+"' where id_diskon = '"+id+"'";
        return ganti(sql);
    }
}
