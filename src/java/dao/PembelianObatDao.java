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
import java.sql.Date;
import model.PembelianObatModel;
import static utils.Encryption.encryptSHA256;

/**
 *
 * @author dinop
 */
public class PembelianObatDao {
    private final Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public PembelianObatDao() {
        this.conn = CliniclyConnection.connection();
    }
    
    public String getNewIdTrans(){
        String newID = null;
        try{
            String lastID = null;
            String query="{CALL GetLastTransaksiIdPembelianObat()}";
            ps=conn.prepareCall(query);
            rs=ps.executeQuery();
            if(rs.next()){
                lastID = rs.getString("id_trans");
                String lastNumStr = lastID.substring(4);
                int newNumInt = Integer.parseInt(lastNumStr)+1;
                String newNumStr = "0000"+newNumInt;
                String fixNewNumStr = newNumStr.substring(newNumStr.length()-5);
                newID = "TROB"+fixNewNumStr;
            }
            else{
                newID = "TROB00001";
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        return newID;
        
    }
    
    public String getNewNoFaktur(){
        String newID = null;
        try{
            String lastID = null;
            String query="{CALL GetLastNoFakturPembelianObat()}";
            ps=conn.prepareCall(query);
            rs=ps.executeQuery();
            if(rs.next()){
                lastID = rs.getString("no_faktur");
                System.out.println(lastID);
                String lastNumStr = lastID.substring(4);
                int newNumInt = Integer.parseInt(lastNumStr)+1;
                String newNumStr = "0000"+newNumInt;
                String fixNewNumStr = newNumStr.substring(newNumStr.length()-5);
                newID = "FAK"+fixNewNumStr;
            }
            else{
                newID = "FAK00001";
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        return newID;
        
    }
    
    public void save(PembelianObatModel model, String page){
        String query=null;
        
        try{
            if(page.equals("update"))
            {
                query="{CALL UpdatePembelianObat(?,?,?,?,?,?,?,?,?,?,?)}";
                ps=conn.prepareCall(query);
                ps.setString(1, model.getIdTransaksi());
                ps.setString(2, model.getIdSupplier());
                ps.setString(3, model.getNoFaktur());
                ps.setDate(4, model.getTglFaktur());
                ps.setString(5, model.getIdObat());
                ps.setDouble(6, model.getHargaBeli());
                ps.setDouble(7, model.getJumlah());
                ps.setString(8, model.getKeterangan());
                ps.setDate(9, model.getTglExpired());
                ps.setDouble(10, model.getJumlahTerjual());
                ps.setBoolean(11, model.getDipisahkan());
                ps.executeUpdate();
                System.out.println("Data updated!");
            }
            else if(page.equals("insert"))
            {
                query="{CALL InsertPembelianObat(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                String newTransID = getNewIdTrans();
                String newNoFaktur = getNewNoFaktur();
                ps=conn.prepareCall(query);
                ps.setString(1, newTransID);
                ps.setString(2, model.getIdSupplier());
                ps.setString(3, newNoFaktur);
                ps.setDate(4, model.getTglFaktur());
                ps.setString(5, model.getIdObat());
                ps.setDouble(6, model.getHargaBeli());
                ps.setDouble(7, model.getJumlah());
                ps.setString(8, model.getKeterangan());
                ps.setDate(9, model.getTglExpired());
                ps.setDouble(10, model.getJumlahTerjual());
                ps.setString(11, model.getIdUser());
                ps.setDate(12, model.getWaktu());
                ps.setBoolean(13, model.getDipisahkan());
                ps.executeUpdate();
                System.out.println("Data inserted!");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
//        PembelianObatDao dao = new PembelianObatDao();
//        long millis=System.currentTimeMillis();  
//        Date date = new Date(millis);
//        PembelianObatModel model = new PembelianObatModel();
//        model.setIdSupplier("SU0001");
//        model.setTglFaktur(date);
//        model.setIdObat("OB000001");
//        model.setHargaBeli(18000);
//        model.setJumlah(40);
//        model.setKeterangan("Untuk batuk bro");
//        model.setTglExpired(date);
//        model.setJumlahTerjual(20);
////        model.setIdUser("US0001");
////        model.setWaktu(date);
//        model.setDipisahkan(false);
//        model.setIdTransaksi("TROB00012");
//        model.setNoFaktur("FAK00004");
//        dao.save(model, "update");

        System.out.println(encryptSHA256("pass"));
    }
    
}
