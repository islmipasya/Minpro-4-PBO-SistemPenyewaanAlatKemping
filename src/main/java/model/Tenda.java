package model;

public class Tenda extends AlatKemping {

    private int kapasitas;

    public Tenda(String namaAlat, double hargaSewa, int stok, int kapasitas) {
        super(namaAlat, hargaSewa, stok);
        this.kapasitas = kapasitas;
    }

    public int getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(int kapasitas) {
        this.kapasitas = kapasitas;
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("Nama Alat  : " + getNamaAlat());
        System.out.println("Jenis      : Tenda");
        System.out.println("Harga Sewa : " + getHargaSewa());
        System.out.println("Stok       : " + getStok());
        System.out.println("Kapasitas  : " + kapasitas + " orang");
    }
}
