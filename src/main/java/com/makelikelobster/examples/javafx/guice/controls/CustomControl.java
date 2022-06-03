package com.makelikelobster.examples.javafx.guice.controls;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.google.inject.Inject;
import com.makelikelobster.examples.javafx.guice.GreetingProvider;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * Custom control that uses Guice dependency injection.
 * 
 * See https://docs.oracle.com/javafx/2/fxml_get_started/custom_control.htm for
 * an example of creating a custom control w/o injection.
 * @author jscott
 *
 */
public class CustomControl extends HBox implements Initializable {
	// The dependency
	private final GreetingProvider greetingProvider;
	
	@FXML
	private TextField testText;
	
	/**
	 * The constructor requiring injected dependencies. Note the Inject annotation
	 * @param greetingProvider
	 */
	@Inject
	public CustomControl(GreetingProvider greetingProvider) {
		// Capture the dependency
		this.greetingProvider = greetingProvider;
		
		// Load the FXML file...
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CustomComponent.fxml"));
		
		// Set this instance as the root and controller
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			// load the FXML
			fxmlLoader.load();
		} catch (IOException loadException) {
			throw new RuntimeException(loadException);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Get the greeting value from the injected dependency and set it
		// on the text field
		setTestText(greetingProvider.getGreeting());
		
		// Add a listener for funsies
		testTextProperty().addListener((observable, oldValue, newValue) -> {
			System.out.printf("Value changed: '%s'%n", newValue);
		});
	}

	public StringProperty testTextProperty() {
		return testText.textProperty();
	}
	
	public void setTestText(String value) {
		testTextProperty().set(value);
	}
	
	public String getTestText() {
		return testTextProperty().get();
	}
}
