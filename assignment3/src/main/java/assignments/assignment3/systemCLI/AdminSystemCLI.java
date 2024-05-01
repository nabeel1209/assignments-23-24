package assignments.assignment3.systemCLI;

import java.util.ArrayList;
import java.util.Arrays;

import assignments.assignment3.MainMenu;
import assignments.assignment2.Menu;
import assignments.assignment2.Restaurant;

public class AdminSystemCLI extends UserSystemCLI{
    // Method handleMenu untuk User dengan role Admin
    protected boolean handleMenu(int command){
        switch(command){
            case 1 -> {System.out.println("--------------Tambah Restoran----------------");handleTambahRestoran();}
            case 2 -> {System.out.println("--------------Hapus Restoran----------------");handleHapusRestoran();}
            case 3 -> {return false;}
            default -> System.out.println("Perintah tidak diketahui, silakan coba kembali");
        }
        return true;
    }

    // Method untuk display menu untuk User dengan role Admin
    protected void displayMenu() {
        System.out.println("\n--------------------------------------------");
        System.out.println("Pilih menu:");
        System.out.println("1. Tambah Restoran");
        System.out.println("2. Hapus Restoran");
        System.out.println("3. Keluar");
        System.out.println("--------------------------------------------");
        System.out.print("Pilihan menu: ");
    }

    // Method untuk handle ketika admin ingin tambah restoran
    protected void handleTambahRestoran(){
        System.out.print("Nama: ");
        String namaResto = input.nextLine();
        boolean cek = true;

        // Validasi nama restaurant
        while(namaResto.strip().length()<4){ 
            System.out.println("Nama Restoran tidak valid!\n");
            System.out.print("Nama: ");
            namaResto = input.nextLine();
        }
        for (Restaurant x:MainMenu.getRestaurants()){
            if (x.namaRestoran.equals(namaResto)){
                System.out.println("Restaurant dengan nama "+ namaResto +" sudah pernah terdaftar. Mohon masukkan nama yang berbeda!\n");
                cek = false;
                break;
            }
        }

        // Saat nama restaurant tidak valid 
        if(!cek){
            handleTambahRestoran();
        }else{
            System.out.print("Jumlah Makanan: "); 
            int jumlahMakanan = input.nextInt(); 
            Restaurant restoBaru = new Restaurant(namaResto); 
            String namaMenu = "";
            double harga = 0;
            input.nextLine();
            
            // Membuat arraylist untuk menyimpan hasil split dari input
            ArrayList<String[]> menu = new ArrayList<String[]>();
            for (int i = 0; i<jumlahMakanan; i++){
                String namaHargaMenu = input.nextLine(); 
                String[] listNamaHargaMenu = namaHargaMenu.split(" "); 
                menu.add(listNamaHargaMenu); 
            }

            for(String[] x:menu){
                // Membuat try catch untuk menangkap error saat casting string ke integer (Validasi harga)
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

            // Saat harga tidak valid
            if(!cek){
                handleTambahRestoran();
            }else{
                System.out.println("Restoran "+namaResto+" berhasil terdaftar."); 
                MainMenu.addRestaurant(restoBaru); // Saat semua input valid maka restoran berhasil ditambahkan
            }
        }
        
    }

    // Method untuk handle ketika admin ingin tambah restoran
    protected void handleHapusRestoran(){
        ArrayList<Restaurant> restoList = MainMenu.getRestaurants();  
        
        // Validasi restolist
        if(restoList.isEmpty()){ 
            System.out.println("Belum ada restoran yang terdaftar!\n");
        }else{
            System.out.print("Nama Restoran: ");
            String namaResto = input.nextLine();
            
            // Validasi nama restaurant
            for(int i = 0; i<restoList.size(); i++){
                if(restoList.get(i).namaRestoran.equalsIgnoreCase(namaResto)){ 
                    MainMenu.removeRestaurant(restoList.get(i)); 
                    System.out.println("Restoran berhasil dihapus.");
                    break;
                }else if(i == restoList.size()-1){ // Saat nama restaurant tidak ada dalam sistem
                    System.out.println("Restoran tidak terdaftar pada sistem.\n"); 
                    handleHapusRestoran();
                }
            }
        }
    }
}
