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
import model.DokterModel;
import model.DokterModel;

/**
 *
 * @author dinop
 */
public class DokterDao {
    private final Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public DokterDao() {
        this.conn = CliniclyConnection.connection();
    }
    
    public String getNewIdDokter(){
        String newID = null;
        try{
            String lastID = null;
            String query="{CALL GetLastIdDokter()}";
            ps=conn.prepareCall(query);
            rs=ps.executeQuery();
            if(rs.next()){
                lastID = rs.getString("id_dokter");
                String lastNumStr = lastID.substring(2);
                int newNumInt = Integer.parseInt(lastNumStr)+1;
                String newNumStr = "000"+newNumInt;
                String fixNewNumStr = newNumStr.substring(newNumStr.length()-4);
                newID = "DR"+fixNewNumStr;
            }
            else{
                newID = "DR0001";
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        return newID;   
    }
    
    public ArrayList<DokterModel> getAllDokter(){
        ArrayList<DokterModel> arrModel = new ArrayList<DokterModel>();
        try{
            String query="{CALL GetAllDokter()}";
            ps=conn.prepareCall(query);
            rs=ps.executeQuery();
            while(rs.next()){
                DokterModel model = new DokterModel();
                model.setIdDokter(rs.getString("id_dokter"));
                model.setNamaDokter(rs.getString("nama_dokter"));
                model.setTglLahir(rs.getDate("tgl_lahir"));
                model.setJenisKelamin(rs.getString("jenis_kelamin"));
                model.setNoKtp(rs.getString("no_ktp"));
                model.setNoNpwp(rs.getString("no_npwp"));
                model.setAlamat(rs.getString("alamat"));
                model.setNoHp(rs.getString("no_hp"));
                model.setIdPoli(rs.getString("id_poli"));
                model.setUserId(rs.getString("user_id"));
                model.setCreatedUserId(rs.getString("created_user_id"));
                model.setSpesialis(rs.getString("spesialis"));
                model.setWaktu(rs.getDate("waktu"));
                model.setEmail(rs.getString("email"));
                model.setPassword(rs.getString("password"));
                model.setId_role(rs.getString("id_role"));
                model.setStatus(rs.getBoolean("status"));
                model.setDes_role(rs.getString("des_role"));
                arrModel.add(model);
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        return arrModel;
    }
    
    public void save(DokterModel model,String page){
        try{
            String query = null;
            String doneMessage = null;
            if(page.equals("insert")){
                query="{CALL InsertDokter(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                doneMessage = "Insert success!";
            }
            else if(page.equals("update")){
                query="{CALL UpdateDokter(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                doneMessage = "Update success!";
            }
            
            ps=conn.prepareCall(query);
            ps.setString(1, model.getEmail());
            ps.setString(2, model.getId_role());
            ps.setString(3, model.getUserId());
            ps.setString(4, model.getPassword());
            ps.setBoolean(5, model.isStatus());
            ps.setString(6, model.getIdDokter());
            ps.setString(7, model.getNamaDokter());
            ps.setDate(8, model.getTglLahir());
            ps.setString(9, model.getIdPoli());
            ps.setString(10, model.getJenisKelamin());
            ps.setString(11, model.getNoKtp());
            ps.setString(12, model.getNoNpwp());
            ps.setString(13, model.getAlamat());
            ps.setString(14, model.getNoHp());
            ps.setString(15, model.getCreatedUserId());
            ps.setDate(16, model.getWaktu());
            ps.setString(17, model.getSpesialis());
            ps.executeUpdate();
            System.out.println(doneMessage);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        DokterDao dao = new DokterDao();
        System.out.println(dao.getNewIdDokter());
    }
}
