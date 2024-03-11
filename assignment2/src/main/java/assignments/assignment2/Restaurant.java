package assignments.assignment2;
import java.util.ArrayList;

public class Restaurant {
     // TODO: tambahkan attributes yang diperlukan untuk class ini
     // Menggunakan modifier public karena data restoran dapat dilihat oleh semua orang
    public String namaRestoran;
    public ArrayList<Menu> menu = new ArrayList<Menu>();
    
    // TODO: buat constructor untuk class ini
    public Restaurant(){}
    public Restaurant(String nama){
        this.namaRestoran = nama;
    }

    // TODO: tambahkan methods yang diperlukan untuk class ini
    // Membuat method addMenu untuk menambahkan menu baru pada restoran
    public void addMenu(Menu newMenu){
        this.menu.add(newMenu);
    }
    // Membuat method showMenu untuk menampilkan menu apa saja yang dimiliki oleh restoran
    public String showMenu(){
        String hasil = "Menu:\n";
        for(int i = 0; i<menu.size(); i++){
            hasil += (i+1)+". "+menu.get(i).toString()+"\n";
        }
        return hasil;
    }
}