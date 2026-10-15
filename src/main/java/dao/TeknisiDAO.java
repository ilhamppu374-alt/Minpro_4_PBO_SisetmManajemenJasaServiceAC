package dao;

import java.util.List;
import model.Teknisi;

public interface TeknisiDAO {

    boolean tambah(Teknisi teknisi);

    Teknisi cariById(String id);

    List<Teknisi> ambilSemua();

    boolean ubah(Teknisi teknisi);

    boolean hapus(String id);

    int hitung();
}
