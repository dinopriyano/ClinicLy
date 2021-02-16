/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.CliniclyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

/**
 *
 * @author dinop
 */
public class LoginDao {
    
    private final Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    private SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");

    public LoginDao() {
        this.conn = CliniclyConnection.connection();
    }
    
    public String loginUser(String email,String pass){
        String status = null;
        try{
            String query="SELECT * FROM user WHERE email = ? AND password = ?";
            ps=conn.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, pass);
            rs=ps.executeQuery();
            if(rs.next()){
                status = "Login berhasil";
            }
            else{
                status = "Login gagal! Cek email dan password";
            }
        }
        catch(Exception e){
            status = "Terjadi error";
        }
        return status;
    }
    
    public static void main(String[] args) {
        LoginDao dao = new LoginDao();
        System.out.println(dao.loginUser("jono@email.com","passw"));
    }
    
}
