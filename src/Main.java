import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    // ArrayList untuk menyimpan data menu restoran (dinamis)
    private static ArrayList<Menu> daftarMenu = new ArrayList<>();
    
    // ArrayList untuk menyimpan pesanan (tidak terbatas)
    private static ArrayList<String> namaPesanan = new ArrayList<>();
    private static ArrayList<Integer> jumlahPesanan = new ArrayList<>();
    private static ArrayList<Integer> hargaPesanan = new ArrayList<>();
    
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
        
        // Menu utama aplikasi
        boolean running = true;
        while (running) {
            tampilkanMenuUtama();
            String pilihan = getValidInput(scanner, "1", "2", "3");
            
            switch (pilihan) {
                case "1":
                    // Menu Pelanggan - Pemesanan
                    menuPelanggan(scanner);
                    break;
                case "2":
                    // Menu Pemilik - Manajemen Menu
                    menuPemilik(scanner);
                    break;
                case "3":
                    // Keluar
                    System.out.println("\nTerima kasih telah menggunakan aplikasi restoran!");
                    running = false;
                    break;
            }
        }
        
        scanner.close();
    }
    
    // Method untuk menginisialisasi data menu
    private static void initializeMenu() {
        // Menu Makanan (4 item)
        daftarMenu.add(new Menu("Nasi Padang", 25000, "Makanan"));
        daftarMenu.add(new Menu("Mie Goreng", 20000, "Makanan"));
        daftarMenu.add(new Menu("Nasi Goreng", 22000, "Makanan"));
        daftarMenu.add(new Menu("Ayam Geprek", 23000, "Makanan"));
        
        // Menu Minuman (4 item)
        daftarMenu.add(new Menu("Es Teh", 5000, "Minuman"));
        daftarMenu.add(new Menu("Es Jeruk", 7000, "Minuman"));
        daftarMenu.add(new Menu("Jus Alpukat", 12000, "Minuman"));
        daftarMenu.add(new Menu("Cappuccino", 15000, "Minuman"));
    }
    
    // Method untuk menampilkan menu utama aplikasi
    private static void tampilkanMenuUtama() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("       SELAMAT DATANG DI RESTORAN SANDY");
        System.out.println("=".repeat(50));
        System.out.println("\n=== MENU UTAMA ===");
        System.out.println("1. Menu Pelanggan (Pemesanan)");
        System.out.println("2. Menu Pemilik (Manajemen Menu)");
        System.out.println("3. Keluar");
        System.out.print("\nPilih menu (1-3): ");
    }
    
    // Method untuk validasi input dengan pilihan tertentu
    private static String getValidInput(Scanner scanner, String... validOptions) {
        while (true) {
            String input = scanner.nextLine().trim();
            for (String option : validOptions) {
                if (input.equals(option)) {
                    return input;
                }
            }
            System.out.print("Input tidak valid! Silakan pilih (" + String.join("/", validOptions) + "): ");
        }
    }
    
    // Method untuk menu pelanggan
    private static void menuPelanggan(Scanner scanner) {
        // Reset pesanan
        namaPesanan.clear();
        jumlahPesanan.clear();
        hargaPesanan.clear();
        
        // Tampilkan menu restoran
        tampilkanMenu();
        
        // Proses pemesanan
        prosesTempatPesanan(scanner);
        
        // Hitung dan tampilkan struk
        if (!namaPesanan.isEmpty()) {
            hitungTotalBiaya();
        } else {
            System.out.println("\nTidak ada pesanan yang dibuat.");
        }
    }
    
    // Method untuk menu pemilik
    private static void menuPemilik(Scanner scanner) {
        boolean kembali = false;
        while (!kembali) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("       MENU MANAJEMEN RESTORAN");
            System.out.println("=".repeat(50));
            System.out.println("\n=== MENU PEMILIK ===");
            System.out.println("1. Lihat Daftar Menu");
            System.out.println("2. Tambah Menu Baru");
            System.out.println("3. Ubah Harga Menu");
            System.out.println("4. Hapus Menu");
            System.out.println("5. Kembali ke Menu Utama");
            System.out.print("\nPilih menu (1-5): ");
            
            String pilihan = getValidInput(scanner, "1", "2", "3", "4", "5");
            
            switch (pilihan) {
                case "1":
                    tampilkanMenu();
                    break;
                case "2":
                    tambahMenu(scanner);
                    break;
                case "3":
                    ubahHargaMenu(scanner);
                    break;
                case "4":
                    hapusMenu(scanner);
                    break;
                case "5":
                    kembali = true;
                    break;
            }
        }
    }
    
    // Method untuk menambah menu baru
    private static void tambahMenu(Scanner scanner) {
        System.out.println("\n=== TAMBAH MENU BARU ===");
        System.out.print("Berapa menu yang ingin ditambahkan? ");
        
        int jumlah = 0;
        while (true) {
            try {
                jumlah = Integer.parseInt(scanner.nextLine().trim());
                if (jumlah > 0) break;
                System.out.print("Jumlah harus lebih dari 0! Masukkan kembali: ");
            } catch (NumberFormatException e) {
                System.out.print("Input tidak valid! Masukkan angka: ");
            }
        }
        
        for (int i = 0; i < jumlah; i++) {
            System.out.println("\n--- Menu ke-" + (i + 1) + " ---");
            
            System.out.print("Nama menu: ");
            String nama = scanner.nextLine().trim();
            
            int harga = 0;
            while (true) {
                try {
                    System.out.print("Harga menu: Rp ");
                    harga = Integer.parseInt(scanner.nextLine().trim());
                    if (harga > 0) break;
                    System.out.println("Harga harus lebih dari 0!");
                } catch (NumberFormatException e) {
                    System.out.println("Input tidak valid! Masukkan angka.");
                }
            }
            
            System.out.print("Kategori (Makanan/Minuman): ");
            String kategori = getValidInput(scanner, "Makanan", "Minuman", "makanan", "minuman");
            kategori = kategori.substring(0, 1).toUpperCase() + kategori.substring(1).toLowerCase();
            
            daftarMenu.add(new Menu(nama, harga, kategori));
            System.out.println("✓ Menu \"" + nama + "\" berhasil ditambahkan!");
        }
        
        System.out.println("\n✓ Total " + jumlah + " menu berhasil ditambahkan!");
    }
    
    // Method untuk mengubah harga menu
    private static void ubahHargaMenu(Scanner scanner) {
        if (daftarMenu.isEmpty()) {
            System.out.println("\nBelum ada menu yang tersedia!");
            return;
        }
        
        System.out.println("\n=== UBAH HARGA MENU ===");
        tampilkanSemuaMenuBerNomor();
        
        System.out.print("\nMasukkan nomor menu yang ingin diubah harganya: ");
        int nomor = 0;
        while (true) {
            try {
                nomor = Integer.parseInt(scanner.nextLine().trim());
                if (nomor > 0 && nomor <= daftarMenu.size()) break;
                System.out.print("Nomor tidak valid! Masukkan nomor (1-" + daftarMenu.size() + "): ");
            } catch (NumberFormatException e) {
                System.out.print("Input tidak valid! Masukkan angka: ");
            }
        }
        
        Menu menu = daftarMenu.get(nomor - 1);
        System.out.println("\nMenu yang dipilih: " + menu.getNama() + " (Harga saat ini: Rp " + menu.getHarga() + ")");
        
        System.out.print("Masukkan harga baru: Rp ");
        int hargaBaru = 0;
        while (true) {
            try {
                hargaBaru = Integer.parseInt(scanner.nextLine().trim());
                if (hargaBaru > 0) break;
                System.out.print("Harga harus lebih dari 0! Masukkan kembali: Rp ");
            } catch (NumberFormatException e) {
                System.out.print("Input tidak valid! Masukkan angka: Rp ");
            }
        }
        
        System.out.print("\nYakin ingin mengubah harga \"" + menu.getNama() + "\" dari Rp " + menu.getHarga() + " menjadi Rp " + hargaBaru + "? (Ya/Tidak): ");
        String konfirmasi = getValidInput(scanner, "Ya", "Tidak", "ya", "tidak");
        
        if (konfirmasi.equalsIgnoreCase("Ya")) {
            menu.setHarga(hargaBaru);
            System.out.println("✓ Harga menu \"" + menu.getNama() + "\" berhasil diubah menjadi Rp " + hargaBaru + "!");
        } else {
            System.out.println("✗ Perubahan harga dibatalkan.");
        }
    }
    
    // Method untuk menghapus menu
    private static void hapusMenu(Scanner scanner) {
        if (daftarMenu.isEmpty()) {
            System.out.println("\nBelum ada menu yang tersedia!");
            return;
        }
        
        System.out.println("\n=== HAPUS MENU ===");
        tampilkanSemuaMenuBerNomor();
        
        System.out.print("\nMasukkan nomor menu yang ingin dihapus: ");
        int nomor = 0;
        while (true) {
            try {
                nomor = Integer.parseInt(scanner.nextLine().trim());
                if (nomor > 0 && nomor <= daftarMenu.size()) break;
                System.out.print("Nomor tidak valid! Masukkan nomor (1-" + daftarMenu.size() + "): ");
            } catch (NumberFormatException e) {
                System.out.print("Input tidak valid! Masukkan angka: ");
            }
        }
        
        Menu menu = daftarMenu.get(nomor - 1);
        System.out.print("\nYakin ingin menghapus menu \"" + menu.getNama() + "\" (Rp " + menu.getHarga() + ")? (Ya/Tidak): ");
        String konfirmasi = getValidInput(scanner, "Ya", "Tidak", "ya", "tidak");
        
        if (konfirmasi.equalsIgnoreCase("Ya")) {
            String namaMenu = menu.getNama();
            daftarMenu.remove(nomor - 1);
            System.out.println("✓ Menu \"" + namaMenu + "\" berhasil dihapus!");
        } else {
            System.out.println("✗ Penghapusan menu dibatalkan.");
        }
    }
    
    // Method untuk menampilkan semua menu dengan nomor
    private static void tampilkanSemuaMenuBerNomor() {
        System.out.println("\nDaftar Menu:");
        System.out.println("-".repeat(50));
        for (int i = 0; i < daftarMenu.size(); i++) {
            Menu menu = daftarMenu.get(i);
            System.out.println((i + 1) + ". " + menu.getNama() + " - Rp " + menu.getHarga() + " [" + menu.getKategori() + "]");
        }
        System.out.println("-".repeat(50));
    }
    
    // Method untuk menampilkan menu berdasarkan kategori
    private static void tampilkanMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("           DAFTAR MENU RESTORAN");
        System.out.println("=".repeat(50));
        
        // Tampilkan menu Makanan
        System.out.println("\n==== MENU MAKANAN ====");
        int nomor = 1;
        for (Menu menu : daftarMenu) {
            if (menu.getKategori().equals("Makanan")) {
                System.out.println(nomor + ". " + menu.getNama() + " - Rp " + menu.getHarga());
                nomor++;
            }
        }
        
        // Tampilkan menu Minuman
        System.out.println("\n==== MENU MINUMAN ====");
        nomor = 1;
        for (Menu menu : daftarMenu) {
            if (menu.getKategori().equals("Minuman")) {
                System.out.println(nomor + ". " + menu.getNama() + " - Rp " + menu.getHarga());
                nomor++;
            }
        }
        
        System.out.println("\n" + "=".repeat(50));
    }
    
    // Method untuk memproses pemesanan (tidak terbatas)
    private static void prosesTempatPesanan(Scanner scanner) {
        System.out.println("\n=== PEMESANAN ===");
        System.out.println("Format: Nama Menu = Jumlah");
        System.out.println("Contoh: Nasi Padang = 2");
        System.out.println("Ketik 'selesai' untuk mengakhiri pesanan\n");
        
        while (true) {
            System.out.print("Pesanan: ");
            String input = scanner.nextLine().trim();
            
            if (input.equalsIgnoreCase("selesai")) {
                break;
            }
            
            // Validasi format input
            if (!input.contains("=")) {
                System.out.println("✗ Format salah! Gunakan format: Nama Menu = Jumlah");
                continue;
            }
            
            prosesInputPesanan(input);
        }
        
        if (!namaPesanan.isEmpty()) {
            System.out.println("\n✓ Total " + namaPesanan.size() + " item berhasil dipesan.");
        }
    }
    
    // Method untuk memproses input pesanan individual
    private static void prosesInputPesanan(String input) {
        String[] parts = input.split("=");
        if (parts.length == 2) {
            String namaMenu = parts[0].trim();
            try {
                int jumlah = Integer.parseInt(parts[1].trim());
                
                if (jumlah <= 0) {
                    System.out.println("✗ Jumlah harus lebih dari 0!");
                    return;
                }
                
                // Cari menu di daftar menu
                Menu menuDipesan = cariMenu(namaMenu);
                if (menuDipesan != null) {
                    namaPesanan.add(menuDipesan.getNama());
                    jumlahPesanan.add(jumlah);
                    hargaPesanan.add(menuDipesan.getHarga());
                    System.out.println("✓ \"" + menuDipesan.getNama() + "\" x" + jumlah + " ditambahkan ke pesanan.");
                } else {
                    System.out.println("✗ Menu \"" + namaMenu + "\" tidak ditemukan! Silakan coba lagi.");
                }
            } catch (NumberFormatException e) {
                System.out.println("✗ Jumlah harus berupa angka!");
            }
        } else {
            System.out.println("✗ Format salah! Gunakan format: Nama Menu = Jumlah");
        }
    }
    
    // Method untuk mencari menu berdasarkan nama
    private static Menu cariMenu(String nama) {
        for (Menu menu : daftarMenu) {
            if (menu.getNama().equalsIgnoreCase(nama)) {
                return menu;
            }
        }
        return null;
    }
    
    // Method untuk menghitung kategori menu
    private static String getKategoriMenu(String namaMenu) {
        for (Menu menu : daftarMenu) {
            if (menu.getNama().equalsIgnoreCase(namaMenu)) {
                return menu.getKategori();
            }
        }
        return "";
    }    // Method untuk menghitung total biaya dan mencetak struk
    private static void hitungTotalBiaya() {
        // Hitung subtotal menggunakan loop
        int subtotal = 0;
        for (int i = 0; i < namaPesanan.size(); i++) {
            subtotal += hargaPesanan.get(i) * jumlahPesanan.get(i);
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
    
    // Method untuk menghitung promo minuman (beli 1 gratis 1) dengan loop
    private static int hitungPromoMinuman() {
        int potongan = 0;
        
        // Loop untuk cek setiap pesanan
        for (int i = 0; i < namaPesanan.size(); i++) {
            String kategori = getKategoriMenu(namaPesanan.get(i));
            if (kategori.equals("Minuman")) {
                if (jumlahPesanan.get(i) >= 2) {
                    potongan = hargaPesanan.get(i);
                    return potongan; // Return saat ketemu minuman pertama dengan jumlah >= 2
                }
            }
        }
        
        return potongan;
    }
    
    // Method untuk mencetak struk pesanan dengan loop
    private static void cetakStruk(int subtotal, int biayaPajak, int totalSebelumDiskon,
                                    int potonganPromoMinuman, String infoPromoMinuman,
                                    int potonganDiskon, String infoDiskon, int totalAkhir) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("            STRUK PEMBAYARAN");
        System.out.println("=".repeat(50));
        System.out.println("Detail Pesanan:");
        System.out.println("-".repeat(50));
        
        // Tampilkan detail pesanan
        for (int i = 0; i < namaPesanan.size(); i++) {
            int totalItem = hargaPesanan.get(i) * jumlahPesanan.get(i);
            System.out.println((i + 1) + ". " + namaPesanan.get(i));
            System.out.println("   " + jumlahPesanan.get(i) + " x Rp " + hargaPesanan.get(i) + " = Rp " + totalItem);
        }
        
        System.out.println("-".repeat(50));
        System.out.println("Subtotal            : Rp " + subtotal);
        System.out.println("Pajak (10%)         : Rp " + biayaPajak);
        System.out.println("Biaya Pelayanan     : Rp " + BIAYA_PELAYANAN);
        System.out.println("-".repeat(50));
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
        
        System.out.println("-".repeat(50));
        System.out.println("TOTAL PEMBAYARAN    : Rp " + totalAkhir);
        System.out.println("=".repeat(50));
        System.out.println("\n    Terima kasih atas kunjungan Anda!");
        System.out.println("=".repeat(50));
    }
}
