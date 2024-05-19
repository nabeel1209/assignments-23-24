package assignments.assignment4.components;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import assignments.assignment1.OrderGenerator;
import assignments.assignment3.DepeFood;
import assignments.assignment3.Menu;
import assignments.assignment3.Order;
import assignments.assignment3.User;
import assignments.assignment4.MainApp;

public class BillPrinter {
    private Stage stage;
    private MainApp mainApp;
    private User user;
    private String localDir = System.getProperty("user.dir");
    private String fontIntegral = "file:\\" + localDir + "\\resources\\font\\IntegralCF-Medium.ttf";
    private String bill;

    public BillPrinter(Stage stage, MainApp mainApp, User user) {
        this.stage = stage;
        this.mainApp = mainApp;
        this.user = user;
    }

    private Scene createBillPrinterForm(String orderID){
        //TODO: Implementasi untuk menampilkan komponen hasil cetak bill
        VBox layout = new VBox(10);
        layout.setStyle("-fx-background-color: #1e1b2f; ");
        layout.setAlignment(Pos.TOP_LEFT);
        layout.setPadding(new Insets(30, 30, 0, 30));

        Label labelCetakBill = new Label("Cetak Bill");
        labelCetakBill.setFont(Font.loadFont(fontIntegral, 25));
        labelCetakBill.setTextFill(Color.WHITE);
        labelCetakBill.setPadding(new Insets(0, 0, 40, 0));
        
        Button kembali = new Button("Kembali");
        kembali.setFont(Font.loadFont(fontIntegral, 15));
        kembali.setTextFill(Color.WHITE);
        kembali.setStyle("-fx-background-color: #f60506; ");

        printBill(orderID);
        Label orderValidasi = new Label(bill);
        if(bill.equals("OrderID tidak valid")){
            orderValidasi.setFont(Font.loadFont(fontIntegral, 20));
            orderValidasi.setTextFill(Color.web("#f60506"));
            orderValidasi.setPadding(new Insets(0, 0, 40, 0));
        }else{
            orderValidasi.setFont(Font.loadFont(fontIntegral, 17));
            orderValidasi.setTextFill(Color.WHITE);
            orderValidasi.setPadding(new Insets(0, 0, 40, 0));
        }
        kembali.setOnAction(Event->mainApp.setScene(mainApp.getScene("Cetak Bill")));
        layout.getChildren().addAll(labelCetakBill, orderValidasi, kembali);
        return new Scene(layout, 500, 700);
    }

    private void printBill(String orderId) {
        //TODO: Implementasi validasi orderID
        Order order = null;
        for(int i = 0;i<DepeFood.getUserLoggedIn().getOrderHistory().size();i++){
            if(orderId.equals(DepeFood.getUserLoggedIn().getOrderHistory().get(i).getOrderId())){
                order = DepeFood.getUserLoggedIn().getOrderHistory().get(i);
                break;
            }
        }
        if(order == null){
            bill = "OrderID tidak valid";
        }else{
            bill =  "Bill\n"+
                    "Order ID:      "+order.getOrderId()+"\n"+
                    "Tanggal Pemesanan:     "+order.getTanggal()+"\n"+
                    "Restaurant:        "+order.getRestaurant().getNama()+"\n"+
                    "Lokasi Pengiriman:     "+DepeFood.getUserLoggedIn().getLokasi()+"\n";
            if(order.getOrderFinished()){
                bill += "Status Pengiriman:     Finished\nPesanan:      ";
            }else{
                bill += "Status Pengiriman:     Not Finished\nPesanan:      ";
            }
            for(Menu x:order.getItems()){
                bill += "\n- "+x.getNamaMakanan()+" Rp "+String.format("%.0f",x.getHarga());
            }
            bill += "\nBiaya Ongkos Kirim:      Rp "+order.getOngkir()+"\n"+
                    "Total Biaya:       Rp "+String.format("%.0f",order.getTotalHarga());
        }
    }

    public Scene getScene(String orderID) {
        return this.createBillPrinterForm(orderID);
    }

    // Class ini opsional
    public class MenuItem {
        private final StringProperty itemName;
        private final StringProperty price;

        public MenuItem(String itemName, String price) {
            this.itemName = new SimpleStringProperty(itemName);
            this.price = new SimpleStringProperty(price);
        }

        public StringProperty itemNameProperty() {
            return itemName;
        }

        public StringProperty priceProperty() {
            return price;
        }

        public String getItemName() {
            return itemName.get();
        }

        public String getPrice() {
            return price.get();
        }
    }
}
