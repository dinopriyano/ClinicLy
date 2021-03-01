/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author dinop
 */
public class BayarLayananModel {
    String idBayarLayanan,idLayanan,idDetailLayanan,idPasien,keterangan,userId;
    Date tglLayanan;

    public String getIdBayarLayanan() {
        return idBayarLayanan;
    }

    public void setIdBayarLayanan(String idBayarLayanan) {
        this.idBayarLayanan = idBayarLayanan;
    }

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

    public String getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(String idPasien) {
        this.idPasien = idPasien;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getTglLayanan() {
        return tglLayanan;
    }

    public void setTglLayanan(Date tglLayanan) {
        this.tglLayanan = tglLayanan;
    }
    
    
}
