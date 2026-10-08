package model;

public abstract class AlatKemping implements DapatDisewa {

    private String namaAlat;
    private double hargaSewa;
    private int stok;

    public AlatKemping(String namaAlat, double hargaSewa, int stok) {
        this.namaAlat = namaAlat;
        this.hargaSewa = hargaSewa;
        this.stok = stok;
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