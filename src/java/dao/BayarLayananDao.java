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
import model.BayarLayananModel;

/**
 *
 * @author dinop
 */
public class BayarLayananDao {
    private final Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public BayarLayananDao() {
        this.conn = CliniclyConnection.connection();
    }
    
    public String getNewIdBayarLayanan(){
        String newID = null;
        try{
            String lastID = null;
            String query="{CALL GetLastIdBayarLayanan()}";
            ps=conn.prepareCall(query);
            rs=ps.executeQuery();
            if(rs.next()){
                lastID = rs.getString("id_bayar_layanan");
                String lastNumStr = lastID.substring(2);
                int newNumInt = Integer.parseInt(lastNumStr)+1;
                String newNumStr = "000"+newNumInt;
                String fixNewNumStr = newNumStr.substring(newNumStr.length()-4);
                newID = "BL"+fixNewNumStr;
            }
            else{
                newID = "BL0001";
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        return newID;
    }
    
    public void save(BayarLayananModel model,String page){
        try{
            String query = null;
            String doneMessage = null;
            if(page.equals("insert")){
                query="{CALL InsertBayarLayanan(?,?,?,?,?,?,?)}";
                doneMessage = "Insert success!";
            }
            else if(page.equals("update")){
                query="{CALL UpdateBayarLayanan(?,?,?,?,?,?,?)}";
                doneMessage = "Update success!";
            }
            
            ps=conn.prepareCall(query);
            ps.setString(1, model.getIdBayarLayanan());
            ps.setString(2, model.getIdLayanan());
            ps.setString(3, model.getIdDetailLayanan());
            ps.setString(4, model.getIdPasien());
            ps.setDate(5, model.getTglLayanan());
            ps.setString(6, model.getKeterangan());
            ps.setString(7, model.getUserId());
            ps.executeUpdate();
            System.out.println(doneMessage);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        BayarLayananDao dao = new BayarLayananDao();
        System.out.println(dao.getNewIdBayarLayanan());
    }
}
