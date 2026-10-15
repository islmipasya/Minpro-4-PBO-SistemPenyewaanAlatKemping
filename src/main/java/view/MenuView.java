package view;

import controller.PenyewaanController;
import model.AlatKemping;
import model.AlatMasak;
import model.Penyewa;
import model.Sewa;
import model.Tenda;

import java.util.List;
import java.util.Scanner;

public class MenuView {

    private Scanner input;

    public MenuView() {
        input = new Scanner(System.in);
    }

    public void jalankan(PenyewaanController controller) {

        int pilihan;

        do {

            System.out.println();
            System.out.println("======================================");
            System.out.println("     SISTEM PENYEWAAN ALAT CAMPING");
            System.out.println("======================================");
            System.out.println("1. Kelola Alat Camping");
            System.out.println("2. Kelola Penyewa");
            System.out.println("3. Kelola Sewa");
            System.out.println("0. Keluar");

            pilihan = inputInt("Pilih menu: ");

            switch (pilihan) {

                case 1:
                    menuAlat(controller);
                    break;

                case 2:
                    menuPenyewa(controller);
                    break;

                case 3:
                    menuSewa(controller);
                    break;

                case 0:
                    System.out.println("Program selesai.");
                    break;

                default:
                    System.out.println("Menu tidak tersedia.");
            }

        } while (pilihan != 0);
    }

    // ==================================
    // MENU ALAT
    // ==================================

    private void menuAlat(PenyewaanController controller) {

        int pilihan;

        do {

            System.out.println();
            System.out.println("=== MENU ALAT CAMPING ===");
            System.out.println("1. Tambah Alat");
            System.out.println("2. Lihat Alat");
            System.out.println("3. Ubah Alat");
            System.out.println("4. Hapus Alat");
            System.out.println("0. Kembali");

            pilihan = inputInt("Pilih menu: ");

            switch (pilihan) {

                case 1:
                    tambahAlat(controller);
                    break;

                case 2:
                    lihatAlat(controller);
                    break;

                case 3:
                    ubahAlat(controller);
                    break;

                case 4:
                    hapusAlat(controller);
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Menu tidak tersedia.");
            }

        } while (pilihan != 0);
    }

    private void tambahAlat(PenyewaanController controller) {

        System.out.println();
        System.out.println("=== TAMBAH ALAT ===");
        System.out.println("1. Tenda");
        System.out.println("2. Alat Masak");

        int jenis = inputInt("Pilih jenis alat: ");

        if (jenis != 1 && jenis != 2) {
            System.out.println("Jenis alat tidak tersedia.");
            return;
        }

        String nama = inputString("Nama alat: ");
        double harga = inputDouble("Harga sewa: ");
        int stok = inputIntNonNegative("Stok: ");

        boolean berhasil;

        if (jenis == 1) {

            int kapasitas =
                    inputIntPositive("Kapasitas tenda: ");

            berhasil = controller.tambahTenda(
                    nama, harga, stok, kapasitas
            );

        } else {

            String bahanBakar =
                    inputString("Jenis bahan bakar: ");

            berhasil = controller.tambahAlatMasak(
                    nama, harga, stok, bahanBakar
            );
        }

        if (berhasil) {
            System.out.println(
                    "Data alat berhasil ditambahkan."
            );
        }
    }

    private void tampilkanDaftarAlat(List<AlatKemping> daftar) {

        System.out.println();
        System.out.println("=== DAFTAR ALAT CAMPING ===");

        if (daftar.isEmpty()) {

            System.out.println("Belum ada data alat.");
            return;
        }

        int nomor = 1;

        for (AlatKemping alat : daftar) {

            System.out.println();
            System.out.println("Data ke-" + nomor);

            // Polymorphism overriding
            alat.tampilkanInfo();

            nomor++;
        }
    }

    private void lihatAlat(PenyewaanController controller) {

        tampilkanDaftarAlat(controller.getDaftarAlat());
    }

    private void ubahAlat(PenyewaanController controller) {

        List<AlatKemping> daftar = controller.getDaftarAlat();

        tampilkanDaftarAlat(daftar);

        if (daftar.isEmpty()) {
            return;
        }

        int nomor =
                inputIntPositive("Pilih nomor alat: ");

        int index = nomor - 1;

        if (index < 0 || index >= daftar.size()) {

            System.out.println("Data tidak ditemukan.");
            return;
        }

        AlatKemping alat = daftar.get(index);

        String nama =
                inputString("Nama alat baru: ");

        double harga =
                inputDouble("Harga sewa baru: ");

        int stok =
                inputIntNonNegative("Stok baru: ");

        boolean berhasil = false;

        if (alat instanceof Tenda) {

            int kapasitas =
                    inputIntPositive(
                            "Kapasitas tenda baru: "
                    );

            berhasil = controller.ubahTenda(
                    alat.getId(), nama, harga, stok, kapasitas
            );

        } else if (alat instanceof AlatMasak) {

            String bahanBakar =
                    inputString(
                            "Jenis bahan bakar baru: "
                    );

            berhasil = controller.ubahAlatMasak(
                    alat.getId(), nama, harga, stok, bahanBakar
            );
        }

        if (berhasil) {
            System.out.println(
                    "Data alat berhasil diubah."
            );
        }
    }

    private void hapusAlat(PenyewaanController controller) {

        List<AlatKemping> daftar = controller.getDaftarAlat();

        tampilkanDaftarAlat(daftar);

        if (daftar.isEmpty()) {
            return;
        }

        int nomor =
                inputIntPositive("Pilih nomor alat: ");

        int index = nomor - 1;

        if (index < 0 || index >= daftar.size()) {

            System.out.println("Data tidak ditemukan.");
            return;
        }

        if (controller.hapusAlat(daftar.get(index).getId())) {
            System.out.println(
                    "Data alat berhasil dihapus."
            );
        }
    }

    // ==================================
    // MENU PENYEWA
    // ==================================

    private void menuPenyewa(
            PenyewaanController controller) {

        int pilihan;

        do {

            System.out.println();
            System.out.println("=== MENU PENYEWA ===");
            System.out.println("1. Tambah Penyewa");
            System.out.println("2. Lihat Penyewa");
            System.out.println("3. Ubah Penyewa");
            System.out.println("4. Hapus Penyewa");
            System.out.println("0. Kembali");

            pilihan = inputInt("Pilih menu: ");

            switch (pilihan) {

                case 1:
                    tambahPenyewa(controller);
                    break;

                case 2:
                    lihatPenyewa(controller);
                    break;

                case 3:
                    ubahPenyewa(controller);
                    break;

                case 4:
                    hapusPenyewa(controller);
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Menu tidak tersedia.");
            }

        } while (pilihan != 0);
    }

    private void tambahPenyewa(
            PenyewaanController controller) {

        String nama =
                inputString("Nama: ");

        String noTelepon =
                inputString("No Telepon: ");

        String alamat =
                inputString("Alamat: ");

        if (controller.tambahPenyewa(nama, noTelepon, alamat)) {
            System.out.println(
                    "Data penyewa berhasil ditambahkan."
            );
        }
    }

    private void tampilkanDaftarPenyewa(List<Penyewa> daftar) {

        System.out.println();
        System.out.println("=== DAFTAR PENYEWA ===");

        if (daftar.isEmpty()) {

            System.out.println("Belum ada data penyewa.");
            return;
        }

        int nomor = 1;

        for (Penyewa penyewa : daftar) {

            System.out.println();
            System.out.println("Data ke-" + nomor);

            penyewa.tampilkanInfo();

            nomor++;
        }
    }

    private void lihatPenyewa(
            PenyewaanController controller) {

        tampilkanDaftarPenyewa(controller.getDaftarPenyewa());
    }

    private void ubahPenyewa(
            PenyewaanController controller) {

        List<Penyewa> daftar = controller.getDaftarPenyewa();

        tampilkanDaftarPenyewa(daftar);

        if (daftar.isEmpty()) {
            return;
        }

        int nomor =
                inputIntPositive("Pilih nomor penyewa: ");

        int index = nomor - 1;

        if (index < 0 || index >= daftar.size()) {

            System.out.println("Data tidak ditemukan.");
            return;
        }

        String nama =
                inputString("Nama baru: ");

        String noTelepon =
                inputString("No Telepon baru: ");

        String alamat =
                inputString("Alamat baru: ");

        boolean berhasil = controller.ubahPenyewa(
                daftar.get(index).getId(),
                nama,
                noTelepon,
                alamat
        );

        if (berhasil) {
            System.out.println(
                    "Data penyewa berhasil diubah."
            );
        }
    }

    private void hapusPenyewa(
            PenyewaanController controller) {

        List<Penyewa> daftar = controller.getDaftarPenyewa();

        tampilkanDaftarPenyewa(daftar);

        if (daftar.isEmpty()) {
            return;
        }

        int nomor =
                inputIntPositive("Pilih nomor penyewa: ");

        int index = nomor - 1;

        if (index < 0 || index >= daftar.size()) {

            System.out.println("Data tidak ditemukan.");
            return;
        }

        if (controller.hapusPenyewa(daftar.get(index).getId())) {
            System.out.println(
                    "Data penyewa berhasil dihapus."
            );
        }
    }

    // ==================================
    // MENU SEWA
    // ==================================

    private void menuSewa(
            PenyewaanController controller) {

        int pilihan;

        do {

            System.out.println();
            System.out.println("=== MENU SEWA ===");
            System.out.println("1. Tambah Sewa");
            System.out.println("2. Lihat Sewa");
            System.out.println("3. Ubah Sewa");
            System.out.println("4. Hapus Sewa");
            System.out.println("0. Kembali");

            pilihan = inputInt("Pilih menu: ");

            switch (pilihan) {

                case 1:
                    tambahSewa(controller);
                    break;

                case 2:
                    lihatSewa(controller);
                    break;

                case 3:
                    ubahSewa(controller);
                    break;

                case 4:
                    hapusSewa(controller);
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Menu tidak tersedia.");
            }

        } while (pilihan != 0);
    }

    private void tambahSewa(
            PenyewaanController controller) {

        String namaPenyewa =
                inputString("Nama penyewa: ");

        String namaAlat =
                inputString("Nama alat yang disewa: ");

        String tanggalSewa =
                inputString("Tanggal sewa: ");

        int lamaSewa =
                inputIntPositive("Lama sewa (hari): ");

        double totalHarga =
                inputDouble("Total harga: ");

        boolean berhasil = controller.tambahSewa(
                namaPenyewa,
                namaAlat,
                tanggalSewa,
                lamaSewa,
                totalHarga
        );

        if (berhasil) {
            System.out.println(
                    "Data sewa berhasil ditambahkan."
            );
        }
    }

    private void tampilkanDaftarSewa(List<Sewa> daftar) {

        System.out.println();
        System.out.println("=== DAFTAR SEWA ===");

        if (daftar.isEmpty()) {

            System.out.println("Belum ada data sewa.");
            return;
        }

        int nomor = 1;

        for (Sewa sewa : daftar) {

            System.out.println();
            System.out.println("Data ke-" + nomor);

            sewa.tampilkanInfo();

            nomor++;
        }
    }

    private void lihatSewa(
            PenyewaanController controller) {

        tampilkanDaftarSewa(controller.getDaftarSewa());
    }

    private void ubahSewa(
            PenyewaanController controller) {

        List<Sewa> daftar = controller.getDaftarSewa();

        tampilkanDaftarSewa(daftar);

        if (daftar.isEmpty()) {
            return;
        }

        int nomor =
                inputIntPositive("Pilih nomor sewa: ");

        int index = nomor - 1;

        if (index < 0 || index >= daftar.size()) {

            System.out.println("Data tidak ditemukan.");
            return;
        }

        String namaPenyewa =
                inputString("Nama penyewa baru: ");

        String namaAlat =
                inputString("Nama alat baru: ");

        String tanggalSewa =
                inputString("Tanggal sewa baru: ");

        int lamaSewa =
                inputIntPositive("Lama sewa baru: ");

        double totalHarga =
                inputDouble("Total harga baru: ");

        boolean berhasil = controller.ubahSewa(
                daftar.get(index).getId(),
                namaPenyewa,
                namaAlat,
                tanggalSewa,
                lamaSewa,
                totalHarga
        );

        if (berhasil) {
            System.out.println(
                    "Data sewa berhasil diubah."
            );
        }
    }

    private void hapusSewa(
            PenyewaanController controller) {

        List<Sewa> daftar = controller.getDaftarSewa();

        tampilkanDaftarSewa(daftar);

        if (daftar.isEmpty()) {
            return;
        }

        int nomor =
                inputIntPositive("Pilih nomor sewa: ");

        int index = nomor - 1;

        if (index < 0 || index >= daftar.size()) {

            System.out.println("Data tidak ditemukan.");
            return;
        }

        if (controller.hapusSewa(daftar.get(index).getId())) {
            System.out.println(
                    "Data sewa berhasil dihapus."
            );
        }
    }

    // ==================================
    // VALIDASI INPUT
    // ==================================

    private String inputString(String pesan) {

        while (true) {

            System.out.print(pesan);

            String nilai = input.nextLine();

            if (!nilai.trim().isEmpty()) {
                return nilai;
            }

            System.out.println("Input tidak boleh kosong.");
        }
    }

    private int inputInt(String pesan) {

        while (true) {

            System.out.print(pesan);

            try {

                return Integer.parseInt(input.nextLine());

            } catch (NumberFormatException e) {

                System.out.println("Input harus berupa angka.");
            }
        }
    }

    private int inputIntPositive(String pesan) {

        while (true) {

            int nilai = inputInt(pesan);

            if (nilai > 0) {
                return nilai;
            }

            System.out.println("Nilai harus lebih dari 0.");
        }
    }

    private int inputIntNonNegative(String pesan) {

        while (true) {

            int nilai = inputInt(pesan);

            if (nilai >= 0) {
                return nilai;
            }

            System.out.println("Nilai tidak boleh negatif.");
        }
    }

    private double inputDouble(String pesan) {

        while (true) {

            System.out.print(pesan);

            try {

                double nilai =
                        Double.parseDouble(input.nextLine());

                if (nilai >= 0) {
                    return nilai;
                }

                System.out.println("Nilai tidak boleh negatif.");

            } catch (NumberFormatException e) {

                System.out.println("Input harus berupa angka.");
            }
        }
    }
}