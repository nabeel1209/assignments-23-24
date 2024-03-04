package assignments.assignment1;
import java.util.Scanner;

// Nama:    Nabeel Muhammad
// NPM:     2306275166
// Kelas:   D

public class OrderGenerator {
    private static final Scanner input = new Scanner(System.in);
    /* 
    Anda boleh membuat method baru sesuai kebutuhan Anda
    Namun, Anda tidak boleh menghapus ataupun memodifikasi return type method yang sudah ada.
    */

    /*
     * Method  ini untuk menampilkan menu
     */
    public static void showMenu(){
        System.out.println(">>=======================================<<");
        System.out.println("|| ___                 ___             _ ||");
        System.out.println("||| . \\ ___  ___  ___ | __>___  ___  _| |||");
        System.out.println("||| | |/ ._>| . \\/ ._>| _>/ . \\/ . \\/ . |||");
        System.out.println("|||___/\\___.|  _/\\___.|_| \\___/\\___/\\___|||");
        System.out.println("||          |_|                          ||");
        System.out.println(">>=======================================<<");
        System.out.println();
        System.out.println("Pilih menu:");
        System.out.println("1. Generate Order ID");
        System.out.println("2. Generate Bill");
        System.out.println("3. Keluar");
        System.out.println("----------------------------");
    }

    /*
     * Method ini digunakan untuk membuat ID
     * dari nama restoran, tanggal order, dan nomor telepon
     * 
     * @return String Order ID dengan format sesuai pada dokumen soal
     */
    public static String generateOrderID(String namaRestoran, String tanggalOrder, String noTelepon) {
        // TODO:Lengkapi method ini sehingga dapat mengenerate Order ID sesuai ketentuan
        String hasil = "";
        hasil += namaRestoran.substring(0, 4).toUpperCase(); // Indexing String namaRestoran sesuai ketentuan dan buat menjadi UPPERCASE lalu concat ke dalam String hasil
    
        hasil += tanggalOrder.replaceAll("/", ""); // Hilangkan "/"" dari String tanggalOrder dan concat ke dalam String hasil  
        
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

    /*
     * Method ini digunakan untuk membuat bill
     * dari order id dan lokasi
     * 
     * @return String Bill dengan format sesuai di bawah:
     *          Bill:
     *          Order ID: [Order ID]
     *          Tanggal Pemesanan: [Tanggal Pemesanan]
     *          Lokasi Pengiriman: [Kode Lokasi]
     *          Biaya Ongkos Kirim: [Total Ongkos Kirim]
     */
    public static String generateBill(String OrderID, String lokasi){
        // TODO:Lengkapi method ini sehingga dapat mengenerate Bill sesuai ketentuan
        String bill = "";
        String tanggalPemesanan = OrderID.substring(4,6)+"/"+OrderID.substring(6, 8)+"/"+OrderID.substring(8,12); // Melakukan format pada String tanggal
        int ongkir = 0; 
        lokasi = lokasi.toUpperCase(); // Ubah lokasi menjadi UPPERCASE
        switch(lokasi){ // Percabangan dengan switch case sesuai ketentuan soal
            case "P": 
                ongkir = 10000;
                break;
            case "U": 
                ongkir = 20000;
                break;
            case "T": 
                ongkir = 35000;
                break;
            case "S": 
                ongkir = 40000;
                break;
            case "B": 
                ongkir = 60000;
                break;
        }
        bill += String.format("Bill:\nOrder ID: %s\nTanggal Pemesanan: %s\nLokasi Pengiriman: %s\nBiaya Ongkos Kirim: Rp %.3f\n", OrderID, tanggalPemesanan, lokasi, (float)ongkir/1000);
        return bill;
    }

    public static boolean checkerPola(String orderID){
        int checksum1 = 0;
        int checksum2 = 0;
        boolean checker1 = true;
        boolean checker2 = true;
        for (int i = 0; i < orderID.length()-2; i++){ // Looping order ID
            if (Character.isDigit(orderID.charAt(i))){ // Saat charAt(i) adalah digit 
                if (i%2 == 0){ // Saat i genap
                    checksum1 += Character.getNumericValue(orderID.charAt(i)); // Jumlahkan numeric value dari charAt(i) pada checksum 1
                }else{
                    checksum2 += Character.getNumericValue(orderID.charAt(i)); // Jumlahkan numeric value dari charAt(i) pada checksum 2
                }
            }else{
                if (i%2 == 0){ 
                    checksum1 += ((int)orderID.charAt(i)-55); // Jumlahkan (ascii value dari charAt(i) - 55) pada checksum 1
                }else{
                    checksum2 += ((int)orderID.charAt(i)-55); // Jumlahkan (ascii value dari charAt(i) - 55) pada checksum 2
                }
            }
        }
        if(Character.isDigit(orderID.charAt(14))){
            checker1 = (checksum1%36 == Character.getNumericValue(orderID.charAt(14))); // Cek kesamaan hasil antara nilai checksum 1 dengan nilai numerik dari charAt(i)
        }else{
            checker1 = (checksum1%36 == (int)orderID.charAt(14)-55); // Cek kesamaan hasil antara nilai checksum 1 dengan nilai ascii-55
        }
        if(Character.isDigit(orderID.charAt(15))){
            checker2 = (checksum2%36 == Character.getNumericValue(orderID.charAt(15))); // Cek kesamaan hasil antara nilai checksum 2 dengan nilai numerik dari charAt(i)
        }else{
            checker2 = (checksum2%36 == (int)orderID.charAt(15)-55); // Cek kesamaan hasil antara nilai checksum 2 dengan nilai ascii-55
        }

        return !(checker1 && checker2); // Return boolean hasil checker 1 dan 2
    }

    public static void menu1(){
        System.out.print("Nama Restoran: ");
        String namaRestoran = input.nextLine();
        namaRestoran = namaRestoran.replaceAll(" ", ""); // Hilangkan spasi dari string namaRestoran
        if (namaRestoran.length()<4){ // Saat input nama restoran tidak valid
            System.out.println("Nama restoran tidak valid\n"); 
            menu1();
        }
        else{
            System.out.print("Tanggal Pemesanan: ");
            String tanggalPemesanan = input.nextLine();
            if ((tanggalPemesanan.charAt(2)!='/') || (tanggalPemesanan.charAt(5)!='/') || tanggalPemesanan.length()!=10||(Character.getNumericValue(tanggalPemesanan.charAt(3))*10+Character.getNumericValue(tanggalPemesanan.charAt(4)))>12){
                System.out.println("Tanggal Pemesanan dalam format DD/MM/YYYY!\n"); // Saat tanggal pemesanan tidak sesuai format 
                menu1();
            }else{
                boolean cek = true;
                System.out.print("No. Telpon: ");
                String noTelp = input.nextLine();
                for(int i = 0;i < noTelp.length();i++){ 
                    if(!Character.isDigit(noTelp.charAt(i))){ // Saat input bukan bilangan bulat
                        System.out.println("Harap masukkan nomor telepon dalam bentuk bilangan bulat positif\n");
                        cek = false;
                        break;
                    }
                }
                if(!cek){ //
                    menu1();
                }else{ // Saat input valid semua 
                    String kode = generateOrderID(namaRestoran, tanggalPemesanan, noTelp);
                    System.out.println("OrderID "+ kode +" diterima!");
                }
            }
        }     
    }
    
    public static void menu2(){
        System.out.print("Order ID: ");
        String orderID= input.nextLine();        
        while (orderID.length()!=16){ // Cek panjang order id
            System.out.println("Order ID minimal 16 karakter\n");
            System.out.print("Order ID: ");
            orderID= input.nextLine();
        }
        boolean cek = checkerPola(orderID); // Cek pola berdasarkan code 39
        if(cek){ // Jika pola tidak sesuai
            System.out.println("Silahkan masukkan Order ID yang valid!\n");
            menu2();
        }else{
            System.out.print("Lokasi Pengiriman: ");
            String lokPeng = input.nextLine();
            lokPeng = lokPeng.toUpperCase();
            if(!(lokPeng.equals("P") || lokPeng.equals("U") || 
                lokPeng.equals("T") || lokPeng.equals("S") || 
                lokPeng.equals("B"))){ // Saat lokasi pengiriman tidak ada pada jangkauan
                
                System.out.println("Harap masukkan lokasi pengiriman yang ada pada jangkauan!\n");
                menu2();
            }else{
                String bill = "\n"+generateBill(orderID, lokPeng); // Saat input valid semua
                System.out.println(bill);
            }
        }
    }

    public static void main(String[] args) {
        // TODO: Implementasikan program sesuai ketentuan yang diberikan
        showMenu();
        while(true){
            System.out.print("Pilihan menu: ");
            int menu = input.nextInt();
            while (menu>3 || menu<1){
                System.out.println("Pilihan menu tidak valid");
                System.out.print("Pilihan menu: ");
                menu = input.nextInt();
            }
            if (menu == 1){
                input.nextLine();
                menu1();
            }else if (menu == 2){
                input.nextLine();
                menu2();
            }else if(menu == 3){
                System.out.println("Terima kasih telah menggunakan DepeFood!");
                break;
            }
            System.out.println("----------------------------");
            System.out.println("Pilih menu:");
            System.err.println("1. Generate Order ID");
            System.out.println("2. Generate Bill");
            System.out.println("3. Keluar");
            System.out.println("----------------------------");   
        }
    }
}
