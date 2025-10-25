import java.util.Scanner;

public class Main {
    // Array untuk menyimpan data menu restoran
    private static Menu[] daftarMenu;
    
    // Array untuk menyimpan pesanan (maksimal 4 menu)
    private static String[] namaPesanan = new String[4];
    private static int[] jumlahPesanan = new int[4];
    private static int[] hargaPesanan = new int[4];
    private static int totalItemPesanan = 0;
    
    // Konstanta untuk perhitungan
    private static final double PAJAK = 0.10;
    private static final int BIAYA_PELAYANAN = 20000;
    private static final double DISKON_BESAR = 0.10;
    private static final int MIN_DISKON_BESAR = 100000;
    private static final int MIN_PROMO_MINUMAN = 50000;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Inisialisasi menu restoran
        initializeMenu();
        
        // Tampilkan menu
        tampilkanMenu();
        
        // Proses pemesanan
        prosesTempatPesanan(scanner);
        
        // Hitung total biaya
        hitungTotalBiaya();
        
        scanner.close();
    }
    
    // Method untuk menginisialisasi data menu
    private static void initializeMenu() {
        daftarMenu = new Menu[8];
        
        // Menu Makanan (4 item)
        daftarMenu[0] = new Menu("Nasi Padang", 25000, "Makanan");
        daftarMenu[1] = new Menu("Mie Goreng", 20000, "Makanan");
        daftarMenu[2] = new Menu("Nasi Goreng", 22000, "Makanan");
        daftarMenu[3] = new Menu("Ayam Geprek", 23000, "Makanan");
        
        // Menu Minuman (4 item)
        daftarMenu[4] = new Menu("Es Teh", 5000, "Minuman");
        daftarMenu[5] = new Menu("Es Jeruk", 7000, "Minuman");
        daftarMenu[6] = new Menu("Jus Alpukat", 12000, "Minuman");
        daftarMenu[7] = new Menu("Cappuccino", 15000, "Minuman");
    }
    
    // Method untuk menampilkan menu berdasarkan kategori
    private static void tampilkanMenu() {
        System.out.println("===========================================");
        System.out.println("       SELAMAT DATANG DI RESTORAN SANDY    ");
        System.out.println("===========================================\n");
        
        // Tampilkan menu Makanan
        System.out.println("==== MENU MAKANAN ====");
        tampilkanMenuMakanan();
        
        System.out.println();
        
        // Tampilkan menu Minuman
        System.out.println("==== MENU MINUMAN ====");
        tampilkanMenuMinuman();
        
        System.out.println("\n===========================================\n");
    }
    
    // Method untuk menampilkan menu makanan
    private static void tampilkanMenuMakanan() {
        if (daftarMenu[0].getKategori().equals("Makanan")) {
            System.out.println("1. " + daftarMenu[0].getNama() + " - Rp " + daftarMenu[0].getHarga());
        }
        if (daftarMenu[1].getKategori().equals("Makanan")) {
            System.out.println("2. " + daftarMenu[1].getNama() + " - Rp " + daftarMenu[1].getHarga());
        }
        if (daftarMenu[2].getKategori().equals("Makanan")) {
            System.out.println("3. " + daftarMenu[2].getNama() + " - Rp " + daftarMenu[2].getHarga());
        }
        if (daftarMenu[3].getKategori().equals("Makanan")) {
            System.out.println("4. " + daftarMenu[3].getNama() + " - Rp " + daftarMenu[3].getHarga());
        }
    }
    
    // Method untuk menampilkan menu minuman
    private static void tampilkanMenuMinuman() {
        if (daftarMenu[4].getKategori().equals("Minuman")) {
            System.out.println("5. " + daftarMenu[4].getNama() + " - Rp " + daftarMenu[4].getHarga());
        }
        if (daftarMenu[5].getKategori().equals("Minuman")) {
            System.out.println("6. " + daftarMenu[5].getNama() + " - Rp " + daftarMenu[5].getHarga());
        }
        if (daftarMenu[6].getKategori().equals("Minuman")) {
            System.out.println("7. " + daftarMenu[6].getNama() + " - Rp " + daftarMenu[6].getHarga());
        }
        if (daftarMenu[7].getKategori().equals("Minuman")) {
            System.out.println("8. " + daftarMenu[7].getNama() + " - Rp " + daftarMenu[7].getHarga());
        }
    }
    
    // Method untuk memproses pemesanan (maksimal 4 menu)
    private static void prosesTempatPesanan(Scanner scanner) {
        System.out.println("SILAKAN LAKUKAN PEMESANAN");
        System.out.println("(Maksimal 4 menu, format: Nama Menu = Jumlah)");
        System.out.println("Contoh: Nasi Padang = 2\n");
        
        // Pemesanan item 1
        System.out.print("Pesanan 1 (atau ketik 'selesai' jika sudah): ");
        String input1 = scanner.nextLine();
        if (!input1.equalsIgnoreCase("selesai")) {
            prosesInputPesanan(input1, 0);
        }
        
        // Pemesanan item 2
        if (totalItemPesanan < 4) {
            System.out.print("Pesanan 2 (atau ketik 'selesai' jika sudah): ");
            String input2 = scanner.nextLine();
            if (!input2.equalsIgnoreCase("selesai")) {
                prosesInputPesanan(input2, 1);
            }
        }
        
        // Pemesanan item 3
        if (totalItemPesanan < 4) {
            System.out.print("Pesanan 3 (atau ketik 'selesai' jika sudah): ");
            String input3 = scanner.nextLine();
            if (!input3.equalsIgnoreCase("selesai")) {
                prosesInputPesanan(input3, 2);
            }
        }
        
        // Pemesanan item 4
        if (totalItemPesanan < 4) {
            System.out.print("Pesanan 4 (atau ketik 'selesai' jika sudah): ");
            String input4 = scanner.nextLine();
            if (!input4.equalsIgnoreCase("selesai")) {
                prosesInputPesanan(input4, 3);
            }
        }
        
        System.out.println();
    }
    
    // Method untuk memproses input pesanan individual
    private static void prosesInputPesanan(String input, int index) {
        String[] parts = input.split("=");
        if (parts.length == 2) {
            String namaMenu = parts[0].trim();
            int jumlah = Integer.parseInt(parts[1].trim());

            // Cari menu di daftar menu
            Menu menuDipesan = cariMenu(namaMenu);
            if (menuDipesan != null) {
                namaPesanan[index] = menuDipesan.getNama();
                jumlahPesanan[index] = jumlah;
                hargaPesanan[index] = menuDipesan.getHarga();
                totalItemPesanan++;
            } else {
                System.out.println("Menu tidak ditemukan!");
            }
        }
    }
    
    // Method untuk mencari menu berdasarkan nama
    private static Menu cariMenu(String nama) {
        if (daftarMenu[0].getNama().equalsIgnoreCase(nama)) {
            return daftarMenu[0];
        }
        if (daftarMenu[1].getNama().equalsIgnoreCase(nama)) {
            return daftarMenu[1];
        }
        if (daftarMenu[2].getNama().equalsIgnoreCase(nama)) {
            return daftarMenu[2];
        }
        if (daftarMenu[3].getNama().equalsIgnoreCase(nama)) {
            return daftarMenu[3];
        }
        if (daftarMenu[4].getNama().equalsIgnoreCase(nama)) {
            return daftarMenu[4];
        }
        if (daftarMenu[5].getNama().equalsIgnoreCase(nama)) {
            return daftarMenu[5];
        }
        if (daftarMenu[6].getNama().equalsIgnoreCase(nama)) {
            return daftarMenu[6];
        }
        if (daftarMenu[7].getNama().equalsIgnoreCase(nama)) {
            return daftarMenu[7];
        }
        return null;
    }
    
    // Method untuk menghitung kategori menu
    private static String getKategoriMenu(String namaMenu) {
        if (daftarMenu[0].getNama().equalsIgnoreCase(namaMenu)) {
            return daftarMenu[0].getKategori();
        }
        if (daftarMenu[1].getNama().equalsIgnoreCase(namaMenu)) {
            return daftarMenu[1].getKategori();
        }
        if (daftarMenu[2].getNama().equalsIgnoreCase(namaMenu)) {
            return daftarMenu[2].getKategori();
        }
        if (daftarMenu[3].getNama().equalsIgnoreCase(namaMenu)) {
            return daftarMenu[3].getKategori();
        }
        if (daftarMenu[4].getNama().equalsIgnoreCase(namaMenu)) {
            return daftarMenu[4].getKategori();
        }
        if (daftarMenu[5].getNama().equalsIgnoreCase(namaMenu)) {
            return daftarMenu[5].getKategori();
        }
        if (daftarMenu[6].getNama().equalsIgnoreCase(namaMenu)) {
            return daftarMenu[6].getKategori();
        }
        if (daftarMenu[7].getNama().equalsIgnoreCase(namaMenu)) {
            return daftarMenu[7].getKategori();
        }
        return "";
    }
    
    // Method untuk menghitung total biaya dan mencetak struk
    private static void hitungTotalBiaya() {
        // Hitung subtotal
        int subtotal = 0;
        if (totalItemPesanan >= 1 && namaPesanan[0] != null) {
            subtotal += hargaPesanan[0] * jumlahPesanan[0];
        }
        if (totalItemPesanan >= 2 && namaPesanan[1] != null) {
            subtotal += hargaPesanan[1] * jumlahPesanan[1];
        }
        if (totalItemPesanan >= 3 && namaPesanan[2] != null) {
            subtotal += hargaPesanan[2] * jumlahPesanan[2];
        }
        if (totalItemPesanan >= 4 && namaPesanan[3] != null) {
            subtotal += hargaPesanan[3] * jumlahPesanan[3];
        }
        
        // Hitung pajak
        int biayaPajak = (int)(subtotal * PAJAK);
        
        // Total sebelum diskon
        int totalSebelumDiskon = subtotal + biayaPajak + BIAYA_PELAYANAN;
        
        // Cek dan terapkan promo minuman (beli 1 gratis 1)
        int potonganPromoMinuman = 0;
        String infoPromoMinuman = "";
        if (subtotal > MIN_PROMO_MINUMAN) {
            potonganPromoMinuman = hitungPromoMinuman();
            if (potonganPromoMinuman > 0) {
                infoPromoMinuman = "Ya (Beli 1 Gratis 1 Minuman)";
            }
        }
        
        // Total setelah promo minuman
        int totalSetelahPromo = totalSebelumDiskon - potonganPromoMinuman;
        
        // Cek dan terapkan diskon 10%
        int potonganDiskon = 0;
        String infoDiskon = "";
        if (subtotal > MIN_DISKON_BESAR) {
            potonganDiskon = (int)(totalSetelahPromo * DISKON_BESAR);
            infoDiskon = "Ya (10%)";
        } else {
            infoDiskon = "Tidak";
        }
        
        // Total akhir
        int totalAkhir = totalSetelahPromo - potonganDiskon;
        
        // Cetak struk
        cetakStruk(subtotal, biayaPajak, totalSebelumDiskon, potonganPromoMinuman, 
                   infoPromoMinuman, potonganDiskon, infoDiskon, totalAkhir);
    }
    
    // Method untuk menghitung promo minuman (beli 1 gratis 1)
    private static int hitungPromoMinuman() {
        int potongan = 0;
        
        // Cek apakah ada minuman di pesanan pertama
        if (totalItemPesanan >= 1 && namaPesanan[0] != null) {
            if (getKategoriMenu(namaPesanan[0]).equals("Minuman")) {
                if (jumlahPesanan[0] >= 2) {
                    potongan = hargaPesanan[0];
                    return potongan;
                }
            }
        }
        
        // Cek apakah ada minuman di pesanan kedua
        if (totalItemPesanan >= 2 && namaPesanan[1] != null) {
            if (getKategoriMenu(namaPesanan[1]).equals("Minuman")) {
                if (jumlahPesanan[1] >= 2) {
                    potongan = hargaPesanan[1];
                    return potongan;
                }
            }
        }
        
        // Cek apakah ada minuman di pesanan ketiga
        if (totalItemPesanan >= 3 && namaPesanan[2] != null) {
            if (getKategoriMenu(namaPesanan[2]).equals("Minuman")) {
                if (jumlahPesanan[2] >= 2) {
                    potongan = hargaPesanan[2];
                    return potongan;
                }
            }
        }
        
        // Cek apakah ada minuman di pesanan keempat
        if (totalItemPesanan >= 4 && namaPesanan[3] != null) {
            if (getKategoriMenu(namaPesanan[3]).equals("Minuman")) {
                if (jumlahPesanan[3] >= 2) {
                    potongan = hargaPesanan[3];
                    return potongan;
                }
            }
        }
        
        return potongan;
    }
    
    // Method untuk mencetak struk pesanan
    private static void cetakStruk(int subtotal, int biayaPajak, int totalSebelumDiskon,
                                    int potonganPromoMinuman, String infoPromoMinuman,
                                    int potonganDiskon, String infoDiskon, int totalAkhir) {
        System.out.println("===========================================");
        System.out.println("            STRUK PEMBAYARAN               ");
        System.out.println("===========================================");
        System.out.println("Detail Pesanan:");
        System.out.println("-------------------------------------------");
        
        // Tampilkan detail pesanan
        if (totalItemPesanan >= 1 && namaPesanan[0] != null) {
            int totalItem1 = hargaPesanan[0] * jumlahPesanan[0];
            System.out.println(namaPesanan[0]);
            System.out.println("  " + jumlahPesanan[0] + " x Rp " + hargaPesanan[0] + " = Rp " + totalItem1);
        }
        
        if (totalItemPesanan >= 2 && namaPesanan[1] != null) {
            int totalItem2 = hargaPesanan[1] * jumlahPesanan[1];
            System.out.println(namaPesanan[1]);
            System.out.println("  " + jumlahPesanan[1] + " x Rp " + hargaPesanan[1] + " = Rp " + totalItem2);
        }
        
        if (totalItemPesanan >= 3 && namaPesanan[2] != null) {
            int totalItem3 = hargaPesanan[2] * jumlahPesanan[2];
            System.out.println(namaPesanan[2]);
            System.out.println("  " + jumlahPesanan[2] + " x Rp " + hargaPesanan[2] + " = Rp " + totalItem3);
        }
        
        if (totalItemPesanan >= 4 && namaPesanan[3] != null) {
            int totalItem4 = hargaPesanan[3] * jumlahPesanan[3];
            System.out.println(namaPesanan[3]);
            System.out.println("  " + jumlahPesanan[3] + " x Rp " + hargaPesanan[3] + " = Rp " + totalItem4);
        }
        
        System.out.println("-------------------------------------------");
        System.out.println("Subtotal            : Rp " + subtotal);
        System.out.println("Pajak (10%)         : Rp " + biayaPajak);
        System.out.println("Biaya Pelayanan     : Rp " + BIAYA_PELAYANAN);
        System.out.println("-------------------------------------------");
        System.out.println("Total Sebelum Promo : Rp " + totalSebelumDiskon);
        
        // Tampilkan promo minuman jika ada
        if (potonganPromoMinuman > 0) {
            System.out.println("\n** PROMO MINUMAN **");
            System.out.println("Promo Beli 1 Gratis 1 : " + infoPromoMinuman);
            System.out.println("Potongan            : Rp " + potonganPromoMinuman);
            System.out.println("Total Setelah Promo : Rp " + (totalSebelumDiskon - potonganPromoMinuman));
        }
        
        // Tampilkan diskon jika ada
        if (!infoDiskon.equals("Tidak")) {
            System.out.println("\n** DISKON **");
            System.out.println("Diskon              : " + infoDiskon);
            System.out.println("Potongan Diskon     : Rp " + potonganDiskon);
        } else {
            System.out.println("\nDiskon              : " + infoDiskon);
        }
        
        System.out.println("-------------------------------------------");
        System.out.println("TOTAL PEMBAYARAN    : Rp " + totalAkhir);
        System.out.println("===========================================");
        System.out.println("\n    Terima kasih atas kunjungan Anda!");
        System.out.println("===========================================");
    }
}
