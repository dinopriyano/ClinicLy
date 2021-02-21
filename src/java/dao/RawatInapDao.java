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
import model.RawatInapModel;

/**
 *
 * @author dinop
 */
public class RawatInapDao {
    private final Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public RawatInapDao() {
        this.conn = CliniclyConnection.connection();
    }
    
    public String getNewIdRawatInap(){
        String newID = null;
        try{
            String lastID = null;
            String query="{CALL GetLastIdRawatInap()}";
            ps=conn.prepareCall(query);
            rs=ps.executeQuery();
            if(rs.next()){
                lastID = rs.getString("id_rawat");
                String lastNumStr = lastID.substring(2);
                int newNumInt = Integer.parseInt(lastNumStr)+1;
                String newNumStr = "000"+newNumInt;
                String fixNewNumStr = newNumStr.substring(newNumStr.length()-4);
                newID = "RI"+fixNewNumStr;
            }
            else{
                newID = "RI0001";
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        return newID;
    }
    
    public void save(RawatInapModel model,String page){
        try{
            String query = null;
            String doneMessage = null;
            if(page.equals("insert")){
                query="{CALL InsertRawatInap(?,?,?,?,?,?)}";
                doneMessage = "Insert success!";
            }
            else if(page.equals("update")){
                query="{CALL UpdateRawatInap(?,?,?,?,?,?)}";
                doneMessage = "Update success!";
            }
            
            ps=conn.prepareCall(query);
            ps.setString(1, model.getIdRawat());
            ps.setString(2, model.getIdPasien());
            ps.setString(3, model.getIdKamar());
            ps.setDate(4, model.getTglCheckin());
            ps.setDate(5, model.getTglCheckout());
            ps.setString(6, model.getKeterangan());
            ps.executeUpdate();
            System.out.println(doneMessage);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        RawatInapDao dao = new RawatInapDao();
        System.out.println(dao.getNewIdRawatInap());
    }
}
