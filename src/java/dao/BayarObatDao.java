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
import model.BayarObatModel;

/**
 *
 * @author dinop
 */
public class BayarObatDao {
    private final Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public BayarObatDao() {
        this.conn = CliniclyConnection.connection();
    }
    
    public String getNewIdBayarObat(){
        String newID = null;
        try{
            String lastID = null;
            String query="{CALL GetLastIdBayarObat()}";
            ps=conn.prepareCall(query);
            rs=ps.executeQuery();
            if(rs.next()){
                lastID = rs.getString("id_pembayaran");
                String lastNumStr = lastID.substring(2);
                int newNumInt = Integer.parseInt(lastNumStr)+1;
                String newNumStr = "000"+newNumInt;
                String fixNewNumStr = newNumStr.substring(newNumStr.length()-4);
                newID = "BO"+fixNewNumStr;
            }
            else{
                newID = "BO0001";
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        return newID;
    }
    
    public void save(BayarObatModel model,String page){
        try{
            String query = null;
            String doneMessage = null;
            if(page.equals("insert")){
                query="{CALL InsertBayarobat(?,?,?,?,?,?,?)}";
                doneMessage = "Insert success!";
            }
            else if(page.equals("update")){
                query="{CALL UpdateBayarobat(?,?,?,?,?,?,?)}";
                doneMessage = "Update success!";
            }
            
            ps=conn.prepareCall(query);
            ps.setString(1, model.getIdPembayaran());
            ps.setDate(2, model.getTglPembayaran());
            ps.setString(3, model.getIdPasien());
            ps.setString(4, model.getIdResep());
            ps.setString(5, model.getJenisPembayaran());
            ps.setDate(6, model.getWaktu());
            ps.setString(7, model.getUserId());
            ps.executeUpdate();
            System.out.println(doneMessage);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        BayarObatDao dao = new BayarObatDao();
        System.out.println(dao.getNewIdBayarObat());
    }
}
