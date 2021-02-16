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
public class PembelianObatModel {
    String idTransaksi,idSupplier,namaSupplier,noFaktur,idObat,namaObat,keterangan,idUser;
    Boolean dipisahkan;
    double hargaBeli,jumlah,jumlahTerjual;
    Date tglExpired,waktu,tglFaktur;

    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public void setIdSupplier(String idSupplier) {
        this.idSupplier = idSupplier;
    }

    public void setNamaSupplier(String namaSupplier) {
        this.namaSupplier = namaSupplier;
    }

    public void setTglFaktur(Date tglFaktur) {
        this.tglFaktur = tglFaktur;
    }

    public void setNoFaktur(String noFaktur) {
        this.noFaktur = noFaktur;
    }

    public void setIdObat(String idObat) {
        this.idObat = idObat;
    }

    public void setNamaObat(String namaObat) {
        this.namaObat = namaObat;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public void setDipisahkan(Boolean dipisahkan) {
        this.dipisahkan = dipisahkan;
    }

    public void setHargaBeli(double hargaBeli) {
        this.hargaBeli = hargaBeli;
    }

    public void setJumlah(double jumlah) {
        this.jumlah = jumlah;
    }

    public void setJumlahTerjual(double jumlahTerjual) {
        this.jumlahTerjual = jumlahTerjual;
    }

    public void setTglExpired(Date tglExpired) {
        this.tglExpired = tglExpired;
    }

    public void setWaktu(Date waktu) {
        this.waktu = waktu;
    }

    public Date getTglFaktur() {
        return tglFaktur;
    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public String getIdSupplier() {
        return idSupplier;
    }

    public String getNamaSupplier() {
        return namaSupplier;
    }

    public String getNoFaktur() {
        return noFaktur;
    }

    public String getIdObat() {
        return idObat;
    }

    public String getNamaObat() {
        return namaObat;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public String getIdUser() {
        return idUser;
    }

    public Boolean getDipisahkan() {
        return dipisahkan;
    }

    public double getHargaBeli() {
        return hargaBeli;
    }

    public double getJumlah() {
        return jumlah;
    }

    public double getJumlahTerjual() {
        return jumlahTerjual;
    }

    public Date getTglExpired() {
        return tglExpired;
    }

    public Date getWaktu() {
        return waktu;
    }
    
    
}
