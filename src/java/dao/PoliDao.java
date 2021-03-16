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
import java.util.ArrayList;
import model.PoliModel;

/**
 *
 * @author dinop
 */
public class PoliDao {
    private final Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public PoliDao() {
        this.conn = CliniclyConnection.connection();
    }
    
    public String getNewIdPoli(){
        String newID = null;
        try{
            String lastID = null;
            String query="{CALL GetLastIdPoli()}";
            ps=conn.prepareCall(query);
            rs=ps.executeQuery();
            if(rs.next()){
                lastID = rs.getString("id_poli");
                String lastNumStr = lastID.substring(2);
                int newNumInt = Integer.parseInt(lastNumStr)+1;
                String newNumStr = "000"+newNumInt;
                String fixNewNumStr = newNumStr.substring(newNumStr.length()-4);
                newID = "PL"+fixNewNumStr;
            }
            else{
                newID = "PL0001";
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        return newID;
    }
    
    public ArrayList<PoliModel> getAllPoli(){
        ArrayList<PoliModel> list = new ArrayList();
        try{
            String query = "{CALL GetAllPoli()}";
            ps=conn.prepareStatement(query);
            rs=ps.executeQuery();
            while(rs.next())
            {
                PoliModel model = new PoliModel();
                model.setIdPoli(rs.getString("id_role"));
                model.setNamaPoli(rs.getString("des_role"));
                list.add(model);
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        return list;
    }
    
    public void save(PoliModel model,String page){
        try{
            String query = null;
            String doneMessage = null;
            if(page.equals("insert")){
                query="{CALL InsertPoli(?,?)}";
                doneMessage = "Insert success!";
            }
            else if(page.equals("update")){
                query="{CALL UpdatePoli(?,?)}";
                doneMessage = "Update success!";
            }
            
            ps=conn.prepareCall(query);
            ps.setString(1, model.getIdPoli());
            ps.setString(2, model.getNamaPoli());
            ps.executeUpdate();
            System.out.println(doneMessage);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        PoliDao dao = new PoliDao();
        System.out.println(dao.getNewIdPoli());
    }
}
