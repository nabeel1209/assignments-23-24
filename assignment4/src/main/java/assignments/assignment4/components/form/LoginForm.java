package assignments.assignment4.components.form;

import assignments.assignment3.DepeFood;
import assignments.assignment3.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import assignments.assignment4.MainApp;
import assignments.assignment4.page.AdminMenu;
import assignments.assignment4.page.CustomerMenu;
import javafx.scene.image.*;
import javafx.scene.layout.HBox;
import javafx.scene.text.*;

public class LoginForm {
    private Stage stage;
    private MainApp mainApp; // MainApp instance
    private TextField nameInput;
    private TextField phoneInput;
    private String localDir = System.getProperty("user.dir");
    private String fontIntegral = "file:\\"+localDir+"\\resources\\font\\IntegralCF-Medium.ttf";
    private String logo = "file:\\"+localDir+"\\resources\\logo\\LOGO.png";
    private String fontMazzard = "file:\\"+localDir+"\\resources\\font\\mazzardsoftl.otf";

    public LoginForm(Stage stage, MainApp mainApp) { // Pass MainApp instance to constructor
        this.stage = stage;
        this.mainApp = mainApp; // Store MainApp instance
    }

    // Method untuk menampilkan komponen form login
    private Scene createLoginForm() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(190, 20, 30, 20));
        grid.setAlignment(Pos.TOP_CENTER);

        // Logo
        GridPane logoBox = new GridPane();
        Image logopng = new Image(logo);
        ImageView image = new ImageView(logopng);
        image.setFitWidth(140);
        image.setPreserveRatio(true);
        logoBox.setAlignment(Pos.TOP_CENTER);
        logoBox.getChildren().add(image);
        
        // Text Welcome
        Text welcome = new Text("Welcome to DepeFood");
        welcome.setFont(Font.loadFont(fontIntegral, 30));
        welcome.setFill(Color.WHITE);

        // Login Form Box
        GridPane loginBox = new GridPane();
        loginBox.setVgap(10);
        loginBox.setPadding(new Insets(30, 0, 0, 0));
        
        // Label Name
        Label nama = new Label("Name");
        nama.setFont(Font.loadFont(fontIntegral, 15));
        nama.setPadding(new Insets(0, 50, 10, 0));
        nama.setTextFill(Color.WHITE);

        // Label Phone Number
        Label noTelp = new Label("Phone Number");
        noTelp.setFont(Font.loadFont(fontIntegral, 15));
        noTelp.setPadding(new Insets(0, 50, 0, 0));
        noTelp.setTextFill(Color.WHITE);

        // Input Nama
        nameInput = new TextField();
        nameInput.setMinSize(220, 15);
        nameInput.setFont(Font.loadFont(fontMazzard, 15));
        
        // Input No Telp
        phoneInput = new TextField();
        phoneInput.setMinSize(220, 15);
        phoneInput.setFont(Font.loadFont(fontMazzard, 15));
        
        // Button box
        HBox buttonBox = new HBox(10);
        Button loginButton = new Button("Login!");
        loginButton.setTextFill(Color.WHITE);
        loginButton.setFont(Font.loadFont(fontIntegral, 15));
        loginButton.setStyle("-fx-background-color: #2931ea; ");
        loginButton.setOnAction(Event -> handleLogin());
        Button exitButton = new Button("Exit");
        exitButton.setTextFill(Color.WHITE);
        exitButton.setFont(Font.loadFont(fontIntegral, 15));
        exitButton.setStyle("-fx-background-color: #f60506; ");
        exitButton.setOnAction(Event -> stage.close());
        buttonBox.getChildren().addAll(loginButton, exitButton);

        // Setting for grid pane Login Box 
        GridPane.setConstraints(nama, 0, 0);
        GridPane.setConstraints(noTelp, 0, 1);
        GridPane.setConstraints(nameInput, 1, 0);
        GridPane.setConstraints(phoneInput, 1, 1);
        GridPane.setConstraints(buttonBox, 1, 2);
        loginBox.getChildren().addAll(nama, noTelp, nameInput, phoneInput, buttonBox);

        // Setting for grid pane Login Form Page
        GridPane.setConstraints(logoBox, 0, 0);
        GridPane.setConstraints(welcome, 0, 1);
        GridPane.setConstraints(loginBox, 0, 2);
        grid.setStyle("-fx-background-color: #1e1b2f; ");
        grid.getChildren().addAll(logoBox, welcome, loginBox);
        return new Scene(grid, 500, 700);
    }

    // Validasi isian form login
    private void handleLogin() {
        String nama = nameInput.getText();
        String noTelp = phoneInput.getText();
        if(nama.isBlank() || noTelp.isBlank()){
            Alert notValidAlert = new Alert(AlertType.ERROR);
            notValidAlert.setTitle("Login failed!");
            notValidAlert.setHeaderText("All input must be filled");
            Stage alertStage = (Stage) notValidAlert.getDialogPane().getScene().getWindow();
            alertStage.getIcons().add(new Image(logo));
            notValidAlert.show();
            return;
        }
        
        // Validasi noTelp
        try{
            long telpInt = Long.parseLong(noTelp);
        }catch(NumberFormatException e){
            Alert notValidAlert = new Alert(AlertType.ERROR);
            notValidAlert.setTitle("Login failed!");
            notValidAlert.setHeaderText("Phone number must be an integer!");
            Stage alertStage = (Stage) notValidAlert.getDialogPane().getScene().getWindow();
            alertStage.getIcons().add(new Image(logo));
            notValidAlert.show();
            return;
        }

        // Validasi User
        User user = DepeFood.getUser(nama, noTelp);
        if(user == null){
            Alert userNotFoundAlert = new Alert(AlertType.ERROR);
            userNotFoundAlert.setTitle("Login failed!");
            userNotFoundAlert.setHeaderText("User not found!");
            Stage alertStage = (Stage) userNotFoundAlert.getDialogPane().getScene().getWindow();
            alertStage.getIcons().add(new Image(logo));
            userNotFoundAlert.show();
            return;
        }
        
        // Set user for main app
        mainApp.setUser(user);
        DepeFood.setPenggunaLoggedIn(user);

        if(user.getRole().equals("Customer")){
            CustomerMenu customerMenu = new CustomerMenu(stage, mainApp, user);
            Scene baseCustomer = customerMenu.createBaseMenu();
            mainApp.setScene(baseCustomer);
        }else if(user.getRole().equals("Admin")){
            AdminMenu adminmenu = new AdminMenu(stage, mainApp, user);
            Scene baseAdmin = adminmenu.createBaseMenu();    
            mainApp.setScene(baseAdmin);
        }
        nameInput.clear();
        phoneInput.clear();
    }

    public Scene getScene(){
        return this.createLoginForm();
    }

}
