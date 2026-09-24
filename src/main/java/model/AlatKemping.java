package model;

public class AlatKemping {

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

    public void tampilkanInfo() {
        System.out.println("Nama Alat  : " + namaAlat);
        System.out.println("Harga Sewa : " + hargaSewa);
        System.out.println("Stok       : " + stok);
    }
}
