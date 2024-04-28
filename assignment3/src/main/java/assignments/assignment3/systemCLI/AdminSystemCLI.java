package assignments.assignment3.systemCLI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import assignments.assignment3.MainMenu;
import assignments.assignment2.Menu;
import assignments.assignment2.Restaurant;

//TODO: Extends Abstract yang diberikan
public class AdminSystemCLI extends UserSystemCLI{
    //TODO: Tambahkan modifier dan buatlah metode ini mengoverride dari Abstract class
    protected boolean handleMenu(int command){
        switch(command){
            case 1 -> {System.out.println("--------------Tambah Restoran----------------");
                        handleTambahRestoran();}
            case 2 -> {System.out.println("--------------Hapus Restoran----------------");handleHapusRestoran();}
            case 3 -> {return false;}
            default -> System.out.println("Perintah tidak diketahui, silakan coba kembali");
        }
        return true;
    }

    //TODO: Tambahkan modifier dan buatlah metode ini mengoverride dari Abstract class
    protected void displayMenu() {
        System.out.println("\n--------------------------------------------");
        System.out.println("Pilih menu:");
        System.out.println("1. Tambah Restoran");
        System.out.println("2. Hapus Restoran");
        System.out.println("3. Keluar");
        System.out.println("--------------------------------------------");
        System.out.print("Pilihan menu: ");
    }

    protected void handleTambahRestoran(){
        // TODO: Implementasi method untuk handle ketika admin ingin tambah restoran
        System.out.print("Nama: ");
        String namaResto = input.nextLine(); // Meminta input nama restoran yang ingin ditambahkan
        boolean cek = true;
        while(namaResto.strip().length()<4){ // Saat nama restoran kurang dari 4 karakter
            System.out.println("Nama Restoran tidak valid!\n");
            System.out.print("Nama: ");
            namaResto = input.nextLine();
        }
        for (Restaurant x:MainMenu.getRestaurants()){
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
                MainMenu.addRestaurant(restoBaru);
            }
        }
        
    }

    protected void handleHapusRestoran(){
        // TODO: Implementasi method untuk handle ketika admin ingin tambah restoran
        ArrayList<Restaurant> restoList = MainMenu.getRestaurants();  
        if(restoList.isEmpty()){ // Saat list restoran kosong
            System.out.println("Belum ada restoran yang terdaftar!\n");
        }else{
            System.out.print("Nama Restoran: ");
            String namaResto = input.nextLine(); // Meminta input nama restoran 
            for(int i = 0; i<restoList.size(); i++){
                if(restoList.get(i).namaRestoran.equalsIgnoreCase(namaResto)){ // Saat nama restoran terdaftar pada sistem
                    MainMenu.removeRestaurant(restoList.get(i)); // Menghapus objek restoran pada list restoran
                    System.out.println("Restoran berhasil dihapus.");
                    break;
                }else if(i == restoList.size()-1){
                    System.out.println("Restoran tidak terdaftar pada sistem.\n"); // Saat nama restoran tidak terdaftar pada sistem
                    handleHapusRestoran();
                }
            }
        }
    }
}
