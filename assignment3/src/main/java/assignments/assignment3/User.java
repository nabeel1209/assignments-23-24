package assignments.assignment3;

import java.util.ArrayList;
import assignments.assignment2.*;
import assignments.assignment3.payment.DepeFoodPaymentSystem;;


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
    private DepeFoodPaymentSystem payment;
    private long saldo;

    public User(String nama, String nomorTelepon, String email, String lokasi, String role, DepeFoodPaymentSystem payment, long saldo){
        // TODO: buat constructor untuk class ini
        this.nama = nama;
        this.nomorTelepon = nomorTelepon;
        this.email = email;
        this.lokasi = lokasi;
        this.role = role;
        this.payment = payment;
        this.saldo = saldo;
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
    public long getSaldo(){
        return this.saldo;
    }
    public void setSaldo(long newSaldo){
        this.saldo = newSaldo;
    }
    public DepeFoodPaymentSystem getPayment(){
        return this.payment;
    }
    public void setPayment(DepeFoodPaymentSystem newPayment){
        this.payment = newPayment;
    }

    public void addOrder(Order newOrder){
        this.orderHistory.add(newOrder);
    }
    
    public Order getOrder(String orderID){
        if (this.getOrderHistory().isEmpty()){ // Saat order history user kosong
            System.out.println("User "+this.getName()+" belum melakukan order!");
        }else{ 
            for (int i = 0; i<this.getOrderHistory().size(); i++){ // Mengecek orderID yang dimiliki oleh user
                if (orderID.equals(this.getOrderHistory().get(i).getOrderID())){
                    return this.getOrderHistory().get(i); // Saat orderID ditemukan
                }else if(i == this.getOrderHistory().size()-1){
                    System.out.println("Order ID tidak dapat ditemukan.\n"); // Saat orderID tidak ditemukan
                    return null;
                }
            }
        }
        return null;
    }
}
