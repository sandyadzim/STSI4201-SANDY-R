# Panduan Pengujian Program Restoran - Tugas 2

## Overview
Program restoran ini memiliki **2 menu utama**:
1. **Menu Pelanggan** - Untuk pemesanan makanan/minuman
2. **Menu Pemilik** - Untuk manajemen menu (CRUD operations)

---

## BAGIAN A: Skenario Menu Pelanggan (Pemesanan)

### Skenario 1: Tanpa Promo dan Diskon
**Tujuan:** Menguji pesanan dengan total < Rp 50.000

**Langkah:**
1. Jalankan program
2. Pilih menu: `1` (Menu Pelanggan)
3. Input pesanan:
   ```
   Mie Goreng = 1
   Es Teh = 1
   selesai
   ```

**Hasil yang Diharapkan:**
- Subtotal: Rp 25.000
- Pajak: Rp 2.500
- Biaya Pelayanan: Rp 20.000
- Total: Rp 47.500
- Tidak ada promo minuman
- Tidak ada diskon

---

### Skenario 2: Dengan Promo Minuman Saja
**Tujuan:** Menguji pesanan dengan Rp 50.000 < total < Rp 100.000 dan ada minuman minimal 2

**Langkah:**
1. Jalankan program
2. Pilih menu: `1` (Menu Pelanggan)
3. Input pesanan:
   ```
   Nasi Padang = 2
   Es Teh = 2
   selesai
   ```

**Hasil yang Diharapkan:**
- Subtotal: Rp 60.000
- Pajak: Rp 6.000
- Biaya Pelayanan: Rp 20.000
- Total Sebelum Promo: Rp 86.000
- **Promo Minuman:** Potongan Rp 5.000 (1 Es Teh gratis)
- Total Setelah Promo: Rp 81.000
- Tidak ada diskon 10%
- **Total Akhir: Rp 81.000**

---

### Skenario 3: Dengan Diskon 10% Saja (Tanpa Minuman)
**Tujuan:** Menguji pesanan dengan total > Rp 100.000 tanpa minuman

**Langkah:**
1. Jalankan program
2. Pilih menu: `1` (Menu Pelanggan)
3. Input pesanan:
   ```
   Nasi Padang = 3
   Ayam Geprek = 2
   selesai
   ```

**Hasil yang Diharapkan:**
- Subtotal: Rp 121.000
- Pajak: Rp 12.100
- Biaya Pelayanan: Rp 20.000
- Total Sebelum Diskon: Rp 153.100
- Tidak ada promo minuman (tidak ada minuman)
- **Diskon 10%:** Potongan Rp 15.310
- **Total Akhir: Rp 137.790**

---

### Skenario 4: Dengan Promo Minuman DAN Diskon 10%
**Tujuan:** Menguji pesanan dengan total > Rp 100.000 dan ada minuman minimal 2

**Langkah:**
1. Jalankan program
2. Pilih menu: `1` (Menu Pelanggan)
3. Input pesanan:
   ```
   Nasi Padang = 2
   Ayam Geprek = 2
   Cappuccino = 2
   Jus Alpukat = 1
   selesai
   ```

**Hasil yang Diharapkan:**
- Subtotal: Rp 142.000
- Pajak: Rp 14.200
- Biaya Pelayanan: Rp 20.000
- Total Sebelum Promo: Rp 176.200
- **Promo Minuman:** Potongan Rp 15.000 (1 Cappuccino gratis)
- Total Setelah Promo: Rp 161.200
- **Diskon 10%:** Potongan Rp 16.120
- **Total Akhir: Rp 145.080**

---

### Skenario 5: Pesanan Tidak Terbatas
**Tujuan:** Menguji pesanan dengan banyak item (>10 item)

**Langkah:**
1. Jalankan program
2. Pilih menu: `1` (Menu Pelanggan)
3. Input pesanan banyak:
   ```
   Nasi Padang = 3
   Mie Goreng = 2
   Nasi Goreng = 2
   Ayam Geprek = 3
   Es Teh = 5
   Es Jeruk = 3
   Jus Alpukat = 2
   Cappuccino = 2
   selesai
   ```

**Hasil yang Diharapkan:**
- Semua pesanan berhasil diproses
- Total 8 item berbeda
- Perhitungan total benar
- Mendapat promo minuman dan diskon 10%

---

## BAGIAN B: Skenario Menu Pemilik (Manajemen Menu)

### Skenario 8: Tambah Menu Baru
**Tujuan:** Menguji fitur tambah menu

**Langkah:**
1. Jalankan program
2. Pilih menu: `2` (Menu Pemilik)
3. Pilih submenu: `2` (Tambah Menu Baru)
4. Input:
   ```
   Berapa menu? 2
   
   --- Menu ke-1 ---
   Nama: Soto Ayam
   Harga: 18000
   Kategori: Makanan
   
   --- Menu ke-2 ---
   Nama: Teh Tarik
   Harga: 8000
   Kategori: Minuman
   ```
5. Pilih: `1` (Lihat Daftar Menu) untuk verifikasi

**Hasil yang Diharapkan:**
- Pesan sukses untuk setiap menu ditambahkan
- Total 2 menu berhasil ditambahkan
- Menu baru muncul di daftar (total jadi 10 menu)

---

### Skenario 9: Ubah Harga Menu dengan Konfirmasi
**Tujuan:** Menguji fitur ubah harga + konfirmasi

**Langkah:**
1. Jalankan program
2. Pilih menu: `2` (Menu Pemilik)
3. Pilih submenu: `3` (Ubah Harga Menu)
4. Daftar menu muncul
5. Input:
   ```
   Nomor menu: 1
   Harga baru: 27000
   Konfirmasi: Ya
   ```

**Hasil yang Diharapkan:**
- Pesan konfirmasi muncul dengan harga lama dan baru
- Harga "Nasi Padang" berubah dari Rp 25000 → Rp 27000
- Pesan sukses ditampilkan

**Test Case 2 - Batalkan:**
```
Nomor menu: 2
Harga baru: 25000
Konfirmasi: Tidak
```

**Hasil yang Diharapkan:**
- Pesan "✗ Perubahan harga dibatalkan."
- Harga tetap Rp 20000 (tidak berubah)

---

### Skenario 10: Hapus Menu dengan Konfirmasi
**Tujuan:** Menguji fitur hapus menu + konfirmasi

**Langkah:**
1. Jalankan program
2. Pilih menu: `2` (Menu Pemilik)
3. Pilih submenu: `4` (Hapus Menu)
4. Daftar menu muncul
5. Input:
   ```
   Nomor menu: 5
   Konfirmasi: Ya
   ```

**Hasil yang Diharapkan:**
- Pesan konfirmasi muncul dengan nama dan harga menu
- Menu "Es Teh" berhasil dihapus
- Total menu berkurang dari 8 → 7
- Nomor urut menu otomatis terurut ulang

**Test Case 2 - Batalkan:**
```
Nomor menu: 6
Konfirmasi: Tidak
```

**Hasil yang Diharapkan:**
- Pesan "✗ Penghapusan menu dibatalkan."
- Menu "Es Jeruk" tetap ada

---

### Skenario 12: Lihat Daftar Menu
**Tujuan:** Menguji fitur lihat semua menu

**Langkah:**
1. Jalankan program
2. Pilih menu: `2` (Menu Pemilik)
3. Pilih submenu: `1` (Lihat Daftar Menu)

**Hasil yang Diharapkan:**
- Semua menu ditampilkan dengan nomor urut
- Format: "No. Nama - Rp Harga (Kategori)"
- Contoh: "1. Nasi Padang - Rp 25000 (Makanan)"

---

## Contoh Output Program

### Output Menu Utama

```
=== MENU UTAMA ===
1. Menu Pelanggan (Pemesanan)
2. Menu Pemilik (Manajemen Menu)
3. Keluar

Pilih menu (1-3): 
```

### Output Menu Pelanggan
```
=== MENU MAKANAN ===
1. Nasi Padang - Rp 25000
2. Mie Goreng - Rp 20000
3. Nasi Goreng - Rp 22000
4. Ayam Geprek - Rp 23000

=== MENU MINUMAN ===
1. Es Teh - Rp 5000
2. Es Jeruk - Rp 7000
3. Jus Alpukat - Rp 12000
4. Cappuccino - Rp 15000

=== PEMESANAN ===
Format: Nama Menu = Jumlah
Ketik 'selesai' untuk mengakhiri pesanan

Pesanan: Nasi Padang = 2
✓ "Nasi Padang" x2 ditambahkan ke pesanan.
Pesanan: Cappuccino = 2
✓ "Cappuccino" x2 ditambahkan ke pesanan.
Pesanan: selesai

✓ Total 2 item berhasil dipesan.
```

### Output Struk Pembayaran
```
========================================
         STRUK PEMBAYARAN
========================================
Item Pesanan:
1. Nasi Padang x2 = Rp 50000
2. Cappuccino x2 = Rp 30000
----------------------------------------
Subtotal           : Rp 80000
Pajak (10%)        : Rp 8000
Biaya Pelayanan    : Rp 20000
Total Sebelum Promo: Rp 108000
Promo Minuman      : -Rp 15000
Total Setelah Promo: Rp 93000
Diskon (10%)       : Tidak
========================================
TOTAL AKHIR        : Rp 93000
========================================
```

### Output Menu Pemilik
```
=== MENU PEMILIK ===
1. Lihat Daftar Menu
2. Tambah Menu Baru
3. Ubah Harga Menu
4. Hapus Menu
5. Kembali ke Menu Utama

Pilih menu (1-5): 
```

### Output Tambah Menu
```
=== TAMBAH MENU BARU ===
Berapa menu yang ingin ditambahkan? 2

--- Menu ke-1 ---
Nama menu: Soto Ayam
Harga menu: Rp 18000
Kategori (Makanan/Minuman): Makanan
✓ Menu "Soto Ayam" berhasil ditambahkan!

--- Menu ke-2 ---
Nama menu: Teh Tarik
Harga menu: Rp 8000
Kategori (Makanan/Minuman): Minuman
✓ Menu "Teh Tarik" berhasil ditambahkan!

✓ Total 2 menu berhasil ditambahkan!
```

### Output Ubah Harga
```
=== UBAH HARGA MENU ===

=== DAFTAR SEMUA MENU ===
1. Nasi Padang - Rp 25000 (Makanan)
2. Mie Goreng - Rp 20000 (Makanan)
3. Nasi Goreng - Rp 22000 (Makanan)
4. Ayam Geprek - Rp 23000 (Makanan)
5. Es Teh - Rp 5000 (Minuman)
6. Es Jeruk - Rp 7000 (Minuman)
7. Jus Alpukat - Rp 12000 (Minuman)
8. Cappuccino - Rp 15000 (Minuman)

Masukkan nomor menu yang ingin diubah harganya: 1
Menu yang dipilih: Nasi Padang (Harga saat ini: Rp 25000)
Masukkan harga baru: Rp 27000

Yakin ingin mengubah harga "Nasi Padang" dari Rp 25000 menjadi Rp 27000? (Ya/Tidak): Ya
✓ Harga menu "Nasi Padang" berhasil diubah menjadi Rp 27000!
```

### Output Hapus Menu
```
=== HAPUS MENU ===

=== DAFTAR SEMUA MENU ===
1. Nasi Padang - Rp 25000 (Makanan)
2. Mie Goreng - Rp 20000 (Makanan)
...

Masukkan nomor menu yang ingin dihapus: 5
Yakin ingin menghapus menu "Es Teh" (Rp 5000)? (Ya/Tidak): Ya
✓ Menu "Es Teh" berhasil dihapus!
```