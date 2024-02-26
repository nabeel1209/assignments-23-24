package assignments.assignment1;
// import java.util.*;

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
        return "Bill";
    }

    public static void menu1(){
        System.out.print("Nama Restoran: ");
        String namaRestoran = input.nextLine();
        if (namaRestoran.length()<4){
            System.out.println("Harap masukkan nama restoran minimal 4 karakter\n");
            menu1();
        }
        
        System.out.print("Tanggal Pemesanan: ");
        String tanggalPemesanan = input.nextLine();
        if ((tanggalPemesanan.charAt(2)!='/') || (tanggalPemesanan.charAt(5)!='/')){
            System.out.println("Tanggal Pemesanan dalam format DD/MM/YYYY!\n");
            menu1();
            //kurang checker panjang
        }

        System.out.print("No. Telpon: ");
        String noTelp = input.nextLine();
        for(int i = 0;i < noTelp.length();i++){ 
            if(Character.isAlphabetic(noTelp.charAt(i))){
                System.out.println("Harap masukkan nomor telepon dalam bentuk bilangan bulat positif\n");
                menu1();
                break;
            }
        }
        String kode = generateOrderID(namaRestoran, tanggalPemesanan, noTelp);
        System.out.println("OrderID "+ kode +" diterima!");
    }
    
    public static void main(String[] args) {
        // TODO: Implementasikan program sesuai ketentuan yang diberikan
        showMenu();
        while(true){
            System.out.print("Pilihan menu: ");
            int menu = input.nextInt();
            if (menu>3 || menu<1){
                System.out.print("Pilihan menu tidak valid");
                System.out.print("Pilihan menu: ");
                menu = input.nextInt();
            }
            if (menu == 1){
                input.nextLine();
                menu1();
            }else if (menu == 2){

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
