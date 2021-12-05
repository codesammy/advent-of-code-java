package aoc2021.day02;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class SubmarineCommandParser {
	private Map<String, Class<? extends SubmarineCommand>> commands = new HashMap<>();
	
	public void registerCommand(String commandWord, Class<? extends SubmarineCommand> clazz) {
		commands.put(commandWord, clazz);
	}
	
	public SubmarineCommand parseLine(String line) {
		String commandWord = line.split(" ")[0];
		int amount = Integer.parseInt(line.split(" ")[1]);
		Class<? extends SubmarineCommand> clazz = commands.get(commandWord);
		SubmarineCommand command;
		try {
			command = clazz.getDeclaredConstructor().newInstance();
			Method setAmount = clazz.getMethod("setAmount", int.class);
			setAmount.invoke(command, amount);
		} catch (Exception e) {
			throw new RuntimeException("Could not instantiate submarine command", e);
		}
		return command;
	}
}