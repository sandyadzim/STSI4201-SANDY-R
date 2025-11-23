import java.util.ArrayList;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Kelas Pesanan untuk mencatat pesanan pelanggan
 */
public class Pesanan {
    private ArrayList<MenuItem> itemPesanan;
    private ArrayList<Integer> jumlahPesanan;
    private String namaPelanggan;
    private LocalDateTime waktuPesanan;
    private static final String DIR_STRUK = "data/struk/";
    
    // Constructor
    public Pesanan(String namaPelanggan) {
        this.namaPelanggan = namaPelanggan;
        this.itemPesanan = new ArrayList<>();
        this.jumlahPesanan = new ArrayList<>();
        this.waktuPesanan = LocalDateTime.now();
        
        // Buat folder struk jika belum ada
        File strukDir = new File(DIR_STRUK);
        if (!strukDir.exists()) {
            strukDir.mkdirs();
        }
    }
    
    /**
     * Menambahkan item ke pesanan
     */
    public void tambahItem(MenuItem item, int jumlah) {
        if (jumlah <= 0) {
            throw new IllegalArgumentException("Jumlah pesanan harus lebih dari 0!");
        }
        
        // Cek apakah item sudah ada dalam pesanan
        for (int i = 0; i < itemPesanan.size(); i++) {
            if (itemPesanan.get(i).getNama().equals(item.getNama())) {
                // Update jumlah jika item sudah ada
                jumlahPesanan.set(i, jumlahPesanan.get(i) + jumlah);
                System.out.println("Jumlah \"" + item.getNama() + "\" diupdate menjadi " + jumlahPesanan.get(i));
                return;
            }
        }
        
        // Tambah item baru
        itemPesanan.add(item);
        jumlahPesanan.add(jumlah);
        System.out.println("\"" + item.getNama() + "\" x" + jumlah + " ditambahkan ke pesanan!");
    }
    
    /**
     * Menghitung total biaya pesanan
     */
    public double hitungTotal() {
        double total = 0;
        for (int i = 0; i < itemPesanan.size(); i++) {
            MenuItem item = itemPesanan.get(i);
            int jumlah = jumlahPesanan.get(i);
            
            total += item.getHarga() * jumlah;
        }
        return total;
    }
    
    /**
     * Menghitung total diskon
     */
    public double hitungTotalDiskon() {
        double totalDiskon = 0;
        for (int i = 0; i < itemPesanan.size(); i++) {
            MenuItem item = itemPesanan.get(i);
            int jumlah = jumlahPesanan.get(i);
            
            // Hanya hitung untuk item bertipe Diskon
            if (item instanceof Diskon) {
                Diskon itemDiskon = (Diskon) item;
                totalDiskon += itemDiskon.getNilaiDiskon() * jumlah;
            }
        }
        return totalDiskon;
    }
    
    /**
     * Menampilkan struk pesanan
     */
    public void tampilkanStruk() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("              STRUK PEMBAYARAN");
        System.out.println("=".repeat(50));
        System.out.println("Pelanggan : " + namaPelanggan);
        System.out.println("Waktu     : " + waktuPesanan.format(formatter));
        System.out.println("=".repeat(50));
        System.out.println("Item Pesanan:");
        System.out.println("-".repeat(50));
        
        double subtotal = 0;
        for (int i = 0; i < itemPesanan.size(); i++) {
            MenuItem item = itemPesanan.get(i);
            int jumlah = jumlahPesanan.get(i);
            double hargaItem = item.getHarga();
            double totalItem = hargaItem * jumlah;
            
            System.out.printf("%d. %-25s x%-3d\n", (i + 1), item.getNama(), jumlah);
            
            // Tampilkan info diskon jika ada
            if (item instanceof Diskon) {
                Diskon itemDiskon = (Diskon) item;
                double nilaiDiskonPerItem = itemDiskon.getNilaiDiskon();
                double totalDiskonItem = nilaiDiskonPerItem * jumlah;
                System.out.printf("   Harga Asli : Rp %,.0f\n", itemDiskon.getHargaAsli());
                System.out.printf("   Diskon %.0f%% : -Rp %,.0f\n", 
                    itemDiskon.getPersenDiskon(), totalDiskonItem);
                System.out.printf("   Harga Akhir: Rp %,.0f\n", hargaItem);
            } else {
                System.out.printf("   @ Rp %,.0f\n", hargaItem);
            }
            
            System.out.printf("  Subtotal   : Rp %,.0f\n", totalItem);
            System.out.println();
            
            subtotal += totalItem;
        }
        
        System.out.println("-".repeat(50));
        
        // Tampilkan summary
        double totalDiskon = hitungTotalDiskon();
        if (totalDiskon > 0) {
            System.out.printf("Total Hemat Diskon : Rp %,.0f\n", totalDiskon);
            System.out.println("-".repeat(50));
        }
        
        System.out.printf("TOTAL PEMBAYARAN   : Rp %,.0f\n", subtotal);
        System.out.println("=".repeat(50));
        System.out.println("     Terima kasih atas kunjungan Anda!");
        System.out.println("=".repeat(50) + "\n");
    }
    
    /**
     * Menyimpan struk ke file
     * Mengimplementasikan File I/O
     */
    public void simpanStrukKeFile() {
        // Generate filename dengan timestamp
        DateTimeFormatter fileFormatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String filename = DIR_STRUK + "struk_" + 
                         namaPelanggan.replaceAll("\\s+", "_") + "_" + 
                         waktuPesanan.format(fileFormatter) + ".txt";
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            
            writer.write("=".repeat(50));
            writer.newLine();
            writer.write("              STRUK PEMBAYARAN");
            writer.newLine();
            writer.write("=".repeat(50));
            writer.newLine();
            writer.write("Pelanggan : " + namaPelanggan);
            writer.newLine();
            writer.write("Waktu     : " + waktuPesanan.format(formatter));
            writer.newLine();
            writer.write("=".repeat(50));
            writer.newLine();
            writer.write("Item Pesanan:");
            writer.newLine();
            writer.write("-".repeat(50));
            writer.newLine();
            
            double subtotal = 0;
            for (int i = 0; i < itemPesanan.size(); i++) {
                MenuItem item = itemPesanan.get(i);
                int jumlah = jumlahPesanan.get(i);
                double hargaItem = item.getHarga();
                double totalItem = hargaItem * jumlah;
                
                writer.write(String.format("%d. %-25s x%-3d%n", (i + 1), item.getNama(), jumlah));
                
                if (item instanceof Diskon) {
                    Diskon itemDiskon = (Diskon) item;
                    double nilaiDiskonPerItem = itemDiskon.getNilaiDiskon();
                    double totalDiskonItem = nilaiDiskonPerItem * jumlah;
                    writer.write(String.format("   Harga Asli : Rp %,.0f%n", itemDiskon.getHargaAsli()));
                    writer.write(String.format("   Diskon %.0f%% : -Rp %,.0f%n", 
                        itemDiskon.getPersenDiskon(), totalDiskonItem));
                    writer.write(String.format("   Harga Akhir: Rp %,.0f%n", hargaItem));
                } else {
                    writer.write(String.format("   @ Rp %,.0f%n", hargaItem));
                }
                
                writer.write(String.format("  Subtotal   : Rp %,.0f%n", totalItem));
                writer.newLine();
                
                subtotal += totalItem;
            }
            
            writer.write("-".repeat(50));
            writer.newLine();
            
            double totalDiskon = hitungTotalDiskon();
            if (totalDiskon > 0) {
                writer.write(String.format("Total Hemat Diskon : Rp %,.0f%n", totalDiskon));
                writer.write("-".repeat(50));
                writer.newLine();
            }
            
            writer.write(String.format("TOTAL PEMBAYARAN   : Rp %,.0f%n", subtotal));
            writer.write("=".repeat(50));
            writer.newLine();
            writer.write("     Terima kasih atas kunjungan Anda!");
            writer.newLine();
            writer.write("=".repeat(50));
            
            System.out.println("Struk berhasil disimpan ke: " + filename);
        } catch (IOException e) {
            System.out.println("Error saat menyimpan struk: " + e.getMessage());
        }
    }
    
    /**
     * Cek apakah pesanan kosong
     */
    public boolean isEmpty() {
        return itemPesanan.isEmpty();
    }
    
    /**
     * Get jumlah item dalam pesanan
     */
    public int getJumlahItem() {
        return itemPesanan.size();
    }
    
    /**
     * Reset pesanan (kosongkan semua item)
     */
    public void reset() {
        itemPesanan.clear();
        jumlahPesanan.clear();
        waktuPesanan = LocalDateTime.now();
        System.out.println("Pesanan direset!");
    }
}
