package model;

public class Penyewa {

    private String nama;
    private String noTelepon;
    private String alamat;

    public Penyewa(String nama, String noTelepon, String alamat) {
        this.nama = nama;
        this.noTelepon = noTelepon;
        this.alamat = alamat;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void tampilkanInfo() {
        System.out.println("Nama       : " + nama);
        System.out.println("No Telepon : " + noTelepon);
        System.out.println("Alamat     : " + alamat);
    }
}
