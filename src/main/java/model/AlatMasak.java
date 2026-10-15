package model;

public class AlatMasak extends AlatKemping {

    private String jenisBahanBakar;

    public AlatMasak(int id, String namaAlat, double hargaSewa,
                     int stok, String jenisBahanBakar) {

        super(id, namaAlat, hargaSewa, stok);
        this.jenisBahanBakar = jenisBahanBakar;
    }

    public AlatMasak(String namaAlat, double hargaSewa,
                     int stok, String jenisBahanBakar) {

        this(0, namaAlat, hargaSewa, stok, jenisBahanBakar);
    }

    public String getJenisBahanBakar() {
        return jenisBahanBakar;
    }

    public void setJenisBahanBakar(String jenisBahanBakar) {
        this.jenisBahanBakar = jenisBahanBakar;
    }

    // Overriding
    @Override
    public void tampilkanInfo() {

        System.out.println("Nama Alat   : " + getNamaAlat());
        System.out.println("Jenis       : Alat Masak");
        System.out.println("Harga Sewa  : " + getHargaSewa());
        System.out.println("Stok        : " + getStok());
        System.out.println("Bahan Bakar : " + jenisBahanBakar);
    }

    // Implementasi interface
    @Override
    public double hitungTotalSewa(int lamaSewa) {

        return getHargaSewa() * lamaSewa;
    }
}