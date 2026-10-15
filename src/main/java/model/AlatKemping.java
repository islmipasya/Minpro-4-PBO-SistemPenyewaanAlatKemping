package model;

public abstract class AlatKemping implements DapatDisewa {

    private int id;
    private String namaAlat;
    private double hargaSewa;
    private int stok;

    // Constructor dengan id (dipakai saat data dibaca dari database)
    public AlatKemping(int id, String namaAlat,
                       double hargaSewa, int stok) {
        this.id = id;
        this.namaAlat = namaAlat;
        this.hargaSewa = hargaSewa;
        this.stok = stok;
    }

    // Constructor tanpa id (dipakai saat data baru akan disimpan)
    public AlatKemping(String namaAlat, double hargaSewa, int stok) {
        this(0, namaAlat, hargaSewa, stok);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaAlat() {
        return namaAlat;
    }

    public void setNamaAlat(String namaAlat) {
        this.namaAlat = namaAlat;
    }

    public double getHargaSewa() {
        return hargaSewa;
    }

    public void setHargaSewa(double hargaSewa) {
        this.hargaSewa = hargaSewa;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    // Abstract method
    public abstract void tampilkanInfo();

    // Overloading pertama
    public double hitungHargaSewa(int lamaSewa) {
        return hargaSewa * lamaSewa;
    }

    // Overloading kedua
    public double hitungHargaSewa(int lamaSewa, int jumlah) {
        return hargaSewa * lamaSewa * jumlah;
    }
}