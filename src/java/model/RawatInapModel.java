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
public class RawatInapModel {
    String idRawat,idPasien,idKamar,keterangan;
    Date tglCheckin,tglCheckout;

    public String getIdRawat() {
        return idRawat;
    }

    public void setIdRawat(String idRawat) {
        this.idRawat = idRawat;
    }

    public String getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(String idPasien) {
        this.idPasien = idPasien;
    }

    public String getIdKamar() {
        return idKamar;
    }

    public void setIdKamar(String idKamar) {
        this.idKamar = idKamar;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Date getTglCheckin() {
        return tglCheckin;
    }

    public void setTglCheckin(Date tglCheckin) {
        this.tglCheckin = tglCheckin;
    }

    public Date getTglCheckout() {
        return tglCheckout;
    }

    public void setTglCheckout(Date tglCheckout) {
        this.tglCheckout = tglCheckout;
    }
    
    
}
