module Tp6_javaFX {
	requires javafx.controls;
	requires java.sql;
	requires javafx.fxml;
	
	opens application to javafx.graphics, javafx.fxml;
}
