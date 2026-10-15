package dao;

import java.util.List;
import model.Pelanggan;

public interface PelangganDAO {

    boolean tambah(Pelanggan pelanggan);

    Pelanggan cariById(String id);

    List<Pelanggan> ambilSemua();

    boolean ubah(Pelanggan pelanggan);

    boolean hapus(String id);

    int hitung();
}
