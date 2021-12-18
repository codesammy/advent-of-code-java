package aoc2021.meta;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BaseDay {
	public static void main(String[] args) throws Throwable {
		Class<?> clazz = Class.forName(args[0]);
		for(Method method : clazz.getMethods()) {
			Input input = method.getAnnotation(Input.class);
			if (input != null) {
				handleMethod(clazz, method);
			}
		}
		System.out.println("---- End ----");
	}

	private static void handleMethod(Class<?> clazz, Method method) throws Throwable {
		String prefix = "/aoc2021/";
		String suffix = "";
		Silver silver = method.getAnnotation(Silver.class);
		if (silver != null) {
			suffix = ".silver";
		}
		Gold gold = method.getAnnotation(Gold.class);
		if (gold != null) {
			suffix = ".gold";
		}
		if (silver == null && gold == null) {
			return;
		}
		URL inputResource = clazz.getResource(prefix+clazz.getSimpleName()+"/input.sample"+suffix);
		if (inputResource == null) {
		    inputResource = clazz.getResource(prefix+clazz.getSimpleName()+"/input.sample");
		}
		if (inputResource != null) {
			// do a sample run
			URL outputResource = clazz.getResource(prefix+clazz.getSimpleName()+"/output.sample"+suffix);
			executeSampleRun(clazz, method, inputResource, outputResource);
		}
		inputResource = clazz.getResource(prefix+clazz.getSimpleName()+"/input"+suffix);
		if (inputResource == null) {
			inputResource = clazz.getResource(prefix+clazz.getSimpleName()+"/input");
		}
		String result = invoke(clazz, method, inputResource);
		System.out.println("Result: " + result);
	}

	private static void executeSampleRun(Class<?> clazz, Method method, URL inputResource, URL outputResource) throws Throwable {
		String actual = invoke(clazz, method, inputResource);
		String expected = Files.readString(Path.of(outputResource.toURI()));
		if (!actual.equals(expected)) {
			throw new IllegalStateException("\n  Actual: " + actual + "\nExpected: " + expected);
		}
	}

	private static String invoke(Class<?> clazz, Method method, URL inputResource) throws Throwable {
		Object dayArgument = null;
		Stream<String> lines = Files.lines(Path.of(inputResource.toURI()));
		
		IntList intList = method.getAnnotation(IntList.class);
		if (intList != null) {
			dayArgument = lines.map(x -> Integer.parseInt(x)).collect(Collectors.toList());
		}
		
		if (dayArgument == null) {
			dayArgument = lines.collect(Collectors.toList());
		}
		
		Object day = clazz.getDeclaredConstructor().newInstance();
		System.out.println("Executing method " + method.getName() + " with resource " + clazz.getSimpleName() + inputResource.getPath().substring(inputResource.getPath().lastIndexOf('/')));
		try {
			Object result = method.invoke(day, dayArgument);
			return result.toString();
		} catch (InvocationTargetException e) {
			throw e.getCause();
		} finally {
			lines.close();
		}
	}
}
