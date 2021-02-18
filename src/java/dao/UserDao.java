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

/**
 *
 * @author dinop
 */
public class UserDao {
    private final Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public UserDao() {
        this.conn = CliniclyConnection.connection();
    }
    
    public void disableUser(String userID){
        try{
            String lastID = null;
            String query="{CALL DisableUser(?)}";
            ps=conn.prepareCall(query);
            ps.setString(1, userID);
            rs=ps.executeQuery();
            System.out.println("Success disable user");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void enableUser(String userID){
        try{
            String lastID = null;
            String query="{CALL EnableUser(?)}";
            ps=conn.prepareCall(query);
            ps.setString(1, userID);
            rs=ps.executeQuery();
            System.out.println("Success enable user");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    public static void main(String[] args) {
        UserDao dao = new UserDao();
        dao.enableUser("US0003");
    }
}
