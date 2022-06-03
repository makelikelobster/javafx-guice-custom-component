package com.makelikelobster.examples.javafx.guice;

/**
 * Sample implementation to be bound in GuiceModule and injected by Guice
 * @author jscott
 *
 */
public class GreetingProviderImpl implements GreetingProvider {

	@Override
	public String getGreeting() {
		return "Test greeting!";
	}

}
