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
            int command = input.nextInt();
            input.nextLine();

            if(command == 1){
                System.out.println("\nSilakan Login:");
                System.out.print("Nama: ");
                String nama = input.nextLine();
                System.out.print("Nomor Telepon: ");
                String noTelp = input.nextLine();

                // TODO: Validasi input login
                userLoggedIn = getUser(nama, noTelp);
                if (userLoggedIn == null){
                    System.out.println("Pengguna dengan data tersebut tidak ditemukan!\n");
                    continue;
                } // TODO: lengkapi
                boolean isLoggedIn = true;
                System.out.println("Selamat Datang "+userLoggedIn.getName()+"!");

                if(userLoggedIn.role == "Customer"){
                    while (isLoggedIn){
                        menuCustomer();
                        int commandCust = input.nextInt();
                        input.nextLine();

                        switch(commandCust){
                            case 1 : 
                                System.out.println("-----------Buat Pesanan-----------");
                                handleBuatPesanan();
                                break;
                            case 2 : 
                                System.out.println("-----------Cetak Bill-----------");
                                handleCetakBill();
                                break;
                            case 3 :
                                System.out.println("-----------Lihat Menu-----------");
                                handleLihatMenu();
                                break;
                            case 4 : 
                                System.out.println("-----------Update Status Pesanan-----------");
                                handleUpdateStatusPesanan();
                                break;
                            case 5 :
                                isLoggedIn = false;
                                break;
                            default : 
                                System.out.println("Perintah tidak diketahui, silakan coba kembali\n");
                        }
                    }
                }else{
                    while (isLoggedIn){
                        menuAdmin();
                        int commandAdmin = input.nextInt();
                        input.nextLine();

                        switch(commandAdmin){
                            case 1 : 
                                System.out.println("-----------Tambah Restoran-----------");
                                handleTambahRestoran();
                                break;
                            case 2 :
                                System.out.println("-----------Hapus Restoran-----------");
                                handleHapusRestoran();
                                break;
                            case 3 : 
                                isLoggedIn = false;
                                break;
                            default : 
                                System.out.println("Perintah tidak diketahui, silakan coba kembali\n");
                        }
                    }
                }
            }else if(command == 2){
                programRunning = false;
            }else{
                System.out.println("Perintah tidak diketahui, silakan periksa kembali.\n");
            }
        }
        System.out.println("\nTerima kasih telah menggunakan DepeFood ^___^");
    }

    public static User getUser(String nama, String nomorTelepon){
        // TODO: Implementasi method untuk mendapat user dari userList
        for (int i = 0; i<userList.size();i++){
            if (nama.equals(userList.get(i).getName()) && nomorTelepon.equals(userList.get(i).getNoTelepon())){
                return userList.get(i);
            }
        }
        return null;
    }

    public static void handleBuatPesanan(){
        // TODO: Implementasi method untuk handle ketika customer membuat pesanan
        System.out.print("Nama Restoran: ");
        String namaResto = input.nextLine();
        boolean cek = true;
        Restaurant resto = new Restaurant(); 
        for(int i = 0; i<restoList.size(); i++){
            if(restoList.get(i).namaRestoran.equals(namaResto)){
                resto = restoList.get(i);
                break;
            }else if(i==restoList.size()-1){
                System.out.println("Restoran tidak terdaftar pada sistem.\n");
                cek = false;
            }
        }
        if(!cek){
            handleBuatPesanan();
        }else{
            System.out.print("Tanggal Pemesanan (DD/MM/YYYY): ");
            String tanggalPemesanan = input.nextLine();
            if ((tanggalPemesanan.charAt(2)!='/') || (tanggalPemesanan.charAt(5)!='/') || tanggalPemesanan.length()!=10||(Character.getNumericValue(tanggalPemesanan.charAt(3))*10+Character.getNumericValue(tanggalPemesanan.charAt(4)))>12){
                System.out.println("Tanggal Pemesanan dalam format DD/MM/YYYY!\n"); // Saat tanggal pemesanan tidak sesuai format 
                handleBuatPesanan();
            }else{
                ArrayList<Menu> listMenu= new ArrayList<Menu>();
                ArrayList<String> listNamaMenu = new ArrayList<String>();
                System.out.print("Jumlah Pesanan: ");
                int jumlahPesanan = input.nextInt();
                System.out.println("Order:");
                String namaMenu;
                input.nextLine();
                for (int i = 0; i<jumlahPesanan; i++){
                    namaMenu = input.nextLine();
                    listNamaMenu.add(namaMenu);
                }
                for (int i = 0; i<listNamaMenu.size(); i++){
                    for(int j = 0; j<resto.menu.size(); j++){
                        if(listNamaMenu.get(i).equals(resto.menu.get(j).namaMakanan)){
                            listMenu.add(resto.menu.get(j));
                            break;
                        }else if(j == resto.menu.size()-1){
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
                    Order order = new Order("", tanggalPemesanan, 0, resto, listMenu);
                    order.setOrderID(userLoggedIn.getNoTelepon());
                    switch(userLoggedIn.getLokasi()){
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
                    order.setTotalBiaya();
                    userLoggedIn.addOrder(order);
                    System.out.println("Pesanan dengan ID "+order.getOrderID()+" diterima!\n");
                }
            }
        }
    }

    public static void handleCetakBill(){
        // TODO: Implementasi method untuk handle ketika customer ingin cetak bill
        System.out.print("Masukkan Order ID: ");
        String orderID = input.nextLine();
        for (int i = 0; i<userLoggedIn.getOrderHistory().size(); i++){
            if (orderID.equals(userLoggedIn.getOrderHistory().get(i).getOrderID())){
                System.out.println(userLoggedIn.getOrderHistory().get(i));
                break;
            }else if(i == userLoggedIn.getOrderHistory().size()-1){
                System.out.println("Order ID tidak dapat ditemukan.\n");
                handleCetakBill();
            }
        }
    }

    public static void handleLihatMenu(){
        // TODO: Implementasi method untuk handle ketika customer ingin melihat menu
        System.out.print("Nama Restoran: ");
        String namaResto = input.nextLine();
        for (int i = 0; i<restoList.size(); i++){
            if(namaResto.equals(restoList.get(i).namaRestoran)){
                System.out.println(restoList.get(i).showMenu());
                break;
            }else if(i == restoList.size()-1){
                System.out.println("Restoran tidak terdaftar pada sistem.\n");
                handleLihatMenu();
            }
        }
    }

    public static void handleUpdateStatusPesanan(){
        // TODO: Implementasi method untuk handle ketika customer ingin update status pesanan
        System.out.print("Order ID: ");
        String orderID = input.nextLine();
        boolean cek = true;
        for (int i = 0; i<userLoggedIn.getOrderHistory().size(); i++){
            if (orderID.equals(userLoggedIn.getOrderHistory().get(i).getOrderID())){
                break;
            }else if(i == userLoggedIn.getOrderHistory().size()-1){
                System.out.println("Order ID tidak dapat ditemukan.\n");
                cek = false;
            }
        }
        if (!cek){
            handleUpdateStatusPesanan();
        }else{
            System.out.print("Status: ");
            String status = input.nextLine();
            boolean statusBool;
            if (status.equalsIgnoreCase("Selesai")){
                statusBool = true;
            }else{
                statusBool = false;
            }
            for (int i = 0; i<userLoggedIn.getOrderHistory().size(); i++){
                if (orderID.equals(userLoggedIn.getOrderHistory().get(i).getOrderID())){
                    if(userLoggedIn.getOrderHistory().get(i).orderFinished == statusBool){
                        System.out.println("Status pesanan dengan ID "+userLoggedIn.getOrderHistory().get(i).getOrderID()+" tidak berhasil diupdate!\n");
                    }else{
                        userLoggedIn.getOrderHistory().get(i).setOrderFinished(statusBool);
                        System.out.println("Status pesanan dengan ID "+userLoggedIn.getOrderHistory().get(i).getOrderID()+" berhasil diupdate!\n");
                    }
                    break;
                }
            }
        }
    }

    public static void handleTambahRestoran(){
        // TODO: Implementasi method untuk handle ketika admin ingin tambah restoran
        System.out.print("Nama: ");
        String namaResto = input.nextLine();
        boolean cek = true;
        while(namaResto.length()<4){
            System.out.println("Nama Restoran tidak valid!\n");
            System.out.print("Nama: ");
            namaResto = input.nextLine();
        }
        for (Restaurant x:restoList){
            if (x.namaRestoran.equals(namaResto)){
                System.out.println("Restaurant dengan nama "+ namaResto +" sudah pernah terdaftar. Mohon masukkan nama yang berbeda!\n");
                cek = false;
                break;
            }
        }
        if(!cek){
            handleTambahRestoran();
        }else{
            System.out.print("Jumlah Makanan: ");
            int jumlahMakanan = input.nextInt();
            Restaurant restoBaru = new Restaurant(namaResto);
            String namaMenu = "";
            double harga = 0;
            input.nextLine();
            ArrayList<String[]> menu = new ArrayList<String[]>();
            for (int i = 0; i<jumlahMakanan; i++){
                String namaHargaMenu = input.nextLine();
                String[] listNamaHargaMenu = namaHargaMenu.split(" ");
                menu.add(listNamaHargaMenu);
            }
            for(String[] x:menu){
                try{
                    namaMenu = String.join(" ", Arrays.copyOfRange(x,0,x.length - 1));
                    harga = Integer.parseInt(x[x.length-1]);
                    restoBaru.addMenu(new Menu(namaMenu, harga));
                }catch(NumberFormatException e){
                    System.out.println("Harga menu harus bilangan bulat!\n");
                    cek = false;
                    break;
                }
            }
            if(!cek){
                handleTambahRestoran();
            }else{
                System.out.println("Restoran "+namaResto+" Berhasil terdaftar.");
                restoList.add(restoBaru);
            }
        }
    }

    public static void handleHapusRestoran(){
        // TODO: Implementasi method untuk handle ketika admin ingin hapus restoran
        if(restoList.isEmpty()){
            System.out.println("Belum ada restoran yang terdaftar!\n");
        }else{
            System.out.print("Nama Restoran: ");
            String namaResto = input.nextLine();
            for(int i = 0; i<restoList.size(); i++){
                if(restoList.get(i).namaRestoran.equalsIgnoreCase(namaResto)){
                    restoList.remove(i);
                    System.out.println("Restoran berhasil dihapus.\n");
                    break;
                }else if(i==restoList.size()-1){
                    System.out.println("Restoran tidak terdaftar pada sistem.\n");
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
