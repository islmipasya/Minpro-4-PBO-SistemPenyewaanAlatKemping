package view;

import controller.PenyewaanController;
import model.AlatKemping;
import model.Tenda;
import model.AlatMasak;

import java.util.Scanner;

public class MenuView {

    private Scanner input;

    public MenuView() {
        input = new Scanner(System.in);
    }

    public void jalankan(
            PenyewaanController controller) {

        int pilihan;

        do {
            System.out.println("\n==================================");
            System.out.println("   SISTEM PENYEWAAN ALAT CAMPING");
            System.out.println("==================================");
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
                    System.out.println("Pilihan tidak tersedia.");
            }

        } while (pilihan != 0);
    }

    // =========================
    // MENU ALAT
    // =========================

    private void menuAlat(
            PenyewaanController controller) {

        int pilihan;

        do {
            System.out.println("\n=== MENU ALAT CAMPING ===");
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
                    System.out.println("Pilihan tidak tersedia.");
            }

        } while (pilihan != 0);
    }

    private void tambahAlat(
            PenyewaanController controller) {

        System.out.println("\n=== TAMBAH ALAT ===");
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

        if (jenis == 1) {

            int kapasitas =
                    inputIntPositive("Kapasitas tenda: ");

            controller.tambahTenda(
                    nama,
                    harga,
                    stok,
                    kapasitas
            );

        } else {

            String bahan =
                    inputString("Jenis bahan bakar: ");

            controller.tambahAlatMasak(
                    nama,
                    harga,
                    stok,
                    bahan
            );
        }

        System.out.println("Data alat berhasil ditambahkan.");
    }

    private void lihatAlat(
            PenyewaanController controller) {

        if (controller.getDaftarAlat().isEmpty()) {
            System.out.println("Belum ada data alat.");
            return;
        }

        System.out.println("\n=== DAFTAR ALAT CAMPING ===");

        for (int i = 0;
             i < controller.getDaftarAlat().size();
             i++) {

            System.out.println("\nData ke-" + (i + 1));

            AlatKemping alat =
                    controller.getDaftarAlat().get(i);

            // POLYMORPHISM
            alat.tampilkanInfo();
        }
    }

    private void ubahAlat(
            PenyewaanController controller) {

        lihatAlat(controller);

        if (controller.getDaftarAlat().isEmpty()) {
            return;
        }

        int nomor =
                inputInt("Nomor data yang diubah: ");

        int index = nomor - 1;

        if (index < 0 ||
            index >= controller.getDaftarAlat().size()) {

            System.out.println("Data tidak ditemukan.");
            return;
        }

        AlatKemping alat =
                controller.getDaftarAlat().get(index);

        String nama =
                inputString("Nama alat baru: ");

        double harga =
                inputDouble("Harga sewa baru: ");

        int stok =
                inputIntNonNegative("Stok baru: ");

        controller.ubahAlat(
                index,
                nama,
                harga,
                stok
        );

        if (alat instanceof Tenda) {

            int kapasitas =
                    inputIntPositive("Kapasitas baru: ");

            controller.ubahTenda(
                    index,
                    kapasitas
            );

        } else if (alat instanceof AlatMasak) {

            String bahan =
                    inputString("Bahan bakar baru: ");

            controller.ubahAlatMasak(
                    index,
                    bahan
            );
        }

        System.out.println("Data alat berhasil diubah.");
    }

    private void hapusAlat(
            PenyewaanController controller) {

        lihatAlat(controller);

        if (controller.getDaftarAlat().isEmpty()) {
            return;
        }

        int nomor =
                inputInt("Nomor data yang dihapus: ");

        int index = nomor - 1;

        if (index >= 0 &&
            index < controller.getDaftarAlat().size()) {

            controller.hapusAlat(index);

            System.out.println("Data alat berhasil dihapus.");

        } else {

            System.out.println("Data tidak ditemukan.");
        }
    }

    // =========================
    // MENU PENYEWA
    // =========================

    private void menuPenyewa(
            PenyewaanController controller) {

        int pilihan;

        do {
            System.out.println("\n=== MENU PENYEWA ===");
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
                    System.out.println("Pilihan tidak tersedia.");
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

        controller.tambahPenyewa(
                nama,
                noTelepon,
                alamat
        );

        System.out.println("Data penyewa berhasil ditambahkan.");
    }

    private void lihatPenyewa(
            PenyewaanController controller) {

        if (controller.getDaftarPenyewa().isEmpty()) {
            System.out.println("Belum ada data penyewa.");
            return;
        }

        System.out.println("\n=== DAFTAR PENYEWA ===");

        for (int i = 0;
             i < controller.getDaftarPenyewa().size();
             i++) {

            System.out.println("\nData ke-" + (i + 1));

            controller.getDaftarPenyewa()
                    .get(i)
                    .tampilkanInfo();
        }
    }

    private void ubahPenyewa(
            PenyewaanController controller) {

        lihatPenyewa(controller);

        if (controller.getDaftarPenyewa().isEmpty()) {
            return;
        }

        int nomor =
                inputInt("Nomor data yang diubah: ");

        int index = nomor - 1;

        if (index < 0 ||
            index >= controller.getDaftarPenyewa().size()) {

            System.out.println("Data tidak ditemukan.");
            return;
        }

        String nama =
                inputString("Nama baru: ");

        String noTelepon =
                inputString("No Telepon baru: ");

        String alamat =
                inputString("Alamat baru: ");

        controller.ubahPenyewa(
                index,
                nama,
                noTelepon,
                alamat
        );

        System.out.println("Data penyewa berhasil diubah.");
    }

    private void hapusPenyewa(
            PenyewaanController controller) {

        lihatPenyewa(controller);

        if (controller.getDaftarPenyewa().isEmpty()) {
            return;
        }

        int nomor =
                inputInt("Nomor data yang dihapus: ");

        int index = nomor - 1;

        if (index >= 0 &&
            index < controller.getDaftarPenyewa().size()) {

            controller.hapusPenyewa(index);

            System.out.println("Data penyewa berhasil dihapus.");

        } else {

            System.out.println("Data tidak ditemukan.");
        }
    }

    // =========================
    // MENU SEWA
    // =========================

    private void menuSewa(
            PenyewaanController controller) {

        int pilihan;

        do {
            System.out.println("\n=== MENU SEWA ===");
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
                    System.out.println("Pilihan tidak tersedia.");
            }

        } while (pilihan != 0);
    }

    private void tambahSewa(
            PenyewaanController controller) {

        String namaPenyewa =
                inputString("Nama penyewa: ");

        String namaAlat =
                inputString("Nama alat yang disewa: ");

        String tanggal =
                inputString("Tanggal sewa: ");

        int lama =
                inputIntPositive("Lama sewa (hari): ");

        double total =
                inputDouble("Total harga: ");

        controller.tambahSewa(
                namaPenyewa,
                namaAlat,
                tanggal,
                lama,
                total
        );

        System.out.println("Data sewa berhasil ditambahkan.");
    }

    private void lihatSewa(
            PenyewaanController controller) {

        if (controller.getDaftarSewa().isEmpty()) {
            System.out.println("Belum ada data sewa.");
            return;
        }

        System.out.println("\n=== DAFTAR SEWA ===");

        for (int i = 0;
             i < controller.getDaftarSewa().size();
             i++) {

            System.out.println("\nData ke-" + (i + 1));

            controller.getDaftarSewa()
                    .get(i)
                    .tampilkanInfo();
        }
    }

    private void ubahSewa(
            PenyewaanController controller) {

        lihatSewa(controller);

        if (controller.getDaftarSewa().isEmpty()) {
            return;
        }

        int nomor =
                inputInt("Nomor data yang diubah: ");

        int index = nomor - 1;

        if (index < 0 ||
            index >= controller.getDaftarSewa().size()) {

            System.out.println("Data tidak ditemukan.");
            return;
        }

        String namaPenyewa =
                inputString("Nama penyewa baru: ");

        String namaAlat =
                inputString("Nama alat baru: ");

        String tanggal =
                inputString("Tanggal sewa baru: ");

        int lama =
                inputIntPositive("Lama sewa baru: ");

        double total =
                inputDouble("Total harga baru: ");

        controller.ubahSewa(
                index,
                namaPenyewa,
                namaAlat,
                tanggal,
                lama,
                total
        );

        System.out.println("Data sewa berhasil diubah.");
    }

    private void hapusSewa(
            PenyewaanController controller) {

        lihatSewa(controller);

        if (controller.getDaftarSewa().isEmpty()) {
            return;
        }

        int nomor =
                inputInt("Nomor data yang dihapus: ");

        int index = nomor - 1;

        if (index >= 0 &&
            index < controller.getDaftarSewa().size()) {

            controller.hapusSewa(index);

            System.out.println("Data sewa berhasil dihapus.");

        } else {

            System.out.println("Data tidak ditemukan.");
        }
    }

    // =========================
    // VALIDASI
    // =========================

    private String inputString(String pesan) {

        while (true) {

            System.out.print(pesan);

            String data =
                    input.nextLine().trim();

            if (!data.isEmpty()) {
                return data;
            }

            System.out.println("Input tidak boleh kosong.");
        }
    }

    private int inputInt(String pesan) {

        while (true) {

            try {

                System.out.print(pesan);

                return Integer.parseInt(
                        input.nextLine()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Input harus berupa angka."
                );
            }
        }
    }

    private int inputIntPositive(String pesan) {

        while (true) {

            int nilai = inputInt(pesan);

            if (nilai > 0) {
                return nilai;
            }

            System.out.println(
                    "Nilai harus lebih dari 0."
            );
        }
    }

    private int inputIntNonNegative(String pesan) {

        while (true) {

            int nilai = inputInt(pesan);

            if (nilai >= 0) {
                return nilai;
            }

            System.out.println(
                    "Nilai tidak boleh negatif."
            );
        }
    }

    private double inputDouble(String pesan) {

        while (true) {

            try {

                System.out.print(pesan);

                double nilai =
                        Double.parseDouble(
                                input.nextLine()
                        );

                if (nilai > 0) {
                    return nilai;
                }

                System.out.println(
                        "Nilai harus lebih dari 0."
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Input harus berupa angka."
                );
            }
        }
    }
}
