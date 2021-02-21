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
import model.SupplierModel;

/**
 *
 * @author dinop
 */
public class SupplierDao {
    private final Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public SupplierDao() {
        this.conn = CliniclyConnection.connection();
    }
    
    public String getNewIdSupplier(){
        String newID = null;
        try{
            String lastID = null;
            String query="{CALL GetLastIdSupplier()}";
            ps=conn.prepareCall(query);
            rs=ps.executeQuery();
            if(rs.next()){
                lastID = rs.getString("id_supplier");
                String lastNumStr = lastID.substring(2);
                int newNumInt = Integer.parseInt(lastNumStr)+1;
                String newNumStr = "000"+newNumInt;
                String fixNewNumStr = newNumStr.substring(newNumStr.length()-4);
                newID = "SU"+fixNewNumStr;
            }
            else{
                newID = "SU0001";
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        return newID;
    }
    
    public void save(SupplierModel model,String page){
        try{
            String query = null;
            String doneMessage = null;
            if(page.equals("insert")){
                query="{CALL InsertSupplier(?,?,?,?,?,?,?)}";
                doneMessage = "Insert success!";
            }
            else if(page.equals("update")){
                query="{CALL UpdateSupplier(?,?,?,?,?,?,?)}";
                doneMessage = "Update success!";
            }
            
            ps=conn.prepareCall(query);
            ps.setString(1, model.getIdSupplier());
            ps.setString(2, model.getNamaSupplier());
            ps.setString(2, model.getAlamat());
            ps.setString(2, model.getNoTelp());
            ps.setString(2, model.getEmail());
            ps.setString(2, model.getUserrID());
            ps.setDate(2, model.getWaktu());
            ps.executeUpdate();
            System.out.println(doneMessage);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        SupplierDao dao = new SupplierDao();
        System.out.println(dao.getNewIdSupplier());
    }
}
