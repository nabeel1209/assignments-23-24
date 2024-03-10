package assignments.assignment2;
import java.util.ArrayList;

public class User {
     // TODO: tambahkan attributes yang diperlukan untuk class ini
    private String nama;
    private String nomorTelepon;
    private String email;
    private String lokasi;
    public String role;
    private ArrayList<Order> orderHistory = new ArrayList<Order>();

    public User(String nama, String nomorTelepon, String email, String lokasi, String role){
        // TODO: buat constructor untuk class ini
        this.nama = nama;
        this.nomorTelepon = nomorTelepon;
        this.email = email;
        this.lokasi = lokasi;
        this.role = role;
    }

    // TODO: tambahkan methods yang diperlukan untuk class ini
    public String getName(){
        return this.nama;
    }
    public String getNoTelepon(){
        return this.nomorTelepon;
    }
    public String getLokasi(){
        return this.lokasi;
    }
    public String getEmail(){
        return this.email;
    }
    public ArrayList<Order> getOrderHistory(){
        return this.orderHistory;
    }
    public void addOrder(Order newOrder){
        this.orderHistory.add(newOrder);
    }

}