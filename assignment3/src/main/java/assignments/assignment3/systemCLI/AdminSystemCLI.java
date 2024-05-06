package assignments.assignment3.systemCLI;

<<<<<<< HEAD
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
=======
import java.util.Arrays;

import assignments.assignment3.Menu;
import assignments.assignment3.Restaurant;

public class AdminSystemCLI extends UserSystemCLI {

    @Override
    boolean handleMenu(int command) {
        switch (command) {
            case 1 -> handleTambahRestoran();
            case 2 -> handleHapusRestoran();
            case 3 -> {
                return false;
            }
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
            default -> System.out.println("Perintah tidak diketahui, silakan coba kembali");
        }
        return true;
    }

<<<<<<< HEAD
    // Method untuk display menu untuk User dengan role Admin
    protected void displayMenu() {
=======
    @Override
    void displayMenu() {
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
        System.out.println("\n--------------------------------------------");
        System.out.println("Pilih menu:");
        System.out.println("1. Tambah Restoran");
        System.out.println("2. Hapus Restoran");
        System.out.println("3. Keluar");
        System.out.println("--------------------------------------------");
        System.out.print("Pilihan menu: ");
    }

<<<<<<< HEAD
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
=======
    private void handleTambahRestoran() {
        System.out.println("--------------Tambah Restoran---------------");
        Restaurant restaurant = null;
        while (restaurant == null) {
            String namaRestaurant = getValidRestaurantName();
            restaurant = new Restaurant(namaRestaurant);
            restaurant = handleTambahMenuRestaurant(restaurant);
        }
        restoList.add(restaurant);
        System.out.print("Restaurant " + restaurant.getNama() + " Berhasil terdaftar.");
    }

    private Restaurant handleTambahMenuRestaurant(Restaurant restoran) {
        System.out.print("Jumlah Makanan: ");
        int jumlahMenu = Integer.parseInt(input.nextLine().trim());
        boolean isMenuValid = true;
        for (int i = 0; i < jumlahMenu; i++) {
            String inputValue = input.nextLine().trim();
            String[] splitter = inputValue.split(" ");
            String hargaStr = splitter[splitter.length - 1];
            boolean isDigit = checkIsDigit(hargaStr);
            String namaMenu = String.join(" ", Arrays.copyOfRange(splitter, 0, splitter.length - 1));
            if (isDigit) {
                int hargaMenu = Integer.parseInt(hargaStr);
                restoran.addMenu(new Menu(namaMenu, hargaMenu));
            } else {
                isMenuValid = false;
            }
        }
        if (!isMenuValid) {
            System.out.println("Harga menu harus bilangan bulat!");
            System.out.println();
        }

        return isMenuValid ? restoran : null;
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
    }

    private void handleHapusRestoran() {
        System.out.println("--------------Hapus Restoran----------------");
        boolean isActionDeleteEnded = false;
        while (!isActionDeleteEnded) {
            System.out.print("Nama Restoran: ");
            String restaurantName = input.nextLine().trim();
            boolean isRestaurantExist = restoList.stream()
                    .anyMatch(restaurant -> restaurant.getNama().toLowerCase().equals(restaurantName.toLowerCase()));
            if (!isRestaurantExist) {
                System.out.println("Restoran tidak terdaftar pada sistem.");
                System.out.println();
            } else {
                restoList.removeIf(
                        restaurant -> restaurant.getNama().toLowerCase().equals(restaurantName.toLowerCase()));

                System.out.println("Restoran berhasil dihapus");
                isActionDeleteEnded = true;
            }
        }
    }

}