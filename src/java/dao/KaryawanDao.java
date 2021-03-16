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
import model.KaryawanModel;
import model.PasienModel;
import static utils.Encryption.encryptSHA256;

/**
 *
 * @author dinop
 */
public class KaryawanDao {
    private final Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public KaryawanDao() {
        this.conn = CliniclyConnection.connection();
    }
    
    public String getNewIdKaryawan(){
        String newID = null;
        try{
            String lastID = null;
            String query="{CALL GetLastIdKaryawan()}";
            ps=conn.prepareCall(query);
            rs=ps.executeQuery();
            if(rs.next()){
                lastID = rs.getString("id_karyawan");
                String lastNumStr = lastID.substring(2);
                int newNumInt = Integer.parseInt(lastNumStr)+1;
                String newNumStr = "000"+newNumInt;
                String fixNewNumStr = newNumStr.substring(newNumStr.length()-4);
                newID = "KR"+fixNewNumStr;
            }
            else{
                newID = "KR0001";
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        return newID;
        
    }
    
    public ArrayList<KaryawanModel> getAllKaryawan(){
        ArrayList<KaryawanModel> arrModel = new ArrayList<KaryawanModel>();
        try{
            String query="{CALL GetAllKaryawan()}";
            ps=conn.prepareCall(query);
            rs=ps.executeQuery();
            while(rs.next()){
                KaryawanModel model = new KaryawanModel();
                model.setId_karyawan(rs.getString("id_karyawan"));
                model.setNama_karyawan(rs.getString("nama_karyawan"));
                model.setTgl_lahir(rs.getDate("tgl_lahir"));
                model.setJenis_kelamin(rs.getString("jenis_kelamin"));
                model.setNo_ktp(rs.getString("no_ktp"));
                model.setNo_npwp(rs.getString("no_npwp"));
                model.setAlamat(rs.getString("alamat"));
                model.setNo_hp(rs.getString("no_hp"));
                model.setBidang_pekerjaan(rs.getString("bidang_pekerjaan"));
                model.setUser_id(rs.getString("user_id"));
                model.setCreated_user_id(rs.getString("created_user_id"));
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
    
    public void save(KaryawanModel model,String page){
        try{
            String query = null;
            String doneMessage = null;
            if(page.equals("insert")){
                query="{CALL InsertKaryawan(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                doneMessage = "Insert success!";
            }
            else if(page.equals("update")){
                query="{CALL UpdateKaryawan(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                doneMessage = "Update success!";
            }
            
            ps=conn.prepareCall(query);
            ps.setString(1, model.getEmail());
            ps.setString(2, model.getId_role());
            ps.setString(3, model.getUser_id());
            ps.setString(4, model.getPassword());
            ps.setBoolean(5, model.isStatus());
            ps.setString(6, model.getId_karyawan());
            ps.setString(7, model.getNama_karyawan());
            ps.setDate(8, model.getTgl_lahir());
            ps.setString(9, model.getBidang_pekerjaan());
            ps.setString(10, model.getJenis_kelamin());
            ps.setString(11, model.getNo_ktp());
            ps.setString(12, model.getNo_npwp());
            ps.setString(13, model.getAlamat());
            ps.setString(14, model.getNo_hp());
            ps.setString(15, model.getCreated_user_id());
            ps.setDate(16, model.getWaktu());
            ps.executeUpdate();
            System.out.println(doneMessage);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        KaryawanDao dao = new KaryawanDao();
        PasienDao pasienDao = new PasienDao();
//        System.out.println(dao.getNewIdKaryawan());
        long millis=System.currentTimeMillis();  
        Date date = new Date(millis);
       
        String newUserID = pasienDao.getNewIdUser();
        String newKaryawanID = dao.getNewIdKaryawan();
        
        KaryawanModel model = new KaryawanModel();
        model.setEmail("marpuah@email.com");
        model.setId_role("R3");
        model.setUser_id("US0006");
        model.setPassword(encryptSHA256("pass"));
        model.setStatus(true);
//        model.setId_karyawan(newKaryawanID);
        model.setNama_karyawan("Marpuah");
        model.setTgl_lahir(date);
        model.setBidang_pekerjaan("Apoteker");
        model.setJenis_kelamin("P");
        model.setNo_ktp("28034020");
        model.setNo_npwp("248399984");
        model.setAlamat("Namex");
        model.setNo_hp("4938023923");
//        model.setWaktu(date);
//        model.setCreated_user_id("US0001");
        dao.save(model, "update");
    }
}
