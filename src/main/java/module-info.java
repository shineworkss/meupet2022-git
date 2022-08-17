module com.shineworks.meupet {
    requires javafx.controls;
    requires javafx.fxml;
    requires mongo.java.driver;
    requires org.apache.poi.ooxml;


    opens com.shineworks.meupet to javafx.fxml;
    exports com.shineworks.meupet;
}