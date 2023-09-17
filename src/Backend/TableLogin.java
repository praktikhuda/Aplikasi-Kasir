/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import java.util.Scanner;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author lenovo
 */
public class TableLogin {
    private Connection con;
    private Statement st;
    private String sql;
    private ResultSet rs;
    private String error;
    
    private String id_kasir;
    private String user;
    private String pass;
    
    public String getid_Kasir(){
        return id_kasir;
    }
    public void setid_Kasir(String id_kasir){
        this.id_kasir = id_kasir;
    }
    public String getUser(){
        return user;
    }
    public void setUser(String user){
        this.user = user;
    }
    public String getPass(){
        return pass;
    }
    public void setPass(String pass){
        this.pass = pass;
    }
    
    public String getMD5(String passwd) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(passwd.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean CariUser(String nama){
        boolean ketemu = false;
        try {
            con = Koneksi.getKoneksi();
            st = con.createStatement();
            sql = "select * from kasir where user = '" + nama + "'";
            rs = st.executeQuery(sql);
            if(rs.next()){
                this.id_kasir = rs.getString("id_kasir");
                this.user = rs.getString("user");
                this.pass = rs.getString("pass");
                ketemu = true;  
            }else{
                ketemu = false;
            }
            
            st.close();
            con.close();
        } catch (Exception e) {}
            return ketemu;
    }
    public static void main(String [] agrs){
        TableLogin tl = new TableLogin();
        Scanner in = new Scanner(System.in);
        String pwbenar;
            System.out.print("Cari username : ");
            String user = in.next();
            System.out.print("Masukan Pass : ");
            String Pass = in.next();
            
            boolean ketemu = tl.CariUser(user);
            String PassMD5 = tl.getMD5(Pass);
            System.out.println("Pass : "+PassMD5);
            if(ketemu){
                String id = tl.getid_Kasir();
                String User = tl.getUser();
                String pass = tl.getPass();
                if(pass.equals(PassMD5)){
                    System.out.println("Ajim");
                    System.out.println("id : "+id);
                    System.out.println("Username : "+User);
                    System.out.println("Password : "+pass);
                }else{
                    System.out.println("Password Salah!!");
                }
            }else{
                System.out.println("Username tidak ketemu!!");
            }
    }
}
