package controller;

import dao.AlatDAO;
import dao.AlatDAOImpl;
import dao.PenyewaDAO;
import dao.PenyewaDAOImpl;
import dao.SewaDAO;
import dao.SewaDAOImpl;
import model.AlatKemping;
import model.AlatMasak;
import model.Penyewa;
import model.Sewa;
import model.Tenda;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PenyewaanController {

    private AlatDAO alatDAO;
    private PenyewaDAO penyewaDAO;
    private SewaDAO sewaDAO;

    public PenyewaanController() {

        alatDAO = new AlatDAOImpl();
        penyewaDAO = new PenyewaDAOImpl();
        sewaDAO = new SewaDAOImpl();
    }

    private void tampilkanError(String aksi, SQLException e) {

        System.out.println(
                "Gagal " + aksi + ": " + e.getMessage()
        );
    }

    // =========================
    // ALAT
    // =========================

    public List<AlatKemping> getDaftarAlat() {

        try {
            return alatDAO.getAll();

        } catch (SQLException e) {
            tampilkanError("mengambil data alat", e);
            return new ArrayList<>();
        }
    }

    public boolean tambahTenda(
            String nama,
            double harga,
            int stok,
            int kapasitas) {

        try {
            alatDAO.insert(
                    new Tenda(nama, harga, stok, kapasitas)
            );
            return true;

        } catch (SQLException e) {
            tampilkanError("menambah tenda", e);
            return false;
        }
    }

    public boolean tambahAlatMasak(
            String nama,
            double harga,
            int stok,
            String bahanBakar) {

        try {
            alatDAO.insert(
                    new AlatMasak(nama, harga, stok, bahanBakar)
            );
            return true;

        } catch (SQLException e) {
            tampilkanError("menambah alat masak", e);
            return false;
        }
    }

    public boolean ubahTenda(
            int id,
            String nama,
            double harga,
            int stok,
            int kapasitas) {

        try {
            alatDAO.update(
                    new Tenda(id, nama, harga, stok, kapasitas)
            );
            return true;

        } catch (SQLException e) {
            tampilkanError("mengubah tenda", e);
            return false;
        }
    }

    public boolean ubahAlatMasak(
            int id,
            String nama,
            double harga,
            int stok,
            String bahanBakar) {

        try {
            alatDAO.update(
                    new AlatMasak(id, nama, harga, stok, bahanBakar)
            );
            return true;

        } catch (SQLException e) {
            tampilkanError("mengubah alat masak", e);
            return false;
        }
    }

    public boolean hapusAlat(int id) {

        try {
            alatDAO.delete(id);
            return true;

        } catch (SQLException e) {
            tampilkanError("menghapus alat", e);
            return false;
        }
    }

    // =========================
    // PENYEWA
    // =========================

    public List<Penyewa> getDaftarPenyewa() {

        try {
            return penyewaDAO.getAll();

        } catch (SQLException e) {
            tampilkanError("mengambil data penyewa", e);
            return new ArrayList<>();
        }
    }

    public boolean tambahPenyewa(
            String nama,
            String noTelepon,
            String alamat) {

        try {
            penyewaDAO.insert(
                    new Penyewa(nama, noTelepon, alamat)
            );
            return true;

        } catch (SQLException e) {
            tampilkanError("menambah penyewa", e);
            return false;
        }
    }

    public boolean ubahPenyewa(
            int id,
            String nama,
            String noTelepon,
            String alamat) {

        try {
            penyewaDAO.update(
                    new Penyewa(id, nama, noTelepon, alamat)
            );
            return true;

        } catch (SQLException e) {
            tampilkanError("mengubah penyewa", e);
            return false;
        }
    }

    public boolean hapusPenyewa(int id) {

        try {
            penyewaDAO.delete(id);
            return true;

        } catch (SQLException e) {
            tampilkanError("menghapus penyewa", e);
            return false;
        }
    }

    // =========================
    // SEWA
    // =========================

    public List<Sewa> getDaftarSewa() {

        try {
            return sewaDAO.getAll();

        } catch (SQLException e) {
            tampilkanError("mengambil data sewa", e);
            return new ArrayList<>();
        }
    }

    public boolean tambahSewa(
            String namaPenyewa,
            String namaAlat,
            String tanggalSewa,
            int lamaSewa,
            double totalHarga) {

        try {
            sewaDAO.insert(
                    new Sewa(namaPenyewa, namaAlat,
                             tanggalSewa, lamaSewa, totalHarga)
            );
            return true;

        } catch (SQLException e) {
            tampilkanError("menambah sewa", e);
            return false;
        }
    }

    public boolean ubahSewa(
            int id,
            String namaPenyewa,
            String namaAlat,
            String tanggalSewa,
            int lamaSewa,
            double totalHarga) {

        try {
            sewaDAO.update(
                    new Sewa(id, namaPenyewa, namaAlat,
                             tanggalSewa, lamaSewa, totalHarga)
            );
            return true;

        } catch (SQLException e) {
            tampilkanError("mengubah sewa", e);
            return false;
        }
    }

    public boolean hapusSewa(int id) {

        try {
            sewaDAO.delete(id);
            return true;

        } catch (SQLException e) {
            tampilkanError("menghapus sewa", e);
            return false;
        }
    }
}