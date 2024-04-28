package assignments.assignment3.systemCLI;

import java.util.ArrayList;

import assignments.assignment3.MainMenu;
import assignments.assignment2.Menu;
import assignments.assignment2.Order;
import assignments.assignment2.Restaurant;
import assignments.assignment3.User;
import assignments.assignment3.payment.CreditCardPayment;
import assignments.assignment3.payment.DebitPayment;

//TODO: Extends abstract class yang diberikan
public class CustomerSystemCLI extends UserSystemCLI{
    //TODO: Tambahkan modifier dan buatlah metode ini mengoverride dari Abstract class
    protected boolean handleMenu(int choice){
        switch(choice){
            case 1 -> {System.out.println("--------------Buat Pesanan----------------");handleBuatPesanan();}
            case 2 -> {System.out.println("--------------Cetak Bill----------------");handleCetakBill();}
            case 3 -> {System.out.println("--------------Lihat Menu----------------");handleLihatMenu();}
            case 4 -> {System.out.println("--------------Bayar Bill----------------");handleBayarBill();}
            case 5 -> {System.out.println("--------------Cek Saldo----------------");handleCekSaldo();}
            case 6 -> {
                return false;
            }
            default -> System.out.println("Perintah tidak diketahui, silakan coba kembali");
        }
        return true;
    }

    //TODO: Tambahkan modifier dan buatlah metode ini mengoverride dari Abstract class
    protected void displayMenu() {
        System.out.println("\n--------------------------------------------");
        System.out.println("Pilih menu:");
        System.out.println("1. Buat Pesanan");
        System.out.println("2. Cetak Bill");
        System.out.println("3. Lihat Menu");
        System.out.println("4. Bayar Bill");
        System.out.println("5. Cek Saldo");
        System.out.println("6. Keluar");
        System.out.println("--------------------------------------------");
        System.out.print("Pilihan menu: ");
    }

    protected void handleBuatPesanan(){
        // TODO: Implementasi method untuk handle ketika customer membuat pesanan
        System.out.print("Nama Restoran: ");
        String namaResto = input.nextLine(); // Meminta input nama restoran
        boolean cek = true;
        Restaurant resto = new Restaurant(); 
        ArrayList<Restaurant> restoList= MainMenu.getRestaurants();
        if(restoList.isEmpty()){ // Saat list restoran masih kosong
            System.out.println("Belum ada restoran yang terdaftar pada sistem!");
        }else{ // Saat list restoran tidak kosong
            for(int i = 0; i<restoList.size(); i++){
                if(restoList.get(i).namaRestoran.equals(namaResto)){
                    resto = restoList.get(i); // Saat restoran ditemukan dalam list restoran
                    break;
                }else if(i == restoList.size()-1){
                    System.out.println("Restoran tidak terdaftar pada sistem.\n"); 
                    cek = false;
                }
            }
            if(!cek){ // Saat restoran tidak ditemukan dalam list restoran
                handleBuatPesanan();
            }else{
                System.out.print("Tanggal Pemesanan (DD/MM/YYYY): ");
                String tanggalPemesanan = input.nextLine(); // Meminta input tanggal pemesanan sesuai format
                if ((tanggalPemesanan.charAt(2)!='/') || (tanggalPemesanan.charAt(5)!='/') || tanggalPemesanan.length()!=10||
                    (Character.getNumericValue(tanggalPemesanan.charAt(3))*10+Character.getNumericValue(tanggalPemesanan.charAt(4)))>12){
                    System.out.println("Tanggal Pemesanan dalam format DD/MM/YYYY!\n"); // Saat tanggal pemesanan tidak sesuai format 
                    handleBuatPesanan();
                }else{ // Saat tanggal pemesanan valid
                    ArrayList<Menu> listMenu= new ArrayList<Menu>(); // Membuat list menu yang akan dimasukkan dalam order
                    ArrayList<String> listNamaMenu = new ArrayList<String>(); // Membuat list nama menu
                    System.out.print("Jumlah Pesanan: "); 
                    int jumlahPesanan = input.nextInt(); // Meminta input jumlah pesanan
                    System.out.println("Order:");
                    String namaMenu;
                    input.nextLine();
                    for (int i = 0; i<jumlahPesanan; i++){
                        namaMenu = input.nextLine(); // Meminta input nama menu yang ingin dipesan
                        listNamaMenu.add(namaMenu); // Memasukkan nama menu pada list nama menu
                    }
                    for (int i = 0; i<listNamaMenu.size(); i++){ // Mengecek nama menu 
                        for(int j = 0; j<resto.menu.size(); j++){
                            if(listNamaMenu.get(i).equals(resto.menu.get(j).namaMakanan)){
                                listMenu.add(resto.menu.get(j)); // Saat nama menu yang dipesan ada di restoran
                                break;
                            }else if(j == resto.menu.size()-1){ // Saat nama menu tidak ada di restoran
                                System.out.println("Mohon memesan menu yang tersedia di Restoran!\n");
                                cek = false;
                            }
                        }
                        if(!cek){
                            break;
                        }
                    }
                    if(!cek){
                        handleBuatPesanan();
                    }else{
                        Order order = new Order("", tanggalPemesanan, 0, resto, listMenu); // Membuat objek order yang nantinya akan ditambahkan pada order history user
                        order.setOrderID(MainMenu.getUserLoggedIn().getNoTelepon()); // Membuat order id sesuai dengan data user dan data pesanan
                        switch(MainMenu.getUserLoggedIn().getLokasi()){ // Menentukan ongkir sesuai dengan lokasi user
                            case "P": 
                                order.setBiayaOngkosKirim(10000);
                                break;
                            case "U": 
                                order.setBiayaOngkosKirim(20000);
                                break;
                            case "T": 
                                order.setBiayaOngkosKirim(35000);
                                break;
                            case "S": 
                                order.setBiayaOngkosKirim(40000);
                                break;
                            case "B": 
                                order.setBiayaOngkosKirim(60000);
                                break;
                        }
                        order.setTotalBiaya(); // Menentukan total biaya dari order yang dibuat user
                        MainMenu.getUserLoggedIn().addOrder(order); // Menambahkan order pada order history user
                        System.out.println("Pesanan dengan ID "+order.getOrderID()+" diterima!\n");
                    }
                }
            }
        }
    }

    protected void handleCetakBill(){
        // TODO: Implementasi method untuk handle ketika customer ingin cetak bill
        System.out.print("Masukkan Order ID: "); 
        String orderID = input.nextLine(); // Meminta input orderID
        User userLoggedIn = MainMenu.getUserLoggedIn();
        if (userLoggedIn.getOrderHistory().isEmpty()){ // Saat order history user kosong
            System.out.println("User "+userLoggedIn.getName()+" belum melakukan order!");
        }else{ 
            for (int i = 0; i<userLoggedIn.getOrderHistory().size(); i++){ // Mengecek orderID yang dimiliki oleh user
                if (orderID.equals(userLoggedIn.getOrderHistory().get(i).getOrderID())){
                    System.out.println(userLoggedIn.getOrderHistory().get(i)); // Saat orderID ditemukan
                    break;
                }else if(i == userLoggedIn.getOrderHistory().size()-1){
                    System.out.println("Order ID tidak dapat ditemukan.\n"); // Saat orderID tidak ditemukan
                    handleCetakBill();
                }
            }
        }
    }

    protected void handleLihatMenu(){
        // TODO: Implementasi method untuk handle ketika customer ingin melihat menu
        System.out.print("Nama Restoran: ");
        String namaResto = input.nextLine();
        ArrayList<Restaurant> restoList = MainMenu.getRestaurants();
        if(restoList.isEmpty()){ // Saat belum ada restoran yang terdaftar
            System.out.println("Belum ada restoran yang terdaftar pada sistem!");
        }else{
            for (int i = 0; i<restoList.size(); i++){ // Mengecek apakah restoran terdaftar pada sistem
                if(namaResto.equalsIgnoreCase(restoList.get(i).namaRestoran)){
                    System.out.println(restoList.get(i).showMenu()); // Saat restoran terdaftar pada sistem
                    break;
                }else if(i == restoList.size()-1){
                    System.out.println("Restoran tidak terdaftar pada sistem.\n"); // Saat restoran tidak ada dalam sistem
                    handleLihatMenu();
                }
            }
        }
    }

    protected void handleBayarBill(){
        // TODO: Implementasi method untuk handle ketika customer ingin melihat menu
        System.out.print("Masukkan Order ID: "); 
        String orderID = input.nextLine(); // Meminta input orderID
        User userLoggedIn = MainMenu.getUserLoggedIn();
        if (userLoggedIn.getOrderHistory().isEmpty()){ // Saat order history user kosong
            System.out.println("User "+userLoggedIn.getName()+" belum melakukan order!");
        }else{ 
            for (int i = 0; i<userLoggedIn.getOrderHistory().size(); i++){ // Mengecek orderID yang dimiliki oleh user
                if (orderID.equals(userLoggedIn.getOrderHistory().get(i).getOrderID())){
                    if(userLoggedIn.getOrderHistory().get(i).statusBayar){
                        System.out.println("Pesanan dengan ID ini sudah lunas!");
                        return;
                    }
                    System.out.println(userLoggedIn.getOrderHistory().get(i)); // Saat orderID ditemukan
                    break;
                }else if(i == userLoggedIn.getOrderHistory().size()-1){
                    System.out.println("Order ID tidak dapat ditemukan.\n"); // Saat orderID tidak ditemukan
                    handleCetakBill();
                }
            }
        }
        System.out.println("\nOpsi Pembayaran:");
        System.out.println("1. Credit Card");
        System.out.println("2. Debit");
        int pilihanMetode = 0;
        boolean cekMetodePembayaran = false;
        while(!cekMetodePembayaran){
            System.out.print("Pilihan Metode Pembayaran: ");
            pilihanMetode = input.nextInt();
            if(pilihanMetode < 1 || pilihanMetode > 2){
                continue;
            }
            if(pilihanMetode == 1){
                cekMetodePembayaran = MainMenu.getUserLoggedIn().getPayment() instanceof CreditCardPayment;
            }else if(pilihanMetode == 2){
                cekMetodePembayaran = MainMenu.getUserLoggedIn().getPayment() instanceof DebitPayment;
            }
            
            if(!cekMetodePembayaran){
                System.out.println("User belum memiliki metode pembayaran ini");
            }
        }
        if(pilihanMetode == 2){
            long totalBiaya = MainMenu.getUserLoggedIn().getOrder(orderID).getTotalBiaya();
            if(totalBiaya < 50000){
                System.out.println("Jumlah pesanan < 50000 mohon menggunakan metode pembayaran yang lain");
                return;
            }else{
                long hasilPayment = MainMenu.getUserLoggedIn().getPayment().processPayment(totalBiaya);
                MainMenu.getUserLoggedIn().getOrder(orderID).getRestaurant().setSaldoResto(MainMenu.getUserLoggedIn().getOrder(orderID).getRestaurant().getSaldoResto()+hasilPayment);
                long saldoMinus = MainMenu.getUserLoggedIn().getSaldo() - hasilPayment;
                if (saldoMinus <= 0){
                    System.out.println("Tidak berhasil membayar bill karena saldo tidak cukup");
                    return;
                }
                MainMenu.getUserLoggedIn().setSaldo(saldoMinus);
                MainMenu.getUserLoggedIn().getOrder(orderID).getRestaurant().setSaldoResto(hasilPayment);
                System.out.println("Berhasil Membayar Bill sebesar Rp " +hasilPayment);
            }
        }else{
            
            long totalBiaya = MainMenu.getUserLoggedIn().getOrder(orderID).getTotalBiaya();
            long hasilPayment = MainMenu.getUserLoggedIn().getPayment().processPayment(totalBiaya);
            long transactionFee = ((CreditCardPayment)MainMenu.getUserLoggedIn().getPayment()).countTransaction(totalBiaya);
            long saldoMinus = MainMenu.getUserLoggedIn().getSaldo() - (hasilPayment+transactionFee);
            if (saldoMinus <= 0){
                System.out.println("Tidak berhasil membayar bill karena saldo tidak cukup");
                return;
            }
            MainMenu.getUserLoggedIn().setSaldo(saldoMinus);
            MainMenu.getUserLoggedIn().getOrder(orderID).getRestaurant().setSaldoResto(hasilPayment+transactionFee);
            System.out.println("Berhasil Membayar Bill sebesar Rp " + hasilPayment +" dengan biaya transaksi sebesar Rp "+transactionFee);
        }
    }

    public void handleUpdateStatusPesanan(){
        // TODO: Implementasi method untuk handle ketika customer ingin update status pesanan
        System.out.print("Order ID: ");
        String orderID = input.nextLine(); // Meminta input orderID
        boolean cek = true;
        User userLoggedIn = MainMenu.getUserLoggedIn();
        if (userLoggedIn.getOrderHistory().isEmpty()){ // Saat order history user kosong
            System.out.println("User "+userLoggedIn.getName()+" belum melakukan order!");
        }else{
            for (int i = 0; i<userLoggedIn.getOrderHistory().size(); i++){ // Mengecek orderID dari order history user
                if (orderID.equals(userLoggedIn.getOrderHistory().get(i).getOrderID())){
                    break; // Saat orderID ada
                }else if(i == userLoggedIn.getOrderHistory().size()-1){
                    System.out.println("Order ID tidak dapat ditemukan.\n"); // Saat orderID tidak ada di order history user
                    cek = false;
                }
            }
            if (!cek){
                handleUpdateStatusPesanan();
            }else{
                System.out.print("Status: ");
                String status = input.nextLine(); // Meminta input status pesanan
                boolean statusBool;
                if (status.equalsIgnoreCase("Selesai")){ // Membuat variable boolean dari string status
                    statusBool = true; 
                }else{
                    statusBool = false;
                }
                for (int i = 0; i<userLoggedIn.getOrderHistory().size(); i++){
                    if (orderID.equals(userLoggedIn.getOrderHistory().get(i).getOrderID())){
                        if(userLoggedIn.getOrderHistory().get(i).orderFinished == statusBool){ // Saat status pesanan sama dengan status yang diupdate
                            System.out.println("Status pesanan dengan ID "+userLoggedIn.getOrderHistory().get(i).getOrderID()+" tidak berhasil diupdate!\n");
                        }else{
                            if (!statusBool && userLoggedIn.getOrderHistory().get(i).orderFinished){ // Saat pesanan sudah selesai namun ingin diubah menjadi tidak selesai
                                System.out.println("Status pesanan dengan ID "+userLoggedIn.getOrderHistory().get(i).getOrderID()+" tidak berhasil diupdate!\n");
                            }else{
                                MainMenu.getUserLoggedIn().getOrderHistory().get(i).setOrderFinished(statusBool); // Saat status pesanan berbeda dengan status yang diupdate
                                System.out.println("Status pesanan dengan ID "+userLoggedIn.getOrderHistory().get(i).getOrderID()+" berhasil diupdate!\n");
                            }
                        }
                        break;
                    }
                }
            }
        }
    }

    public void handleCekSaldo(){
        long saldo = MainMenu.getUserLoggedIn().getSaldo();
        System.out.println("Sisa saldo sebesar Rp "+saldo);
    }
}
