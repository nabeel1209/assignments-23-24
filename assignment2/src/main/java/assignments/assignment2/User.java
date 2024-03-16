package assignments.assignment2;
<<<<<<< HEAD
import java.util.ArrayList;
=======
>>>>>>> 36b55a62b2b95bb51b1bbee92e9e492f05e50a61

public class User {
     // TODO: tambahkan attributes yang diperlukan untuk class ini
     // Menggunakan private modifier karena data dari objek user adalah data yang bersifat pribadi 
     // kecuali data yang tidak bersifat pribadi seperti role dari objek user
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
    // Karena Program hanya menggunakan user sebagai objek yang memvalidasi login 
    // maka user hanya membutuhkan getter tanpa setter kecuali order history
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
