/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author USER
 */
public class ServicePerbaikanAC extends ServiceAC {

    private String tingkatKerusakan;

    public ServicePerbaikanAC(String tingkatKerusakan) {
        super("Perbaikan AC");
        this.tingkatKerusakan = tingkatKerusakan;
    }

    public String getTingkatKerusakan() {
        return tingkatKerusakan;
    }

    public void setTingkatKerusakan(String tingkatKerusakan) {
        this.tingkatKerusakan = tingkatKerusakan;
    }

    @Override
    public void tampilkanService() {
        System.out.println(
                "Service : " + getNamaService()
                + " | Tingkat Kerusakan : " + tingkatKerusakan
                + " | Meliputi pemeriksaan dan perbaikan kerusakan AC."
        );
    }
}