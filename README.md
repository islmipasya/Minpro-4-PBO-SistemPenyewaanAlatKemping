# Minpro-4-PBO-PenyewaanAlatCamping

**Nama:** Muhammad Islami Pasya
**NIM:** 2509116108
**Program Studi:** Sistem Informasi, Fakultas Teknik, Universitas Mulawarman

---

## 1. Deskripsi Singkat Program

Sistem Penyewaan Alat Camping adalah aplikasi berbasis konsol (Command Line Interface) berbahasa Java untuk mengelola data penyewaan alat camping. Program mengelola tiga kelompok data:

- **Alat Camping**: nama alat, harga sewa, stok, serta data khusus tiap jenis (kapasitas untuk Tenda, jenis bahan bakar untuk Alat Masak).
- **Penyewa**: nama, nomor telepon, alamat.
- **Sewa**: nama penyewa, nama alat, tanggal sewa, lama sewa, total harga.

Untuk setiap data tersedia fitur **CRUD** (tambah, lihat, ubah, hapus) dengan validasi input (tidak boleh kosong, harus angka, tidak boleh negatif).

Program ini merupakan lanjutan dari Mini Project 3. Pada Mini Project 4, penyimpanan data dipindah dari `ArrayList` ke **database MySQL** menggunakan **JDBC (PreparedStatement)** dan pola **DAO**, sehingga data tidak hilang saat program ditutup. Data awal (dummy) diisi lewat file `database/db_penyewaan_camping.sql`, bukan lewat kode Java.

**Teknologi:** Java, NetBeans + Maven, MySQL, JDBC (`mysql-connector-j`).

---

## 2. Penjelasan Struktur Package

Program menggunakan struktur **MVC** yang ditambah lapisan **DAO** dan **config**.

```
SistemPenyewaanAlatKempingg/
├── pom.xml
├── README.md
├── database/
│   └── db_penyewaan_camping.sql
└── src/main/java/
    ├── config/
    │   ├── Koneksi.java
    │   └── TesKoneksi.java
    ├── controller/
    │   └── PenyewaanController.java
    ├── dao/
    │   ├── AlatDAO.java
    │   ├── AlatDAOImpl.java
    │   ├── PenyewaDAO.java
    │   ├── PenyewaDAOImpl.java
    │   ├── SewaDAO.java
    │   └── SewaDAOImpl.java
    ├── main/
    │   └── Main.java
    ├── model/
    │   ├── AlatKemping.java
    │   ├── AlatMasak.java
    │   ├── DapatDisewa.java
    │   ├── Penyewa.java
    │   ├── Sewa.java
    │   └── Tenda.java
    └── view/
        └── MenuView.java
```

**Struktur Package di NetBeans**

<img width="490" height="684" alt="WhatsApp Image 2026-10-15 at 22 07 57" src="https://github.com/user-attachments/assets/e8005003-fb4c-4615-be4c-4ac6117a3b01" />

| Package | Isi | Tugas |
|---|---|---|
| `config` | `Koneksi`, `TesKoneksi` | `Koneksi` membuat dan menyediakan koneksi JDBC ke MySQL. `TesKoneksi` menguji koneksi database. |
| `controller` | `PenyewaanController` | Penghubung antara View dan DAO. |
| `dao` | `AlatDAO`, `PenyewaDAO`, `SewaDAO` (interface), `AlatDAOImpl`, `PenyewaDAOImpl`, `SewaDAOImpl` (implementasi) | Menangani seluruh operasi database. Semua query SQL ada di sini. |
| `main` | `Main` | Titik awal program. |
| `model` | `DapatDisewa`, `AlatKemping`, `Tenda`, `AlatMasak`, `Penyewa`, `Sewa` | Menyimpan data dan menerapkan konsep OOP. |
| `view` | `MenuView` | Menampilkan menu, menerima input, dan memvalidasi input. |

**Struktur database** (`db_penyewaan_camping`):

| Tabel | Kolom |
|---|---|
| `alat_kemping` | `id`, `nama_alat`, `harga_sewa`, `stok`, `jenis` (TENDA / ALAT_MASAK), `kapasitas`, `jenis_bahan_bakar` |
| `penyewa` | `id`, `nama`, `no_telepon`, `alamat` |
| `sewa` | `id`, `nama_penyewa`, `nama_alat`, `tanggal_sewa`, `lama_sewa`, `total_harga` |

`Tenda` dan `AlatMasak` disimpan dalam satu tabel `alat_kemping` dan dibedakan oleh kolom `jenis`. Kolom `kapasitas` hanya terisi untuk Tenda, sedangkan `jenis_bahan_bakar` hanya terisi untuk Alat Masak.

---

## 3. Penjelasan Alur Program

### 3.1 Alur Utama

Program dimulai dari `main()` di class `Main`. Dibuat objek `PenyewaanController` dan `MenuView`, lalu `view.jalankan(controller)` dipanggil. Berbeda dari Mini Project 3, `PenyewaanController` **tidak lagi membuat `ArrayList` dan method `dataAwal()`**. Constructor-nya hanya membuat tiga objek DAO:

```java
public PenyewaanController() {

    alatDAO = new AlatDAOImpl();
    penyewaDAO = new PenyewaDAOImpl();
    sewaDAO = new SewaDAOImpl();
}
```

`MenuView.jalankan()` menampilkan menu utama berulang dengan `do-while` sampai pengguna memilih **0 (Keluar)**.

**Tampilan Menu Utama**

<img width="487" height="238" alt="WhatsApp Image 2026-10-15 at 22 12 09" src="https://github.com/user-attachments/assets/7ab44e03-5eb0-4f10-9f1c-6932cb4539b3" />


Alur setiap aksi:

```
View (MenuView)  →  Controller (PenyewaanController)  →  DAO (…DAOImpl)  →  Database MySQL
```

1. User memilih menu dan mengisi data. `MenuView` memvalidasi input.
2. `MenuView` meneruskan data ke `PenyewaanController`.
3. `PenyewaanController` membuat objek model (`Tenda`, `AlatMasak`, `Penyewa`, `Sewa`) lalu memanggil method DAO (`insert`, `getAll`, `update`, `delete`).
4. DAO mengambil koneksi dari `Koneksi.getConnection()` dan menjalankan query dengan `PreparedStatement`.
5. Hasil kembali lewat jalur sebaliknya. Untuk **Read**, `ResultSet` diubah menjadi objek model lalu ditampilkan. Untuk **Create/Update/Delete**, controller mengembalikan status berhasil/gagal dan View menampilkan pesannya.
6. Jika terjadi `SQLException`, controller menangkapnya dan menampilkan pesan gagal tanpa menghentikan program.

### 3.2 Data Awal (Dummy) dari File .sql

Data awal tidak dibuat lewat kode Java. Pada Mini Project 3, data awal ditulis di method `dataAwal()`. Pada Mini Project 4 method itu dihapus:

```java
// Mini Project 3 (lama, sudah tidak dipakai)
public PenyewaanController() {
    daftarAlat = new ArrayList<>();
    daftarPenyewa = new ArrayList<>();
    daftarSewa = new ArrayList<>();
    dataAwal();
}

// Mini Project 4 (baru, data dari database)
public PenyewaanController() {
    alatDAO = new AlatDAOImpl();
    penyewaDAO = new PenyewaDAOImpl();
    sewaDAO = new SewaDAOImpl();
}
```

Dummy data berasal dari file `database/db_penyewaan_camping.sql`, yang di-import satu kali lewat phpMyAdmin sebelum program dijalankan:

```sql
INSERT INTO alat_kemping
    (nama_alat, harga_sewa, stok, jenis, kapasitas, jenis_bahan_bakar)
VALUES
    ('Tenda Dome', 50000, 5, 'TENDA', 4, NULL),
    ('Tenda Family', 80000, 2, 'TENDA', 8, NULL),
    ('Kompor Portable', 30000, 3, 'ALAT_MASAK', NULL, 'Gas');

INSERT INTO penyewa (nama, no_telepon, alamat) VALUES
    ('Pasya', '081234567890', 'Samarinda'),
    ('Yuda', '082345678901', 'Balikpapan');

INSERT INTO sewa
    (nama_penyewa, nama_alat, tanggal_sewa, lama_sewa, total_harga)
VALUES
    ('Yuda', 'Tenda Dome', '22-09-2026', 2, 100000);
```

**Tabel di phpMyAdmin setelah import `.sql`, sebelum program dijalankan**

<img width="1506" height="676" alt="WhatsApp Image 2026-10-15 at 22 21 15" src="https://github.com/user-attachments/assets/dfeb42b0-1500-436e-929d-10d6f8fbe474" />


**Menu Lihat Alat langsung menampilkan data awal**

<img width="1540" height="724" alt="WhatsApp Image 2026-10-15 at 22 25 41" src="https://github.com/user-attachments/assets/f5629d89-5f17-49ba-9ae8-f41fda44b9b8" />


Saat program pertama kali dijalankan dan pengguna langsung memilih Kelola Alat → Lihat Alat, data langsung muncul tanpa input apa pun. Ini bukti data diambil dari database, bukan dari kode.

### 3.3 Alur CRUD

Contoh di bawah memakai data **Alat Camping**. Data Penyewa dan Sewa memakai pola yang sama.

**Create** (`MenuView.tambahAlat()` → `controller.tambahTenda()` → `AlatDAO.insert()`)

<img width="856" height="574" alt="WhatsApp Image 2026-10-15 at 23 16 50" src="https://github.com/user-attachments/assets/6c9a3628-ad54-432b-9c19-45fe5aebb8bb" />

```
```
<img width="768" height="469" alt="WhatsApp Image 2026-10-15 at 23 13 40" src="https://github.com/user-attachments/assets/7096685d-541d-4014-a6a8-1d3a3aac1a76" />

```
```
**Proses Tambah Data di Program**

<img width="607" height="262" alt="WhatsApp Image 2026-10-15 at 22 29 42" src="https://github.com/user-attachments/assets/a5719b01-2d54-40d5-bf5e-30be5aca7eb6" />


**BUKTI: phpMyAdmin setelah tambah data, muncul baris baru**

<img width="1552" height="720" alt="WhatsApp Image 2026-10-15 at 22 30 46" src="https://github.com/user-attachments/assets/753b7e17-8c5d-47a4-b7ec-40eefc0cbce9" />

**Hasil Lihat Alat di Program**

<img width="415" height="717" alt="WhatsApp Image 2026-10-15 at 22 31 36" src="https://github.com/user-attachments/assets/f4374249-133b-49aa-a52d-8cc0ab711ea4" />


**Update** (`MenuView.ubahAlat()` → `controller.ubahTenda()` / `ubahAlatMasak()` → `AlatDAO.update()`)

User memilih nomor data di layar. Nomor itu diubah menjadi `id` database lewat `daftar.get(index).getId()`, lalu dipakai pada `UPDATE ... WHERE id = ?`.

```java
// view/MenuView.java
AlatKemping alat = daftar.get(index);

if (alat instanceof Tenda) {
    int kapasitas = inputIntPositive("Kapasitas tenda baru: ");
    berhasil = controller.ubahTenda(alat.getId(), nama, harga, stok, kapasitas);
}
```

**Proses Ubah Data di Program**

<img width="520" height="184" alt="WhatsApp Image 2026-10-15 at 22 35 38" src="https://github.com/user-attachments/assets/012624de-d06b-4f11-90c0-40d0ef6a03ba" />

**BUKTI: phpMyAdmin setelah update, data berubah**

<img width="1546" height="708" alt="WhatsApp Image 2026-10-15 at 22 36 30" src="https://github.com/user-attachments/assets/79a4db99-c82b-4319-89fa-beba7886f730" />


**Delete** (`MenuView.hapusAlat()` → `controller.hapusAlat()` → `AlatDAO.delete()`)

```java
// view/MenuView.java
if (controller.hapusAlat(daftar.get(index).getId())) {
    System.out.println("Data alat berhasil dihapus.");
}
```

**Proses Hapus Data di Program**

<img width="541" height="84" alt="WhatsApp Image 2026-10-15 at 22 37 53" src="https://github.com/user-attachments/assets/5270a72b-6205-4615-bb52-c1fae9249cc6" />


**BUKTI: phpMyAdmin setelah hapus, baris hilang**

<img width="775" height="329" alt="image" src="https://github.com/user-attachments/assets/8f23cc0b-440c-40a8-84d2-2ff3ad716bb6" />


### 3.4 Validasi Input

Validasi ditangani method `input...()` di `MenuView`, agar program tidak berhenti tiba-tiba saat input salah.

```java
// Harus angka
private int inputInt(String pesan) {

    while (true) {

        System.out.print(pesan);

        try {
            return Integer.parseInt(input.nextLine());

        } catch (NumberFormatException e) {
            System.out.println("Input harus berupa angka.");
        }
    }
}

// Tidak boleh kosong
private String inputString(String pesan) {

    while (true) {

        System.out.print(pesan);

        String nilai = input.nextLine();

        if (!nilai.trim().isEmpty()) {
            return nilai;
        }

        System.out.println("Input tidak boleh kosong.");
    }
}
```

| Method | Aturan |
|---|---|
| `inputInt()` | Harus berupa angka bulat |
| `inputIntPositive()` | Harus lebih dari 0 (kapasitas, lama sewa, nomor data) |
| `inputIntNonNegative()` | Tidak boleh negatif (stok) |
| `inputDouble()` | Harus angka dan tidak boleh negatif (harga) |
| `inputString()` | Tidak boleh kosong |

**Invalid Input: Pilihan Menu Bukan Angka**

<img width="543" height="103" alt="WhatsApp Image 2026-10-15 at 22 58 26" src="https://github.com/user-attachments/assets/669e4980-9c34-4d77-8d34-78b187ef274d" />

**Invalid Input: Stok Negatif**

<img width="543" height="58" alt="WhatsApp Image 2026-10-15 at 22 56 19" src="https://github.com/user-attachments/assets/0413c886-5724-4b9c-9b9b-20d088ed30ee" />

**Invalid Input: Nama Dikosongkan**

<img width="514" height="57" alt="WhatsApp Image 2026-10-15 at 22 56 53" src="https://github.com/user-attachments/assets/ca87d09e-0853-44fd-a17a-3a7deb925c3f" />

**Invalid Input: Nomor Data Tidak Ditemukan**

<img width="508" height="64" alt="WhatsApp Image 2026-10-15 at 22 57 52" src="https://github.com/user-attachments/assets/241604fc-8463-4c36-ac9b-69090c614d1f" />

### 3.5 Cara Menjalankan

1. Nyalakan MySQL (XAMPP/Laragon).
2. Import `database/db_penyewaan_camping.sql` lewat phpMyAdmin.
3. Buka project di NetBeans, lalu **Clean and Build** agar dependency `mysql-connector-j` terunduh otomatis lewat Maven.
4. (Opsional) Jalankan `config/TesKoneksi.java` untuk memastikan koneksi berhasil.
5. Jalankan `main/Main.java`.

Jika MySQL memakai password, ubah nilai `PASS` di `config/Koneksi.java`.

---

## 4. Penjelasan Penerapan Encapsulation dan Inheritance

### Encapsulation

Seluruh atribut pada class model bersifat `private` dan hanya bisa diakses lewat getter dan setter.

```java
public abstract class AlatKemping implements DapatDisewa {

    private int id;
    private String namaAlat;
    private double hargaSewa;
    private int stok;

    public String getNamaAlat() { return namaAlat; }
    public void setNamaAlat(String namaAlat) { this.namaAlat = namaAlat; }
}
```

| Class | Atribut `private` |
|---|---|
| `AlatKemping` | `id`, `namaAlat`, `hargaSewa`, `stok` |
| `Tenda` | `kapasitas` |
| `AlatMasak` | `jenisBahanBakar` |
| `Penyewa` | `id`, `nama`, `noTelepon`, `alamat` |
| `Sewa` | `id`, `namaPenyewa`, `namaAlat`, `tanggalSewa`, `lamaSewa`, `totalHarga` |

Dengan begitu data tidak diubah langsung dari luar class. DAO membaca nilai lewat getter sebelum menyimpannya ke database, contohnya `alat.getNamaAlat()`, `tenda.getKapasitas()`, dan `penyewa.getNoTelepon()`.

Pada Mini Project 4, setiap model ditambah atribut `id` (primary key dari database) beserta getter dan setter-nya, agar data bisa diubah dan dihapus berdasarkan `id`.

### Inheritance

```
AlatKemping (abstract)
├── Tenda
└── AlatMasak
```

`AlatKemping` adalah **superclass** yang menyimpan atribut dan perilaku umum. `Tenda` dan `AlatMasak` adalah **subclass** (`extends AlatKemping`) yang memanggil constructor superclass dengan `super(...)` lalu menambahkan atribut sendiri:

```java
public class Tenda extends AlatKemping {

    private int kapasitas;

    public Tenda(int id, String namaAlat, double hargaSewa,
                 int stok, int kapasitas) {

        super(id, namaAlat, hargaSewa, stok);
        this.kapasitas = kapasitas;
    }
}
```

```java
public class AlatMasak extends AlatKemping {

    private String jenisBahanBakar;

    public AlatMasak(int id, String namaAlat, double hargaSewa,
                     int stok, String jenisBahanBakar) {

        super(id, namaAlat, hargaSewa, stok);
        this.jenisBahanBakar = jenisBahanBakar;
    }
}
```

Atribut dan method umum (`id`, `namaAlat`, `hargaSewa`, `stok`, `hitungHargaSewa`) cukup ditulis satu kali di `AlatKemping` dan diwarisi kedua subclass.

**Struktur Package Model**

<img width="444" height="184" alt="WhatsApp Image 2026-10-15 at 23 03 10" src="https://github.com/user-attachments/assets/09b1151d-a938-4820-b7fc-aa6ac35f8eb2" />

---

## 5. Penjelasan Penerapan Polymorphism dan Abstraction

### Abstraction

**Abstract class dan abstract method.** `AlatKemping` adalah `abstract class` dengan abstract method `tampilkanInfo()` yang wajib diimplementasikan subclass:

```java
public abstract class AlatKemping implements DapatDisewa {

    // Abstract method
    public abstract void tampilkanInfo();
}
```

`AlatKemping` tidak bisa dibuat objeknya langsung, hanya `Tenda` dan `AlatMasak`. `AlatDAOImpl.getAll()` memanfaatkan hal ini: objek dari database selalu dibuat sebagai `Tenda` atau `AlatMasak` sesuai kolom `jenis`.

**Interface pada model.** `DapatDisewa` memiliki method `hitungTotalSewa()` yang diimplementasikan di `Tenda` dan `AlatMasak`:

```java
public interface DapatDisewa {

    double hitungTotalSewa(int lamaSewa);
}
```

**Interface pada DAO.** `AlatDAO`, `PenyewaDAO`, dan `SewaDAO` mendefinisikan operasi database tanpa menampilkan detail query. Controller hanya bergantung pada interface, sedangkan detail JDBC disembunyikan di class `...DAOImpl`:

```java
public interface AlatDAO {

    List<AlatKemping> getAll() throws SQLException;
    void insert(AlatKemping alat) throws SQLException;
    void update(AlatKemping alat) throws SQLException;
    void delete(int id) throws SQLException;
}
```

**Isi `AlatKemping.java` yang menunjukkan abstract class dan abstract method**

<img width="859" height="186" alt="WhatsApp Image 2026-10-15 at 23 05 46" src="https://github.com/user-attachments/assets/c3818db2-e0c3-44f9-8bb2-96fd2a74f8ca" />

### Polymorphism

**Overriding.** Method `tampilkanInfo()` ditulis ulang (`@Override`) di `Tenda` dan `AlatMasak` dengan isi berbeda:

```java
// Tenda.java
@Override
public void tampilkanInfo() {

    System.out.println("Nama Alat  : " + getNamaAlat());
    System.out.println("Jenis      : Tenda");
    System.out.println("Harga Sewa : " + getHargaSewa());
    System.out.println("Stok       : " + getStok());
    System.out.println("Kapasitas  : " + kapasitas + " orang");
}
```

```java
// AlatMasak.java
@Override
public void tampilkanInfo() {

    System.out.println("Nama Alat   : " + getNamaAlat());
    System.out.println("Jenis       : Alat Masak");
    System.out.println("Harga Sewa  : " + getHargaSewa());
    System.out.println("Stok        : " + getStok());
    System.out.println("Bahan Bakar : " + jenisBahanBakar);
}
```

Dipanggil secara polymorphic di `MenuView`. Variabel bertipe `AlatKemping`, tetapi yang dijalankan adalah versi `Tenda` atau `AlatMasak` sesuai objek aslinya:

```java
for (AlatKemping alat : daftar) {
    alat.tampilkanInfo();
}
```

`hitungTotalSewa()` dari interface `DapatDisewa` juga di-override di `Tenda` dan `AlatMasak`.

**Hasil Overriding `tampilkanInfo()` pada Tenda dan Alat Masak**

<img width="531" height="580" alt="WhatsApp Image 2026-10-15 at 23 08 19" src="https://github.com/user-attachments/assets/e668c77f-af53-41f8-b135-fd90af1d66a6" />


**Overloading.** Method `hitungHargaSewa()` di `AlatKemping` punya dua versi dengan nama sama dan parameter berbeda:

```java
// Overloading pertama
public double hitungHargaSewa(int lamaSewa) {
    return hargaSewa * lamaSewa;
}

// Overloading kedua
public double hitungHargaSewa(int lamaSewa, int jumlah) {
    return hargaSewa * lamaSewa * jumlah;
}
```

Constructor model juga di-overload: satu versi dengan `id` (untuk data dari database) dan satu versi tanpa `id` (untuk data baru):

```java
public Tenda(int id, String namaAlat, double hargaSewa, int stok, int kapasitas) {
    super(id, namaAlat, hargaSewa, stok);
    this.kapasitas = kapasitas;
}

public Tenda(String namaAlat, double hargaSewa, int stok, int kapasitas) {
    this(0, namaAlat, hargaSewa, stok, kapasitas);
}
```

---

## 6. Penjelasan Penerapan JDBC

Koneksi database diatur di `config/Koneksi.java` memakai `DriverManager`:


<img width="580" height="514" alt="WhatsApp Image 2026-10-15 at 22 02 31" src="https://github.com/user-attachments/assets/f1c075d0-a64f-4615-8b85-ea1c8ab24d20" />

Koneksi disimpan di satu variabel `static` dan dipakai bersama oleh semua DAO. Jika koneksi masih `null` atau sudah tertutup, koneksi dibuat ulang. Driver MySQL (`mysql-connector-j`) ditambahkan lewat dependency Maven di `pom.xml`.

Koneksi ini diuji lewat class terpisah `config/TesKoneksi.java`:

<img width="351" height="250" alt="image" src="https://github.com/user-attachments/assets/8d3bea2c-14bd-4ad3-8b9b-6411618e13b5" />

**GAMBAR Hasil Run TesKoneksi.java, Koneksi Berhasil**

<img width="1396" height="643" alt="WhatsApp Image 2026-10-15 at 21 51 35" src="https://github.com/user-attachments/assets/b4733ce3-432b-431d-acdd-c2dfe61f2579" />


Semua query memakai `PreparedStatement`, sehingga nilai dari input pengguna **tidak pernah digabung langsung ke teks SQL** (mencegah SQL Injection). Tanda `?` adalah parameter yang diisi lewat `ps.setString()`, `ps.setInt()`, `ps.setDouble()`, dan seterusnya, bukan dengan menyambung String secara manual. Contohnya pada `insert()` di `AlatDAOImpl`:

```java
String sql = "INSERT INTO alat_kemping "
        + "(nama_alat, harga_sewa, stok, jenis, "
        + "kapasitas, jenis_bahan_bakar) "
        + "VALUES (?, ?, ?, ?, ?, ?)";

try (PreparedStatement ps =
             Koneksi.getConnection().prepareStatement(sql)) {

    ps.setString(1, alat.getNamaAlat());
    ps.setDouble(2, alat.getHargaSewa());
    ps.setInt(3, alat.getStok());
    // ...
    ps.executeUpdate();
}
```

`try-with-resources` dipakai pada `PreparedStatement` dan `ResultSet`, sehingga keduanya otomatis tertutup setelah selesai dipakai tanpa perlu `finally` manual. `Connection` sengaja tidak ditutup di setiap method karena dipakai bersama lewat `Koneksi`. Setiap operasi dibungkus `try-catch` untuk `SQLException` di `PenyewaanController`, yang menampilkan pesan gagal tanpa menghentikan program secara tiba-tiba.

**Pola DAO.** 

Setiap tabel punya satu interface dan satu implementasi (`AlatDAO` dan `AlatDAOImpl`, `PenyewaDAO` dan `PenyewaDAOImpl`, `SewaDAO` dan `SewaDAOImpl`). `PenyewaanController` **tidak pernah menulis SQL sendiri**, ia hanya memanggil method seperti `alatDAO.insert()` atau `alatDAO.delete()`. Semua detail `PreparedStatement`, `ResultSet`, dan query tersembunyi di class `...DAOImpl`. Jika database diganti, cukup ubah class `...DAOImpl`, tanpa menyentuh controller, view, atau model.
