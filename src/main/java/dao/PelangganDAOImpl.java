package dao;

import config.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import model.Pelanggan;

public class PelangganDAOImpl implements PelangganDAO {

    @Override
    public boolean tambah(Pelanggan pelanggan) {

        String sql = "INSERT INTO pelanggan "
                + "(id, nama, no_telepon, alamat) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, pelanggan.getId());
            ps.setString(2, pelanggan.getNama());
            ps.setString(3, pelanggan.getNoTelepon());
            ps.setString(4, pelanggan.getAlamat());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            System.err.println(
                    "Gagal menambah pelanggan: " + e.getMessage()
            );

            return false;
        }
    }

    @Override
    public Pelanggan cariById(String id) {

        String sql = "SELECT id, nama, no_telepon, alamat "
                + "FROM pelanggan WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return mapRow(rs);
                }
            }

        } catch (SQLException e) {

            System.err.println(
                    "Gagal mencari pelanggan: " + e.getMessage()
            );
        }

        return null;
    }

    @Override
    public List<Pelanggan> ambilSemua() {

        List<Pelanggan> hasil = new LinkedList<>();

        String sql = "SELECT id, nama, no_telepon, alamat "
                + "FROM pelanggan ORDER BY id";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                hasil.add(mapRow(rs));
            }

        } catch (SQLException e) {

            System.err.println(
                    "Gagal mengambil data pelanggan: "
                    + e.getMessage()
            );
        }

        return hasil;
    }

    @Override
    public boolean ubah(Pelanggan pelanggan) {

        String sql = "UPDATE pelanggan "
                + "SET nama = ?, no_telepon = ?, alamat = ? "
                + "WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, pelanggan.getNama());
            ps.setString(2, pelanggan.getNoTelepon());
            ps.setString(3, pelanggan.getAlamat());
            ps.setString(4, pelanggan.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            System.err.println(
                    "Gagal mengubah pelanggan: " + e.getMessage()
            );

            return false;
        }
    }

    @Override
    public boolean hapus(String id) {

        String sql = "DELETE FROM pelanggan WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            // Biasanya karena masih dipakai di tabel service (foreign key)
            System.err.println(
                    "Gagal menghapus pelanggan: " + e.getMessage()
            );

            return false;
        }
    }

    @Override
    public int hitung() {

        String sql = "SELECT COUNT(*) FROM pelanggan";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {

            System.err.println(
                    "Gagal menghitung pelanggan: " + e.getMessage()
            );
        }

        return 0;
    }

    private Pelanggan mapRow(ResultSet rs) throws SQLException {

        return new Pelanggan(
                rs.getString("id"),
                rs.getString("nama"),
                rs.getString("no_telepon"),
                rs.getString("alamat")
        );
    }
}
