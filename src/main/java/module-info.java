module com.makelikelobster.examples.javafx.guice {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
	requires transitive com.google.guice;

    opens com.makelikelobster.examples.javafx.guice to javafx.fxml;
    opens com.makelikelobster.examples.javafx.guice.controls to javafx.fxml;
    
    exports com.makelikelobster.examples.javafx.guice;
    exports com.makelikelobster.examples.javafx.guice.controls;
}
