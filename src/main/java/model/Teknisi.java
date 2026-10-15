/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author USER
 */
public class Teknisi extends DataOrang {

    private String spesialisasi;

    public Teknisi(
            String id,
            String nama,
            String noTelepon,
            String spesialisasi) {

        super(id, nama, noTelepon);
        this.spesialisasi = spesialisasi;
    }

    public String getSpesialisasi() {
        return spesialisasi;
    }

    public void setSpesialisasi(String spesialisasi) {
        this.spesialisasi = spesialisasi;
    }

    @Override
    public void tampilkanInfo() {
        System.out.println(
                "ID Teknisi : " + getId()
                + " | Nama : " + getNama()
                + " | No. Telepon : " + getNoTelepon()
                + " | Spesialisasi : " + getSpesialisasi()
        );
    }
}
