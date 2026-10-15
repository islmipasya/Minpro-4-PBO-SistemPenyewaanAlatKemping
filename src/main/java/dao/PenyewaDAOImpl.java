package dao;

import config.Koneksi;
import model.Penyewa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PenyewaDAOImpl implements PenyewaDAO {

    @Override
    public List<Penyewa> getAll() throws SQLException {

        String sql = "SELECT * FROM penyewa ORDER BY id";

        List<Penyewa> daftar = new ArrayList<>();

        try (PreparedStatement ps =
                     Koneksi.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                daftar.add(new Penyewa(
                        rs.getInt("id"),
                        rs.getString("nama"),
                        rs.getString("no_telepon"),
                        rs.getString("alamat")
                ));
            }
        }

        return daftar;
    }

    @Override
    public void insert(Penyewa penyewa) throws SQLException {

        String sql = "INSERT INTO penyewa "
                + "(nama, no_telepon, alamat) VALUES (?, ?, ?)";

        try (PreparedStatement ps =
                     Koneksi.getConnection().prepareStatement(sql)) {

            ps.setString(1, penyewa.getNama());
            ps.setString(2, penyewa.getNoTelepon());
            ps.setString(3, penyewa.getAlamat());

            ps.executeUpdate();
        }
    }

    @Override
    public void update(Penyewa penyewa) throws SQLException {

        String sql = "UPDATE penyewa SET nama = ?, "
                + "no_telepon = ?, alamat = ? WHERE id = ?";

        try (PreparedStatement ps =
                     Koneksi.getConnection().prepareStatement(sql)) {

            ps.setString(1, penyewa.getNama());
            ps.setString(2, penyewa.getNoTelepon());
            ps.setString(3, penyewa.getAlamat());
            ps.setInt(4, penyewa.getId());

            ps.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {

        String sql = "DELETE FROM penyewa WHERE id = ?";

        try (PreparedStatement ps =
                     Koneksi.getConnection().prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}