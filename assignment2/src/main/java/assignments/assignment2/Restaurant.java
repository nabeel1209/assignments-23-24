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
    public void sortMenu(){
        for (int i = 0; i<menu.size(); i++){
            for (int j = menu.size()-1; i < j; j--){
                if (menu.get(i).harga > menu.get(j).harga){
                    menu.set(i, menu.set(j, menu.get(i))); // Swap posisi saat harga berbeda
                }else if(menu.get(i).harga == menu.get(j).harga){ // Saat harga sama cek urutan alfabet
                    if(menu.get(i).namaMakanan.length() > menu.get(j).namaMakanan.length()){ 
                        for(int a = 0; a<menu.get(j).namaMakanan.length(); a++){
                            if(menu.get(i).namaMakanan.charAt(a) > menu.get(j).namaMakanan.charAt(a)){
                                menu.set(i, menu.set(j, menu.get(i))); // Swap posisi saat untuk sort berdasarkan alfabet
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
    // Membuat method showMenu untuk menampilkan menu apa saja yang dimiliki oleh restoran
    public String showMenu(){
        sortMenu(); // Sort menu sebelum melakukan show menu
        String hasil = "Menu:\n";
        for(int i = 0; i<menu.size(); i++){
            hasil += (i+1)+". "+menu.get(i).toString()+"\n";
        }
        return hasil.strip();
    }
}