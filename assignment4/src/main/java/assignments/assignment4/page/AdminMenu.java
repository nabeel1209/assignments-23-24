package assignments.assignment4.page;


import assignments.assignment3.DepeFood;
import assignments.assignment3.Restaurant;
import assignments.assignment3.User;
import assignments.assignment3.Menu;
import assignments.assignment4.MainApp;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.image.*;

public class AdminMenu extends MemberMenu{
    private Stage stage;
    private Scene scene;
    private User user;
    private Scene addRestaurantScene;
    private Scene addMenuScene;
    private Scene viewRestaurantsScene;
    private MainApp mainApp; // Reference to MainApp instance
    private String localDir = System.getProperty("user.dir");
    private String fontIntegral = "file:\\"+localDir+"\\resources\\font\\IntegralCF-Medium.ttf";
    private String fontMazzard = "file:\\"+localDir+"\\resources\\font\\mazzardsoftl.otf";
    private String folderImage = "file:\\"+localDir+"\\resources\\buttonImage\\";

    public AdminMenu(Stage stage, MainApp mainApp, User user) {
        this.stage = stage;
        this.mainApp = mainApp;
        this.user = user; // Store the user
        this.scene = createBaseMenu();
        this.addRestaurantScene = createAddRestaurantForm();
        this.addMenuScene = createAddMenuForm();
        this.viewRestaurantsScene = createViewRestaurantsForm();
    }

    // Method ini untuk menampilkan menu untuk Admin
    @Override
    public Scene createBaseMenu() {
        VBox menuLayout = new VBox(40);
        menuLayout.setStyle("-fx-background-color: #1e1b2f; ");
        menuLayout.setAlignment(Pos.TOP_LEFT);
        menuLayout.setPadding(new Insets(30, 30,0,30));
        
        // Label welcome admin
        HBox welcomeBox = new HBox(70);
        welcomeBox.setAlignment(Pos.CENTER_LEFT);
        Label welcomeAdmin = new Label("Selamat Datang, \n"+ user.getNama());
        welcomeAdmin.setFont(Font.loadFont(fontIntegral, 25));
        welcomeAdmin.setTextFill(Color.WHITE);
        Button logout = new Button("Log Out");
        logout.setFont(Font.loadFont(fontIntegral, 15));
        logout.setTextFill(Color.WHITE);
        logout.setStyle("-fx-background-color: #f60506; ");
        welcomeBox.getChildren().addAll(welcomeAdmin, logout);

        // Label pesan
        Label pesan = new Label("Mau ngapain nih?");
        pesan.setFont(Font.loadFont(fontIntegral, 20));
        pesan.setTextFill(Color.WHITE);
        pesan.setPadding(new Insets(20,0,0,0));
        
        // Button tambah restoran
        HBox tambahRestoBox = new HBox(20);
        tambahRestoBox.setAlignment(Pos.CENTER_LEFT);
        Image restoImage = new Image(folderImage+"Restaurant.png");
        ImageView imageViewResto = new ImageView(restoImage);
        imageViewResto.setFitWidth(80);
        imageViewResto.setPreserveRatio(true);
        Button tambahRestoran = new Button("Tambah Restoran");
        tambahRestoran.setFont(Font.loadFont(fontIntegral, 15));
        tambahRestoran.setTextFill(Color.WHITE);
        tambahRestoran.setStyle("-fx-background-color: #2931ea; ");
        tambahRestoBox.getChildren().addAll(imageViewResto, tambahRestoran);

        // Button tambah menu restoran 
        HBox tambahMenuBox = new HBox(20);
        tambahMenuBox.setAlignment(Pos.CENTER_LEFT);
        Image menuImage = new Image(folderImage+"Menu.png");
        ImageView imageViewMenu = new ImageView(menuImage);
        imageViewMenu.setFitWidth(80);
        imageViewMenu.setPreserveRatio(true);
        Button tambahMenuRestoran = new Button("Tambah Menu Restoran");
        tambahMenuRestoran.setFont(Font.loadFont(fontIntegral, 15));
        tambahMenuRestoran.setTextFill(Color.WHITE);
        tambahMenuRestoran.setStyle("-fx-background-color: #2931ea; ");
        tambahMenuBox.getChildren().addAll(imageViewMenu, tambahMenuRestoran);

        // Button lihat resto
        HBox lihatRestoBox = new HBox(20);
        lihatRestoBox.setAlignment(Pos.CENTER_LEFT);
        Image lihatRestoImage = new Image(folderImage+"ListResto.png");
        ImageView imageViewLihatResto = new ImageView(lihatRestoImage);
        imageViewLihatResto.setFitWidth(80);
        imageViewLihatResto.setPreserveRatio(true);
        Button lihatRestoran = new Button("Lihat Daftar Restoran");
        lihatRestoran.setFont(Font.loadFont(fontIntegral, 15));
        lihatRestoran.setTextFill(Color.WHITE);
        lihatRestoran.setStyle("-fx-background-color: #2931ea; ");
        lihatRestoBox.getChildren().addAll(imageViewLihatResto, lihatRestoran);

        // Set action button
        logout.setOnAction(Event->mainApp.logout());
        tambahRestoran.setOnAction(Event->mainApp.setScene(createAddRestaurantForm()));
        tambahMenuRestoran.setOnAction(Event->mainApp.setScene(createAddMenuForm()));
        lihatRestoran.setOnAction(Event->mainApp.setScene(createViewRestaurantsForm()));
        menuLayout.getChildren().addAll(welcomeBox, pesan, tambahRestoBox, tambahMenuBox, lihatRestoBox);
        
        return new Scene(menuLayout, 500, 700);
    }

    // Method ini untuk menampilkan page tambah restoran
    private Scene createAddRestaurantForm() {
        VBox layout = new VBox(10);
        layout.setStyle("-fx-background-color: #1e1b2f; ");
        layout.setAlignment(Pos.TOP_LEFT);
        layout.setPadding(new Insets(30, 30,0,30));
        
        // Label tambah restoran
        Label labelTambahResto = new Label("Tambah Restoran");
        labelTambahResto.setFont(Font.loadFont(fontIntegral, 25));
        labelTambahResto.setTextFill(Color.WHITE);
        labelTambahResto.setPadding(new Insets(0,0,80,0));

        // Box tambah resto
        GridPane tambahRestoPane = new GridPane(40, 10);
        Label namaRestoLabel = new Label("Nama restoran");
        namaRestoLabel.setFont(Font.loadFont(fontIntegral, 15));
        namaRestoLabel.setTextFill(Color.WHITE);

        TextField inputNamaResto = new TextField();
        inputNamaResto.setMinSize(220, 15);
        inputNamaResto.setFont(Font.loadFont(fontMazzard, 15));
        inputNamaResto.setPromptText("Masukkan nama restoran");

        // Box untuk button
        HBox buttonBox = new HBox(10);
        
        // Button submit untuk proses data
        Button submit = new Button("Submit");
        submit.setFont(Font.loadFont(fontIntegral, 15));
        submit.setTextFill(Color.WHITE);
        submit.setStyle("-fx-background-color: #2931ea; ");

        // Button kembali untuk kembali ke page sebelumnya
        Button kembali = new Button("Kembali");
        kembali.setFont(Font.loadFont(fontIntegral, 15));
        kembali.setTextFill(Color.WHITE);
        kembali.setStyle("-fx-background-color: #f60506; ");
        buttonBox.getChildren().addAll(submit, kembali);

        // Setting untuk grid pane
        GridPane.setConstraints(namaRestoLabel, 0, 0);
        GridPane.setConstraints(inputNamaResto, 1, 0);
        GridPane.setConstraints(buttonBox, 1, 1);
        tambahRestoPane.getChildren().addAll(namaRestoLabel, inputNamaResto, buttonBox);

        // Set action untuk button
        submit.setOnAction(Event->handleTambahRestoran(inputNamaResto.getText()));
        kembali.setOnAction(Event->mainApp.setScene(scene));

        layout.getChildren().addAll(labelTambahResto, tambahRestoPane);
        return new Scene(layout, 500, 700);
    }

    // Method ini untuk menampilkan page tambah menu restoran
    private Scene createAddMenuForm() {
        VBox layout = new VBox(20);
        layout.setStyle("-fx-background-color: #1e1b2f; ");
        layout.setAlignment(Pos.TOP_LEFT);
        layout.setPadding(new Insets(30, 30,0,30));
        
        // Label tambah menu restoran
        Label labelTambahMenuResto = new Label("Tambah Menu Restoran");
        labelTambahMenuResto.setFont(Font.loadFont(fontIntegral, 25));
        labelTambahMenuResto.setTextFill(Color.WHITE);
        labelTambahMenuResto.setPadding(new Insets(0,0,80,0));

        // Box tambah menu resto
        GridPane tambahMenuPane = new GridPane(40, 10);
        Label namaRestoLabel = new Label("Nama Restoran");
        namaRestoLabel.setFont(Font.loadFont(fontIntegral, 15));
        namaRestoLabel.setTextFill(Color.WHITE);

        TextField restoInput = new TextField();
        restoInput.setPromptText("Masukkan nama restoran");
        restoInput.setFont(Font.loadFont(fontMazzard, 15));
        
        Label namaMenuLabel = new Label("Nama Menu");
        namaMenuLabel.setFont(Font.loadFont(fontIntegral, 15));
        namaMenuLabel.setTextFill(Color.WHITE);

        TextField namaMenuInput = new TextField();
        namaMenuInput.setPromptText("Masukkan nama menu");
        namaMenuInput.setFont(Font.loadFont(fontMazzard, 15));

        Label hargaLabel = new Label("Harga");
        hargaLabel.setFont(Font.loadFont(fontIntegral, 15));
        hargaLabel.setTextFill(Color.WHITE);

        TextField hargaInput = new TextField();
        hargaInput.setPromptText("Masukkan harga menu");
        hargaInput.setFont(Font.loadFont(fontMazzard, 15));

        // Box untuk button
        HBox buttonBox = new HBox(10);
        
        // Button add menu untuk proses data
        Button addMenu = new Button("Tambah menu");
        addMenu.setFont(Font.loadFont(fontIntegral, 15));
        addMenu.setTextFill(Color.WHITE);
        addMenu.setStyle("-fx-background-color: #2931ea; ");

        // Button kembali untuk kembali ke page sebelumnya
        Button kembali = new Button("Kembali");
        kembali.setFont(Font.loadFont(fontIntegral, 15));
        kembali.setTextFill(Color.WHITE);
        kembali.setStyle("-fx-background-color: #f60506; ");
        buttonBox.getChildren().addAll(addMenu, kembali);

        // Setting untuk grid pane
        GridPane.setConstraints(namaRestoLabel, 0, 0);
        GridPane.setConstraints(restoInput, 1, 0);
        GridPane.setConstraints(namaMenuLabel, 0, 1);
        GridPane.setConstraints(namaMenuInput, 1, 1);
        GridPane.setConstraints(hargaLabel, 0, 2);
        GridPane.setConstraints(hargaInput, 1, 2);
        GridPane.setConstraints(buttonBox, 1, 3);

        // Set action untuk button
        addMenu.setOnAction(Event->handleTambahMenuRestoran(DepeFood.getRestaurantByName(restoInput.getText()), namaMenuInput.getText(), hargaInput.getText()));
        kembali.setOnAction(Event->mainApp.setScene(scene));

        tambahMenuPane.getChildren().addAll(namaRestoLabel, restoInput, namaMenuLabel, namaMenuInput, hargaLabel, hargaInput, buttonBox);
        layout.getChildren().addAll(labelTambahMenuResto, tambahMenuPane);
        return new Scene(layout, 500, 700);
    }
    
    // Method ini untuk menampilkan page daftar restoran
    private Scene createViewRestaurantsForm() {
        VBox layout = new VBox(10);
        layout.setStyle("-fx-background-color: #1e1b2f; ");
        layout.setAlignment(Pos.TOP_LEFT);
        layout.setPadding(new Insets(30, 30,0,30));
        
        // Label lihat restoran
        Label labelLihatResto = new Label("Lihat Restoran");
        labelLihatResto.setFont(Font.loadFont(fontIntegral, 25));
        labelLihatResto.setTextFill(Color.WHITE);
        labelLihatResto.setPadding(new Insets(0,0,80,0));

        // Box nama restoran
        HBox namaRestoBox = new HBox(40);
        Label namaRestoLabel = new Label("Nama Restoran");
        namaRestoLabel.setFont(Font.loadFont(fontIntegral, 15));
        namaRestoLabel.setTextFill(Color.WHITE);

        ComboBox<String> listResto = new ComboBox<>();
        for (Restaurant x:DepeFood.getRestoList()){
            listResto.getItems().add(x.getNama());
        }
        listResto.setMinWidth(200);
        listResto.setPromptText("Pilih Restoran");
        listResto.getEditor().setFont(Font.loadFont(fontIntegral, 13));
        namaRestoBox.getChildren().addAll(namaRestoLabel, listResto);

        // Box untuk button
        HBox buttonBox = new HBox(10);
        
        // Button search untuk proses data
        Button search = new Button("Search");
        search.setFont(Font.loadFont(fontIntegral, 15));
        search.setTextFill(Color.WHITE);
        search.setStyle("-fx-background-color: #2931ea; ");

        // Button kembali untuk kembali ke page sebelumnya
        Button kembali = new Button("Kembali");
        kembali.setFont(Font.loadFont(fontIntegral, 15));
        kembali.setTextFill(Color.WHITE);
        kembali.setStyle("-fx-background-color: #f60506; ");
        buttonBox.getChildren().addAll(search, kembali);

        // Box untuk list view
        VBox listMenuBox = new VBox(20);
        listMenuBox.setAlignment(Pos.TOP_CENTER);
        Label listMenuLabel = new Label("Menu");
        listMenuLabel.setFont(Font.loadFont(fontIntegral, 15));
        listMenuLabel.setTextFill(Color.WHITE);
        
        ListView<String> listMenu = new ListView<>();
        listMenuBox.getChildren().addAll(listMenuLabel, listMenu);

        // Set action untuk button
        search.setOnAction(Event->{listMenu.getItems().clear();for(Menu x: DepeFood.getRestaurantByName(listResto.getValue()).getMenu()){
            listMenu.getItems().add(x.getNamaMakanan()+" - Rp"+x.getHarga());
        }; listMenu.refresh();});
        kembali.setOnAction(Event->mainApp.setScene(scene));
        layout.getChildren().addAll(labelLihatResto, namaRestoBox, buttonBox, listMenuBox);

        return new Scene(layout, 500, 700);
    }
    

    // Validasi isian nama Restoran
    private void handleTambahRestoran(String nama) {
        Restaurant resto = DepeFood.getRestaurantByName(nama);
        if(resto != null){
            showAlert("Gagal menambahkan restoran!", "Restoran sudah terdaftar dalam sistem", "Masukkan nama restoran yang belum terdaftar", AlertType.ERROR);
            return;
        }else if(nama.isBlank()){
            showAlert("Gagal menambahkan restoran!", "Nama restoran tidak valid", "Nama restoran tidak boleh kosong", AlertType.ERROR);
            return;
        }else if(nama.strip().length()<4){
            showAlert("Gagal menambahkan restoran!", "Nama restoran tidak valid", "Nama restoran minimal 4 huruf", AlertType.ERROR);
            return;
        }
        DepeFood.getRestoList().add(new Restaurant(nama));
        showAlert("Berhasil menambahkan restoran!", "Restoran "+nama+" berhasil ditambahkan", "", AlertType.INFORMATION);
    }

    // Validasi isian menu Restoran
    private void handleTambahMenuRestoran(Restaurant restaurant, String itemName, String price) {
        double priceDouble = 0;
        if(restaurant == null){
            showAlert("Gagal menambahkan menu pada restoran!", "Restoran tidak terdaftar pada sistem ", "", AlertType.ERROR);
            return;
        }else if(itemName.isBlank()){
            showAlert("Gagal menambahkan menu pada restoran!", "Menu "+itemName+" gagal ditambahkan pada restoran "+ restaurant.getNama(), "Nama menu tidak boleh kosong", AlertType.ERROR);
            return;
        }
        try{
            priceDouble = Double.parseDouble(price);
        }catch(NumberFormatException e){
            showAlert("Gagal menambahkan menu pada restoran!", "Menu "+itemName+" gagal ditambahkan pada restoran "+ restaurant.getNama(), "Harga harus berupa angka", AlertType.ERROR);
            return;
        }

        for(int i = 0; i<restaurant.getMenu().size();i++){
            if(restaurant.getMenu().get(i).getNamaMakanan().equals(itemName)){
                showAlert("Gagal menambahkan menu pada restoran!", "Menu "+itemName+" gagal ditambahkan pada restoran "+ restaurant.getNama(), "Menu sudah ada pada restoran", AlertType.ERROR);
                return;
            }
        }
        DepeFood.handleTambahMenuRestoran(restaurant, itemName, priceDouble);
        showAlert("Berhasil menambahkan menu pada restoran!", "Menu "+itemName+" berhasil ditambahkan pada restoran "+ restaurant.getNama(), "", AlertType.INFORMATION);
    }
}
