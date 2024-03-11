package assignments.assignment2;
import java.util.ArrayList;

public class Order {
     // TODO: tambahkan attributes yang diperlukan untuk class ini
     // Menggunakan modifier private karena data dalam objek order hanya dapat dilihat oleh 
     // User sebagai pembuat pesanan dan Restoran sebagai penerima pesanan
    private String orderID;
    private String tanggalPemesanan;
    private int biayaOngkosKirim;
    private Restaurant restaurant;
    private ArrayList<Menu> items;
    public boolean orderFinished;
    private int totalBiaya;

    // TODO: buat constructor untuk class ini
    public Order(String orderId, String tanggal, int ongkir, Restaurant resto, ArrayList<Menu> items){
        this.orderID = orderId;
        this.tanggalPemesanan = tanggal;
        this.biayaOngkosKirim = ongkir;
        this.restaurant = resto;
        this.items = items; 
    }
    
    // TODO: tambahkan methods yang diperlukan untuk class ini
    // Membuat method setter dan getter karena data dari objek order bersifat private
    public String getOrderID(){
        return this.orderID;
    }
    public String getTanggalPemesanan(){
        return this.tanggalPemesanan;
    }
    public int getBiayaOngkosKirim(){
        return this.biayaOngkosKirim;
    }
    public Restaurant getRestaurant(){
        return this.restaurant;
    }
    public ArrayList<Menu> getItems(){
        return this.items;
    }
    public int getTotalBiaya(){
        return this.totalBiaya;
    }

    public void setOrderID(String noTelepon){
        this.orderID = this.generateOrderID(noTelepon);
    }
    public void setTanggalPemesanan(String tanggalPemesanan){
        this.tanggalPemesanan = tanggalPemesanan;
    }
    public void setBiayaOngkosKirim(int biayaOngkosKirim){
        this.biayaOngkosKirim = biayaOngkosKirim;
    }
    public void setRestaurant(Restaurant restaurant){
        this.restaurant = restaurant;
    }
    public void setItems(Menu menuBaru){
        this.items.add(menuBaru);
    }
    public void setOrderFinished(boolean orderFinished) {
        this.orderFinished = orderFinished;
    }
    public void setTotalBiaya(){
        int totalBiaya = this.biayaOngkosKirim;
        for(Menu x:items){
            totalBiaya += x.harga;
        }
        this.totalBiaya = totalBiaya;
    }

    // Menambahkan method generateOrderID pada objek order agar dapat membuat order id dari data yang dimilikinya
    public String generateOrderID(String noTelepon) {
        String hasil = "";
        hasil += this.restaurant.namaRestoran.substring(0, 4).toUpperCase(); // Indexing String namaRestoran sesuai ketentuan dan buat menjadi UPPERCASE lalu concat ke dalam String hasil
    
        hasil += this.tanggalPemesanan.replaceAll("/", ""); // Hilangkan "/"" dari String tanggalOrder dan concat ke dalam String hasil  
        
        int kodeTelp = 0;
        for (int i = 0; i < noTelepon.length(); i++){ // Looping String dari input noTelepon 
            kodeTelp += Character.getNumericValue(noTelepon.charAt(i)); // Jumlahkan numeric value dari charAt(i) pada variable kodeTelp
        }
        kodeTelp = kodeTelp%100; // Melakukan modulo 100 sesuai dengan ketentuan soal
        String strKodeTelp = Integer.toString(kodeTelp); // Ubah int kodeTelp menjadi String ke dalam variable baru sehingga mudah dalam melakukan cek jumlah digit 
        if (strKodeTelp.length()==1){ // Saat hasil modulo hanya satu digit
            strKodeTelp = "0" + strKodeTelp; // Tambahkan 0 didepan hasil modulo
        }
        hasil += strKodeTelp; // Concat strKodeTelp ke String hasil 
        int checksum1 = 0;
        int checksum2 = 0;
        for (int i = 0; i < hasil.length(); i++){ // Looping String hasil
            if (Character.isDigit(hasil.charAt(i))){  // Saat charAt(i) berupa digit
                if (i%2 == 0){ // Saat i genap
                    checksum1 += Character.getNumericValue(hasil.charAt(i)); // Jumlahkan numeric value dari charAt(i) pada checksum 1
                }else{ // Saat i ganjil
                    checksum2 += Character.getNumericValue(hasil.charAt(i)); // Jumlahkan numeric value dari charAt(i) pada checksum 2
                }
            }else{ // Saat charAt(i) bukan digit
                if (i%2 == 0){ // Saat i genap
                    checksum1 += ((int)hasil.charAt(i)-55); // Jumlahkan (ascii value dari charAt(i) - 55) pada checksum 1
                }else{ // Saat i ganjil
                    checksum2 += ((int)hasil.charAt(i)-55); // Jumlahkan (ascii value dari charAt(i) - 55) pada checksum 2
                }
            }
        }
        
        checksum1 = checksum1 % 36; // Melakukan modulo 36 sesuai ketentuan soal
        if (checksum1 >=0 && checksum1 <= 9){ // Saat hasil modulo kurang dari 10
            hasil += Integer.toString(checksum1); // Concat String dari integer
        }else{ // Saat hasil modulo lebih dari 10
            hasil += (char) (checksum1+55); // Concat char dari ascii value (checksum + 55)
        }

        checksum2 = checksum2 % 36; // Melakukan modulo 36 sesuai ketentuan soal
        if (checksum2 >=0 && checksum2 <= 9){ // Saat hasil modulo kurang dari 10
            hasil += Integer.toString(checksum2); // Concat String dari integer
        }else{ // Saat hasil modulo lebih dari 10
            hasil += (char) (checksum2+55); // Concat char dari ascii value (checksum + 55)
        }

        return hasil;    
    }
    
    // Melakukan override pada method toString yang dimiliki oleh class Object untuk menampilkan objek order
    @Override
    public String toString(){
        String hasil =  "Bill:\n"+
                        "Order ID: "+this.orderID+"\n"+
                        "Tanggal Pemesanan: "+this.tanggalPemesanan+"\n"+
                        "Restaurant: "+this.restaurant.namaRestoran+"\n";
        switch(this.biayaOngkosKirim){
            case 10000: 
                hasil +=  "Lokasi Pengiriman: P\n";
                break;
            case 20000: 
                hasil +=  "Lokasi Pengiriman: U\n";
                break;
            case 35000: 
                hasil +=  "Lokasi Pengiriman: T\n";
                break;
            case 40000: 
                hasil +=  "Lokasi Pengiriman: S\n";
                break;
            case 60000: 
                hasil +=  "Lokasi Pengiriman: B\n";
                break;
        }
        if(orderFinished){
            hasil += "Status Pengiriman: Finished\n";
        }else{
            hasil += "Status Pengiriman: Not Finished\n";
        }
        hasil += "Pesanan:\n";
        for(Menu x :items){
            hasil += "- "+x.namaMakanan+String.format(" %.0f",x.harga)+"\n";
        }
        hasil +=    "Biaya Ongkos Kirim: Rp "+this.biayaOngkosKirim+"\n"+
                    "Total Biaya: Rp "+this.totalBiaya;
        return hasil;
    }
}
