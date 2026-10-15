/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author USER
 */
public class Pelanggan extends DataOrang {

    private String alamat;

    public Pelanggan(
            String id,
            String nama,
            String noTelepon,
            String alamat) {

        super(id, nama, noTelepon);
        this.alamat = alamat;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    @Override
    public void tampilkanInfo() {
        System.out.println(
                "ID Pelanggan : " + getId()
                + " | Nama : " + getNama()
                + " | No. Telepon : " + getNoTelepon()
                + " | Alamat : " + getAlamat()
        );
    }
}
