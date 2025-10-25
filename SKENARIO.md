# Panduan Pengujian Program Restoran

## Skenario Pengujian Detail

### Skenario 1: Tanpa Promo dan Diskon
**Tujuan:** Menguji pesanan dengan total < Rp 50.000

**Langkah:**
1. Jalankan program
2. Input:
   ```
   Mie Goreng = 1
   Es Teh = 1
   selesai
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
2. Input:
   ```
   Nasi Padang = 2
   Es Teh = 2
   selesai
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
2. Input:
   ```
   Nasi Padang = 3
   Ayam Geprek = 2
   selesai
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
2. Input:
   ```
   Nasi Padang = 2
   Ayam Geprek = 2
   Cappuccino = 2
   Jus Alpukat = 1
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

### Skenario 5: Pesanan dengan Total Tepat di Batas
**Tujuan:** Menguji pesanan dengan total tepat Rp 50.000 atau Rp 100.000

**Langkah A - Tepat Rp 50.000:**
1. Jalankan program
2. Input:
   ```
   Mie Goreng = 2
   Es Teh = 2
   selesai
   selesai
   ```

**Hasil yang Diharapkan:**
- Subtotal: Rp 50.000
- Tidak dapat promo (harus > Rp 50.000, bukan >=)

**Langkah B - Sedikit di Atas Rp 50.000:**
1. Jalankan program
2. Input:
   ```
   Nasi Padang = 2
   Es Jeruk = 2
   selesai
   selesai
   ```

**Hasil yang Diharapkan:**
- Subtotal: Rp 64.000
- Dapat promo minuman (1 Es Jeruk gratis = Rp 7.000)

---

### Skenario 6: Pesanan 1 Item Saja
**Tujuan:** Menguji pesanan minimal

**Langkah:**
1. Jalankan program
2. Input:
   ```
   Nasi Goreng = 1
   selesai
   selesai
   selesai
   ```

**Hasil yang Diharapkan:**
- Subtotal: Rp 22.000
- Pajak: Rp 2.200
- Biaya Pelayanan: Rp 20.000
- Total: Rp 44.200
- Tidak ada promo
- Tidak ada diskon

---

## Contoh Output Struk yang Benar

```
===========================================
            STRUK PEMBAYARAN               
===========================================
Detail Pesanan:
-------------------------------------------
Nasi Padang
  2 x Rp 25000 = Rp 50000
Cappuccino
  2 x Rp 15000 = Rp 30000
-------------------------------------------
Subtotal            : Rp 80000
Pajak (10%)         : Rp 8000
Biaya Pelayanan     : Rp 20000
-------------------------------------------
Total Sebelum Promo : Rp 108000

** PROMO MINUMAN **
Promo Beli 1 Gratis 1 : Ya (Beli 1 Gratis 1 Minuman)
Potongan            : Rp 15000
Total Setelah Promo : Rp 93000

Diskon              : Tidak
-------------------------------------------
TOTAL PEMBAYARAN    : Rp 93000
===========================================

    Terima kasih atas kunjungan Anda!
===========================================
```
