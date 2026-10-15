/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author USER
 */
import dao.PelangganDAO;
import dao.PelangganDAOImpl;
import dao.ServiceDAO;
import dao.ServiceDAOImpl;
import dao.TeknisiDAO;
import dao.TeknisiDAOImpl;
import java.util.List;
import model.DetailService;
import model.Pelanggan;
import model.Teknisi;

public class ServiceController {

    private PelangganDAO pelangganDAO;
    private TeknisiDAO teknisiDAO;
    private ServiceDAO serviceDAO;

    public ServiceController() {

        pelangganDAO = new PelangganDAOImpl();
        teknisiDAO = new TeknisiDAOImpl();
        serviceDAO = new ServiceDAOImpl();
    }


    public List<Pelanggan> getDaftarPelanggan() {
        return pelangganDAO.ambilSemua();
    }

    public List<Teknisi> getDaftarTeknisi() {
        return teknisiDAO.ambilSemua();
    }

    public List<DetailService> getDaftarService() {
        return serviceDAO.ambilSemua();
    }


    public boolean tambahPelanggan(Pelanggan pelanggan) {

        if (cariPelanggan(pelanggan.getId()) != null) {
            return false;
        }

        return pelangganDAO.tambah(pelanggan);
    }

    public Pelanggan cariPelanggan(String id) {
        return pelangganDAO.cariById(id);
    }

    public boolean ubahPelanggan(
            String id,
            String nama,
            String noTelepon,
            String alamat) {

        if (cariPelanggan(id) == null) {
            return false;
        }

        return pelangganDAO.ubah(
                new Pelanggan(id, nama, noTelepon, alamat)
        );
    }

    public boolean hapusPelanggan(String id) {

        if (cariPelanggan(id) == null) {
            return false;
        }

        return pelangganDAO.hapus(id);
    }


    public boolean tambahTeknisi(Teknisi teknisi) {

        if (cariTeknisi(teknisi.getId()) != null) {
            return false;
        }

        return teknisiDAO.tambah(teknisi);
    }

    public Teknisi cariTeknisi(String id) {
        return teknisiDAO.cariById(id);
    }

    public boolean ubahTeknisi(
            String id,
            String nama,
            String noTelepon,
            String spesialisasi) {

        if (cariTeknisi(id) == null) {
            return false;
        }

        return teknisiDAO.ubah(
                new Teknisi(id, nama, noTelepon, spesialisasi)
        );
    }

    public boolean hapusTeknisi(String id) {

        if (cariTeknisi(id) == null) {
            return false;
        }

        return teknisiDAO.hapus(id);
    }


    public boolean tambahService(DetailService service) {

        if (cariService(service.getIdService()) != null) {
            return false;
        }

        if (cariPelanggan(service.getIdPelanggan()) == null) {
            return false;
        }

        if (cariTeknisi(service.getIdTeknisi()) == null) {
            return false;
        }

        return serviceDAO.tambah(service);
    }

    public DetailService cariService(String id) {
        return serviceDAO.cariById(id);
    }

    public boolean ubahService(
            String id,
            String idPelanggan,
            String idTeknisi,
            String jenisAc,
            String keluhan,
            String tanggal,
            String status,
            double biaya) {

        if (cariService(id) == null) {
            return false;
        }

        if (cariPelanggan(idPelanggan) == null) {
            return false;
        }

        if (cariTeknisi(idTeknisi) == null) {
            return false;
        }

        return serviceDAO.ubah(
                new DetailService(
                        id,
                        idPelanggan,
                        idTeknisi,
                        jenisAc,
                        keluhan,
                        tanggal,
                        status,
                        biaya
                )
        );
    }

    public boolean hapusService(String id) {

        if (cariService(id) == null) {
            return false;
        }

        return serviceDAO.hapus(id);
    }

    public boolean ubahStatusService(
            String idService,
            String statusBaru) {

        if (cariService(idService) == null) {
            return false;
        }

        return serviceDAO.ubahStatus(idService, statusBaru);
    }

    public int jumlahPelanggan() {
        return pelangganDAO.hitung();
    }

    public int jumlahTeknisi() {
        return teknisiDAO.hitung();
    }

    public int jumlahService() {
        return serviceDAO.hitung();
    }
}
