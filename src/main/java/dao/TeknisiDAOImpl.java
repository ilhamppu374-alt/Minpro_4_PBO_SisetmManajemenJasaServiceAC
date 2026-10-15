package dao;

import config.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import model.Teknisi;

public class TeknisiDAOImpl implements TeknisiDAO {

    @Override
    public boolean tambah(Teknisi teknisi) {

        String sql = "INSERT INTO teknisi "
                + "(id, nama, no_telepon, spesialisasi) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, teknisi.getId());
            ps.setString(2, teknisi.getNama());
            ps.setString(3, teknisi.getNoTelepon());
            ps.setString(4, teknisi.getSpesialisasi());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            System.err.println(
                    "Gagal menambah teknisi: " + e.getMessage()
            );

            return false;
        }
    }

    @Override
    public Teknisi cariById(String id) {

        String sql = "SELECT id, nama, no_telepon, spesialisasi "
                + "FROM teknisi WHERE id = ?";

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
                    "Gagal mencari teknisi: " + e.getMessage()
            );
        }

        return null;
    }

    @Override
    public List<Teknisi> ambilSemua() {

        List<Teknisi> hasil = new LinkedList<>();

        String sql = "SELECT id, nama, no_telepon, spesialisasi "
                + "FROM teknisi ORDER BY id";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                hasil.add(mapRow(rs));
            }

        } catch (SQLException e) {

            System.err.println(
                    "Gagal mengambil data teknisi: "
                    + e.getMessage()
            );
        }

        return hasil;
    }

    @Override
    public boolean ubah(Teknisi teknisi) {

        String sql = "UPDATE teknisi "
                + "SET nama = ?, no_telepon = ?, spesialisasi = ? "
                + "WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, teknisi.getNama());
            ps.setString(2, teknisi.getNoTelepon());
            ps.setString(3, teknisi.getSpesialisasi());
            ps.setString(4, teknisi.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            System.err.println(
                    "Gagal mengubah teknisi: " + e.getMessage()
            );

            return false;
        }
    }

    @Override
    public boolean hapus(String id) {

        String sql = "DELETE FROM teknisi WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            System.err.println(
                    "Gagal menghapus teknisi: " + e.getMessage()
            );

            return false;
        }
    }

    @Override
    public int hitung() {

        String sql = "SELECT COUNT(*) FROM teknisi";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {

            System.err.println(
                    "Gagal menghitung teknisi: " + e.getMessage()
            );
        }

        return 0;
    }

    private Teknisi mapRow(ResultSet rs) throws SQLException {

        return new Teknisi(
                rs.getString("id"),
                rs.getString("nama"),
                rs.getString("no_telepon"),
                rs.getString("spesialisasi")
        );
    }
}
