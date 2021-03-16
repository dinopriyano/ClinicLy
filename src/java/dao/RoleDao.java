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
import model.KaryawanModel;
import model.RoleModel;

/**
 *
 * @author dinop
 */
public class RoleDao {
    private final Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public RoleDao() {
        this.conn = CliniclyConnection.connection();
    }
    
    public String getNewIdRole(){
        String newID = null;
        try{
            String lastID = null;
            String query="{CALL GetLastIdRole()}";
            ps=conn.prepareCall(query);
            rs=ps.executeQuery();
            if(rs.next()){
                lastID = rs.getString("id_role");
                String lastNumStr = lastID.substring(1);
                int newNumInt = Integer.parseInt(lastNumStr)+1;
                String newNumStr = String.valueOf(newNumInt);
                String fixNewNumStr = newNumStr.substring(newNumStr.length()-1);
                newID = "R"+fixNewNumStr;
            }
            else{
                newID = "R1";
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        return newID;
    }
    
    public ArrayList<RoleModel> getAllRole(){
        ArrayList<RoleModel> list = new ArrayList();
        try{
            String query = "{CALL GetAllRole()}";
            ps=conn.prepareStatement(query);
            rs=ps.executeQuery();
            while(rs.next())
            {
                RoleModel model = new RoleModel();
                model.setIdRole(rs.getString("id_role"));
                model.setDesRole(rs.getString("des_role"));
                list.add(model);
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        return list;
    }
    
    public void save(RoleModel model,String page){
        try{
            String query = null;
            String doneMessage = null;
            if(page.equals("insert")){
                query="{CALL InsertRole(?,?)}";
                doneMessage = "Insert success!";
            }
            else if(page.equals("update")){
                query="{CALL UpdateRole(?,?)}";
                doneMessage = "Update success!";
            }
            
            ps=conn.prepareCall(query);
            ps.setString(1, model.getIdRole());
            ps.setString(2, model.getDesRole());
            ps.executeUpdate();
            System.out.println(doneMessage);
        } 
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        RoleDao dao = new RoleDao();
//        System.out.println(dao.getNewIdRole());
        RoleModel model = new RoleModel();
        model.setIdRole("R1");
        model.setDesRole("Karyawan");
        dao.save(model, "update");
    }
}
