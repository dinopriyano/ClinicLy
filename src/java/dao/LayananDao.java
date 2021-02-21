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
import model.KaryawanModel;
import model.LayananModel;

/**
 *
 * @author dinop
 */
public class LayananDao {
    private final Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public LayananDao() {
        this.conn = CliniclyConnection.connection();
    }
    
    public String getNewIdLayanan(){
        String newID = null;
        try{
            String lastID = null;
            String query="{CALL GetLastIdLayanan()}";
            ps=conn.prepareCall(query);
            rs=ps.executeQuery();
            if(rs.next()){
                lastID = rs.getString("id_layanan");
                String lastNumStr = lastID.substring(2);
                int newNumInt = Integer.parseInt(lastNumStr)+1;
                String newNumStr = "000"+newNumInt;
                String fixNewNumStr = newNumStr.substring(newNumStr.length()-4);
                newID = "LY"+fixNewNumStr;
            }
            else{
                newID = "LY0001";
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        return newID;
    }
    
    public void save(LayananModel model,String page){
        try{
            String query = null;
            String doneMessage = null;
            if(page.equals("insert")){
                query="{CALL InsertLayanan(?,?)}";
                doneMessage = "Insert success!";
            }
            else if(page.equals("update")){
                query="{CALL UpdateLayanan(?,?)}";
                doneMessage = "Update success!";
            }
            
            ps=conn.prepareCall(query);
            ps.setString(1, model.getIdLayanan());
            ps.setString(2, model.getNamaLayanan());
            ps.executeUpdate();
            System.out.println(doneMessage);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        LayananDao dao = new LayananDao();
        System.out.println(dao.getNewIdLayanan());
    }
}
