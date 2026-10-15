package dao;

import java.util.List;
import model.DetailService;

public interface ServiceDAO {

    boolean tambah(DetailService service);

    DetailService cariById(String idService);

    List<DetailService> ambilSemua();

    boolean ubah(DetailService service);

    boolean ubahStatus(String idService, String statusBaru);

    boolean hapus(String idService);

    int hitung();
}
