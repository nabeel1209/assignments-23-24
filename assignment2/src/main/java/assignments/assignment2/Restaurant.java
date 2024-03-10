package assignments.assignment2;
import java.util.ArrayList;

public class Restaurant {
     // TODO: tambahkan attributes yang diperlukan untuk class ini
    public String namaRestoran;
    public ArrayList<Menu> menu = new ArrayList<Menu>();
    
    // TODO: buat constructor untuk class ini
    public Restaurant(){}
    public Restaurant(String nama){
        this.namaRestoran = nama;
    }

    // TODO: tambahkan methods yang diperlukan untuk class ini
    public void addMenu(Menu newMenu){
        this.menu.add(newMenu);
    }
    public String showMenu(){
        String hasil = "Menu:\n";
        for(int i = 0; i<menu.size(); i++){
            hasil += (i+1)+". "+menu.get(i).namaMakanan+" "+String.format("%.0f",menu.get(i).harga)+"\n";
        }
        return hasil;
    }
}