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
public class SupplierModel {
    String idSupplier,namaSupplier,alamat,noTelp,email,userrID;
    Date waktu;

    public void setIdSupplier(String idSupplier) {
        this.idSupplier = idSupplier;
    }

    public void setNamaSupplier(String namaSupplier) {
        this.namaSupplier = namaSupplier;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserrID(String userrID) {
        this.userrID = userrID;
    }

    public void setWaktu(Date waktu) {
        this.waktu = waktu;
    }

    

    public String getIdSupplier() {
        return idSupplier;
    }

    public String getNamaSupplier() {
        return namaSupplier;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public String getEmail() {
        return email;
    }

    public String getUserrID() {
        return userrID;
    }

    public Date getWaktu() {
        return waktu;
    }
    
    
}
