/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.CliniclyConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.KaryawanModel;
import model.ObatModel;

/**
 *
 * @author dinop
 */
public class ObatDao {
    private final Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public ObatDao() {
        this.conn = CliniclyConnection.connection();
    }
    
    public String getNewIdObat(){
        String newID = null;
        try{
            String lastID = null;
            String query="{CALL GetLastIdObat()}";
            ps=conn.prepareCall(query);
            rs=ps.executeQuery();
            if(rs.next()){
                lastID = rs.getString("id_obat");
                String lastNumStr = lastID.substring(2);
                int newNumInt = Integer.parseInt(lastNumStr)+1;
                String newNumStr = "00000"+newNumInt;
                String fixNewNumStr = newNumStr.substring(newNumStr.length()-6);
                newID = "OB"+fixNewNumStr;
            }
            else{
                newID = "OB000001";
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        return newID;
        
    }
    
    public void save(ObatModel model,String page){
        try{
            String query = null;
            String doneMessage = null;
            if(page.equals("insert")){
                query="{CALL InsertObat(?,?,?,?,?,?,?)}";
                doneMessage = "Insert success!";
            }
            else if(page.equals("update")){
                query="{CALL UpdateObat(?,?,?,?,?,?,?)}";
                doneMessage = "Update success!";
            }
            
            ps=conn.prepareCall(query);
            ps.setString(1, model.getIdObat());
            ps.setString(2, model.getNamaObat());
            ps.setString(3, model.getSatuan());
            ps.setDouble(4, model.getStok());
            ps.setDouble(5, model.getHargaJual());
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
        ObatDao dao = new ObatDao();
        long millis=System.currentTimeMillis();  
        Date date = new Date(millis);
//        System.out.println(dao.getNewIdObat());

        String newIDObat = dao.getNewIdObat();
        ObatModel model = new ObatModel();
        model.setIdObat("OB000003");
        model.setNamaObat("Bodrex");
        model.setSatuan("Strip");
        model.setStok(50);
        model.setHargaJual(6000);
        model.setWaktu(date);
        model.setUserId("US0001");
        dao.save(model, "update");
    }
}
