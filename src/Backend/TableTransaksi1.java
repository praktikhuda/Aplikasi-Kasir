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
public class TableTransaksi1 {
    private String id_riwayat;
    private String id_barang;
    private int qty;
    private int total;
    
    private Connection con;
    private Statement st;
    private String sql;
    private ResultSet rs;
    private String error;
    private Object[] baris;
    private ArrayList<Object[]> listRiwayat;
    
    public String getId_riwayat(String id_riwayat){
        return id_riwayat;
    }
    public void setId_riwayat(String id){
        this.id_riwayat = id;
    }
    public String getId_barang(){
        return id_barang;
    }
    public void setId_barang(String id_barang){
        this.id_barang = id_barang;
    }
    public int getQty(){
        return qty;
    }
    public void setQty(int qty){
        this.qty = qty;
    }
    public int getTotal(){
        return total;
    }
    public void setTotal(int total){
        this.total = total;
    }
    
    private ArrayList baca(){
        listRiwayat = new ArrayList<Object[]>();
        error = null;
        try {
            con = Koneksi.getKoneksi();
            st = con.createStatement();
            sql = "select * from riwayat";
            rs = st.executeQuery(sql);
            
            while(rs.next()){
                id_riwayat = rs.getString("id_riwayat");
                id_barang = rs.getString("id_barang");
                qty = rs.getInt("qty");
                total = rs.getInt("total_harga");
                baris = new Object[] {id_riwayat, id_barang, qty, total};

                listRiwayat.add(baris);
            }
            st.close(); 
            rs.close(); 
            con.close(); 
        }catch(Exception e){
            error = "gagal baca tabel";
        }
        return listRiwayat;
    }
    public ArrayList<Object[]> getSemua(){
        return baca();
    }
}
