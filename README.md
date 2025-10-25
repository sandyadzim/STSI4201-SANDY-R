# Tugas 1 STSI4201 - Program Aplikasi Restoran Sederhana

## Deskripsi
Program Java ini adalah aplikasi restoran sederhana yang memungkinkan pelanggan untuk memesan makanan dan minuman, menghitung total biaya dengan pajak, biaya pelayanan, diskon, dan promo, serta mencetak struk pembayaran.

## Fitur Program
1. **Input Menu Restoran**: Menyimpan data menu makanan dan minuman dalam array
2. **Pemesanan**: Menerima pesanan maksimal 4 menu dengan format "Nama Menu = Jumlah"
3. **Perhitungan Biaya**: 
   - Subtotal pesanan
   - Pajak 10%
   - Biaya pelayanan Rp 20.000
   - Diskon 10% untuk pembelian > Rp 100.000
   - Promo beli 1 gratis 1 minuman untuk pembelian > Rp 50.000
4. **Struk Pembayaran**: Menampilkan detail lengkap pesanan dan pembayaran

## Struktur Kelas

### Kelas Menu
- **Atribut**: nama, harga, kategori
- **Method**: Constructor, getter, dan setter

### Kelas App (Main)
- **Method**:
  - `initializeMenu()`: Inisialisasi data menu (4 makanan, 4 minuman)
  - `tampilkanMenu()`: Menampilkan menu berdasarkan kategori
  - `prosesTempatPesanan()`: Menerima input pesanan
  - `hitungTotalBiaya()`: Menghitung total dengan pajak, diskon, dan promo
  - `cetakStruk()`: Mencetak struk pembayaran lengkap

## Menu Restoran

### Makanan:
1. Nasi Padang - Rp 25.000
2. Mie Goreng - Rp 20.000
3. Nasi Goreng - Rp 22.000
4. Ayam Geprek - Rp 23.000

### Minuman:
5. Es Teh - Rp 5.000
6. Es Jeruk - Rp 7.000
7. Jus Alpukat - Rp 12.000
8. Cappuccino - Rp 15.000

## Skenario Pengujian

### Skenario 1: Pesanan Tanpa Diskon dan Tanpa Promo (< Rp 50.000)
**Input:**
```
Pesanan 1: Mie Goreng = 1
Pesanan 2: Es Teh = 1
Pesanan 3: selesai
```
**Output yang diharapkan:**
- Subtotal: Rp 25.000
- Tidak ada promo minuman (subtotal < Rp 50.000)
- Tidak ada diskon (subtotal < Rp 100.000)

### Skenario 2: Pesanan Dengan Promo Minuman (> Rp 50.000, < Rp 100.000)
**Input:**
```
Pesanan 1: Nasi Padang = 2
Pesanan 2: Es Teh = 2
Pesanan 3: selesai
```
**Output yang diharapkan:**
- Subtotal: Rp 60.000
- Dapat promo beli 1 gratis 1 minuman (subtotal > Rp 50.000)
- Tidak dapat diskon 10% (subtotal < Rp 100.000)

### Skenario 3: Pesanan Dengan Diskon 10% (> Rp 100.000)
**Input:**
```
Pesanan 1: Nasi Padang = 3
Pesanan 2: Ayam Geprek = 2
Pesanan 3: selesai
```
**Output yang diharapkan:**
- Subtotal: Rp 121.000
- Dapat promo beli 1 gratis 1 minuman (jika ada minuman minimal 2)
- Dapat diskon 10% (subtotal > Rp 100.000)

### Skenario 4: Pesanan Maksimal (4 Menu) dengan Semua Promo
**Input:**
```
Pesanan 1: Nasi Padang = 2
Pesanan 2: Ayam Geprek = 2
Pesanan 3: Cappuccino = 2
Pesanan 4: Jus Alpukat = 1
```
**Output yang diharapkan:**
- Subtotal: Rp 142.000
- Dapat promo beli 1 gratis 1 minuman Cappuccino (subtotal > Rp 50.000)
- Dapat diskon 10% (subtotal > Rp 100.000)

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
- Program menggunakan **array** untuk mengelola menu dan pesanan
- Menggunakan **struktur keputusan (if)** tanpa loop (sesuai petunjuk)
- Maksimal 4 menu per pesanan
- Format input: "Nama Menu = Jumlah"
- Promo minuman otomatis diterapkan pada minuman pertama yang jumlahnya ≥ 2

