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
import model.LayananModel;
import model.PendaftaranModel;

/**
 *
 * @author dinop
 */
public class PendaftaranDao {
    private final Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public PendaftaranDao() {
        this.conn = CliniclyConnection.connection();
    }
    
    public String getNoAntrian(String idPoli,Date tglDaftar){
        String newNo = null;
        try{
            String lastNo = null;
            String query="{CALL GetLastNoAntrianPendaftaran(?,?)}";
            ps=conn.prepareCall(query);
            ps.setString(1, idPoli);
            ps.setDate(2, tglDaftar);
            rs=ps.executeQuery();
            if(rs.next()){
                lastNo = rs.getString("no_antrian");
                int newNumAntrian = Integer.parseInt(lastNo)+1;
                newNo = String.valueOf(newNumAntrian);
            }
            else{
                newNo = "1";
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        return newNo;
    }
    
    public ArrayList<PendaftaranModel> getAllPendaftaran(){
        ArrayList<PendaftaranModel> list = new ArrayList();
        try{
            String query = "{CALL GetAllPendaftaran()}";
            ps=conn.prepareStatement(query);
            rs=ps.executeQuery();
            while(rs.next())
            {
                PendaftaranModel model = new PendaftaranModel();
                model.setId(Integer.parseInt(rs.getString("id")));
                model.setNoAntrian(rs.getString("no_antrian"));
                model.setIdPasien(rs.getString("id_pasien"));
                model.setIdPoli(rs.getString("id_poli"));
                model.setTglDaftar(rs.getDate("tgl_daftar"));
                model.setKeterangan(rs.getString("keterangan"));
                model.setUserId(rs.getString("user_id"));
                model.setWaktu(rs.getDate("waktu"));
                list.add(model);
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        return list;
    }
    
    public String save(PendaftaranModel model,String page){
        String noAntrian = null;
        try{
            String query = null;
            String doneMessage = null;
            if(page.equals("insert")){
                query="{CALL InsertPendaftaran(?,?,?,?,?,?,?,?)}";
                doneMessage = "Insert success!";
            }
            else if(page.equals("update")){
                query="{CALL UpdatePendaftaran(?,?,?,?,?,?,?,?)}";
                doneMessage = "Update success!";
            }
            
            ps=conn.prepareCall(query);
            ps.setString(1, String.valueOf(model.getId()));
            ps.setString(2, model.getNoAntrian());
            ps.setString(3, model.getIdPasien());
            ps.setString(4, model.getIdPoli());
            ps.setDate(5, model.getTglDaftar());
            ps.setString(6, model.getKeterangan());
            ps.setString(7, model.getUserId());
            ps.setDate(8, model.getWaktu());
            ps.executeUpdate();
            System.out.println(doneMessage);
            noAntrian = model.getNoAntrian();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        return noAntrian;
    }
    
    public static void main(String[] args) {
        PendaftaranDao dao = new PendaftaranDao();
        long millis=System.currentTimeMillis();  
        Date date = new Date(millis);
        System.out.println(dao.getNoAntrian("01", date));
    }
}
