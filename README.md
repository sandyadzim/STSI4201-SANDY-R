# Tugas 2 STSI4201 - Program Aplikasi Restoran dengan Manajemen Menu

## Deskripsi
Program Java ini adalah aplikasi restoran lengkap yang memungkinkan pelanggan untuk memesan makanan dan minuman serta pemilik restoran untuk mengelola menu.

## Fitur Program

### 1. Menu Pelanggan (Pemesanan)
- **Input Menu Restoran**: Menyimpan data menu makanan dan minuman dalam ArrayList
- **Pemesanan Tidak Terbatas**: Pelanggan dapat memesan sebanyak yang diinginkan hingga mengetik 'selesai'
- **Validasi Input**: Sistem akan terus meminta input jika format salah atau menu tidak ditemukan
- **Perhitungan Biaya**: 
  - Subtotal pesanan
  - Pajak 10%
  - Biaya pelayanan Rp 20.000
  - Diskon 10% untuk pembelian > Rp 100.000
  - Promo beli 1 gratis 1 minuman untuk pembelian > Rp 50.000
- **Struk Pembayaran**: Menampilkan detail lengkap pesanan dan pembayaran

### 2. Menu Pemilik (Manajemen Menu)
- **Lihat Daftar Menu**: Menampilkan semua menu yang tersedia
- **Tambah Menu Baru**: Menambahkan satu atau beberapa menu sekaligus
- **Ubah Harga Menu**: Mengubah harga menu dengan konfirmasi
- **Hapus Menu**: Menghapus menu dengan konfirmasi
- **Validasi Input**: Sistem akan terus meminta input jika pilihan tidak valid
- **Konfirmasi**: Setiap perubahan/penghapusan memerlukan konfirmasi 'Ya/Tidak'

## Struktur Kelas

### Kelas Menu
- **Atribut**: nama, harga, kategori
- **Method**: Constructor, getter, dan setter

### Kelas Main
- **Data Struktur**: 
  - `ArrayList<Menu> daftarMenu` - Menyimpan semua menu (dinamis)
  - `ArrayList<String> namaPesanan` - Menyimpan nama pesanan (tidak terbatas)
  - `ArrayList<Integer> jumlahPesanan` - Menyimpan jumlah pesanan
  - `ArrayList<Integer> hargaPesanan` - Menyimpan harga pesanan

- **Method Utama**:
  - `main()` - Entry point dengan menu utama
  - `tampilkanMenuUtama()` - Menampilkan menu utama aplikasi
  - `getValidInput()` - Validasi input dengan pilihan tertentu

- **Method Pelanggan**:
  - `menuPelanggan()` - Mengelola alur pemesanan pelanggan
  - `tampilkanMenu()` - Menampilkan daftar menu berdasarkan kategori
  - `prosesTempatPesanan()` - Menerima input pesanan
  - `prosesInputPesanan()` - Memproses setiap input pesanan
  - `cariMenu()` - Mencari menu berdasarkan nama
  - `hitungTotalBiaya()` - Menghitung total dengan pajak, diskon, dan promo
  - `hitungPromoMinuman()` - Menghitung promo minuman
  - `cetakStruk()` - Mencetak struk pembayaran

- **Method Pemilik**:
  - `menuPemilik()` - Menu manajemen untuk pemilik
  - `tambahMenu()` - Menambahkan menu baru
  - `ubahHargaMenu()` - Mengubah harga menu dengan konfirmasi
  - `hapusMenu()` - Menghapus menu dengan konfirmasi
  - `tampilkanSemuaMenuBerNomor()` - Menampilkan semua menu dengan nomor

- **Helper Method**:
  - `initializeMenu()` - Inisialisasi data menu awal
  - `getKategoriMenu()` - Mendapatkan kategori menu

## Menu Restoran (Initial Data)

### Makanan:
1. Nasi Padang - Rp 25.000
2. Mie Goreng - Rp 20.000
3. Nasi Goreng - Rp 22.000
4. Ayam Geprek - Rp 23.000

### Minuman:
1. Es Teh - Rp 5.000
2. Es Jeruk - Rp 7.000
3. Jus Alpukat - Rp 12.000
4. Cappuccino - Rp 15.000


## Aturan Perhitungan

1. **Subtotal** = Σ (Harga × Jumlah) untuk setiap item
2. **Pajak** = Subtotal × 10%
3. **Total Sebelum Promo** = Subtotal + Pajak + Biaya Pelayanan (Rp 20.000)
4. **Promo Minuman** (jika subtotal > Rp 50.000 dan ada minuman dengan jumlah ≥ 2):
   - Potongan = Harga 1 minuman
5. **Total Setelah Promo** = Total Sebelum Promo - Potongan Promo
6. **Diskon 10%** (jika subtotal > Rp 100.000):
   - Potongan Diskon = Total Setelah Promo × 10%
7. **Total Akhir** = Total Setelah Promo - Potongan Diskon

## Catatan Implementasi
- Program menggunakan **ArrayList** untuk mengelola menu dan pesanan (dinamis)
- Menggunakan **loop (for, while, for-each)** untuk efisiensi dan fleksibilitas
- Menggunakan **struktur keputusan (if-else, switch-case)** untuk logika aplikasi
- Pesanan **tidak terbatas** - pelanggan dapat memesan sebanyak yang diinginkan
- **Validasi input** ketat - sistem akan terus meminta input jika tidak valid
- **Konfirmasi** untuk setiap perubahan/penghapusan data
- Format input: "Nama Menu = Jumlah"
- Promo minuman otomatis diterapkan pada minuman pertama yang jumlahnya ≥ 2

