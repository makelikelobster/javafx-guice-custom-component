package com.makelikelobster.examples.javafx.guice;

import com.google.inject.AbstractModule;
import com.makelikelobster.examples.javafx.guice.controls.CustomControl;

public class GuiceModule extends AbstractModule {

	@Override
	protected void configure() {
		// Bind the implementation to the interface
		bind(GreetingProvider.class).to(GreetingProviderImpl.class);
		
		// Make Guice aware of the controller so it will inject it to JavaFX
		// when referenced in FXML. THIS IS REQUIRED.
		bind(CustomControl.class);
	}
	
}
