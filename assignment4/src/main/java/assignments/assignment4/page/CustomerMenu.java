package assignments.assignment4.page;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.image.*;
import assignments.assignment1.OrderGenerator;
import assignments.assignment3.Order;
import assignments.assignment3.payment.*;
import assignments.assignment3.DepeFood;
import assignments.assignment3.Menu;
import assignments.assignment3.Restaurant;
import assignments.assignment3.User;
import assignments.assignment4.MainApp;
import assignments.assignment4.components.BillPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class CustomerMenu extends MemberMenu {
    private Stage stage;
    private Scene scene;
    private Scene addOrderScene;
    private Scene printBillScene;
    private Scene payBillScene;
    private Scene cekSaldoScene;
    private BillPrinter billPrinter; // Instance of BillPrinter
    private ComboBox<String> restaurantComboBox = new ComboBox<>();
    private static Label label = new Label();
    private MainApp mainApp;
    private List<Restaurant> restoList = new ArrayList<>();
    private User user;
    private String localDir = System.getProperty("user.dir");
    private String fontIntegral = "file:\\" + localDir + "\\resources\\font\\IntegralCF-Medium.ttf";
    private String fontMazzard = "file:\\" + localDir + "\\resources\\font\\mazzardsoftl.otf";
    private String folderImage = "file:\\" + localDir + "\\resources\\buttonImage\\";

    public CustomerMenu(Stage stage, MainApp mainApp, User user) {
        this.stage = stage;
        this.mainApp = mainApp;
        this.user = user; // Store the user
        this.scene = createBaseMenu();
        this.addOrderScene = createTambahPesananForm();
        this.billPrinter = new BillPrinter(stage, mainApp, this.user); // Pass user to BillPrinter constructor
        this.printBillScene = createBillPrinter();
        this.payBillScene = createBayarBillForm();
        this.cekSaldoScene = createCekSaldoScene();
    }

    @Override
    public Scene createBaseMenu() {
        // TODO: Implementasikan method ini untuk menampilkan menu untuk Customer
        VBox menuLayout = new VBox(10);
        menuLayout.setStyle("-fx-background-color: #1e1b2f; ");
        menuLayout.setAlignment(Pos.TOP_LEFT);
        menuLayout.setPadding(new Insets(30, 30, 0, 30));

        // Box welcome dan log out
        HBox welcomeBox = new HBox(70);
        welcomeBox.setAlignment(Pos.CENTER_LEFT);
        Label welcomeUser = new Label("Selamat Datang, \n" + user.getNama());
        welcomeUser.setFont(Font.loadFont(fontIntegral, 25));
        welcomeUser.setTextFill(Color.WHITE);
        Button logout = new Button("Log Out");
        logout.setFont(Font.loadFont(fontIntegral, 15));
        logout.setTextFill(Color.WHITE);
        logout.setStyle("-fx-background-color: #f60506; ");
        welcomeBox.getChildren().addAll(welcomeUser, logout);

        // Label pesan
        Label pesan = new Label("Mau ngapain nih?");
        pesan.setFont(Font.loadFont(fontIntegral, 20));
        pesan.setTextFill(Color.WHITE);
        pesan.setPadding(new Insets(20, 0, 90, 0));

        GridPane gridButton = new GridPane(90, 90);
        gridButton.setAlignment(Pos.BASELINE_CENTER);

        // Box button Buat Pesanan
        VBox buatPesananBox = new VBox(10);
        buatPesananBox.setAlignment(Pos.BOTTOM_CENTER);
        Image pesananImage = new Image(folderImage + "Pesanan.png");
        ImageView imageViewPesanan = new ImageView(pesananImage);
        imageViewPesanan.setFitWidth(80);
        imageViewPesanan.setPreserveRatio(true);
        Button buatPesanan = new Button("Buat Pesanan");
        buatPesanan.setFont(Font.loadFont(fontIntegral, 15));
        buatPesanan.setTextFill(Color.WHITE);
        buatPesanan.setStyle("-fx-background-color: #2931ea; ");
        buatPesananBox.getChildren().addAll(imageViewPesanan, buatPesanan);

        // Box button cetak Bill
        VBox cetakBillBox = new VBox(10);
        cetakBillBox.setAlignment(Pos.BOTTOM_CENTER);
        Image cetakBillImage = new Image(folderImage + "Bill.png");
        ImageView imageViewCetakBill = new ImageView(cetakBillImage);
        imageViewCetakBill.setFitWidth(80);
        imageViewCetakBill.setPreserveRatio(true);
        Button cetakBill = new Button("Cetak Bill");
        cetakBill.setFont(Font.loadFont(fontIntegral, 15));
        cetakBill.setTextFill(Color.WHITE);
        cetakBill.setStyle("-fx-background-color: #2931ea; ");
        cetakBillBox.getChildren().addAll(imageViewCetakBill, cetakBill);

        // Box button bayar bill
        VBox bayarBillBox = new VBox(10);
        bayarBillBox.setAlignment(Pos.BOTTOM_CENTER);
        Image bayarBillImage = new Image(folderImage + "BayarBill.png");
        ImageView imageViewBayarBill = new ImageView(bayarBillImage);
        imageViewBayarBill.setFitWidth(80);
        imageViewBayarBill.setPreserveRatio(true);
        Button bayarBill = new Button("Bayar Bill");
        bayarBill.setFont(Font.loadFont(fontIntegral, 15));
        bayarBill.setTextFill(Color.WHITE);
        bayarBill.setStyle("-fx-background-color: #2931ea; ");
        bayarBillBox.getChildren().addAll(imageViewBayarBill, bayarBill);

        // Box untuk cek saldo
        VBox cekSaldoBox = new VBox(10);
        cekSaldoBox.setAlignment(Pos.BOTTOM_CENTER);
        Image cekSaldoImage = new Image(folderImage + "Saldo.png");
        ImageView imageViewCekSaldo = new ImageView(cekSaldoImage);
        imageViewCekSaldo.setFitWidth(80);
        imageViewCekSaldo.setPreserveRatio(true);
        Button cekSaldo = new Button("Cek Saldo");
        cekSaldo.setFont(Font.loadFont(fontIntegral, 15));
        cekSaldo.setTextFill(Color.WHITE);
        cekSaldo.setStyle("-fx-background-color: #2931ea; ");
        cekSaldoBox.getChildren().addAll(imageViewCekSaldo, cekSaldo);

        GridPane.setConstraints(buatPesananBox, 0, 0);
        GridPane.setConstraints(cetakBillBox, 1, 0);
        GridPane.setConstraints(bayarBillBox, 0, 1);
        GridPane.setConstraints(cekSaldoBox, 1, 1);
        gridButton.getChildren().addAll(buatPesananBox, cetakBillBox, bayarBillBox, cekSaldoBox);

        buatPesanan.setOnAction(Event -> mainApp.setScene(createTambahPesananForm()));
        cetakBill.setOnAction(Event -> {mainApp.setScene(createBillPrinter());mainApp.addScene("Cetak Bill", createBillPrinter());});
        bayarBill.setOnAction(Event -> mainApp.setScene(createBayarBillForm()));
        cekSaldo.setOnAction(Event -> mainApp.setScene(createCekSaldoScene()));
        logout.setOnAction(Event -> mainApp.logout());
        menuLayout.getChildren().addAll(welcomeBox, pesan, gridButton);
        return new Scene(menuLayout, 500, 700);
    }

    private Scene createTambahPesananForm() {
        // TODO: Implementasikan method ini untuk menampilkan page tambah pesanan
        VBox layout = new VBox(10);
        layout.setStyle("-fx-background-color: #1e1b2f; ");
        layout.setAlignment(Pos.TOP_LEFT);
        layout.setPadding(new Insets(30, 30, 0, 30));

        // Label tambah pesanan
        Label labelTambahPesanan = new Label("Buat Pesanan");
        labelTambahPesanan.setFont(Font.loadFont(fontIntegral, 25));
        labelTambahPesanan.setTextFill(Color.WHITE);
        labelTambahPesanan.setPadding(new Insets(0, 0, 50, 0));

        // Box Input
        GridPane inputBox = new GridPane(40, 15);
        // HBox untuk nama restoran
        Label namaRestoLabel = new Label("Nama Restoran");
        namaRestoLabel.setFont(Font.loadFont(fontIntegral, 13));
        namaRestoLabel.setTextFill(Color.WHITE);

        ComboBox<String> listResto = new ComboBox<>();
        for (Restaurant x:DepeFood.getRestoList()){
            listResto.getItems().add(x.getNama());
        }
        listResto.setMinWidth(80);
        listResto.setPromptText("Pilih Restoran");
        listResto.getEditor().setFont(Font.loadFont(fontIntegral, 13));

        // Box tanggal
        Label tanggalLabel = new Label("Tanggal (DD/MM/YYYY)");
        tanggalLabel.setFont(Font.loadFont(fontIntegral, 13));
        tanggalLabel.setTextFill(Color.WHITE);

        TextField tanggalInput = new TextField();
        tanggalInput.setFont(Font.loadFont(fontMazzard, 15));
        tanggalInput.setPromptText("Masukkan tanggal (DD/MM/YYYY)");
        GridPane.setConstraints(namaRestoLabel, 0, 0);
        GridPane.setConstraints(listResto, 1, 0);
        GridPane.setConstraints(tanggalLabel, 0, 1);
        GridPane.setConstraints(tanggalInput, 1, 1);
        inputBox.getChildren().addAll(namaRestoLabel, listResto, tanggalLabel, tanggalInput);

        Button menuButton = new Button("Menu");
        menuButton.setFont(Font.loadFont(fontIntegral, 15));
        menuButton.setTextFill(Color.WHITE);
        menuButton.setStyle("-fx-background-color: #2931ea; ");

        VBox listMenuBox = new VBox(10);
        listMenuBox.setAlignment(Pos.TOP_CENTER);
        Label listMenuLabel = new Label("Menu");
        listMenuLabel.setFont(Font.loadFont(fontIntegral, 15));
        listMenuLabel.setTextFill(Color.WHITE);
        ListView<String> listMenu = new ListView<>();
        listMenu.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listMenuBox.getChildren().addAll(listMenuLabel, listMenu);

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.TOP_LEFT);
        Button buatPesanan = new Button("Buat Pesanan");
        buatPesanan.setFont(Font.loadFont(fontIntegral, 15));
        buatPesanan.setTextFill(Color.WHITE);
        buatPesanan.setStyle("-fx-background-color: #2931ea; ");
        Button kembali = new Button("Kembali");
        kembali.setFont(Font.loadFont(fontIntegral, 15));
        kembali.setTextFill(Color.WHITE);
        kembali.setStyle("-fx-background-color: #f60506; ");

        buttonBox.getChildren().addAll(buatPesanan, kembali);
        menuButton.setOnAction(Event->{listMenu.getItems().clear();for(Menu x: DepeFood.getRestaurantByName(listResto.getValue()).getMenu()){
            listMenu.getItems().add(x.getNamaMakanan());
        }; listMenu.refresh();});
        buatPesanan.setOnAction(Event->handleBuatPesanan(listResto.getValue(), tanggalInput.getText(),listMenu.getSelectionModel().getSelectedItems()));
        kembali.setOnAction(Event->mainApp.setScene(scene));

        layout.getChildren().addAll(labelTambahPesanan, inputBox, menuButton, listMenuBox, buttonBox);
        return new Scene(layout, 500, 700);
    }

    private Scene createBillPrinter() {
        // TODO: Implementasikan method ini untuk menampilkan page cetak bill
        VBox layout = new VBox(10);
        layout.setStyle("-fx-background-color: #1e1b2f; ");
        layout.setAlignment(Pos.TOP_LEFT);
        layout.setPadding(new Insets(30, 30, 0, 30));

        Label labelCetakBill = new Label("Cetak Bill");
        labelCetakBill.setFont(Font.loadFont(fontIntegral, 25));
        labelCetakBill.setTextFill(Color.WHITE);
        labelCetakBill.setPadding(new Insets(0, 0, 80, 0));

        GridPane inputBox = new GridPane(40, 20);
        Label orderIDLabel = new Label("OrderID");
        orderIDLabel.setFont(Font.loadFont(fontIntegral, 15));
        orderIDLabel.setTextFill(Color.WHITE);
        TextField orderIDInput = new TextField();
        orderIDInput.setPromptText("Masukkan Order ID");
        orderIDInput.setFont(Font.loadFont(fontMazzard, 15));

        HBox buttonBox = new HBox(10);
        Button printBill = new Button("Print Bill");
        printBill.setFont(Font.loadFont(fontIntegral, 15));
        printBill.setTextFill(Color.WHITE);
        printBill.setStyle("-fx-background-color: #2931ea; ");
        Button kembali = new Button("Kembali");
        kembali.setFont(Font.loadFont(fontIntegral, 15));
        kembali.setTextFill(Color.WHITE);
        kembali.setStyle("-fx-background-color: #f60506; ");
        buttonBox.getChildren().addAll(printBill, kembali);

        GridPane.setConstraints(orderIDLabel, 0, 0);
        GridPane.setConstraints(orderIDInput, 1, 0);
        GridPane.setConstraints(buttonBox, 1, 1);
        inputBox.getChildren().addAll(orderIDLabel, orderIDInput, buttonBox);

        printBill.setOnAction(Event->mainApp.setScene(billPrinter.getScene(orderIDInput.getText())));
        kembali.setOnAction(Event->mainApp.setScene(scene));

        layout.getChildren().addAll(labelCetakBill, inputBox);
        return new Scene(layout, 500, 700);
    }

    private Scene createBayarBillForm() {
        // TODO: Implementasikan method ini untuk menampilkan page bayar bill
        VBox layout = new VBox(10);
        layout.setStyle("-fx-background-color: #1e1b2f; ");
        layout.setAlignment(Pos.TOP_LEFT);
        layout.setPadding(new Insets(30, 30, 0, 30));

        Label labelBayarBill = new Label("Bayar Bill");
        labelBayarBill.setFont(Font.loadFont(fontIntegral, 25));
        labelBayarBill.setTextFill(Color.WHITE);
        labelBayarBill.setPadding(new Insets(0, 0, 80, 0));

        GridPane inputBox = new GridPane(40, 20);
        Label orderIDLabel = new Label("OrderID");
        orderIDLabel.setFont(Font.loadFont(fontIntegral, 15));
        orderIDLabel.setTextFill(Color.WHITE);
        TextField orderIDInput = new TextField();
        orderIDInput.setPromptText("Masukkan Order ID");
        orderIDInput.setFont(Font.loadFont(fontMazzard, 15));

        Label opsiPembayaranLabel = new Label("Opsi Pembayaran");
        opsiPembayaranLabel.setFont(Font.loadFont(fontIntegral, 15));
        opsiPembayaranLabel.setTextFill(Color.WHITE);

        ComboBox<String> listPembayaran = new ComboBox<>();
        listPembayaran.getItems().addAll(new String("Credit Card"), new String("Debit"));
        listPembayaran.setMinWidth(80);
        listPembayaran.setPromptText("Pilih Opsi Pembayaran");
        // listPembayaran.getEditor().setFont(Font.loadFont(fontIntegral, 13));

        HBox buttonBox = new HBox(10);
        Button bayar = new Button("Bayar");
        bayar.setFont(Font.loadFont(fontIntegral, 15));
        bayar.setTextFill(Color.WHITE);
        bayar.setStyle("-fx-background-color: #2931ea; ");
        Button kembali = new Button("Kembali");
        kembali.setFont(Font.loadFont(fontIntegral, 15));
        kembali.setTextFill(Color.WHITE);
        kembali.setStyle("-fx-background-color: #f60506; ");
        buttonBox.getChildren().addAll(bayar, kembali);


        bayar.setOnAction(Event->handleBayarBill(orderIDInput.getText(), (listPembayaran.getValue().equals("Debit"))?1:0));
        kembali.setOnAction(Event->mainApp.setScene(scene));

        GridPane.setConstraints(orderIDLabel, 0, 0);
        GridPane.setConstraints(orderIDInput, 1, 0);
        GridPane.setConstraints(opsiPembayaranLabel, 0, 1);
        GridPane.setConstraints(listPembayaran, 1, 1);
        GridPane.setConstraints(buttonBox, 1, 2);
        inputBox.getChildren().addAll(orderIDLabel, orderIDInput, opsiPembayaranLabel, listPembayaran, buttonBox);

        layout.getChildren().addAll(labelBayarBill, inputBox);
        return new Scene(layout, 500, 700);
    }

    private Scene createCekSaldoScene() {
        // TODO: Implementasikan method ini untuk menampilkan page cetak saldo
        VBox layout = new VBox(40);
        layout.setStyle("-fx-background-color: #1e1b2f; ");
        layout.setAlignment(Pos.TOP_LEFT);
        layout.setPadding(new Insets(30, 30, 0, 30));

        Label labelCekSaldo = new Label("Cek Saldo");
        labelCekSaldo.setFont(Font.loadFont(fontIntegral, 25));
        labelCekSaldo.setTextFill(Color.WHITE);
        labelCekSaldo.setPadding(new Insets(0, 0, 40, 0));

        String output = DepeFood.getUserLoggedIn().getNama()+"\n"+"Saldo:       "+DepeFood.getUserLoggedIn().getSaldo();

        Label labelOutput= new Label(output);
        labelOutput.setFont(Font.loadFont(fontIntegral, 20));
        labelOutput.setTextFill(Color.WHITE);

        Button kembali = new Button("Kembali");
        kembali.setFont(Font.loadFont(fontIntegral, 15));
        kembali.setTextFill(Color.WHITE);
        kembali.setStyle("-fx-background-color: #f60506; ");

        kembali.setOnAction(Event->mainApp.setScene(scene));

        layout.getChildren().addAll(labelCekSaldo, labelOutput, kembali);
        return new Scene(layout, 500, 700);
    }

    private void handleBuatPesanan(String namaRestoran, String tanggalPemesanan, List<String> menuItems) {
        // TODO: Implementasi validasi isian pesanan
        if(namaRestoran.isBlank()){
            showAlert("Gagal membuat pesanan!", "Nama restoran kosong", "Harap masukkan nama restoran", AlertType.ERROR);
            return;
        }else if(!OrderGenerator.validateDate(tanggalPemesanan)){
            showAlert("Gagal membuat pesanan!", "Tanggal tidak valid", "Harap masukkan tanggal dengan format (DD/MM/YYYY)", AlertType.ERROR);
            return;
        }else if(menuItems.isEmpty()){
            showAlert("Gagal membuat pesanan!", "Belum ada menu yang dipilih", "Harap pilih menu yang ingin ada pesan", AlertType.ERROR);
            return;
        }
        String orderID = OrderGenerator.generateOrderID(namaRestoran, tanggalPemesanan, DepeFood.getUserLoggedIn().getNomorTelepon());
        int ongkir = OrderGenerator.calculateDeliveryCost(DepeFood.getUserLoggedIn().getLokasi());
        Restaurant resto = DepeFood.getRestaurantByName(namaRestoran);
        Menu[] menu = new Menu[menuItems.size()];
        for(int i = 0;i<menuItems.size();i++){
            for(Menu x: resto.getMenu()){
                if(x.getNamaMakanan().equals(menuItems.get(i))){
                    menu[i]=x;
                    break;
                }
            }
        }
        Order order = new Order(orderID, tanggalPemesanan, ongkir, resto, menu);
        DepeFood.getUserLoggedIn().addOrderHistory(order);
        showAlert("Berhasil membuat pesanan", "Order dengan ID "+orderID+" berhasil ditambahkan", "", AlertType.INFORMATION);
    }

    private void handleBayarBill(String orderID, int pilihanPembayaran) {
        // TODO: Implementasi validasi pembayaran
        Order order = null;
        for(int i = 0;i<DepeFood.getUserLoggedIn().getOrderHistory().size();i++){
            if(orderID.equals(DepeFood.getUserLoggedIn().getOrderHistory().get(i).getOrderId())){
                order = DepeFood.getUserLoggedIn().getOrderHistory().get(i);
                break;
            }
        }
        if(order == null){
            showAlert("Gagal membayar bill", "OrderID tidak ditemukan pada sistem", "", AlertType.ERROR);
            return;
        }

        DepeFoodPaymentSystem metodePembayaran;
        boolean validasiPembayaran = false;
        if(pilihanPembayaran==1){
            validasiPembayaran = DepeFood.getUserLoggedIn().getPaymentSystem() instanceof DebitPayment;
            metodePembayaran = new DebitPayment();
        }else{
            validasiPembayaran = DepeFood.getUserLoggedIn().getPaymentSystem() instanceof CreditCardPayment;
            metodePembayaran = new CreditCardPayment();
        }
        if(!validasiPembayaran){
            showAlert("Gagal membayar bill", "Metode pembayaran yang dimiliki user berbeda", "",AlertType.ERROR);
            return;
        }

        if(metodePembayaran instanceof DebitPayment){
            if(order.getTotalHarga()<50000){
                showAlert("Gagal melakukan pembayaran", "Minimal order tidak terpenuhi", "", AlertType.ERROR);
                return;
            }else if(DepeFood.getUserLoggedIn().getSaldo() - (long)order.getTotalHarga() < 0){
                showAlert("Gagal melakukan pembayaran", "Saldo user tidak cukup", "", AlertType.ERROR);
                return;
            }
            DepeFood.getUserLoggedIn().setSaldo(DepeFood.getUserLoggedIn().getSaldo()- (long)order.getTotalHarga());
            showAlert("Berhasil membayar bill", "Berhasil membayar bill sebesar Rp "+String.format("%.0f",order.getTotalHarga()), "", AlertType.INFORMATION);
        }else{
            if(DepeFood.getUserLoggedIn().getSaldo() - (long)order.getTotalHarga() < 0){
                showAlert("Gagal melakukan pembayaran", "Saldo user tidak cukup", "", AlertType.ERROR);
                return;
            }
            DepeFood.getUserLoggedIn().setSaldo(DepeFood.getUserLoggedIn().getSaldo()-(long)order.getTotalHarga()-(long)(order.getTotalHarga()*0.02));
            showAlert("Berhasil membayar bill", "Berhasil membayar bill sebesar Rp "+String.format("%.0f",order.getTotalHarga())+" dengan biaya transaksi sebesar Rp "+String.format("%.0f",order.getTotalHarga()*0.02), "", AlertType.INFORMATION);
        }
    }
}