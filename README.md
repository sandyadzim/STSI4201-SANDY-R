# Tugas 3 - Manajemen Restoran

## Deskripsi
Program ini memungkinkan pengguna untuk mengelola menu restoran dan memproses pesanan pelanggan dengan fitur simpan/muat data dari file.

---

## Struktur Kelas

### 1. **Abstract Class: MenuItem**
```
MenuItem (abstract)
├── Atribut:
│   ├── private String nama
│   ├── private double harga
│   └── private String kategori
│
├── Method:
│   ├── Constructor(nama, harga, kategori)
│   ├── Getter/Setter untuk semua atribut
│   ├── abstract void tampilMenu()
│   └── abstract String toFileString()
```

### 2. **Subclass: Makanan**
```
Makanan extends MenuItem
├── Atribut Tambahan:
│   └── private String jenisMakanan
│
├── Method:
│   ├── Constructor(nama, harga, jenisMakanan)
│   ├── @Override void tampilMenu()
│   └── String toFileString()
```

### 3. **Subclass: Minuman**
```
Minuman extends MenuItem
├── Atribut Tambahan:
│   └── private String jenisMinuman
│
├── Method:
│   ├── Constructor(nama, harga, jenisMinuman)
│   ├── @Override void tampilMenu()
│   └── String toFileString()
```

### 4. **Subclass: Diskon**
```
Diskon extends MenuItem
├── Atribut Tambahan:
│   ├── private double persenDiskon
│   └── private double hargaAsli
│
├── Method:
│   ├── Constructor(nama, hargaAsli, persenDiskon)
│   ├── getPersenDiskon()
│   ├── getHargaAsli()
│   ├── @Override void tampilMenu()
│   ├── double getNilaiDiskon()
│   └── String toFileString()
```

### 5. **Class: Menu**
```
Menu
├── Atribut:
│   └── private ArrayList<MenuItem> daftarMenu
│
├── Method:
│   ├── void tambahItem(MenuItem item)
│   ├── void tampilkanSemuaMenu()
│   ├── void tampilkanMenuBerNomor()
│   ├── MenuItem getItem(int index) throws IndexOutOfBoundsException
│   ├── int getJumlahItem()
│   ├── void simpanKeFile()
│   ├── void muatDariFile()
│   └── void inisialisasiMenuDefault()
```

### 6. **Class: Pesanan**
```
Pesanan
├── Atribut:
│   ├── private ArrayList<MenuItem> itemPesanan
│   ├── private ArrayList<Integer> jumlahPesanan
│   ├── private String namaPelanggan
│   └── private LocalDateTime waktuPesanan
│
### 6. **Class: Pesanan**
```
Pesanan
├── Atribut:
│   ├── private ArrayList<MenuItem> itemPesanan
│   ├── private ArrayList<Integer> jumlahPesanan
│   ├── private String namaPelanggan
│   └── private LocalDateTime waktuPesanan
│
├── Method:
│   ├── Constructor(namaPelanggan)
│   ├── void tambahItem(MenuItem, int) throws IllegalArgumentException
│   ├── double hitungTotal()
│   ├── double hitungTotalDiskon()
│   ├── void tampilkanStruk()
│   ├── void simpanStrukKeFile()
│   ├── boolean isEmpty()
│   ├── int getJumlahItem()
│   └── void reset()
```

### 7. **Main Class**
```
Main
├── Static Variables:
│   ├── Scanner scanner
│   ├── Menu menu
│   └── Pesanan pesananAktif
│
├── Method:
│   ├── main(String[] args)
│   ├── void tampilkanMenuUtama()
│   ├── void tambahItemKeMenu()
│   ├── void terimaPesanan()
│   ├── void hitungTotalPesanan()
│   ├── void tampilkanDanSimpanStruk()
│   ├── boolean keluar()
│   └── String getValidInput(int max)
```

---

## Fitur Program

### Menu 1: Tambah Item ke Menu
- Tambah **Makanan** dengan jenis (Nasi, Mie, Ayam, dll)
- Tambah **Minuman** dengan jenis (Dingin, Panas, Juice)
- Tambah **Item Diskon** dengan persentase diskon
- Validasi input harga dan persentase diskon
- Auto-save ke file setelah menambah

### Menu 2: Tampilkan Menu Restoran
- Menampilkan semua menu dengan **polymorphism**
- Dikelompokkan: Makanan, Minuman, Promo Spesial
- Setiap tipe ditampilkan dengan format berbeda

### Menu 3: Terima Pesanan Pelanggan
- Input nama pelanggan
- Pilih menu dari daftar bernomor
- Input jumlah pesanan
- Pesanan bisa ditambah berkali-kali

### Menu 4: Hitung Total Biaya Pesanan
- Menghitung total dari semua item
- Otomatis menghitung diskon jika ada item diskon
- Menampilkan ringkasan pesanan

### Menu 5: Tampilkan & Simpan Struk
- Tampilkan struk di layar dengan detail lengkap
- Nilai diskon ditampilkan sebagai total untuk item (nilai per-item × jumlah)
- Simpan struk ke file dengan format:
  - `data/struk/struk_[nama]_[timestamp].txt`
- Detail diskon jika ada dengan breakdown lengkap
- **Auto-reset pesanan** setelah struk disimpan (siap terima pelanggan baru)

### Menu 6: Keluar
- Pesan terima kasih
- Tutup scanner dan keluar dari program

---