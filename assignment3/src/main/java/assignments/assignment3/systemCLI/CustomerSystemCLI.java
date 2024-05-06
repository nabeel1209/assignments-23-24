package assignments.assignment3.systemCLI;

<<<<<<< HEAD
import java.util.ArrayList;

import assignments.assignment3.MainMenu;
import assignments.assignment2.Menu;
import assignments.assignment2.Order;
import assignments.assignment2.Restaurant;
import assignments.assignment3.User;
import assignments.assignment3.payment.CreditCardPayment;
import assignments.assignment3.payment.DebitPayment;

public class CustomerSystemCLI extends UserSystemCLI{
    // Method handleMenu untuk User dengan role Customer
    protected boolean handleMenu(int choice){
        switch(choice){
            case 1 -> {System.out.println("--------------Buat Pesanan----------------");handleBuatPesanan();}
            case 2 -> {System.out.println("--------------Cetak Bill----------------");handleCetakBill();}
            case 3 -> {System.out.println("--------------Lihat Menu----------------");handleLihatMenu();}
            case 4 -> {System.out.println("--------------Bayar Bill----------------");handleBayarBill();}
            case 5 -> {System.out.println("--------------Cek Saldo----------------");handleCekSaldo();}
=======
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

import assignments.assignment1.OrderGenerator;
import assignments.assignment3.Order;
import assignments.assignment3.Restaurant;
import assignments.assignment3.payment.CreditCardPayment;
import assignments.assignment3.payment.DepeFoodPaymentSystem;

public class CustomerSystemCLI extends UserSystemCLI {

    @Override
    boolean handleMenu(int choice) {
        switch (choice) {
            case 1 -> handleBuatPesanan();
            case 2 -> handleCetakBill();
            case 3 -> handleLihatMenu();
            case 4 -> handleBayarBill();
            case 5 -> handleCekSaldo();
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
            case 6 -> {
                return false;
            }
            default -> System.out.println("Perintah tidak diketahui, silakan coba kembali");
        }
        return true;
    }

<<<<<<< HEAD
     // Method untuk display menu untuk User dengan role Customer
    protected void displayMenu() {
=======
    @Override
    void displayMenu() {
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
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

<<<<<<< HEAD
    // Method untuk handle ketika customer membuat pesanan
    protected void handleBuatPesanan(){
        System.out.print("Nama Restoran: ");
        String namaResto = input.nextLine();
        boolean cek = true;
        Restaurant resto = new Restaurant(); 
        ArrayList<Restaurant> restoList= MainMenu.getRestaurants();
        
        // Validasi restolist
        if(restoList.isEmpty()){ 
            System.out.println("Belum ada restoran yang terdaftar pada sistem!");
            return;
        }
        // Validasi nama restaurant
        for(int i = 0; i<restoList.size(); i++){
            if(restoList.get(i).namaRestoran.equals(namaResto)){
                resto = restoList.get(i); 
                break;
            }else if(i == restoList.size()-1){ // Saat nama restaurant tidak terdaftar pada sistem
                System.out.println("Restoran tidak terdaftar pada sistem.\n"); 
                cek = false;
            }
        }

        // Saat nama restaurant tidak valid
        if(!cek){
            handleBuatPesanan();
            return;
        }
        System.out.print("Tanggal Pemesanan (DD/MM/YYYY): ");
        String tanggalPemesanan = input.nextLine(); 
        
        // Validasi format tanggal pemesanan
        if ((tanggalPemesanan.charAt(2)!='/') || (tanggalPemesanan.charAt(5)!='/') || tanggalPemesanan.length()!=10||
            (Character.getNumericValue(tanggalPemesanan.charAt(3))*10+Character.getNumericValue(tanggalPemesanan.charAt(4)))>12){
            System.out.println("Tanggal Pemesanan dalam format DD/MM/YYYY!\n");
            handleBuatPesanan();
            return;
        }

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

        // Validasi nama menu 
        for (int i = 0; i<listNamaMenu.size(); i++){ 
            for(int j = 0; j<resto.menu.size(); j++){
                if(listNamaMenu.get(i).equals(resto.menu.get(j).namaMakanan)){
                    listMenu.add(resto.menu.get(j)); 
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

        // Saat nama restaurant tidak valid
        if(!cek){
            handleBuatPesanan();
            return;
        }
        Order order = new Order("", tanggalPemesanan, 0, resto, listMenu); 
        order.setOrderID(MainMenu.getUserLoggedIn().getNoTelepon()); 
        switch(MainMenu.getUserLoggedIn().getLokasi()){ 
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
        MainMenu.getUserLoggedIn().addOrder(order); 
        System.out.println("Pesanan dengan ID "+order.getOrderID()+" diterima!\n");
    }

    // Method untuk handle ketika customer ingin cetak bill
    protected void handleCetakBill(){
        System.out.print("Masukkan Order ID: "); 
        String orderID = input.nextLine(); 
        User userLoggedIn = MainMenu.getUserLoggedIn();
        
        // Validasi order history
        if (userLoggedIn.getOrderHistory().isEmpty()){ 
            System.out.println("User "+userLoggedIn.getName()+" belum melakukan order!");
            return;
        }
        // Validasi order id
        for (int i = 0; i<userLoggedIn.getOrderHistory().size(); i++){
            if (orderID.equals(userLoggedIn.getOrderHistory().get(i).getOrderID())){
                System.out.println(userLoggedIn.getOrderHistory().get(i)); 
                break;
            }else if(i == userLoggedIn.getOrderHistory().size()-1){ // Saat order id tidak ditemukan dalam order history user
                System.out.println("Order ID tidak dapat ditemukan.\n");
                handleCetakBill();
            }
        }
        
    }

    // Method untuk handle ketika customer ingin melihat menu
    protected void handleLihatMenu(){
        System.out.print("Nama Restoran: ");
        String namaResto = input.nextLine();
        ArrayList<Restaurant> restoList = MainMenu.getRestaurants();
        
        // Validasi restolist
        if(restoList.isEmpty()){
            System.out.println("Belum ada restoran yang terdaftar pada sistem!");
            return;
        }
        // Validasi nama restaurant
        for (int i = 0; i<restoList.size(); i++){ 
            if(namaResto.equalsIgnoreCase(restoList.get(i).namaRestoran)){
                System.out.println(restoList.get(i).showMenu());
                break;
            }else if(i == restoList.size()-1){
                System.out.println("Restoran tidak terdaftar pada sistem.\n"); // Saat restoran tidak ada dalam sistem
                handleLihatMenu();
            }
        }
        
    }

    // Method untuk handle ketika customer ingin bayar bill
    protected void handleBayarBill(){
        System.out.print("Masukkan Order ID: "); 
        String orderID = input.nextLine(); 
        User userLoggedIn = MainMenu.getUserLoggedIn();

        // Validasi order history 
        if (userLoggedIn.getOrderHistory().isEmpty()){
            System.out.println("User "+userLoggedIn.getName()+" belum melakukan order!");
            return;
        }
        // Validasi order id
        for (int i = 0; i<userLoggedIn.getOrderHistory().size(); i++){ 
            if (orderID.equals(userLoggedIn.getOrderHistory().get(i).getOrderID())){
                if(userLoggedIn.getOrderHistory().get(i).statusBayar){
                    System.out.println("Pesanan dengan ID ini sudah lunas!");
                    return;
                }
                System.out.println(userLoggedIn.getOrderHistory().get(i)); 
            }else if(i == userLoggedIn.getOrderHistory().size()-1){
                System.out.println("Order ID tidak dapat ditemukan.\n"); 
                handleCetakBill();
                return;
            }
        }
        
        // Menu untuk pembayaran
        System.out.println("\nOpsi Pembayaran:");
        System.out.println("1. Credit Card");
        System.out.println("2. Debit");
        int pilihanMetode = 0;
        boolean cekMetodePembayaran = false;
        
        // Mengecek metode pembayaran
        while(!cekMetodePembayaran){
            System.out.print("Pilihan Metode Pembayaran: ");
            pilihanMetode = input.nextInt();
            if(pilihanMetode < 1 || pilihanMetode > 2){
                System.out.println("Metode Pembayaran tidak valid.");
                continue;
            }
            if(pilihanMetode == 1){
                cekMetodePembayaran = MainMenu.getUserLoggedIn().getPayment() instanceof CreditCardPayment;
            }else if(pilihanMetode == 2){
                cekMetodePembayaran = MainMenu.getUserLoggedIn().getPayment() instanceof DebitPayment;
            }
            
            // Saat metode pembayaran tdiak sesuai
            if(!cekMetodePembayaran){
                System.out.println("User belum memiliki metode pembayaran ini");
            }
        }

        // Menjalankan proses pembayaran sesuai metode pembayaran user
        if(pilihanMetode == 2){
            long totalBiaya = MainMenu.getUserLoggedIn().getOrder(orderID).getTotalBiaya();
            
            // Validasi total biaya
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
                MainMenu.getUserLoggedIn().setSaldo(saldoMinus); // Mengurangi saldo user setelah pembayaran
                MainMenu.getUserLoggedIn().getOrder(orderID).getRestaurant().setSaldoResto(hasilPayment); // Menambahkan saldo user setelah pembayaran
                MainMenu.getUserLoggedIn().getOrder(orderID).setOrderFinished(true);
                MainMenu.getUserLoggedIn().getOrder(orderID).statusBayar = true;
                System.out.println("Berhasil Membayar Bill sebesar Rp " +hasilPayment);
            }
        }else{
            long totalBiaya = MainMenu.getUserLoggedIn().getOrder(orderID).getTotalBiaya();
            long hasilPayment = MainMenu.getUserLoggedIn().getPayment().processPayment(totalBiaya);
            long transactionFee = ((CreditCardPayment)MainMenu.getUserLoggedIn().getPayment()).countTransaction(hasilPayment);
            long saldoMinus = MainMenu.getUserLoggedIn().getSaldo() - (hasilPayment+transactionFee);
            if (saldoMinus <= 0){
                System.out.println("Tidak berhasil membayar bill karena saldo tidak cukup");
                return;
            }
            MainMenu.getUserLoggedIn().setSaldo(saldoMinus); // Mengurangi saldo user setelah pembayaran
            MainMenu.getUserLoggedIn().getOrder(orderID).getRestaurant().setSaldoResto(hasilPayment); // Menambahkan saldo restaurant setalah pembayaran
            MainMenu.getUserLoggedIn().getOrder(orderID).setOrderFinished(true);
            MainMenu.getUserLoggedIn().getOrder(orderID).statusBayar = true;
            System.out.println("Berhasil Membayar Bill sebesar Rp " + hasilPayment +" dengan biaya transaksi sebesar Rp "+transactionFee);
        }
        
    }

    // Method untuk handle ketika customer ingin cek saldo
    public void handleCekSaldo(){
        long saldo = MainMenu.getUserLoggedIn().getSaldo();
        System.out.println("Sisa saldo sebesar Rp "+saldo);
=======
    private void handleBuatPesanan() {
        System.out.println("--------------Buat Pesanan----------------");
        while (true) {
            System.out.print("Nama Restoran: ");
            String restaurantName = input.nextLine().trim();
            Restaurant restaurant = getRestaurantByName(restaurantName);
            if (restaurant == null) {
                System.out.println("Restoran tidak terdaftar pada sistem.\n");
                continue;
            }

            System.out.print("Tanggal Pemesanan (DD/MM/YYYY): ");
            String tanggalPemesanan = input.nextLine().trim();
            if (!OrderGenerator.validateDate(tanggalPemesanan)) {
                System.out.println("Masukkan tanggal sesuai format (DD/MM/YYYY)");
                System.out.println();
                continue;
            }

            System.out.print("Jumlah Pesanan: ");
            int jumlahPesanan = Integer.parseInt(input.nextLine().trim());
            System.out.println("Order: ");

            List<String> listMenuPesananRequest = new ArrayList<>();

            for (int i = 0; i < jumlahPesanan; i++) {
                listMenuPesananRequest.add(input.nextLine().trim());
            }

            if (!validateRequestPesanan(restaurant, listMenuPesananRequest)) {
                System.out.println("Mohon memesan menu yang tersedia di Restoran!");
                continue;
            }

            Order order = new Order(
                    OrderGenerator.generateOrderID(restaurantName, tanggalPemesanan, userLoggedIn.getNomorTelepon()),
                    tanggalPemesanan,
                    OrderGenerator.calculateDeliveryCost(userLoggedIn.getLokasi()),
                    restaurant,
                    getMenuRequest(restaurant, listMenuPesananRequest));

            System.out.printf("Pesanan dengan ID %s diterima!", order.getOrderId());
            userLoggedIn.addOrderHistory(order);
            return;
        }
    }

    private void handleCetakBill() {
        System.out.println("--------------Cetak Bill----------------");
        while (true) {
            System.out.print("Masukkan Order ID: ");
            String orderId = input.nextLine().trim();
            Order order = getOrderOrNull(orderId);
            if (order == null) {
                System.out.println("Order ID tidak dapat ditemukan.\n");
                continue;
            }
            System.out.println("");
            System.out.print(outputBillPesanan(order));
            return;
        }

    }

    void handleLihatMenu() {
        System.out.println("--------------Lihat Menu----------------");
        while (true) {
            System.out.print("Nama Restoran: ");
            String restaurantName = input.nextLine().trim();
            Restaurant restaurant = getRestaurantByName(restaurantName);
            if (restaurant == null) {
                System.out.println("Restoran tidak terdaftar pada sistem.\n");
                continue;
            }
            System.out.print(restaurant.printMenu());
            return;
        }
    }

    void handleUpdateStatusPesanan(Order order) {
        order.setOrderFinished(true);
    }

    void handleBayarBill() {
        System.out.println("--------------Bayar Bill----------------");
        while (true) {
            System.out.print("Masukkan Order ID: ");
            String orderId = input.nextLine().trim();

            Order order = getOrderOrNull(orderId);

            if (order == null) {
                System.out.println("Order ID tidak dapat ditemukan.\n");
                continue;
            }

            if (order.getOrderFinished()) {
                System.out.println("Pesanan dengan ID ini sudah lunas!\n");
                return;
            }

            System.out.println(outputBillPesanan(order));

            System.out.println("Opsi Pembayaran:");
            System.out.println("1. Credit Card");
            System.out.println("2. Debit");

            System.out.print("Pilihan Metode Pembayaran: ");
            String paymentOption = input.nextLine().trim();

            if (!paymentOption.equals("1") && !paymentOption.equals("2")) {
                System.out.println("Pilihan tidak valid, silakan coba kembali\n");
                continue;
            }

            DepeFoodPaymentSystem paymentSystem = userLoggedIn.getPaymentSystem();

            boolean isCreditCard = paymentSystem instanceof CreditCardPayment;

            if ((isCreditCard && paymentOption.equals("2")) || (!isCreditCard && paymentOption.equals("1"))) {
                System.out.println("User belum memiliki metode pembayaran ini!\n");
                continue;
            }

            long amountToPay = 0;

            try {
                amountToPay = paymentSystem.processPayment(userLoggedIn.getSaldo(), (long) order.getTotalHarga());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println();
                continue;
            }

            long saldoLeft = userLoggedIn.getSaldo() - amountToPay;

            userLoggedIn.setSaldo(saldoLeft);
            handleUpdateStatusPesanan(order);

            DecimalFormat decimalFormat = new DecimalFormat();
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setGroupingSeparator('.');
            decimalFormat.setDecimalFormatSymbols(symbols);

            System.out.printf("Berhasil Membayar Bill sebesar Rp %s", decimalFormat.format(amountToPay));

            return;
        }
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
    }

    void handleCekSaldo() {
        System.out.println("--------------Cek Saldo----------------");

        DecimalFormat decimalFormat = new DecimalFormat();
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        decimalFormat.setDecimalFormatSymbols(symbols);

        System.out.printf("Sisa saldo sebesar Rp %s", decimalFormat.format(userLoggedIn.getSaldo()));
    }

}