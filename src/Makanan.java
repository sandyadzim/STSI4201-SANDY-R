public class Makanan extends MenuItem {
    // Atribut tambahan khusus untuk Makanan
    private String jenisMakanan;

    // Constructor
    public Makanan(String nama, double harga, String jenisMakanan) {
        super(nama, harga, "Makanan");
        this.jenisMakanan = jenisMakanan;
    }
    
    /**
     * Override method abstrak dari MenuItem
     * Implementasi polymorphism - setiap class punya tampilan berbeda
     */
    @Override
    public void tampilMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("[MAKANAN]");
        System.out.println();
        System.out.println("Nama         : " + getNama());
        System.out.println("Jenis        : " + jenisMakanan);
        System.out.println("Harga        : Rp " + String.format("%.0f", getHarga()));
        System.out.println("=".repeat(50));
        System.out.println();
    }
    
    /**
     * Ovverride method untuk konversi ke format file
     * @return String format: MAKANAN|nama|harga|jenis
     */
    @Override
    public String toFileString() {
        return "MAKANAN|" + getNama() + "|" + getHarga() + "|" + jenisMakanan;
    }
}
