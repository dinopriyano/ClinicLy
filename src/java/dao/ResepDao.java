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
import model.ResepModel;

/**
 *
 * @author dinop
 */
public class ResepDao {
    private final Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public ResepDao() {
        this.conn = CliniclyConnection.connection();
    }
    
    public String getNewIdResep(){
        String newID = null;
        try{
            String lastID = null;
            String query="{CALL GetLastIdResep()}";
            ps=conn.prepareCall(query);
            rs=ps.executeQuery();
            if(rs.next()){
                lastID = rs.getString("id_resep");
                String lastNumStr = lastID.substring(2);
                int newNumInt = Integer.parseInt(lastNumStr)+1;
                String newNumStr = "000"+newNumInt;
                String fixNewNumStr = newNumStr.substring(newNumStr.length()-4);
                newID = "RP"+fixNewNumStr;
            }
            else{
                newID = "RP0001";
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        return newID;
    }
    
    public void save(ResepModel model,String page){
        try{
            String query = null;
            String doneMessage = null;
            if(page.equals("insert")){
                query="{CALL InsertResep(?,?,?,?,?,?)}";
                doneMessage = "Insert success!";
            }
            else if(page.equals("update")){
                query="{CALL UpdateResep(?,?,?,?,?,?)}";
                doneMessage = "Update success!";
            }
            
            ps=conn.prepareCall(query);
            ps.setString(1, model.getIdResep());
            ps.setString(2, model.getIdDokter());
            ps.setDate(2, model.getTglResep());
            ps.setString(2, model.getIdPoli());
            ps.setString(2, model.getUserId());
            ps.setDate(2, model.getWaktu());
            ps.executeUpdate();
            System.out.println(doneMessage);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        ResepDao dao = new ResepDao();
        System.out.println(dao.getNewIdResep());
    }
}
