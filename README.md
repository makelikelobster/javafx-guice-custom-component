# javafx-guice-custom-control
Example project that demonstrates using Guice dependency injection with custom JavaFX controls.

I recently started using JavaFX for a work project and ran into some trouble creating re-usable custom controls that used construction dependency injection with Guice. After digging in, I got things working and wanted to add a resource for others who may need the same. 

There are two ways to create re-usable custom controls:
1. Include a FXML file directly inline within another. The included FXML will specify its controller, and JavaFX will create an instance at runtime using the Controller Factory.
2. Include a Control object (the same as you would a native control, e.g. a Label or TextField), and that object will load its FXML at runtime using the Builder Factory.

To use Guice injection in method 1, you'll need to provide a Controller Factory that provides your bindings (and *only* your bindings). Method 2 uses the Builder Factory, which has the same requirements.

Method 2 above was my preferred method as it seemed more featureful at the time. Time will tell if that's truely the case. This is the method I'm demonstrating here.

This is a Maven project, so library dependencies should be handled.

The rubber hits the road in *GuiceBuilderFactory.java* where the bindings are mapped to JavaFX builders, and in *App.java* where the builder factory is added.

I'll show how I generated and modified the project for reference below.

## Versions Used
- JavaFX `17.0.2`
- Guice `5.1.0`
- javafx-maven-plugin `0.0.8`

## Project Creation
The Maven project was created using the following archetype:
- Group: `org.openjfx`
- Artifact ID: `javafx-archetype-fxml`

## POM Changes
Changed the Java version to 17:
```xml
<maven.compiler.source>17</maven.compiler.source>
<maven.compiler.target>17</maven.compiler.target>
```
Added the Guice dependency:
```xml
<!-- https://mvnrepository.com/artifact/com.google.inject/guice -->
<dependency>
  <groupId>com.google.inject</groupId>
  <artifactId>guice</artifactId>
  <version>5.1.0</version>
</dependency>
```

## Project Changes
The archetype generated examples were removed (`primary` and `secondary` controls and FXML files).

`module-info.java` was changed to open and export the project code to JavaFX:
```java
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
```

Added `GuiceBuilderFactory.java` and my custom controller and FXML file.

## Key Points
Take a look at the guide to create custom controls from Oracle [here](https://docs.oracle.com/javafx/2/fxml_get_started/custom_control.htm). This will demonstrate creating standard, non-injected custom controls.

After that, you need to provide a Builder Factory to map your controller and its dependencies for JavaFX's use when instantiating your controllers. Then create your controllers and FXML files, satisfy your dependency trees in your bindings, and load your root FXML file.
