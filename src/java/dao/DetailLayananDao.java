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
import model.DetailLayananModel;
import model.LayananModel;

/**
 *
 * @author dinop
 */
public class DetailLayananDao {
    private final Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public DetailLayananDao() {
        this.conn = CliniclyConnection.connection();
    }
    
    public String getNewIdDetailLayanan(String idLayanan){
        String newID = null;
        try{
            String lastID = null;
            String query="{CALL GetLastIdDetailLayanan(?)}";
            ps=conn.prepareCall(query);
            ps.setString(1, idLayanan);
            rs=ps.executeQuery();
            if(rs.next()){
                lastID = rs.getString("id_detail_layanan");
                String lastNumStr = lastID.substring(2);
                int newNumInt = Integer.parseInt(lastNumStr)+1;
                String newNumStr = "000"+newNumInt;
                String fixNewNumStr = newNumStr.substring(newNumStr.length()-4);
                newID = "DL"+fixNewNumStr;
            }
            else{
                newID = "DL0001";
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        return newID;
    }
    
    public void save(DetailLayananModel model,String page){
        try{
            String query = null;
            String doneMessage = null;
            if(page.equals("insert")){
                query="{CALL InsertDetailLayanan(?,?,?,?,?)}";
                doneMessage = "Insert success!";
            }
            else if(page.equals("update")){
                query="{CALL UpdateDetailLayanan(?,?,?,?,?)}";
                doneMessage = "Update success!";
            }
            
            ps=conn.prepareCall(query);
            ps.setString(1, model.getIdLayanan());
            ps.setString(2, model.getIdDetailLayanan());
            ps.setString(3, model.getDeskripsi());
            ps.setDouble(4, model.getBiayaLayanan());
            ps.setString(5, model.getKeterangan());
            ps.executeUpdate();
            System.out.println(doneMessage);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        DetailLayananDao dao = new DetailLayananDao();
        System.out.println(dao.getNewIdDetailLayanan("123456"));
    }
    
}
