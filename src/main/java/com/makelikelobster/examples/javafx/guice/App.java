package com.makelikelobster.examples.javafx.guice;

import java.io.IOException;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.TypeLiteral;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App that demonstrates a method to use Guice to provide JavaFX controller instances
 * to custom components
 */
public class App extends Application {

	@Override
	public void start(Stage stage) throws IOException {
		final Injector injector = Guice.createInjector(new GuiceModule());

		// Create a root FXMLLoader with the Guice builder factory
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("RootComponent.fxml"), null,
				new GuiceBuilderFactory(injector));

		// Alternatively, use lambdas to do the same as the GuiceBuilderFactory above. Much less
		// readable, but concise.
		/*
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("RootComponent.fxml"), null,
				// Outer lambda provides the custom builder which tests if the requested type
				// is explicitly bound in the injector. If so, it provides the inner lambda that
				// returns the instance, otherwise it returns null to let JavaFX provide
				// the builder with the default factory
				(type) -> injector.findBindingsByType(TypeLiteral.get(type)).isEmpty() ? null
						: () -> injector.getInstance(type));
		*/

		stage.setScene(new Scene(fxmlLoader.load(), 640, 480));
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}

}