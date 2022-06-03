package com.makelikelobster.examples.javafx.guice;

import com.google.inject.Injector;
import com.google.inject.TypeLiteral;

import javafx.util.Builder;
import javafx.util.BuilderFactory;

/**
 * Factory to map types to types explicitly bound type instances to a builder
 * 
 * @author jscott
 *
 */
public class GuiceBuilderFactory implements BuilderFactory {
	private final Injector injector;

	public GuiceBuilderFactory(Injector injector) {
		this.injector = injector;
	}

	@Override
	public Builder<?> getBuilder(Class<?> type) {
		// If there is no explicit binding in the injector...
		if (injector.findBindingsByType(TypeLiteral.get(type)).isEmpty()) {
			// ...return null to let JavaFX attempt to provide the builder with the
			// default builder (required for JavaFX types)
			return null;
		} else {
			// ...else, the injector has the requested type bound, so return 
			// a lambda to provide the instance
			return () -> injector.getInstance(type);
		}
	}

}
