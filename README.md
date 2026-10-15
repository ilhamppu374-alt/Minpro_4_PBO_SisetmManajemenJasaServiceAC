*Nama*: (Muhammad Ilham Zaini)

*NIM*: (2509116091)

---

## 1. Deskripsi Singkat Program
Program *Sistem Manajemen Jasa Service AC* adalah program yang digunakan untuk mengelola data pelanggan, teknisi, dan layanan service AC secara permanen menggunakan basis data MySQL. Program menerapkan arsitektur *MVC*, pola *DAO (Data Access Object)*, serta koneksi *JDBC* ke database db_service_ac. Data pelanggan dan teknisi yang masih dipakai pada data service dilindungi oleh *Foreign Key*, sehingga tidak dapat dihapus sembarangan.

---

## 2. Penjelasan Struktur Package
Proyek ini disusun secara modular berbasis pola arsitektur MVC dan JDBC Data Layer:

1. config:
   - DatabaseConnection.java: Mengelola pembuatan koneksi (Connection) ke database MySQL db_service_ac.
2. model:
   - DataOrang.java: Abstract class dasar untuk entitas orang (id, nama, noTelepon).
   - Pelanggan.java & Teknisi.java: Subclass dari DataOrang dengan atribut khusus (alamat dan spesialisasi).
   - ServiceAC.java: Abstract class dasar untuk jenis layanan service.
   - ServiceCuciAC.java & ServicePerbaikanAC.java: Subclass spesifik jenis layanan.
   - DetailService.java: Entitas data service (pelanggan, teknisi, jenis AC, keluhan, tanggal, status, biaya).
3. dao:
   - PelangganDAO.java, TeknisiDAO.java, ServiceDAO.java: Interface yang menjadi kontrak operasi CRUD.
   - PelangganDAOImpl.java, TeknisiDAOImpl.java, ServiceDAOImpl.java: Kelas implementasi yang mengeksekusi perintah SQL ke database.
4. controller:
   - ServiceController.java: Menghubungkan interaksi layer View dengan DAO, sekaligus memvalidasi logika data (cek ID duplikat, cek pelanggan dan teknisi tersedia).
5. view:
   - ServiceView.java: Menangani antarmuka CLI, pilihan menu, serta validasi input pengguna.
6. util:
   - TanggalUtil.java: Validasi dan konversi tanggal antara format tampilan (DD-MM-YYYY) dan tipe DATE di database.
7. main:
   - Main.java: Class entry point utama penggerak aplikasi.

---

## 3. Penjelasan Alur Program
1. *Inisialisasi Koneksi*: Program dijalankan melalui Main.java yang memanggil DatabaseConnection.testKoneksi(). Jika koneksi gagal, program berhenti dengan pesan peringatan. Jika berhasil, Main membuat ServiceController dan ServiceView, lalu memanggil jalankanProgram().
2. *Read (Lihat Data)*: Saat menu Lihat Pelanggan, Teknisi, atau Service dipilih, DAO mengeksekusi query SELECT ke database dan mengembalikan data awal dari file service_ac.sql beserta data baru yang ditambahkan.
3. *Tambah Data (Create)*: Input dari pengguna divalidasi di View, dicek duplikasinya oleh Controller, lalu dikirim ke DAO yang menjalankan INSERT INTO. Untuk service, status awal otomatis "Menunggu".
4. *Ubah Data (Update)*: Mengubah record secara spesifik di database melalui UPDATE ... WHERE id = ? setelah Controller memastikan data yang dituju ada.
5. *Ubah Status Service*: Menu khusus untuk mengubah status service (Menunggu, Diproses, Selesai) tanpa mengubah data lainnya.
6. *Hapus Data (Delete)*: Menghapus record terpilih dengan konfirmasi (Y/T). Jika pelanggan atau teknisi masih dipakai pada data service, penghapusan ditolak oleh Foreign Key.
7. *Pencarian Data*: Controller mencari data berdasarkan ID (cariById) untuk validasi sebelum tambah, ubah, dan hapus.
8. *Dashboard*: Menampilkan total pelanggan, teknisi, dan service menggunakan query hitung.

---

## 4. Penjelasan Penerapan Encapsulation dan Inheritance
* *Encapsulation*:
  * Seluruh atribut entitas bertipe private (id, nama, noTelepon, alamat, spesialisasi, idService, idPelanggan, idTeknisi, jenisAc, keluhan, tanggal, status, biaya).
  * Akses baca dan ubah dialirkan melalui getter dan setter.
* *Inheritance*:
  * Class Pelanggan dan Teknisi mewarisi (extends) seluruh karakteristik dari class induk DataOrang.
  * Class ServiceCuciAC dan ServicePerbaikanAC mewarisi (extends) class induk ServiceAC.

---

## 5. Penjelasan Penerapan Polymorphism dan Abstraction
* *Abstraction*:
  * Class DataOrang berstatus abstract class, memiliki abstract method tampilkanInfo() yang diimplementasikan oleh Pelanggan dan Teknisi.
  * Class ServiceAC berstatus abstract class, memiliki abstract method tampilkanService() yang diimplementasikan oleh ServiceCuciAC dan ServicePerbaikanAC.
  * PelangganDAO, TeknisiDAO, dan ServiceDAO berupa interface yang mendefinisikan kontrak tanpa implementasi.
* *Polymorphism*:
  * *Overriding*: Implementasi method tampilkanInfo() pada Pelanggan dan Teknisi, serta tampilkanService() pada ServiceCuciAC dan ServicePerbaikanAC.
  * *Overloading*: Constructor ganda pada DetailService (dengan dan tanpa parameter status), serta method inputInt() di ServiceView (satu versi tanpa batas, satu versi dengan batas minimum dan maksimum).
  * *Runtime Polymorphism*: Pada menu Informasi Jenis Service, objek dibuat dengan tipe induk (ServiceAC cuci = new ServiceCuciAC(1);), lalu pemanggilan cuci.tampilkanService() menjalankan versi milik class anak.

---

## 6. Penjelasan Penerapan JDBC
JDBC (Java Database Connectivity) adalah API standar Java untuk menghubungkan program ke database. Pada program ini, JDBC dipakai agar data pelanggan, teknisi, dan service tersimpan permanen di MySQL dan tidak hilang saat program ditutup.

### 6.1 JDBC Driver
* Program menggunakan driver *mysql-connector-j* yang ditambahkan ke project melalui *Libraries* di NetBeans.
* Driver ini menjadi penghubung antara Java dan MySQL.

### 6.2 URL Koneksi (DatabaseConnection.java)
```java
private static final String URL
        = "jdbc:mysql://localhost:3306/db_service_ac"
        + "?useSSL=false&allowPublicKeyRetrieval=true";

private static final String USER = "root";
private static final String PASSWORD = "";
```

| Bagian URL | Keterangan |
|---|---|
| jdbc:mysql:// | Protokol JDBC untuk MySQL |
| localhost:3306 | Alamat server dan port MySQL (Laragon) |
| db_service_ac | Nama database yang digunakan |
| useSSL=false | Menonaktifkan SSL (cukup untuk koneksi lokal) |
| allowPublicKeyRetrieval=true | Mencegah error autentikasi pada MySQL versi baru |

### 6.3 Pembuatan Koneksi
```java
public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(URL, USER, PASSWORD);
}
```
Method DriverManager.getConnection() membuka koneksi ke database menggunakan URL, user, dan password, lalu mengembalikan objek Connection.

### 6.4 Tes Koneksi Saat Program Dimulai
```java
public static boolean testKoneksi() {
    try (Connection conn = getConnection()) {
        return conn.isValid(3);
    } catch (SQLException e) {
        System.err.println("Koneksi database gagal: " + e.getMessage());
        return false;
    }
}
```
Method testKoneksi() dipanggil di Main.java sebelum menu tampil. Connection.isValid(3) memeriksa apakah koneksi hidup dengan batas waktu 3 detik. Jika gagal, program berhenti dengan pesan agar pengguna memastikan MySQL Laragon sudah Start dan database sudah diimport.

### 6.5 Try-with-Resources
Setiap pemanggil membuka koneksi baru dan menutupnya sendiri lewat try-with-resources:
```java
try (Connection conn = DatabaseConnection.getConnection()) {
    // jalankan query
}
```
Koneksi otomatis ditutup saat blok selesai, bahkan ketika terjadi error. Hal ini mencegah connection leak (koneksi yang tertinggal terbuka).

### 6.6 Pola DAO (Data Access Object)
Seluruh perintah SQL dikumpulkan di lapisan DAO sehingga View dan Controller tidak berhubungan langsung dengan database.

Alur: *View → Controller → DAO (interface) → DAOImpl → JDBC → MySQL*

| Interface | Method |
|---|---|
| PelangganDAO | tambah, cariById, ambilSemua, ubah, hapus, hitung |
| TeknisiDAO | tambah, cariById, ambilSemua, ubah, hapus, hitung |
| ServiceDAO | tambah, cariById, ambilSemua, ubah, ubahStatus, hapus, hitung |

Controller hanya bergantung pada interface:
```java
pelangganDAO = new PelangganDAOImpl();
teknisiDAO = new TeknisiDAOImpl();
serviceDAO = new ServiceDAOImpl();
```
Jika database diganti, cukup mengganti kelas DAOImpl tanpa mengubah Controller dan View.

### 6.7 Pemetaan Operasi CRUD ke JDBC

| Operasi | Method DAO | Perintah SQL | Eksekusi JDBC |
|---|---|---|---|
| Create | tambah() | INSERT INTO | executeUpdate() |
| Read (satu data) | cariById() | SELECT ... WHERE id = ? | executeQuery() |
| Read (semua data) | ambilSemua() | SELECT * FROM | executeQuery() + loop ResultSet |
| Update | ubah(), ubahStatus() | UPDATE ... WHERE id = ? | executeUpdate() |
| Delete | hapus() | DELETE ... WHERE id = ? | executeUpdate() |
| Hitung | hitung() | SELECT COUNT(*) | executeQuery() |

executeUpdate() mengembalikan jumlah baris yang berubah, sehingga method DAO dapat mengembalikan boolean (true jika berhasil). Hasil executeQuery() berupa ResultSet yang dipetakan menjadi objek Pelanggan, Teknisi, atau DetailService.

### 6.8 Konversi Tanggal Java dan MySQL (TanggalUtil.java)
Kolom tanggal di database bertipe DATE, sedangkan pengguna mengetik format DD-MM-YYYY.
```java
public static Date keSqlDate(String tanggal) {
    return Date.valueOf(LocalDate.parse(tanggal, FORMAT));
}

public static String dariSqlDate(Date date) {
    return date.toLocalDate().format(FORMAT);
}
```
* Saat *menyimpan*: "20-10-2026" diubah menjadi java.sql.Date.
* Saat *membaca*: java.sql.Date dari ResultSet diubah kembali menjadi "20-10-2026".
* Validasi memakai ResolverStyle.STRICT, sehingga tanggal tidak nyata seperti 31-02-2026 ditolak sebelum sampai ke database.

### 6.9 Foreign Key dan Integritas Data
Tabel service memiliki foreign key ke tabel pelanggan dan teknisi dengan aturan ON DELETE RESTRICT. Jika pelanggan atau teknisi masih dipakai pada data service lalu dihapus, MySQL melempar SQLException. DAO menangkapnya dan mengembalikan false, kemudian View menampilkan pesan:
> Pelanggan gagal dihapus! Data masih dipakai pada data service.

### 6.10 Penanganan Error
Operasi JDBC melempar SQLException (checked exception) sehingga wajib ditangani dengan try-catch. Error tidak membuat program berhenti mendadak: method DAO mengembalikan false atau null, dan View menampilkan pesan yang mudah dipahami pengguna.


---

## 8. Contoh Output Program & Screenshots

### BAGIAN YANG DI-SCREENSHOT (SS) PADA RUNTIME NETBEANS:
1. *(Tampilan menu utama)*:
<img width="426" height="207" alt="image" src="https://github.com/user-attachments/assets/2ddc6438-4731-4329-b39b-c8309c3aefc5" />

---

2. *(Tampil Data Awal / Read Dummy SQL pada setiap menu untuk Pelanggan, Teknisi, Data Service AC)*:
<img width="917" height="316" alt="image" src="https://github.com/user-attachments/assets/216438a9-b78d-45e4-875f-3aab8962eb2a" />

<img width="872" height="297" alt="image" src="https://github.com/user-attachments/assets/2eb5f07d-cdd4-45b5-ad2d-faa579ed4505" />
<img width="407" height="862" alt="image" src="https://github.com/user-attachments/assets/4829d234-702d-4a46-b166-89ec7c8689f1" />


---

3. *(Tambah Data / Create JDBC pada menu Pelanggan, Teknisi, dan Data Service Ac)*:
<img width="540" height="176" alt="image" src="https://github.com/user-attachments/assets/2cf8d22b-9aa6-4ee9-b07d-7e610f93b85a" />

<img width="497" height="177" alt="image" src="https://github.com/user-attachments/assets/3f48ec7e-9431-4594-97ba-f57b374a273c" />

<img width="362" height="180" alt="image" src="https://github.com/user-attachments/assets/98b581e2-12e3-4efa-a285-349943d89f98" />

---

4. *(Ubah Data / Update pada menu Pelanggan, Teknisi, dan Data Service)*:
<img width="308" height="132" alt="image" src="https://github.com/user-attachments/assets/823b37da-f5ac-4719-ade6-ec2f3d78afb6" />

<img width="325" height="142" alt="image" src="https://github.com/user-attachments/assets/ea439139-d122-4925-9532-95a53f2d2947" />

<img width="340" height="273" alt="image" src="https://github.com/user-attachments/assets/0366c370-7d0b-4df5-8240-ddb560ff98b1" />
   
---

5. *(Ubah Status Service)*:
<img width="355" height="160" alt="image" src="https://github.com/user-attachments/assets/9a9a5ee1-c629-4f78-a582-1deff2432ebb" />

---

6. *(Hapus Data / Delete JDBC)*:
<img width="346" height="91" alt="image" src="https://github.com/user-attachments/assets/ea848899-40bf-4eb1-8f77-d6e925c26227" />

<img width="530" height="112" alt="image" src="https://github.com/user-attachments/assets/31b30213-ce5d-45f1-91f5-f3efc55ff9ae" />

---

7. *(Informasi Jenis Service / Polymorphism)*:
<img width="905" height="437" alt="image" src="https://github.com/user-attachments/assets/2bb8a3e5-3ed8-4c65-89b5-0110d433f6d7" />

---

8. *(Dashboard)*:
<img width="463" height="395" alt="image" src="https://github.com/user-attachments/assets/961b44b9-dcdd-4ecc-bb00-07c46cb5859a" />

---

9. *(Validasi Input)*:
<img width="510" height="40" alt="image" src="https://github.com/user-attachments/assets/19355897-b97a-4d3f-bb37-1cd08ba50b43" />

<img width="257" height="45" alt="image" src="https://github.com/user-attachments/assets/dbc9e2c6-1ee5-4b40-8364-718873d6fe55" />

<img width="346" height="42" alt="image" src="https://github.com/user-attachments/assets/4878cad2-2072-4d56-acb3-7267f29b3304" />
