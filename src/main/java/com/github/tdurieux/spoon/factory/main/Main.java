package com.github.tdurieux.spoon.factory.main;

import com.github.tdurieux.spoon.factory.processor.SpoonFactoryProcessor;
import spoon.Launcher;
import spoon.SpoonAPI;

public class Main {
	public static void main(String[] args) {
		SpoonAPI spoon = new Launcher();
		spoon.addInputResource("../spoon/src/main/java");
		spoon.addProcessor(new SpoonFactoryProcessor());
		spoon.getEnvironment().setAutoImports(true);
		spoon.getEnvironment().setNoClasspath(true);
		spoon.getEnvironment().setGenerateJavadoc(true);
		spoon.getEnvironment().setCopyResources(false);
		spoon.setOutputFilter("com.github.tdurieux.spoon.factory.F");
		spoon.setSourceOutputDirectory("src/main/java");
		spoon.run();
	}
}
