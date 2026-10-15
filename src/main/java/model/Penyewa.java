package model;

public class Penyewa {

    private int id;
    private String nama;
    private String noTelepon;
    private String alamat;

    public Penyewa(int id, String nama,
                   String noTelepon, String alamat) {
        this.id = id;
        this.nama = nama;
        this.noTelepon = noTelepon;
        this.alamat = alamat;
    }

    public Penyewa(String nama, String noTelepon, String alamat) {
        this(0, nama, noTelepon, alamat);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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