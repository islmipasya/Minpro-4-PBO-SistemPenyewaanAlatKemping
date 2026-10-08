package controller;

import model.AlatKemping;
import model.Tenda;
import model.AlatMasak;
import model.Penyewa;
import model.Sewa;

import java.util.ArrayList;

public class PenyewaanController {

    private ArrayList<AlatKemping> daftarAlat;
    private ArrayList<Penyewa> daftarPenyewa;
    private ArrayList<Sewa> daftarSewa;

    public PenyewaanController() {

        daftarAlat = new ArrayList<>();
        daftarPenyewa = new ArrayList<>();
        daftarSewa = new ArrayList<>();

        dataAwal();
    }

    private void dataAwal() {

        daftarAlat.add(
            new Tenda(
                "Tenda Dome",
                50000,
                5,
                4
            )
        );

        daftarAlat.add(
            new AlatMasak(
                "Kompor Portable",
                30000,
                3,
                "Gas"
            )
        );

        daftarPenyewa.add(
            new Penyewa(
                "Pasya",
                "081234567890",
                "Samarinda"
            )
        );

        daftarSewa.add(
            new Sewa(
                "Yuda",
                "Tenda Dome",
                "22-09-2026",
                2,
                100000
            )
        );
    }

    // =========================
    // ALAT
    // =========================

    public ArrayList<AlatKemping> getDaftarAlat() {
        return daftarAlat;
    }

    public void tambahTenda(
            String nama,
            double harga,
            int stok,
            int kapasitas) {

        daftarAlat.add(
            new Tenda(
                nama,
                harga,
                stok,
                kapasitas
            )
        );
    }

    public void tambahAlatMasak(
            String nama,
            double harga,
            int stok,
            String bahanBakar) {

        daftarAlat.add(
            new AlatMasak(
                nama,
                harga,
                stok,
                bahanBakar
            )
        );
    }

    public void ubahAlat(
            int index,
            String nama,
            double harga,
            int stok) {

        if (index >= 0 && index < daftarAlat.size()) {

            AlatKemping alat = daftarAlat.get(index);

            alat.setNamaAlat(nama);
            alat.setHargaSewa(harga);
            alat.setStok(stok);
        }
    }

    public void ubahTenda(
            int index,
            int kapasitas) {

        if (index >= 0 &&
            index < daftarAlat.size() &&
            daftarAlat.get(index) instanceof Tenda) {

            Tenda tenda =
                    (Tenda) daftarAlat.get(index);

            tenda.setKapasitas(kapasitas);
        }
    }

    public void ubahAlatMasak(
            int index,
            String bahanBakar) {

        if (index >= 0 &&
            index < daftarAlat.size() &&
            daftarAlat.get(index) instanceof AlatMasak) {

            AlatMasak alat =
                    (AlatMasak) daftarAlat.get(index);

            alat.setJenisBahanBakar(bahanBakar);
        }
    }

    public void hapusAlat(int index) {

        if (index >= 0 &&
            index < daftarAlat.size()) {

            daftarAlat.remove(index);
        }
    }

    // =========================
    // PENYEWA
    // =========================

    public ArrayList<Penyewa> getDaftarPenyewa() {
        return daftarPenyewa;
    }

    public void tambahPenyewa(
            String nama,
            String noTelepon,
            String alamat) {

        daftarPenyewa.add(
            new Penyewa(
                nama,
                noTelepon,
                alamat
            )
        );
    }

    public void ubahPenyewa(
            int index,
            String nama,
            String noTelepon,
            String alamat) {

        if (index >= 0 &&
            index < daftarPenyewa.size()) {

            Penyewa penyewa =
                    daftarPenyewa.get(index);

            penyewa.setNama(nama);
            penyewa.setNoTelepon(noTelepon);
            penyewa.setAlamat(alamat);
        }
    }

    public void hapusPenyewa(int index) {

        if (index >= 0 &&
            index < daftarPenyewa.size()) {

            daftarPenyewa.remove(index);
        }
    }

    // =========================
    // SEWA
    // =========================

    public ArrayList<Sewa> getDaftarSewa() {
        return daftarSewa;
    }

    public void tambahSewa(
            String namaPenyewa,
            String namaAlat,
            String tanggalSewa,
            int lamaSewa,
            double totalHarga) {

        daftarSewa.add(
            new Sewa(
                namaPenyewa,
                namaAlat,
                tanggalSewa,
                lamaSewa,
                totalHarga
            )
        );
    }

    public void ubahSewa(
            int index,
            String namaPenyewa,
            String namaAlat,
            String tanggalSewa,
            int lamaSewa,
            double totalHarga) {

        if (index >= 0 &&
            index < daftarSewa.size()) {

            Sewa sewa =
                    daftarSewa.get(index);

            sewa.setNamaPenyewa(namaPenyewa);
            sewa.setNamaAlat(namaAlat);
            sewa.setTanggalSewa(tanggalSewa);
            sewa.setLamaSewa(lamaSewa);
            sewa.setTotalHarga(totalHarga);
        }
    }

    public void hapusSewa(int index) {

        if (index >= 0 &&
            index < daftarSewa.size()) {

            daftarSewa.remove(index);
        }
    }
}