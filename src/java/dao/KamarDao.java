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

/**
 *
 * @author dinop
 */
public class KamarDao {
    private final Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public KamarDao() {
        this.conn = CliniclyConnection.connection();
    }
    
    public String getNewIdKamar(){
        String newID = null;
        try{
            String lastID = null;
            String query="{CALL GetLastIdKamar()}";
            ps=conn.prepareCall(query);
            rs=ps.executeQuery();
            if(rs.next()){
                lastID = rs.getString("id_kamar");
                String lastNumStr = lastID.substring(2);
                int newNumInt = Integer.parseInt(lastNumStr)+1;
                String newNumStr = "000"+newNumInt;
                String fixNewNumStr = newNumStr.substring(newNumStr.length()-4);
                newID = "KM"+fixNewNumStr;
            }
            else{
                newID = "KM0001";
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        return newID;
        
    }
    
    public static void main(String[] args) {
        KamarDao dao = new KamarDao();
        System.out.println(dao.getNewIdKamar());
    }
}
