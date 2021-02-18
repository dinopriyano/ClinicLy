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
import model.UserModel;
import static utils.Encryption.encryptSHA256;

/**
 *
 * @author dinop
 */
public class LoginDao {
    
    private final Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public LoginDao() {
        this.conn = CliniclyConnection.connection();
    }
    
    public UserModel loginUser(String email,String pass){
        UserModel model = new UserModel();
        try{
            String query="{CALL LoginUser(?,?)}";
            ps=conn.prepareCall(query);
            ps.setString(1, email);
            ps.setString(2, encryptSHA256(pass));
            rs=ps.executeQuery();
            if(rs.next()){
                model.setEmail(rs.getString("email"));
                model.setIdRole(rs.getString("id_role"));
                model.setIdUser(rs.getString("id_user"));
                model.setNameRole(rs.getString("des_role"));
                System.out.println("Login success!");
            }
            else{
                System.out.println("Something wrong!");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return model;
    }
    
    
    
    public static void main(String[] args) {
        LoginDao dao = new LoginDao();
        System.out.println(dao.loginUser("jono@email.com","pass").getNameRole());
    }
    
}
