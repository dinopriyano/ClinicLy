/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author dinop
 */
public class DetailLayananModel {
    String idLayanan,idDetailLayanan,deskripsi,keterangan;
    double biayaLayanan;

    public String getIdLayanan() {
        return idLayanan;
    }

    public void setIdLayanan(String idLayanan) {
        this.idLayanan = idLayanan;
    }

    public String getIdDetailLayanan() {
        return idDetailLayanan;
    }

    public void setIdDetailLayanan(String idDetailLayanan) {
        this.idDetailLayanan = idDetailLayanan;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public double getBiayaLayanan() {
        return biayaLayanan;
    }

    public void setBiayaLayanan(double biayaLayanan) {
        this.biayaLayanan = biayaLayanan;
    }
    
    
}
