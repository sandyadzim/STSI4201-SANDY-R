import java.util.ArrayList;
import java.io.*;

/**
 * Kelas Menu untuk mengelola semua item menu dalam restoran
 */
public class Menu {
    private ArrayList<MenuItem> daftarMenu;
    private static final String FILE_MENU = "data/menu.txt";
    
    // Constructor
    public Menu() {
        this.daftarMenu = new ArrayList<>();

        // Buat folder data jika belum ada
        File dataDir = new File("data");
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }
    }
    
    /**
     * Menambahkan item menu baru
     */
    public void tambahItem(MenuItem item) {
        daftarMenu.add(item);
        System.out.println("Item \"" + item.getNama() + "\" berhasil ditambahkan ke menu!");
    }
    
    /**
     * Menampilkan semua item menu
     * Menggunakan polymorphism - setiap item akan memanggil tampilMenu() sesuai jenisnya
     */
    public void tampilkanSemuaMenu() {
        if (daftarMenu.isEmpty()) {
            System.out.println("\n Menu masih kosong. Silakan tambah item terlebih dahulu.\n");
            return;
        }
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("         DAFTAR MENU RESTORAN");
        System.out.println("=".repeat(50));
        
        // Tampilkan Makanan
        System.out.println("\n MAKANAN:");
        boolean adaMakanan = false;
        for (MenuItem item : daftarMenu) {
            if (item instanceof Makanan) {
                item.tampilMenu(); // Polymorphism!
                adaMakanan = true;
            }
        }
        if (!adaMakanan) {
            System.out.println("(Belum ada makanan)");
        }
        
        // Tampilkan Minuman
        System.out.println("\n MINUMAN:");
        boolean adaMinuman = false;
        for (MenuItem item : daftarMenu) {
            if (item instanceof Minuman) {
                item.tampilMenu(); // Polymorphism!
                adaMinuman = true;
            }
        }
        if (!adaMinuman) {
            System.out.println("(Belum ada minuman)");
        }
        
        // Tampilkan Item Diskon
        System.out.println("\n PROMO:");
        boolean adaDiskon = false;
        for (MenuItem item : daftarMenu) {
            if (item instanceof Diskon) {
                item.tampilMenu(); // Polymorphism!
                adaDiskon = true;
            }
        }
        if (!adaDiskon) {
            System.out.println("(Tidak ada promo saat ini)");
        }
        
        System.out.println("\n" + "=".repeat(50) + "\n");
    }
    
    /**
     * Menampilkan menu dengan nomor urut (untuk memilih)
     */
    public void tampilkanMenuBerNomor() {
        if (daftarMenu.isEmpty()) {
            System.out.println("\n Menu masih kosong.\n");
            return;
        }
        
        System.out.println("\n=== DAFTAR MENU ===");
        for (int i = 0; i < daftarMenu.size(); i++) {
            MenuItem item = daftarMenu.get(i);
            System.out.println((i + 1) + ". " + item.getNama() + " - Rp " + 
                String.format("%.0f", item.getHarga()) + " ("+ item.getKategori() + ")");
        }
        System.out.println();
    }
    
    /**
     * Get item berdasarkan index
     */
    public MenuItem getItem(int index) {
        if (index < 0 || index >= daftarMenu.size()) {
            throw new IndexOutOfBoundsException("Index menu tidak valid!");
        }
        return daftarMenu.get(index);
    }
    
    /**
     * Get jumlah item dalam menu
     */
    public int getJumlahItem() {
        return daftarMenu.size();
    }
    
    /**
     * Menyimpan menu ke file
     */
    public void simpanKeFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_MENU))) {
            for (MenuItem item : daftarMenu) {
                if (item instanceof Makanan) {
                    writer.write(((Makanan) item).toFileString());
                } else if (item instanceof Minuman) {
                    writer.write(((Minuman) item).toFileString());
                } else if (item instanceof Diskon) {
                    writer.write(((Diskon) item).toFileString());
                }
                writer.newLine();
            }
            System.out.println("Menu berhasil disimpan ke file: " + FILE_MENU);
        } catch (IOException e) {
            System.out.println("Error saat menyimpan menu: " + e.getMessage());
        }
    }
    
    /**
     * Memuat menu dari file
     */
    public void muatDariFile() {
        File file = new File(FILE_MENU);
        if (!file.exists()) {
            System.out.println("File menu tidak ditemukan. Menggunakan menu kosong.");
            return;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_MENU))) {
            String line;
            int count = 0;
            daftarMenu.clear(); // Clear existing menu
            
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length < 4) continue;
                
                String tipe = parts[0];
                String nama = parts[1];
                double harga = Double.parseDouble(parts[2]);
                String jenis = parts[3];
                
                // Buat object sesuai tipe menggunakan polymorphism
                MenuItem item = null;
                switch (tipe) {
                    case "MAKANAN":
                        item = new Makanan(nama, harga, jenis);
                        break;
                    case "MINUMAN":
                        item = new Minuman(nama, harga, jenis);
                        break;
                    case "DISKON":
                        double persenDiskon = Double.parseDouble(jenis);
                        item = new Diskon(nama, harga, persenDiskon);
                        break;
                }
                
                if (item != null) {
                    daftarMenu.add(item);
                    count++;
                }
            }
            
            System.out.println("Berhasil memuat " + count + " item dari file: " + FILE_MENU);
        } catch (IOException e) {
            System.out.println("Error saat memuat menu: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error format data dalam file menu: " + e.getMessage());
        }
    }
    
    /**
     * Inisialisasi menu default
     */
    public void inisialisasiMenuDefault() {
        tambahItem(new Makanan("Nasi Goreng", 25000, "Nasi"));
        tambahItem(new Makanan("Mie Goreng", 20000, "Mie"));
        tambahItem(new Makanan("Ayam Geprek", 23000, "Ayam"));
        tambahItem(new Makanan("Nasi Padang", 28000, "Nasi"));
        
        tambahItem(new Minuman("Es Teh", 5000, "Dingin"));
        tambahItem(new Minuman("Es Jeruk", 7000, "Dingin"));
        tambahItem(new Minuman("Cappuccino", 15000, "Panas"));
        tambahItem(new Minuman("Jus Alpukat", 12000, "Dingin"));
        
        tambahItem(new Diskon("Paket Hemat A", 50000, 20));
        tambahItem(new Diskon("Paket Hemat B", 35000, 15));
        
        System.out.println("Menu default berhasil diinisialisasi!");
    }
}
