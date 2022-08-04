module com.shineworks.meupet {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.shineworks.meupet to javafx.fxml;
    exports com.shineworks.meupet;
}