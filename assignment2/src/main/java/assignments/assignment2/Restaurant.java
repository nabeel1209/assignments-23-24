package main.java.assignments.assignment2;
import java.util.ArrayList;

public class Restaurant {
     // TODO: tambahkan attributes yang diperlukan untuk class ini
    public String namaRestoran;
    public ArrayList<Menu> menu;
    
    public Restaurant(String nama){
        // TODO: buat constructor untuk class ini
        this.namaRestoran = nama;
    }

    // TODO: tambahkan methods yang diperlukan untuk class ini
    public void addMenu(Menu newMenu){
        this.menu.add(newMenu);
    }
}
