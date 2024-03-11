package assignments.assignment2;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class MainMenu {
    private static final Scanner input = new Scanner(System.in);
    private static ArrayList<Restaurant> restoList = new ArrayList<Restaurant>();
    private static ArrayList<User> userList;
    private static User userLoggedIn;

    public static void main(String[] args) {
        boolean programRunning = true;
        while(programRunning){
            printHeader();
            startMenu();
            initUser();
            int command = input.nextInt(); // Meminta input command awal
            input.nextLine();

            if(command == 1){ // Saat pengguna ingin login
                System.out.println("\nSilakan Login:");
                System.out.print("Nama: ");
                String nama = input.nextLine(); // Memasukkan input nama
                System.out.print("Nomor Telepon: ");
                String noTelp = input.nextLine(); // Memasukkan input noTelp

                // TODO: Validasi input login
                userLoggedIn = getUser(nama, noTelp); // Mengambil user dari list user 
                if (userLoggedIn == null){ // Memastikan user ada
                    System.out.println("Pengguna dengan data tersebut tidak ditemukan!");
                    continue;
                }
                boolean isLoggedIn = true;
                System.out.println("Selamat Datang "+userLoggedIn.getName()+"!");

                if(userLoggedIn.role == "Customer"){ // Saat role user adalah Customer
                    while (isLoggedIn){
                        menuCustomer(); // Menampilkan menu untuk customer
                        int commandCust = input.nextInt(); // Meminta input command untuk customer
                        input.nextLine();

                        switch(commandCust){
                            case 1 : // Saat command customer adalah 1
                                System.out.println("-----------Buat Pesanan-----------");
                                handleBuatPesanan();
                                break;
                            case 2 : // Saat command customer adalah 2
                                System.out.println("-----------Cetak Bill-----------");
                                handleCetakBill();
                                break;
                            case 3 : // Saat command customer adalah 3
                                System.out.println("-----------Lihat Menu-----------");
                                handleLihatMenu();
                                break;
                            case 4 : // Saat command customer adalah 4
                                System.out.println("-----------Update Status Pesanan-----------");
                                handleUpdateStatusPesanan();
                                break;
                            case 5 : // Saat command customer adalah 5
                                isLoggedIn = false;
                                break;
                            default : // Saat command customer tidak ada dalam sistem
                                System.out.println("Perintah tidak diketahui, silakan coba kembali\n");
                        }
                    }
                }else{ // Saat role user adalah Admin
                    while (isLoggedIn){
                        menuAdmin(); // Menampilkan menu admin
                        int commandAdmin = input.nextInt(); // Meminta input command untuk admin
                        input.nextLine();

                        switch(commandAdmin){
                            case 1 : // Saat command admin adalah 1
                                System.out.println("-----------Tambah Restoran-----------");
                                handleTambahRestoran();
                                break;
                            case 2 : // Saat command admin adalah 2
                                System.out.println("-----------Hapus Restoran-----------");
                                handleHapusRestoran();
                                break;
                            case 3 : // Saat command admin adalah 3
                                isLoggedIn = false;
                                break;
                            default : // Saat command admin tidak ada pada sistem
                                System.out.println("Perintah tidak diketahui, silakan coba kembali\n");
                        }
                    }
                }
            }else if(command == 2){ // Saat input command awal adalah 2
                programRunning = false;
            }else{ // Saat command awal tidak ada pada sistem
                System.out.println("Perintah tidak diketahui, silakan periksa kembali.\n");
            }
        }
        System.out.println("\nTerima kasih telah menggunakan DepeFood ^___^"); // Saat program berhenti berjalan
    }

    public static User getUser(String nama, String nomorTelepon){
        // TODO: Implementasi method untuk mendapat user dari userList
        for (int i = 0; i<userList.size();i++){
            if (nama.equals(userList.get(i).getName()) && nomorTelepon.equals(userList.get(i).getNoTelepon())){
                return userList.get(i); // Mereturn user saat user ditemukan dalam list user
            }
        }
        return null; // Mereturn null saat user tidak ditemukan dalam list user
    }

    public static void handleBuatPesanan(){
        // TODO: Implementasi method untuk handle ketika customer membuat pesanan
        System.out.print("Nama Restoran: ");
        String namaResto = input.nextLine(); // Meminta input nama restoran
        boolean cek = true;
        Restaurant resto = new Restaurant(); 
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
                        order.setOrderID(userLoggedIn.getNoTelepon()); // Membuat order id sesuai dengan data user dan data pesanan
                        switch(userLoggedIn.getLokasi()){ // Menentukan ongkir sesuai dengan lokasi user
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
                        userLoggedIn.addOrder(order); // Menambahkan order pada order history user
                        System.out.println("Pesanan dengan ID "+order.getOrderID()+" diterima!\n");
                    }
                }
            }
        }
    }
    
    public static void handleCetakBill(){
        // TODO: Implementasi method untuk handle ketika customer ingin cetak bill
        System.out.print("Masukkan Order ID: "); 
        String orderID = input.nextLine(); // Meminta input orderID
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

    public static void handleLihatMenu(){
        // TODO: Implementasi method untuk handle ketika customer ingin melihat menu
        System.out.print("Nama Restoran: ");
        String namaResto = input.nextLine();
        if(restoList.isEmpty()){ // Saat belum ada restoran yang terdaftar
            System.out.println("Belum ada restoran yang terdaftar pada sistem!");
        }else{
            for (int i = 0; i<restoList.size(); i++){ // Mengecek apakah restoran terdaftar pada sistem
                if(namaResto.equals(restoList.get(i).namaRestoran)){
                    System.out.println(restoList.get(i).showMenu()); // Saat restoran terdaftar pada sistem
                    break;
                }else if(i == restoList.size()-1){
                    System.out.println("Restoran tidak terdaftar pada sistem.\n"); // Saat restoran tidak ada dalam sistem
                    handleLihatMenu();
                }
            }
        }
    }

    public static void handleUpdateStatusPesanan(){
        // TODO: Implementasi method untuk handle ketika customer ingin update status pesanan
        System.out.print("Order ID: ");
        String orderID = input.nextLine(); // Meminta input orderID
        boolean cek = true;
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
                            userLoggedIn.getOrderHistory().get(i).setOrderFinished(statusBool); // Saat status pesanan berbeda dengan status yang diupdate
                            System.out.println("Status pesanan dengan ID "+userLoggedIn.getOrderHistory().get(i).getOrderID()+" berhasil diupdate!\n");
                        }
                        break;
                    }
                }
            }
        }
    }

    public static void handleTambahRestoran(){
        // TODO: Implementasi method untuk handle ketika admin ingin tambah restoran
        System.out.print("Nama: ");
        String namaResto = input.nextLine(); // Meminta input nama restoran yang ingin ditambahkan
        boolean cek = true;
        while(namaResto.strip().length()<4){ // Saat nama restoran kurang dari 4 karakter
            System.out.println("Nama Restoran tidak valid!\n");
            System.out.print("Nama: ");
            namaResto = input.nextLine();
        }
        for (Restaurant x:restoList){
            if (x.namaRestoran.equals(namaResto)){ // Saat restoran sudah terdaftar pada sistem
                System.out.println("Restaurant dengan nama "+ namaResto +" sudah pernah terdaftar. Mohon masukkan nama yang berbeda!\n");
                cek = false;
                break;
            }
        }
        if(!cek){
            handleTambahRestoran();
        }else{
            System.out.print("Jumlah Makanan: "); 
            int jumlahMakanan = input.nextInt(); // Meminta jumlah makanan yang disajikan oleh restoran
            Restaurant restoBaru = new Restaurant(namaResto); // Membuat objek restoran
            String namaMenu = "";
            double harga = 0;
            input.nextLine();
            ArrayList<String[]> menu = new ArrayList<String[]>();
            for (int i = 0; i<jumlahMakanan; i++){
                String namaHargaMenu = input.nextLine(); // Meminta nama serta harga menu
                String[] listNamaHargaMenu = namaHargaMenu.split(" "); // Melakukan split dari hasil input
                menu.add(listNamaHargaMenu); // Memasukkan hasil split input ke dalam array menu
            }
            for(String[] x:menu){
                try{ // Membuat try catch untuk menangkap error saat casting string ke integer
                    namaMenu = String.join(" ", Arrays.copyOfRange(x,0,x.length - 1)); // Menyatukan nama makanan dari array yang telah dicopy hanya nama makanannya saja
                    harga = Integer.parseInt(x[x.length-1]); // Melakukan casting string ke integer
                    restoBaru.addMenu(new Menu(namaMenu, harga)); // Menambahkan menu baru pada restoran
                }catch(NumberFormatException e){ // Saat error casting terjadi
                    System.out.println("Harga menu harus bilangan bulat!\n");
                    cek = false;
                    break;
                }
            }
            if(!cek){
                handleTambahRestoran();
            }else{
                System.out.println("Restoran "+namaResto+" berhasil terdaftar."); // Saat semua input valid maka restoran berhasil ditambahkan
                restoList.add(restoBaru);
            }
        }
    }

    public static void handleHapusRestoran(){
        // TODO: Implementasi method untuk handle ketika admin ingin hapus restoran
        if(restoList.isEmpty()){ // Saat list restoran kosong
            System.out.println("Belum ada restoran yang terdaftar!\n");
        }else{
            System.out.print("Nama Restoran: ");
            String namaResto = input.nextLine(); // Meminta input nama restoran 
            for(int i = 0; i<restoList.size(); i++){
                if(restoList.get(i).namaRestoran.equalsIgnoreCase(namaResto)){ // Saat nama restoran terdaftar pada sistem
                    restoList.remove(i); // Menghapus objek restoran pada list restoran
                    System.out.println("Restoran berhasil dihapus.");
                    break;
                }else if(i == restoList.size()-1){
                    System.out.println("Restoran tidak terdaftar pada sistem.\n"); // Saat nama restoran tidak terdaftar pada sistem
                    handleHapusRestoran();
                }
            }
        }
    }

    public static void initUser(){
        userList = new ArrayList<User>();
        userList.add(new User("Thomas N", "9928765403", "thomas.n@gmail.com", "P", "Customer"));
        userList.add(new User("Sekar Andita", "089877658190", "dita.sekar@gmail.com", "B", "Customer"));
        userList.add(new User("Sofita Yasusa", "084789607222", "sofita.susa@gmail.com", "T", "Customer"));
        userList.add(new User("Dekdepe G", "080811236789", "ddp2.gampang@gmail.com", "S", "Customer"));
        userList.add(new User("Aurora Anum", "087788129043", "a.anum@gmail.com", "U", "Customer"));

        userList.add(new User("Admin", "123456789", "admin@gmail.com", "-", "Admin"));
        userList.add(new User("Admin Baik", "9123912308", "admin.b@gmail.com", "-", "Admin"));
    }

    public static void printHeader(){
        System.out.println("\n>>=======================================<<");
        System.out.println("|| ___                 ___             _ ||");
        System.out.println("||| . \\ ___  ___  ___ | __>___  ___  _| |||");
        System.out.println("||| | |/ ._>| . \\/ ._>| _>/ . \\/ . \\/ . |||");
        System.out.println("|||___/\\___.|  _/\\___.|_| \\___/\\___/\\___|||");
        System.out.println("||          |_|                          ||");
        System.out.println(">>=======================================<<");
    }

    public static void startMenu(){
        System.out.println("Selamat datang di DepeFood!");
        System.out.println("--------------------------------------------");
        System.out.println("Pilih menu:");
        System.out.println("1. Login");
        System.out.println("2. Keluar");
        System.out.println("--------------------------------------------");
        System.out.print("Pilihan menu: ");
    }

    public static void menuAdmin(){
        System.out.println("\n--------------------------------------------");
        System.out.println("Pilih menu:");
        System.out.println("1. Tambah Restoran");
        System.out.println("2. Hapus Restoran");
        System.out.println("3. Keluar");
        System.out.println("--------------------------------------------");
        System.out.print("Pilihan menu: ");
    }

    public static void menuCustomer(){
        System.out.println("\n--------------------------------------------");
        System.out.println("Pilih menu:");
        System.out.println("1. Buat Pesanan");
        System.out.println("2. Cetak Bill");
        System.out.println("3. Lihat Menu");
        System.out.println("4. Update Status Pesanan");
        System.out.println("5. Keluar");
        System.out.println("--------------------------------------------");
        System.out.print("Pilihan menu: ");
    }
}
