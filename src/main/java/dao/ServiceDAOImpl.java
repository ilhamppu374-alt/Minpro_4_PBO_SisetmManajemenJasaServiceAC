package dao;

import config.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import model.DetailService;
import util.TanggalUtil;

public class ServiceDAOImpl implements ServiceDAO {

    private static final String KOLOM
            = "id_service, id_pelanggan, id_teknisi, jenis_ac, "
            + "keluhan, tanggal, status, biaya";

    @Override
    public boolean tambah(DetailService service) {

        String sql = "INSERT INTO service (" + KOLOM + ") "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, service.getIdService());
            ps.setString(2, service.getIdPelanggan());
            ps.setString(3, service.getIdTeknisi());
            ps.setString(4, service.getJenisAc());
            ps.setString(5, service.getKeluhan());
            ps.setDate(6, TanggalUtil.keSqlDate(service.getTanggal()));
            ps.setString(7, service.getStatus());
            ps.setDouble(8, service.getBiaya());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            System.err.println(
                    "Gagal menambah service: " + e.getMessage()
            );

            return false;
        }
    }

    @Override
    public DetailService cariById(String idService) {

        String sql = "SELECT " + KOLOM
                + " FROM service WHERE id_service = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, idService);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return mapRow(rs);
                }
            }

        } catch (SQLException e) {

            System.err.println(
                    "Gagal mencari service: " + e.getMessage()
            );
        }

        return null;
    }

    @Override
    public List<DetailService> ambilSemua() {

        List<DetailService> hasil = new LinkedList<>();

        String sql = "SELECT " + KOLOM
                + " FROM service ORDER BY id_service";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                hasil.add(mapRow(rs));
            }

        } catch (SQLException e) {

            System.err.println(
                    "Gagal mengambil data service: "
                    + e.getMessage()
            );
        }

        return hasil;
    }

    @Override
    public boolean ubah(DetailService service) {

        String sql = "UPDATE service SET "
                + "id_pelanggan = ?, id_teknisi = ?, jenis_ac = ?, "
                + "keluhan = ?, tanggal = ?, status = ?, biaya = ? "
                + "WHERE id_service = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, service.getIdPelanggan());
            ps.setString(2, service.getIdTeknisi());
            ps.setString(3, service.getJenisAc());
            ps.setString(4, service.getKeluhan());
            ps.setDate(5, TanggalUtil.keSqlDate(service.getTanggal()));
            ps.setString(6, service.getStatus());
            ps.setDouble(7, service.getBiaya());
            ps.setString(8, service.getIdService());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            System.err.println(
                    "Gagal mengubah service: " + e.getMessage()
            );

            return false;
        }
    }

    @Override
    public boolean ubahStatus(String idService, String statusBaru) {

        String sql = "UPDATE service SET status = ? "
                + "WHERE id_service = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, statusBaru);
            ps.setString(2, idService);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            System.err.println(
                    "Gagal mengubah status service: "
                    + e.getMessage()
            );

            return false;
        }
    }

    @Override
    public boolean hapus(String idService) {

        String sql = "DELETE FROM service WHERE id_service = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, idService);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            System.err.println(
                    "Gagal menghapus service: " + e.getMessage()
            );

            return false;
        }
    }

    @Override
    public int hitung() {

        String sql = "SELECT COUNT(*) FROM service";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {

            System.err.println(
                    "Gagal menghitung service: " + e.getMessage()
            );
        }

        return 0;
    }

    private DetailService mapRow(ResultSet rs) throws SQLException {

        return new DetailService(
                rs.getString("id_service"),
                rs.getString("id_pelanggan"),
                rs.getString("id_teknisi"),
                rs.getString("jenis_ac"),
                rs.getString("keluhan"),
                TanggalUtil.dariSqlDate(rs.getDate("tanggal")),
                rs.getString("status"),
                rs.getDouble("biaya")
        );
    }
}
