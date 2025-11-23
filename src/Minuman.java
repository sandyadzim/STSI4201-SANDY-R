public class Minuman extends MenuItem {
    // Atribut tambahan khusus untuk Minuman
    private String jenisMinuman;
    
    // Constructor
    public Minuman(String nama, double harga, String jenisMinuman) {
        super(nama, harga, "Minuman");
        this.jenisMinuman = jenisMinuman;
    }
    
    /**
     * Override method abstrak dari MenuItem
     * Implementasi polymorphism - setiap class punya tampilan berbeda
     */
    @Override
    public void tampilMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("[MINUMAN]");
        System.out.println();
        System.out.println("Nama         : " + getNama());
        System.out.println("Jenis        : " + jenisMinuman);
        System.out.println("Harga        : Rp " + String.format("%.0f", getHarga()));
        System.out.println("=".repeat(50));
        System.out.println();
    }
    
    /**
     * Override method untuk konversi ke format file
     * @return String format: MINUMAN|nama|harga|jenis
     */
    @Override
    public String toFileString() {
        return "MINUMAN|" + getNama() + "|" + getHarga() + "|" + jenisMinuman;
    }
}
