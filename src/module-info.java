module MBTATracker {
	requires javafx.controls;
	requires javafx.fxml;
	requires org.json;

	opens application to javafx.graphics, javafx.fxml;
}
