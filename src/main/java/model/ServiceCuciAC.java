/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author USER
 */
public class ServiceCuciAC extends ServiceAC {

    private int jumlahUnit;

    public ServiceCuciAC(int jumlahUnit) {
        super("Cuci AC");
        this.jumlahUnit = jumlahUnit;
    }

    public int getJumlahUnit() {
        return jumlahUnit;
    }

    public void setJumlahUnit(int jumlahUnit) {
        this.jumlahUnit = jumlahUnit;
    }

    @Override
    public void tampilkanService() {
        System.out.println(
                "Service : " + getNamaService()
                + " | Jumlah Unit : " + jumlahUnit
                + " | Cocok untuk membersihkan AC dari debu dan kotoran."
        );
    }
}