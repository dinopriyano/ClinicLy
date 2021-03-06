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
import model.RekamMedikModel;
import model.RekamMedikModel;

/**
 *
 * @author dinop
 */
public class RekamMedikDao {
    private final Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public RekamMedikDao() {
        this.conn = CliniclyConnection.connection();
    }
    
    public String getNewIdRekamMedik(){
        String newID = null;
        try{
            String lastID = null;
            String query="{CALL GetLastIdRekamMedik()}";
            ps=conn.prepareCall(query);
            rs=ps.executeQuery();
            if(rs.next()){
                lastID = rs.getString("id");
                String lastNumStr = lastID.substring(2);
                int newNumInt = Integer.parseInt(lastNumStr)+1;
                String newNumStr = "000"+newNumInt;
                String fixNewNumStr = newNumStr.substring(newNumStr.length()-4);
                newID = "RM"+fixNewNumStr;
            }
            else{
                newID = "RM0001";
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        return newID;
    }
    
    public ArrayList<RekamMedikModel> getAllRekamMedik(){
        ArrayList<RekamMedikModel> list = new ArrayList();
        try{
            String query = "{CALL GetAllRekamMedik()}";
            ps=conn.prepareStatement(query);
            rs=ps.executeQuery();
            while(rs.next())
            {
                RekamMedikModel model = new RekamMedikModel();
                model.setId(rs.getString("id"));
                model.setPendaftaran_id(rs.getString("pendaftaran_id"));
                model.setTensi(rs.getString("tensi"));
                model.setBerat(Double.parseDouble("berat"));
                model.setTinggi(Double.parseDouble("tinggi"));
                model.setKeluhan(rs.getString("keluhan"));
                model.setTindakan(rs.getString("tindakan"));
                model.setSaran(rs.getString("saran"));
                model.setIdDokter(rs.getString("id_dokter"));
                model.setIdResep(rs.getString("id_resep"));
                model.setDiagnosa(rs.getString("diagnosa"));
                model.setWaktu(rs.getDate("waktu"));
                model.setUserId(rs.getString("user_id"));
                list.add(model);
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        return list;
    }
    
    public void save(RekamMedikModel model,String page){
        try{
            String query = null;
            String doneMessage = null;
            if(page.equals("insert")){
                query="{CALL InsertRekamMedik(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                doneMessage = "Insert success!";
            }
            else if(page.equals("update")){
                query="{CALL UpdateRekamMedik(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                doneMessage = "Update success!";
            }
            
            ps=conn.prepareCall(query);
            ps.setString(1, model.getId());
            ps.setString(2, model.getPendaftaran_id());
            ps.setString(3, model.getTensi());
            ps.setDouble(4, model.getBerat());
            ps.setDouble(5, model.getTinggi());
            ps.setString(6, model.getKeluhan());
            ps.setString(7, model.getTindakan());
            ps.setString(8, model.getSaran());
            ps.setString(9, model.getIdDokter());
            ps.setString(10, model.getIdResep());
            ps.setString(11, model.getDiagnosa());
            ps.setDate(12, model.getWaktu());
            ps.setString(13, model.getUserId());
            ps.executeUpdate();
            System.out.println(doneMessage);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        RekamMedikDao dao = new RekamMedikDao();
        System.out.println(dao.getNewIdRekamMedik());
    }
}
