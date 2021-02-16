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
public class SupplierModel {
    String idSupplier,namaSupplier,alamat,noTelp,email,userrID;
    Date waktu;

    public SupplierModel(String idSupplier, String namaSupplier, String alamat, String noTelp, String email, String userrID, Date waktu) {
        this.idSupplier = idSupplier;
        this.namaSupplier = namaSupplier;
        this.alamat = alamat;
        this.noTelp = noTelp;
        this.email = email;
        this.userrID = userrID;
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
