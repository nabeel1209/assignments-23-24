package assignments.assignment2;

public class Menu {
     // TODO: tambahkan attributes yang diperlukan untuk class ini
     // Menggunakan modifier public karena nama serta harga menu dapat diakses oleh semua objek dalam program
    public String namaMakanan;
    public double harga;
    
    // TODO: buat constructor untuk class ini
    public Menu(String namaMakanan, double harga){
        this.namaMakanan = namaMakanan;
        this.harga = harga;
    }

    // TODO: tambahkan methods yang diperlukan untuk class ini
    // Override method toString dari class Object untuk menampilkan menu
    @Override
    public String toString(){
        return namaMakanan+" "+String.format("%.0f",harga);
    }
}
