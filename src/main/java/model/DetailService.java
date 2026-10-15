/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author USER
 */
public class DetailService {

    private String idService;
    private String idPelanggan;
    private String idTeknisi;
    private String jenisAc;
    private String keluhan;
    private String tanggal;
    private String status;
    private double biaya;

    public DetailService(
            String idService,
            String idPelanggan,
            String idTeknisi,
            String jenisAc,
            String keluhan,
            String tanggal,
            String status,
            double biaya) {

        this.idService = idService;
        this.idPelanggan = idPelanggan;
        this.idTeknisi = idTeknisi;
        this.jenisAc = jenisAc;
        this.keluhan = keluhan;
        this.tanggal = tanggal;
        this.status = status;
        this.biaya = biaya;
    }

    public DetailService(
            String idService,
            String idPelanggan,
            String idTeknisi,
            String jenisAc,
            String keluhan,
            String tanggal,
            double biaya) {

        this(
                idService,
                idPelanggan,
                idTeknisi,
                jenisAc,
                keluhan,
                tanggal,
                "Menunggu",
                biaya
        );
    }

    public String getIdService() {
        return idService;
    }

    public void setIdService(String idService) {
        this.idService = idService;
    }

    public String getIdPelanggan() {
        return idPelanggan;
    }

    public void setIdPelanggan(String idPelanggan) {
        this.idPelanggan = idPelanggan;
    }

    public String getIdTeknisi() {
        return idTeknisi;
    }

    public void setIdTeknisi(String idTeknisi) {
        this.idTeknisi = idTeknisi;
    }

    public String getJenisAc() {
        return jenisAc;
    }

    public void setJenisAc(String jenisAc) {
        this.jenisAc = jenisAc;
    }

    public String getKeluhan() {
        return keluhan;
    }

    public void setKeluhan(String keluhan) {
        this.keluhan = keluhan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getBiaya() {
        return biaya;
    }

    public void setBiaya(double biaya) {
        this.biaya = biaya;
    }
}