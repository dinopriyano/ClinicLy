/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author dinop
 */
public class ObatModel {
    String idObat,namaObat,satuan,userId;
    int stok;
    double hargaJual;
    Date waktu;

    public ObatModel(String idObat, String namaObat, String satuan, String userId, int stok, double hargaJual, Date waktu) {
        this.idObat = idObat;
        this.namaObat = namaObat;
        this.satuan = satuan;
        this.userId = userId;
        this.stok = stok;
        this.hargaJual = hargaJual;
        this.waktu = waktu;
    }

    public String getIdObat() {
        return idObat;
    }

    public String getNamaObat() {
        return namaObat;
    }

    public String getSatuan() {
        return satuan;
    }

    public String getUserId() {
        return userId;
    }

    public int getStok() {
        return stok;
    }

    public double getHargaJual() {
        return hargaJual;
    }

    public Date getWaktu() {
        return waktu;
    }
    
    
}
