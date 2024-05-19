package assignments.assignment4.page;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public abstract class MemberMenu {
    private Scene scene;
    private String localDir = System.getProperty("user.dir");
    private String logo = "file:\\"+localDir+"\\resources\\logo\\LOGO.png";
    abstract protected Scene createBaseMenu();

    protected void showAlert(String title, String header, String content, Alert.AlertType c){
        Alert alert = new Alert(c);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
        alertStage.getIcons().add(new Image(logo));
        alert.showAndWait();
    }

    public Scene getScene(){
        return this.scene;
    }

    protected void refresh(){
        //TODO: Implemenetasi method ini untuk merefresh data yang dimiliki aplikasi
        // Hint: Method ini digunakan pada *seluruh method* yang membutuhkan update
    }

}
