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
public class BayarObatModel {
    String idPembayaran,idPasien,idResep,jenisPembayaran,userId;
    Date tglPembayaran,waktu;

    public String getIdPembayaran() {
        return idPembayaran;
    }

    public void setIdPembayaran(String idPembayaran) {
        this.idPembayaran = idPembayaran;
    }

    public String getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(String idPasien) {
        this.idPasien = idPasien;
    }

    public String getIdResep() {
        return idResep;
    }

    public void setIdResep(String idResep) {
        this.idResep = idResep;
    }

    public String getJenisPembayaran() {
        return jenisPembayaran;
    }

    public void setJenisPembayaran(String jenisPembayaran) {
        this.jenisPembayaran = jenisPembayaran;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getTglPembayaran() {
        return tglPembayaran;
    }

    public void setTglPembayaran(Date tglPembayaran) {
        this.tglPembayaran = tglPembayaran;
    }

    public Date getWaktu() {
        return waktu;
    }

    public void setWaktu(Date waktu) {
        this.waktu = waktu;
    }
    
    
}
