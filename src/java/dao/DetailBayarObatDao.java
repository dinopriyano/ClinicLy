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
import model.DetailBayarObatModel;

/**
 *
 * @author dinop
 */
public class DetailBayarObatDao {
    private final Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public DetailBayarObatDao() {
        this.conn = CliniclyConnection.connection();
    }
    
    public void save(DetailBayarObatModel model,String page){
        try{
            String query = null;
            String doneMessage = null;
            if(page.equals("insert")){
                query="{CALL InsertDetailBayarObat(?,?,?,?,?)}";
                doneMessage = "Insert success!";
            }
            else if(page.equals("update")){
                query="{CALL UpdateDetailBayarObat(?,?,?,?,?)}";
                doneMessage = "Update success!";
            }
            
            ps=conn.prepareCall(query);
            ps.setString(1, model.getIdPembayaran());
            ps.setString(2, model.getIdObat());
            ps.setString(3, model.getNoFaktur());
            ps.setDouble(4, model.getHarga());
            ps.setDouble(5, model.getJumlah());
            ps.executeUpdate();
            System.out.println(doneMessage);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        
    }
}
