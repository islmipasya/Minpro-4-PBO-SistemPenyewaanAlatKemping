package model;

public class AlatMasak extends AlatKemping {

    private String jenisBahanBakar;

    public AlatMasak(String namaAlat, double hargaSewa, int stok,
                     String jenisBahanBakar) {
        super(namaAlat, hargaSewa, stok);
        this.jenisBahanBakar = jenisBahanBakar;
    }

    public String getJenisBahanBakar() {
        return jenisBahanBakar;
    }

    public void setJenisBahanBakar(String jenisBahanBakar) {
        this.jenisBahanBakar = jenisBahanBakar;
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("Nama Alat   : " + getNamaAlat());
        System.out.println("Jenis       : Alat Masak");
        System.out.println("Harga Sewa  : " + getHargaSewa());
        System.out.println("Stok        : " + getStok());
        System.out.println("Bahan Bakar : " + jenisBahanBakar);
    }
}
