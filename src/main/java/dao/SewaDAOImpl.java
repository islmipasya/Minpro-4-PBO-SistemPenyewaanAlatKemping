package dao;

import config.Koneksi;
import model.Sewa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SewaDAOImpl implements SewaDAO {

    @Override
    public List<Sewa> getAll() throws SQLException {

        String sql = "SELECT * FROM sewa ORDER BY id";

        List<Sewa> daftar = new ArrayList<>();

        try (PreparedStatement ps =
                     Koneksi.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                daftar.add(new Sewa(
                        rs.getInt("id"),
                        rs.getString("nama_penyewa"),
                        rs.getString("nama_alat"),
                        rs.getString("tanggal_sewa"),
                        rs.getInt("lama_sewa"),
                        rs.getDouble("total_harga")
                ));
            }
        }

        return daftar;
    }

    @Override
    public void insert(Sewa sewa) throws SQLException {

        String sql = "INSERT INTO sewa "
                + "(nama_penyewa, nama_alat, tanggal_sewa, "
                + "lama_sewa, total_harga) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps =
                     Koneksi.getConnection().prepareStatement(sql)) {

            ps.setString(1, sewa.getNamaPenyewa());
            ps.setString(2, sewa.getNamaAlat());
            ps.setString(3, sewa.getTanggalSewa());
            ps.setInt(4, sewa.getLamaSewa());
            ps.setDouble(5, sewa.getTotalHarga());

            ps.executeUpdate();
        }
    }

    @Override
    public void update(Sewa sewa) throws SQLException {

        String sql = "UPDATE sewa SET nama_penyewa = ?, "
                + "nama_alat = ?, tanggal_sewa = ?, "
                + "lama_sewa = ?, total_harga = ? "
                + "WHERE id = ?";

        try (PreparedStatement ps =
                     Koneksi.getConnection().prepareStatement(sql)) {

            ps.setString(1, sewa.getNamaPenyewa());
            ps.setString(2, sewa.getNamaAlat());
            ps.setString(3, sewa.getTanggalSewa());
            ps.setInt(4, sewa.getLamaSewa());
            ps.setDouble(5, sewa.getTotalHarga());
            ps.setInt(6, sewa.getId());

            ps.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {

        String sql = "DELETE FROM sewa WHERE id = ?";

        try (PreparedStatement ps =
                     Koneksi.getConnection().prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}