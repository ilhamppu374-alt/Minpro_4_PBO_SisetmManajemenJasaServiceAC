/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author USER
 */
import controller.ServiceController;
import model.DetailService;
import model.Pelanggan;
import model.ServiceAC;
import model.ServiceCuciAC;
import model.ServicePerbaikanAC;
import model.Teknisi;
import java.util.Scanner;
import util.TanggalUtil;

public class ServiceView {

    private Scanner input;
    private ServiceController controller;

    public ServiceView(ServiceController controller) {

        this.controller = controller;
        this.input = new Scanner(System.in);
    }


    public void jalankanProgram() {

        int pilihan;

        do {

            tampilkanMenuUtama();

            pilihan = inputInt(
                    "Pilih menu: ",
                    0,
                    5
            );

            switch (pilihan) {

                case 1:
                    menuPelanggan();
                    break;

                case 2:
                    menuTeknisi();
                    break;

                case 3:
                    menuService();
                    break;

                case 4:
                    tampilkanInformasiService();
                    break;

                case 5:
                    tampilkanDashboard();
                    break;

                case 0:
                    System.out.println(
                            "\nTerima kasih telah menggunakan "
                            + "Sistem Manajemen Jasa Service AC."
                    );
                    break;
            }

        } while (pilihan != 0);
    }


    private void tampilkanMenuUtama() {

        System.out.println(
                "\n=============================================="
        );

        System.out.println(
                "       SISTEM MANAJEMEN JASA SERVICE AC"
        );

        System.out.println(
                "=============================================="
        );

        System.out.println("1. Kelola Data Pelanggan");
        System.out.println("2. Kelola Data Teknisi");
        System.out.println("3. Kelola Data Service AC");
        System.out.println("4. Informasi Jenis Service");
        System.out.println("5. Dashboard");
        System.out.println("0. Keluar");

        System.out.println(
                "=============================================="
        );
    }


    private void menuPelanggan() {

        int pilihan;

        do {

            System.out.println(
                    "\n========== DATA PELANGGAN =========="
            );

            System.out.println("1. Tambah Pelanggan");
            System.out.println("2. Lihat Pelanggan");
            System.out.println("3. Ubah Pelanggan");
            System.out.println("4. Hapus Pelanggan");
            System.out.println("0. Kembali");

            pilihan = inputInt(
                    "Pilih menu: ",
                    0,
                    4
            );

            switch (pilihan) {

                case 1:
                    tambahPelanggan();
                    break;

                case 2:
                    tampilkanPelanggan();
                    break;

                case 3:
                    ubahPelanggan();
                    break;

                case 4:
                    hapusPelanggan();
                    break;
            }

        } while (pilihan != 0);
    }

    private void tambahPelanggan() {

        System.out.println(
                "\n---------- TAMBAH PELANGGAN ----------"
        );

        String id;

        while (true) {

            id = inputNonKosong("ID Pelanggan: ");

            if (controller.cariPelanggan(id) == null) {
                break;
            }

            System.out.println(
                    "ID pelanggan sudah digunakan!"
            );
        }

        String nama = inputNama("Nama Pelanggan: ");

        String noTelepon = inputTelepon(
                "Nomor Telepon: "
        );

        String alamat = inputNonKosong(
                "Alamat: "
        );

        Pelanggan pelanggan = new Pelanggan(
                id,
                nama,
                noTelepon,
                alamat
        );

        if (controller.tambahPelanggan(pelanggan)) {

            System.out.println(
                    "Pelanggan berhasil ditambahkan!"
            );

        } else {

            System.out.println(
                    "Pelanggan gagal ditambahkan!"
            );
        }
    }

    private void tampilkanPelanggan() {

        System.out.println(
                "\n---------- DAFTAR PELANGGAN ----------"
        );

        if (controller.getDaftarPelanggan().isEmpty()) {

            System.out.println(
                    "Belum ada data pelanggan."
            );

            return;
        }

        for (Pelanggan p :
                controller.getDaftarPelanggan()) {

            p.tampilkanInfo();
        }
    }

    private void ubahPelanggan() {

        System.out.println(
                "\n---------- UBAH PELANGGAN ----------"
        );

        String id = inputNonKosong(
                "ID Pelanggan: "
        );

        if (controller.cariPelanggan(id) == null) {

            System.out.println(
                    "ID pelanggan tidak ditemukan!"
            );

            return;
        }

        String nama = inputNama(
                "Nama Baru: "
        );

        String noTelepon = inputTelepon(
                "Nomor Telepon Baru: "
        );

        String alamat = inputNonKosong(
                "Alamat Baru: "
        );

        if (controller.ubahPelanggan(
                id,
                nama,
                noTelepon,
                alamat)) {

            System.out.println(
                    "Data pelanggan berhasil diubah!"
            );

        } else {

            System.out.println(
                    "Data pelanggan gagal diubah!"
            );
        }
    }

    private void hapusPelanggan() {

        System.out.println(
                "\n---------- HAPUS PELANGGAN ----------"
        );

        String id = inputNonKosong(
                "ID Pelanggan: "
        );

        if (controller.cariPelanggan(id) == null) {

            System.out.println(
                    "ID pelanggan tidak ditemukan!"
            );

            return;
        }

        String konfirmasi = inputNonKosong(
                "Yakin hapus data? (Y/T): "
        );

        if (konfirmasi.equalsIgnoreCase("Y")) {

            if (controller.hapusPelanggan(id)) {

                System.out.println(
                        "Pelanggan berhasil dihapus!"
                );

            } else {

                System.out.println(
                        "Pelanggan gagal dihapus! Data masih "
                        + "dipakai pada data service."
                );
            }

        } else {

            System.out.println(
                    "Penghapusan dibatalkan."
            );
        }
    }


    private void menuTeknisi() {

        int pilihan;

        do {

            System.out.println(
                    "\n========== DATA TEKNISI =========="
            );

            System.out.println("1. Tambah Teknisi");
            System.out.println("2. Lihat Teknisi");
            System.out.println("3. Ubah Teknisi");
            System.out.println("4. Hapus Teknisi");
            System.out.println("0. Kembali");

            pilihan = inputInt(
                    "Pilih menu: ",
                    0,
                    4
            );

            switch (pilihan) {

                case 1:
                    tambahTeknisi();
                    break;

                case 2:
                    tampilkanTeknisi();
                    break;

                case 3:
                    ubahTeknisi();
                    break;

                case 4:
                    hapusTeknisi();
                    break;
            }

        } while (pilihan != 0);
    }

    private void tambahTeknisi() {

        System.out.println(
                "\n---------- TAMBAH TEKNISI ----------"
        );

        String id;

        while (true) {

            id = inputNonKosong(
                    "ID Teknisi: "
            );

            if (controller.cariTeknisi(id) == null) {
                break;
            }

            System.out.println(
                    "ID teknisi sudah digunakan!"
            );
        }

        String nama = inputNama(
                "Nama Teknisi: "
        );

        String noTelepon = inputTelepon(
                "Nomor Telepon: "
        );

        String spesialisasi = inputNonKosong(
                "Spesialisasi: "
        );

        Teknisi teknisi = new Teknisi(
                id,
                nama,
                noTelepon,
                spesialisasi
        );

        if (controller.tambahTeknisi(teknisi)) {

            System.out.println(
                    "Teknisi berhasil ditambahkan!"
            );

        } else {

            System.out.println(
                    "Teknisi gagal ditambahkan!"
            );
        }
    }

    private void tampilkanTeknisi() {

        System.out.println(
                "\n---------- DAFTAR TEKNISI ----------"
        );

        if (controller.getDaftarTeknisi().isEmpty()) {

            System.out.println(
                    "Belum ada data teknisi."
            );

            return;
        }

        for (Teknisi t :
                controller.getDaftarTeknisi()) {

            t.tampilkanInfo();
        }
    }

    private void ubahTeknisi() {

        System.out.println(
                "\n---------- UBAH TEKNISI ----------"
        );

        String id = inputNonKosong(
                "ID Teknisi: "
        );

        if (controller.cariTeknisi(id) == null) {

            System.out.println(
                    "ID teknisi tidak ditemukan!"
            );

            return;
        }

        String nama = inputNama(
                "Nama Baru: "
        );

        String noTelepon = inputTelepon(
                "Nomor Telepon Baru: "
        );

        String spesialisasi = inputNonKosong(
                "Spesialisasi Baru: "
        );

        if (controller.ubahTeknisi(
                id,
                nama,
                noTelepon,
                spesialisasi)) {

            System.out.println(
                    "Data teknisi berhasil diubah!"
            );

        } else {

            System.out.println(
                    "Data teknisi gagal diubah!"
            );
        }
    }

    private void hapusTeknisi() {

        System.out.println(
                "\n---------- HAPUS TEKNISI ----------"
        );

        String id = inputNonKosong(
                "ID Teknisi: "
        );

        if (controller.cariTeknisi(id) == null) {

            System.out.println(
                    "ID teknisi tidak ditemukan!"
            );

            return;
        }

        String konfirmasi = inputNonKosong(
                "Yakin hapus data? (Y/T): "
        );

        if (konfirmasi.equalsIgnoreCase("Y")) {

            if (controller.hapusTeknisi(id)) {

                System.out.println(
                        "Teknisi berhasil dihapus!"
                );

            } else {

                System.out.println(
                        "Teknisi gagal dihapus! Data masih "
                        + "dipakai pada data service."
                );
            }

        } else {

            System.out.println(
                    "Penghapusan dibatalkan."
            );
        }
    }


    private void menuService() {

        int pilihan;

        do {

            System.out.println(
                    "\n========== DATA SERVICE AC =========="
            );

            System.out.println("1. Tambah Service");
            System.out.println("2. Lihat Service");
            System.out.println("3. Ubah Service");
            System.out.println("4. Hapus Service");
            System.out.println("5. Ubah Status Service");
            System.out.println("0. Kembali");

            pilihan = inputInt(
                    "Pilih menu: ",
                    0,
                    5
            );

            switch (pilihan) {

                case 1:
                    tambahService();
                    break;

                case 2:
                    tampilkanService();
                    break;

                case 3:
                    ubahService();
                    break;

                case 4:
                    hapusService();
                    break;

                case 5:
                    ubahStatusService();
                    break;
            }

        } while (pilihan != 0);
    }

    private void tambahService() {

        System.out.println(
                "\n---------- TAMBAH SERVICE ----------"
        );

        String idService;

        while (true) {

            idService = inputNonKosong(
                    "ID Service: "
            );

            if (controller.cariService(idService) == null) {
                break;
            }

            System.out.println(
                    "ID service sudah digunakan!"
            );
        }

        String idPelanggan;

        while (true) {

            idPelanggan = inputNonKosong(
                    "ID Pelanggan: "
            );

            if (controller.cariPelanggan(
                    idPelanggan) != null) {

                break;
            }

            System.out.println(
                    "ID pelanggan tidak ditemukan!"
            );
        }

        String idTeknisi;

        while (true) {

            idTeknisi = inputNonKosong(
                    "ID Teknisi: "
            );

            if (controller.cariTeknisi(
                    idTeknisi) != null) {

                break;
            }

            System.out.println(
                    "ID teknisi tidak ditemukan!"
            );
        }

        String jenisAc = inputNonKosong(
                "Jenis AC: "
        );

        String keluhan = inputNonKosong(
                "Keluhan: "
        );

        String tanggal = inputTanggal(
                "Tanggal Service (DD-MM-YYYY): "
        );

        double biaya = inputDoublePositif(
                "Biaya Service: Rp "
        );

        DetailService service =
                new DetailService(
                        idService,
                        idPelanggan,
                        idTeknisi,
                        jenisAc,
                        keluhan,
                        tanggal,
                        biaya
                );

        if (controller.tambahService(service)) {

            System.out.println(
                    "\nService berhasil ditambahkan!"
            );

            System.out.println(
                    "Status awal service: Menunggu"
            );

        } else {

            System.out.println(
                    "Service gagal ditambahkan!"
            );
        }
    }

    private void tampilkanService() {

        System.out.println(
                "\n---------- DAFTAR SERVICE AC ----------"
        );

        if (controller.getDaftarService().isEmpty()) {

            System.out.println(
                    "Belum ada data service."
            );

            return;
        }

        for (DetailService s :
                controller.getDaftarService()) {

            Pelanggan p =
                    controller.cariPelanggan(
                            s.getIdPelanggan()
                    );

            Teknisi t =
                    controller.cariTeknisi(
                            s.getIdTeknisi()
                    );

            System.out.println(
                    "\nID Service : " + s.getIdService()
                    + "\nPelanggan  : "
                    + p.getNama()
                    + " (" + p.getId() + ")"
                    + "\nTeknisi    : "
                    + t.getNama()
                    + " (" + t.getId() + ")"
                    + "\nJenis AC   : " + s.getJenisAc()
                    + "\nKeluhan    : " + s.getKeluhan()
                    + "\nTanggal    : " + s.getTanggal()
                    + "\nStatus     : " + s.getStatus()
                    + "\nBiaya      : Rp "
                    + s.getBiaya()
            );
        }
    }

    private void ubahService() {

        System.out.println(
                "\n---------- UBAH SERVICE ----------"
        );

        String idService = inputNonKosong(
                "ID Service: "
        );

        DetailService service =
                controller.cariService(idService);

        if (service == null) {

            System.out.println(
                    "ID service tidak ditemukan!"
            );

            return;
        }

        String idPelanggan = inputNonKosong(
                "ID Pelanggan Baru: "
        );

        if (controller.cariPelanggan(
                idPelanggan) == null) {

            System.out.println(
                    "ID pelanggan tidak ditemukan!"
            );

            return;
        }

        String idTeknisi = inputNonKosong(
                "ID Teknisi Baru: "
        );

        if (controller.cariTeknisi(
                idTeknisi) == null) {

            System.out.println(
                    "ID teknisi tidak ditemukan!"
            );

            return;
        }

        String jenisAc = inputNonKosong(
                "Jenis AC Baru: "
        );

        String keluhan = inputNonKosong(
                "Keluhan Baru: "
        );

        String tanggal = inputTanggal(
                "Tanggal Baru (DD-MM-YYYY): "
        );

        String status = inputStatus();

        double biaya = inputDoublePositif(
                "Biaya Baru: Rp "
        );

        if (controller.ubahService(
                idService,
                idPelanggan,
                idTeknisi,
                jenisAc,
                keluhan,
                tanggal,
                status,
                biaya)) {

            System.out.println(
                    "Data service berhasil diubah!"
            );

        } else {

            System.out.println(
                    "Data service gagal diubah!"
            );
        }
    }

    private void hapusService() {

        System.out.println(
                "\n---------- HAPUS SERVICE ----------"
        );

        String id = inputNonKosong(
                "ID Service: "
        );

        if (controller.cariService(id) == null) {

            System.out.println(
                    "ID service tidak ditemukan!"
            );

            return;
        }

        String konfirmasi = inputNonKosong(
                "Yakin hapus data? (Y/T): "
        );

        if (konfirmasi.equalsIgnoreCase("Y")) {

            if (controller.hapusService(id)) {

                System.out.println(
                        "Service berhasil dihapus!"
                );

            } else {

                System.out.println(
                        "Service gagal dihapus!"
                );
            }

        } else {

            System.out.println(
                    "Penghapusan dibatalkan."
            );
        }
    }

    private void ubahStatusService() {

        System.out.println(
                "\n---------- UBAH STATUS SERVICE ----------"
        );

        String id = inputNonKosong(
                "ID Service: "
        );

        if (controller.cariService(id) == null) {

            System.out.println(
                    "ID service tidak ditemukan!"
            );

            return;
        }

        String status = inputStatus();

        if (controller.ubahStatusService(
                id,
                status)) {

            System.out.println(
                    "Status service berhasil diubah!"
            );

        } else {

            System.out.println(
                    "Status service gagal diubah!"
            );
        }
    }


    private void tampilkanInformasiService() {

        System.out.println(
                "\n=============================================="
        );

        System.out.println(
                "           INFORMASI JENIS SERVICE"
        );

        System.out.println(
                "=============================================="
        );

        ServiceAC cuci =
                new ServiceCuciAC(1);

        ServiceAC perbaikan =
                new ServicePerbaikanAC("Sedang");

        System.out.println("\n1. Cuci AC");
        cuci.tampilkanService();

        System.out.println("\n2. Perbaikan AC");
        perbaikan.tampilkanService();
    }


    private void tampilkanDashboard() {

        System.out.println(
                "\n=============================================="
        );

        System.out.println(
                "                  DASHBOARD"
        );

        System.out.println(
                "=============================================="
        );

        System.out.println(
                "Total Pelanggan : "
                + controller.jumlahPelanggan()
        );

        System.out.println(
                "Total Teknisi   : "
                + controller.jumlahTeknisi()
        );

        System.out.println(
                "Total Service   : "
                + controller.jumlahService()
        );

        System.out.println(
                "=============================================="
        );
    }


    private String inputNonKosong(String pesan) {

        while (true) {

            System.out.print(pesan);

            String inputUser =
                    input.nextLine().trim();

            if (!inputUser.isEmpty()) {
                return inputUser;
            }

            System.out.println(
                    "Input tidak boleh kosong!"
            );
        }
    }

    private String inputNama(String pesan) {

        while (true) {

            String nama = inputNonKosong(pesan);

            if (nama.matches("[a-zA-Z .]+")) {
                return nama;
            }

            System.out.println(
                    "Nama hanya boleh berisi huruf!"
            );
        }
    }

    private String inputTelepon(String pesan) {

        while (true) {

            String nomor = inputNonKosong(pesan);

            if (nomor.matches("\\d{10,13}")) {
                return nomor;
            }

            System.out.println(
                    "Nomor telepon harus 10-13 digit angka! "
                    + "Contoh: 081234567890"
            );
        }
    }


    private int inputInt(String pesan) {

        while (true) {

            System.out.print(pesan);

            String nilai =
                    input.nextLine().trim();

            try {

                return Integer.parseInt(nilai);

            } catch (NumberFormatException e) {

                System.out.println(
                        "Input harus berupa angka bulat! "
                        + "Coba lagi."
                );
            }
        }
    }

    private int inputInt(
            String pesan,
            int minimum,
            int maksimum) {

        while (true) {

            int nilai = inputInt(pesan);

            if (nilai >= minimum
                    && nilai <= maksimum) {

                return nilai;
            }

            System.out.println(
                    "Input harus berada di antara "
                    + minimum + " sampai "
                    + maksimum + "."
            );
        }
    }


    private double inputDoublePositif(
            String pesan) {

        while (true) {

            System.out.print(pesan);

            String nilai =
                    input.nextLine().trim();

            try {

                double hasil =
                        Double.parseDouble(nilai);

                if (hasil > 0) {
                    return hasil;
                }

                System.out.println(
                        "Biaya harus lebih dari 0!"
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Input harus berupa angka! "
                        + "Contoh: 150000"
                );
            }
        }
    }


    private String inputTanggal(String pesan) {

        while (true) {

            String tanggal =
                    inputNonKosong(pesan);

            if (TanggalUtil.valid(tanggal)) {

                return tanggal;
            }

            System.out.println(
                    "Format atau tanggal tidak valid!"
            );

            System.out.println(
                    "Gunakan format DD-MM-YYYY."
            );
        }
    }


    private String inputStatus() {

        while (true) {

            System.out.println(
                    "1. Menunggu"
            );

            System.out.println(
                    "2. Diproses"
            );

            System.out.println(
                    "3. Selesai"
            );

            int pilihan = inputInt(
                    "Pilih status: ",
                    1,
                    3
            );

            switch (pilihan) {

                case 1:
                    return "Menunggu";

                case 2:
                    return "Diproses";

                case 3:
                    return "Selesai";
            }
        }
    }
}