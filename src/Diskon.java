public class Diskon extends MenuItem {
    // Atribut tambahan khusus untuk Diskon
    private double persenDiskon; // Contoh: 10 untuk 10%
    private double hargaAsli;    // Harga sebelum diskon
    
    // Constructor
    public Diskon(String nama, double hargaAsli, double persenDiskon) {
        // Hitung harga setelah diskon
        super(nama, hargaAsli - (hargaAsli * persenDiskon / 100), "Diskon");
        this.hargaAsli = hargaAsli;
        this.persenDiskon = persenDiskon;
    }
    
    public double getPersenDiskon() {
        return persenDiskon;
    }

    public double getHargaAsli() {
        return hargaAsli;
    }
    
    public double getNilaiDiskon() {
        return hargaAsli - getHarga();
    }
    
    /**
     * Override method abstrak dari MenuItem
     * Implementasi polymorphism - setiap class punya tampilan berbeda
     */
    @Override
    public void tampilMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("[DISKON SPESIAL!]");
        System.out.println();
        System.out.println("Nama         : " + getNama());
        System.out.println("Harga Asli   : Rp " + String.format("%.0f", hargaAsli));
        System.out.println("Diskon       : " + String.format("%.0f", persenDiskon) + "%");
        System.out.println("Potongan     : Rp " + String.format("%.0f", getNilaiDiskon()));
        System.out.println("Harga Akhir  : Rp " + String.format("%.0f", getHarga()));
        System.out.println("=".repeat(50));
        System.out.println();
    }
    
    /**
     * Override method untuk konversi ke format file
     * @return String format: DISKON|nama|hargaAsli|persenDiskon
     */
    @Override
    public String toFileString() {
        return "DISKON|" + getNama() + "|" + hargaAsli + "|" + persenDiskon;
    }
}
