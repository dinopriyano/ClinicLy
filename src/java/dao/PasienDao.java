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
import java.util.ArrayList;
import java.util.List;
import model.PasienModel;
import static utils.Encryption.encryptSHA256;

/**
 *
 * @author dinop
 */
public class PasienDao {
    private final Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public PasienDao() {
        this.conn = CliniclyConnection.connection();
    }
    
    public String getNewIdUser(){
        String newID = null;
        try{
            String lastID = null;
            String query="{CALL GetLastIdUser()}";
            ps=conn.prepareCall(query);
            rs=ps.executeQuery();
            if(rs.next()){
                lastID = rs.getString("id_user");
                String lastNumStr = lastID.substring(2);
                int newNumInt = Integer.parseInt(lastNumStr)+1;
                String newNumStr = "000"+newNumInt;
                String fixNewNumStr = newNumStr.substring(newNumStr.length()-4);
                newID = "US"+fixNewNumStr;
            }
            else{
                newID = "US0001";
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        return newID;
        
    }
    
    public String getNewIdPasien(){
        String newID = null;
        try{
            String lastID = null;
            String query="{CALL GetLastIdPasien()}";
            ps=conn.prepareCall(query);
            rs=ps.executeQuery();
            if(rs.next()){
                lastID = rs.getString("id_pasien");
                String lastNumStr = lastID.substring(2);
                int newNumInt = Integer.parseInt(lastNumStr)+1;
                String newNumStr = "000"+newNumInt;
                String fixNewNumStr = newNumStr.substring(newNumStr.length()-4);
                newID = "PS"+fixNewNumStr;
            }
            else{
                newID = "PS0001";
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        return newID;
        
    }
    
    public void save(PasienModel model,String page){
        try{
            String query = null;
            if(page.equals("insert")){
                query="{CALL InsertPasien(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                ps=conn.prepareCall(query);
                ps.setString(1, model.getEmail());
                ps.setString(2, model.getId_role());
                ps.setString(3, model.getUser_id());
                ps.setString(4, model.getPassword());
                ps.setBoolean(5, model.isStatus());
                ps.setString(6, model.getId_pasien());
                ps.setString(7, model.getNama_pasien());
                ps.setDate(8, model.getTgl_lahir());
                ps.setString(9, model.getJenis_kelamin());
                ps.setString(10, model.getNo_ktp());
                ps.setString(11, model.getAlamat());
                ps.setString(12, model.getNo_hp());
                ps.setString(13, model.getGol_darah());
                ps.setString(14, model.getCreated_user_id());
                ps.setDate(15, model.getCreated_date());
                ps.executeUpdate();
                System.out.println("Data inserted!");
            }
            else if(page.equals("update")){
                query="{CALL UpdatePasien(?,?,?,?,?,?,?,?,?,?,?,?)}";
                String newUserId = getNewIdUser();
                String newPasienId = getNewIdPasien();
                ps=conn.prepareCall(query);
                ps.setString(1, model.getEmail());
                ps.setString(2, model.getId_role());
                ps.setString(3, model.getUser_id());
                ps.setBoolean(4, model.isStatus());
                ps.setString(5, model.getId_pasien());
                ps.setString(6, model.getNama_pasien());
                ps.setDate(7, model.getTgl_lahir());
                ps.setString(8, model.getJenis_kelamin());
                ps.setString(9, model.getNo_ktp());
                ps.setString(10, model.getAlamat());
                ps.setString(11, model.getNo_hp());
                ps.setString(12, model.getGol_darah());
                ps.executeUpdate();
                System.out.println("Data updated!");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public ArrayList<PasienModel> getAllPasien(){
        ArrayList<PasienModel> arrModel = new ArrayList<PasienModel>();
        try{
            String lastID = null;
            String query="{CALL GetAllPasien()}";
            ps=conn.prepareCall(query);
            rs=ps.executeQuery();
            while(rs.next()){
                PasienModel model = new PasienModel();
                model.setId_pasien(rs.getString("id_pasien"));
                model.setNama_pasien(rs.getString("nama_pasien"));
                model.setTgl_lahir(rs.getDate("tgl_lahir"));
                model.setJenis_kelamin(rs.getString("jenis_kelamin"));
                model.setNo_ktp(rs.getString("no_ktp"));
                model.setAlamat(rs.getString("alamat"));
                model.setNo_hp(rs.getString("no_hp"));
                model.setGol_darah(rs.getString("gol_darah"));
                model.setUser_id(rs.getString("user_id"));
                model.setCreated_user_id(rs.getString("created_user_id"));
                model.setCreated_date(rs.getDate("created_date"));
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
    
    public static void main(String[] args) {
        long millis=System.currentTimeMillis();  
        Date date = new Date(millis);
        
        PasienDao dao = new PasienDao();
        PasienModel model = new PasienModel();
        model.setEmail("pojo@email.com");
        model.setId_role("R3");
        model.setUser_id("");
        model.setPassword(encryptSHA256("pass"));
        model.setStatus(true);
        model.setId_pasien("");
        model.setNama_pasien("Pojo");
        model.setTgl_lahir(date);
        model.setJenis_kelamin("L");
        model.setNo_ktp("098293829");
        model.setAlamat("Namex");
        model.setNo_hp("092879323");
        model.setGol_darah("A");
        model.setCreated_date(date);
        model.setCreated_user_id("US0001");
        dao.save(model, "insert");

//        System.out.println(dao.getAllPasien().get(0).getEmail());
        
    }
    
    
}
