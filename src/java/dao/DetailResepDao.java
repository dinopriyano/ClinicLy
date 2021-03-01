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
import model.DetailResepModel;

/**
 *
 * @author dinop
 */
public class DetailResepDao {
    private final Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public DetailResepDao() {
        this.conn = CliniclyConnection.connection();
    }
    
    public void save(DetailResepModel model,String page){
        try{
            String query = null;
            String doneMessage = null;
            if(page.equals("insert")){
                query="{CALL InsertDetailResep(?,?,?,?,?,?,?,?)}";
                doneMessage = "Insert success!";
            }
            else if(page.equals("update")){
                query="{CALL UpdateDetailResep(?,?,?,?,?,?,?,?)}";
                doneMessage = "Update success!";
            }
            
            ps=conn.prepareCall(query);
            ps.setString(1, model.getIdResep());
            ps.setString(2, model.getIdObat());
            ps.setString(3, model.getNoFakturObat());
            ps.setDouble(4, model.getHarga());
            ps.setDouble(5, model.getJumlah());
            ps.setString(6, model.getKeterangan());
            ps.setString(7, model.getUserId());
            ps.setDate(8, model.getWaktu());
            ps.executeUpdate();
            System.out.println(doneMessage);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
