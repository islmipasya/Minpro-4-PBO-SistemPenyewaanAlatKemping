package dao;

import config.Koneksi;
import model.AlatKemping;
import model.AlatMasak;
import model.Tenda;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class AlatDAOImpl implements AlatDAO {

    @Override
    public List<AlatKemping> getAll() throws SQLException {

        String sql = "SELECT * FROM alat_kemping ORDER BY id";

        List<AlatKemping> daftar = new ArrayList<>();

        try (PreparedStatement ps =
                     Koneksi.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                int id = rs.getInt("id");
                String nama = rs.getString("nama_alat");
                double harga = rs.getDouble("harga_sewa");
                int stok = rs.getInt("stok");
                String jenis = rs.getString("jenis");

                if ("TENDA".equals(jenis)) {

                    daftar.add(new Tenda(
                            id, nama, harga, stok,
                            rs.getInt("kapasitas")
                    ));

                } else {

                    daftar.add(new AlatMasak(
                            id, nama, harga, stok,
                            rs.getString("jenis_bahan_bakar")
                    ));
                }
            }
        }

        return daftar;
    }

    @Override
    public void insert(AlatKemping alat) throws SQLException {

        String sql = "INSERT INTO alat_kemping "
                + "(nama_alat, harga_sewa, stok, jenis, "
                + "kapasitas, jenis_bahan_bakar) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps =
                     Koneksi.getConnection().prepareStatement(sql)) {

            ps.setString(1, alat.getNamaAlat());
            ps.setDouble(2, alat.getHargaSewa());
            ps.setInt(3, alat.getStok());

            if (alat instanceof Tenda) {

                Tenda tenda = (Tenda) alat;

                ps.setString(4, "TENDA");
                ps.setInt(5, tenda.getKapasitas());
                ps.setNull(6, Types.VARCHAR);

            } else if (alat instanceof AlatMasak) {

                AlatMasak masak = (AlatMasak) alat;

                ps.setString(4, "ALAT_MASAK");
                ps.setNull(5, Types.INTEGER);
                ps.setString(6, masak.getJenisBahanBakar());
            }

            ps.executeUpdate();
        }
    }

    @Override
    public void update(AlatKemping alat) throws SQLException {

        String sql = "UPDATE alat_kemping SET "
                + "nama_alat = ?, harga_sewa = ?, stok = ?, "
                + "kapasitas = ?, jenis_bahan_bakar = ? "
                + "WHERE id = ?";

        try (PreparedStatement ps =
                     Koneksi.getConnection().prepareStatement(sql)) {

            ps.setString(1, alat.getNamaAlat());
            ps.setDouble(2, alat.getHargaSewa());
            ps.setInt(3, alat.getStok());

            if (alat instanceof Tenda) {

                Tenda tenda = (Tenda) alat;

                ps.setInt(4, tenda.getKapasitas());
                ps.setNull(5, Types.VARCHAR);

            } else if (alat instanceof AlatMasak) {

                AlatMasak masak = (AlatMasak) alat;

                ps.setNull(4, Types.INTEGER);
                ps.setString(5, masak.getJenisBahanBakar());
            }

            ps.setInt(6, alat.getId());

            ps.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {

        String sql = "DELETE FROM alat_kemping WHERE id = ?";

        try (PreparedStatement ps =
                     Koneksi.getConnection().prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}