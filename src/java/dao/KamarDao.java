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
import model.KamarModel;
import model.KaryawanModel;
import model.ObatModel;

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
    
    public ArrayList<KamarModel> getAllKamar(){
        ArrayList<KamarModel> list = new ArrayList();
        try{
            String query = "{CALL GetAllKamar()}";
            ps=conn.prepareStatement(query);
            rs=ps.executeQuery();
            while(rs.next())
            {
                KamarModel model = new KamarModel();
                model.setIdKamar(rs.getString("id_kamar"));
                model.setNamaRuang(rs.getString("nama_ruang"));
                model.setNoKamar(rs.getString("no_kamar"));
                model.setKelas(rs.getString("kelas"));
                model.setHargaPerhari(rs.getDouble("harga_perhari"));
                model.setDeskripsiKamar(rs.getString("des_kamar"));
                model.setKapasitas(rs.getInt("kapasitas"));
                model.setTerisi(rs.getInt("terisi"));
                model.setStatus(rs.getString("status"));
                list.add(model);
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return list;
    }
    
    public void save(KamarModel model,String page){
        try{
            String query = null;
            String doneMessage = null;
            if(page.equals("insert")){
                query="{CALL InsertKamar(?,?,?,?,?,?,?,?,?)}";
                doneMessage = "Insert success!";
            }
            else if(page.equals("update")){
                query="{CALL InsertKamar(?,?,?,?,?,?,?,?,?)}";
                doneMessage = "Update success!";
            }
            
            ps=conn.prepareCall(query);
            ps.setString(1, model.getIdKamar());
            ps.setString(2, model.getNamaRuang());
            ps.setString(3, model.getNoKamar());
            ps.setString(4, model.getKelas());
            ps.setDouble(5, model.getHargaPerhari());
            ps.setString(6, model.getDeskripsiKamar());
            ps.setInt(7, model.getKapasitas());
            ps.setInt(8, model.getTerisi());
            ps.setString(9, model.getStatus());
            ps.executeUpdate();
            System.out.println(doneMessage);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        KamarDao dao = new KamarDao();
        System.out.println(dao.getNewIdKamar());
    }
}
