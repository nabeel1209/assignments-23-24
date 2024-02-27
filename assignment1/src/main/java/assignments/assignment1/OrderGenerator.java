package assignments.assignment1;
import java.util.Scanner;

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
        System.err.println("||| . \\ ___  ___  ___ | __>___  ___  _| |||");
        System.out.println("||| | |/ ._>| . \\/ ._>| _>/ . \\/ . \\/ . |||");
        System.out.println("|||___/\\___.|  _/\\___.|_| \\___/\\___/\\___|||");
        System.out.println("||          |_|                          ||");
        System.out.println(">>=======================================<<");
        System.out.println();
        System.out.println("Pilih menu:");
        System.err.println("1. Generate Order ID");
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
        namaRestoran = namaRestoran.replaceAll(" ", "");
        namaRestoran = namaRestoran.substring(0, 4);
        hasil += namaRestoran.toUpperCase();
        
        tanggalOrder = tanggalOrder.replaceAll("/", "");
        hasil += tanggalOrder;
        
        int kodeTelp = 0;
        for (int i = 0; i < noTelepon.length(); i++){
            kodeTelp += Character.getNumericValue(noTelepon.charAt(i));
        }
        kodeTelp = kodeTelp%100;
        String strKodeTelp = Integer.toString(kodeTelp);
        if (strKodeTelp.length()==1){
            strKodeTelp = "0" + strKodeTelp;
        }
        hasil += strKodeTelp;
        int checksum1 = 0;
        int checksum2 = 0;
        for (int i = 0; i < hasil.length(); i++){
            if (Character.isDigit(hasil.charAt(i))){
                if (i%2 == 0){
                    checksum1 += Character.getNumericValue(hasil.charAt(i));
                }else{
                    checksum2 += Character.getNumericValue(hasil.charAt(i));
                }
            }else{
                if (i%2 == 0){ 
                    checksum1 += ((int)hasil.charAt(i)-55);
                }else{
                    checksum2 += ((int)hasil.charAt(i)-55);
                }
            }
        }
        
        checksum1 = checksum1 % 36;
        if (checksum1 >=0 && checksum1 <= 9){
            hasil += Integer.toString(checksum1);
        }else{
            hasil += (char) (checksum1+55);
        }

        checksum2 = checksum2 % 36;
        if (checksum2 >=0 && checksum2 <= 9){
            hasil += Integer.toString(checksum2);
        }else{
            hasil += (char) (checksum2+55);
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
        String tanggalPemesanan = OrderID.substring(4,6)+"/"+OrderID.substring(6, 8)+"/"+OrderID.substring(8,12);
        long ongkir=0;
        lokasi = lokasi.toUpperCase();
        switch(lokasi){
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
        for (int i = 0; i < orderID.length()-2; i++){
            if (Character.isDigit(orderID.charAt(i))){
                if (i%2 == 0){
                    checksum1 += Character.getNumericValue(orderID.charAt(i));
                }else{
                    checksum2 += Character.getNumericValue(orderID.charAt(i));
                }
            }else{
                if (i%2 == 0){ 
                    checksum1 += ((int)orderID.charAt(i)-55);
                }else{
                    checksum2 += ((int)orderID.charAt(i)-55);
                }
            }
        }
        if(Character.isDigit(orderID.charAt(14))){
            checker1 = (checksum1%36 == Character.getNumericValue(orderID.charAt(14)));
        }else{
            checker1 = (checksum1%36 == (int)orderID.charAt(14)-55);
        }
        if(Character.isDigit(orderID.charAt(15))){
            checker2 = (checksum2%36 == Character.getNumericValue(orderID.charAt(15)));
        }else{
            checker2 = (checksum2%36 == (int)orderID.charAt(15)-55);
        }

        return !(checker1 && checker2);
    }
    public static void menu2(){
        System.out.print("Order ID: ");
        String orderID= input.nextLine();        
        while (orderID.length()!=16){
            System.out.println("Order ID minimal 16 karakter\n");
            System.out.print("Order ID: ");
            orderID= input.nextLine();
        }
        boolean cek = checkerPola(orderID);
        while(cek){
            System.out.println("Silahkan masukkan Order ID yang valid!\n");
            System.out.print("Order ID: ");
            orderID = input.nextLine();
            while (orderID.length()!=16){
                System.out.println("Order ID minimal 16 karakter\n");
                System.out.print("Order ID: ");
                orderID= input.nextLine();
            }
            cek = checkerPola(orderID);
        }
        System.out.print("Lokasi Pengiriman: ");
        String lokPeng = input.nextLine();
        lokPeng = lokPeng.toUpperCase();
        while(!(lokPeng.equals("P") || lokPeng.equals("U") || 
            lokPeng.equals("T") || lokPeng.equals("S") || 
            lokPeng.equals("B"))){
            
            System.out.println("Harap masukkan lokasi pengiriman yang ada pada jangkauan!\n");
            System.out.print("Lokasi Pengiriman: ");
            lokPeng = input.nextLine();
            lokPeng = lokPeng.toUpperCase();
        }
        String bill = "\n"+generateBill(orderID, lokPeng);
        System.out.println(bill);
    }

    public static void menu1(){
        System.out.print("Nama Restoran: ");
        String namaRestoran = input.nextLine();
        if (namaRestoran.replaceAll(" ", "").length()<4){
            System.out.println("Harap masukkan nama restoran minimal 4 karakter\n");
            menu1();
        }
        else{
        System.out.print("Tanggal Pemesanan: ");
        String tanggalPemesanan = input.nextLine();
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // try{
        //     LocalDate.parse(tanggalPemesanan, formatter);
        // }catch(Exception e){
        //     System.out.println("Tanggal Pemesanan dalam format DD/MM/YYYY!\n");
        //     menu1();
        //     throw null;
        // }
        if ((tanggalPemesanan.charAt(2)!='/') || (tanggalPemesanan.charAt(5)!='/') || tanggalPemesanan.length()!=10){
            System.out.println("Tanggal Pemesanan dalam format DD/MM/YYYY!\n");
            menu1();
        }else{
            boolean cek = true;
            System.out.print("No. Telpon: ");
            String noTelp = input.nextLine();
            for(int i = 0;i < noTelp.length();i++){ 
                if(!Character.isDigit(noTelp.charAt(i))){
                    System.out.println("Harap masukkan nomor telepon dalam bentuk bilangan bulat positif\n");
                    cek = false;
                    break;
                }
            }
            if (!cek){
                menu1();
            }else{
                String kode = generateOrderID(namaRestoran, tanggalPemesanan, noTelp);
                System.out.println("OrderID "+ kode +" diterima!");
                }
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
