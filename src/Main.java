import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Menu menu = new Menu();
    private static Pesanan pesananAktif = null;
    
    public static void main(String[] args) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("  SELAMAT DATANG DI RESTORAN SANDY   ");
        System.out.println("=".repeat(50));
        System.out.println();
        
        // Inisialisasi: Coba muat dari file, kalau tidak ada gunakan default
        System.out.println("🔄 Memuat data menu...");
        menu.muatDariFile();
        
        if (menu.getJumlahItem() == 0) {
            System.out.println("\n Menu kosong. Inisialisasi menu default...");
            menu.inisialisasiMenuDefault();
        }
        
        // Main menu loop
        boolean running = true;
        while (running) {
            tampilkanMenuUtama();
            String pilihan = getValidInput(6);
            
            try {
                switch (pilihan) {
                    case "1":
                        tambahItemKeMenu();
                        break;
                    case "2":
                        menu.tampilkanSemuaMenu();
                        break;
                    case "3":
                        terimaPesanan();
                        break;
                    case "4":
                        hitungTotalPesanan();
                        break;
                    case "5":
                        tampilkanDanSimpanStruk();
                        break;
                    case "6":
                        running = keluar();
                        break;
                }
            } catch (Exception e) {
                System.out.println("\n ERROR: " + e.getMessage() + "\n");
            }
        }
    }
    
    /**
     * Menampilkan menu utama aplikasi
     */
    private static void tampilkanMenuUtama() {
        System.out.println("\n=== MENU UTAMA ===");
        System.out.println("────────────────────────────────────────");
        System.out.println("1. Tambah Item ke Menu                 ");
        System.out.println("2. Tampilkan Menu Restoran             ");
        System.out.println("3. Terima Pesanan Pelanggan            ");
        System.out.println("4. Hitung Total Biaya Pesanan          ");
        System.out.println("5. Tampilkan & Simpan Struk            ");
        System.out.println("6. Keluar                              ");
        System.out.print("\nPilih menu [1-6]: ");
    }
    
    /**
     * Menu 1: Tambah item baru ke menu
     * Menggunakan polymorphism - bisa tambah Makanan, Minuman, atau Diskon
     */
    private static void tambahItemKeMenu() {
        System.out.println("\n=== TAMBAH ITEM KE MENU ===");
        System.out.println("Jenis item:");
        System.out.println("1. Makanan");
        System.out.println("2. Minuman");
        System.out.println("3. Item Diskon");
        System.out.print("\nPilih jenis [1-3]: ");
        
        String jenis = getValidInput(3);
        
        System.out.print("Nama item: ");
        String nama = scanner.nextLine().trim();
        
        if (nama.isEmpty()) {
            System.out.println("Nama tidak boleh kosong!");
            return;
        }
        
        try {
            switch (jenis) {
                case "1": // Makanan
                    System.out.print("Harga: Rp ");
                    double hargaMakanan = Double.parseDouble(scanner.nextLine());
                    
                    System.out.print("Jenis makanan: ");
                    String jenisMakanan = scanner.nextLine().trim();
                    
                    Makanan makanan = new Makanan(nama, hargaMakanan, jenisMakanan);
                    menu.tambahItem(makanan);
                    break;
                    
                case "2": // Minuman
                    System.out.print("Harga: Rp ");
                    double hargaMinuman = Double.parseDouble(scanner.nextLine());
                    
                    System.out.print("Jenis minuman: ");
                    String jenisMinuman = scanner.nextLine().trim();
                    
                    Minuman minuman = new Minuman(nama, hargaMinuman, jenisMinuman);
                    menu.tambahItem(minuman);
                    break;
                    
                case "3": // Diskon
                    System.out.print("Harga asli: Rp ");
                    double hargaAsli = Double.parseDouble(scanner.nextLine());
                    
                    System.out.print("Persentase diskon (%): ");
                    double persenDiskon = Double.parseDouble(scanner.nextLine());
                    
                    if (persenDiskon < 0 || persenDiskon > 100) {
                        throw new IllegalArgumentException("Persentase diskon harus antara 0-100!");
                    }
                    
                    Diskon diskon = new Diskon(nama, hargaAsli, persenDiskon);
                    menu.tambahItem(diskon);
                    break;
            }
            
            // Simpan ke file setelah menambah
            menu.simpanKeFile();
            
        } catch (NumberFormatException e) {
            System.out.println("Input harga/diskon tidak valid!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Menu 3: Terima pesanan dari pelanggan
     */
    private static void terimaPesanan() {
        // Cek apakah menu kosong
        if (menu.getJumlahItem() == 0) {
            System.out.println("\n Menu masih kosong! Tambahkan item terlebih dahulu.\n");
            return;
        }
        
        // Buat pesanan baru jika belum ada atau yang lama sudah selesai
        if (pesananAktif == null || pesananAktif.isEmpty()) {
            System.out.print("\nNama pelanggan: ");
            String namaPelanggan = scanner.nextLine().trim();
            
            if (namaPelanggan.isEmpty()) {
                namaPelanggan = "Tamu";
            }
            
            pesananAktif = new Pesanan(namaPelanggan);
            System.out.println("Pesanan baru dibuat untuk: " + namaPelanggan);
        }
        
        System.out.println("\n=== PROSES PEMESANAN ===");
        
        boolean lanjutPesan = true;
        while (lanjutPesan) {
            // Tampilkan menu dengan nomor
            menu.tampilkanMenuBerNomor();
            
            System.out.print("Pilih nomor menu (0 untuk selesai): ");
            String input = scanner.nextLine().trim();
            
            if (input.equals("0")) {
                lanjutPesan = false;
                continue;
            }
            
            try {
                int nomorMenu = Integer.parseInt(input);
                
                MenuItem item = menu.getItem(nomorMenu - 1);
                
                System.out.print("Jumlah pesanan: ");
                int jumlah = Integer.parseInt(scanner.nextLine());
                
                pesananAktif.tambahItem(item, jumlah);
                
                System.out.println("\nLanjut pesan? (y/n): ");
                String lanjut = scanner.nextLine().trim().toLowerCase();
                if (!lanjut.equals("y")) {
                    lanjutPesan = false;
                }
                
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid! Masukkan angka.");
            }
        }
        
        System.out.println("\n Pesanan berhasil dicatat!");
    }
    
    /**
     * Menu 4: Hitung total biaya pesanan
     */
    private static void hitungTotalPesanan() {
        if (pesananAktif == null || pesananAktif.isEmpty()) {
            System.out.println("\n Belum ada pesanan!\n");
            return;
        }
        
        double total = pesananAktif.hitungTotal();
        double totalDiskon = pesananAktif.hitungTotalDiskon();
        
        System.out.println("\n┌────────────────────────────────────────┐");
        System.out.println("│         RINGKASAN PESANAN              │");
        System.out.println("├────────────────────────────────────────┤");
        System.out.printf("│ Jumlah Item  : %-23d │\n", pesananAktif.getJumlahItem());
        
        if (totalDiskon > 0) {
            System.out.printf("│ Total Diskon : Rp %-20.0f │\n", totalDiskon);
        }
        
        System.out.printf("│ TOTAL BIAYA  : Rp %-20.0f │\n", total);
        System.out.println("└────────────────────────────────────────┘\n");
    }
    
    /**
     * Menu 5: Tampilkan dan simpan struk
     * Mengimplementasikan File I/O untuk menyimpan struk
     */
    private static void tampilkanDanSimpanStruk() {
        if (pesananAktif == null || pesananAktif.isEmpty()) {
            System.out.println("\n Belum ada pesanan!\n");
            return;
        }
        
        // Tampilkan struk di layar
        pesananAktif.tampilkanStruk();
        
        // Simpan ke file
        pesananAktif.simpanStrukKeFile();
        
        // Auto-reset pesanan setelah struk disimpan
        pesananAktif = null;
        System.out.println("Transaksi selesai. Siap menerima pesanan baru!\n");
    }
    
    /**
     * Menu 6: Keluar dari aplikasi
     */
    private static boolean keluar() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println(" Terima kasih telah menggunakan aplikasi  ");
        System.out.println("         Sampai jumpa kembali!            ");
        System.out.println("=".repeat(50));
        
        scanner.close();
        return false;
    }
    
    /**
     * Utility method untuk validasi input angka
     */
    private static String getValidInput(int max) {
        String input;
        while (true) {
            input = scanner.nextLine().trim();
            
            // Validasi input harus 1 sampai max
            for (int i = 1; i <= max; i++) {
                if (input.equals(String.valueOf(i))) {
                    return input;
                }
            }
            
            System.out.println("Input tidak valid! Pilih angka 1-" + max);
        }
    }
}
