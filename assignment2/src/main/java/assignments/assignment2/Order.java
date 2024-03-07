package main.java.assignments.assignment2;
import java.util.ArrayList;

public class Order {
     // TODO: tambahkan attributes yang diperlukan untuk class ini
    private String orderID;
    private String tanggalPemesanan;
    private int biayaOngkosKirim;
    private Restaurant restaurant;
    private ArrayList<Menu> items;
    public boolean orderFinished;


    public Order(String orderId, String tanggal, int ongkir, Restaurant resto, ArrayList<Menu> items){
        // TODO: buat constructor untuk class ini
        this.orderID = orderId;
        this.tanggalPemesanan = tanggal;
        this.biayaOngkosKirim = ongkir;
        this.restaurant = resto;
        this.items = items; 
    }
    
    // TODO: tambahkan methods yang diperlukan untuk class ini
    public String getOrderID(){
        return this.orderID;
    }
    // Implementasi methods
}
