package model;

public class Sewa {

    private int id;
    private String namaPenyewa;
    private String namaAlat;
    private String tanggalSewa;
    private int lamaSewa;
    private double totalHarga;

    public Sewa(int id,
                String namaPenyewa,
                String namaAlat,
                String tanggalSewa,
                int lamaSewa,
                double totalHarga) {

        this.id = id;
        this.namaPenyewa = namaPenyewa;
        this.namaAlat = namaAlat;
        this.tanggalSewa = tanggalSewa;
        this.lamaSewa = lamaSewa;
        this.totalHarga = totalHarga;
    }

    public Sewa(String namaPenyewa,
                String namaAlat,
                String tanggalSewa,
                int lamaSewa,
                double totalHarga) {

        this(0, namaPenyewa, namaAlat,
             tanggalSewa, lamaSewa, totalHarga);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaPenyewa() {
        return namaPenyewa;
    }

    public void setNamaPenyewa(String namaPenyewa) {
        this.namaPenyewa = namaPenyewa;
    }

    public String getNamaAlat() {
        return namaAlat;
    }

    public void setNamaAlat(String namaAlat) {
        this.namaAlat = namaAlat;
    }

    public String getTanggalSewa() {
        return tanggalSewa;
    }

    public void setTanggalSewa(String tanggalSewa) {
        this.tanggalSewa = tanggalSewa;
    }

    public int getLamaSewa() {
        return lamaSewa;
    }

    public void setLamaSewa(int lamaSewa) {
        this.lamaSewa = lamaSewa;
    }

    public double getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(double totalHarga) {
        this.totalHarga = totalHarga;
    }

    public void tampilkanInfo() {

        System.out.println("Nama Penyewa : " + namaPenyewa);
        System.out.println("Nama Alat    : " + namaAlat);
        System.out.println("Tanggal Sewa : " + tanggalSewa);
        System.out.println("Lama Sewa    : " + lamaSewa + " hari");
        System.out.println("Total Harga  : " + totalHarga);
    }
}