package com.acuevas.bcnTrees.util;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * This class only has a Map which stores Functions to turn a String into a
 * Primitive Type Data.
 *
 * @author Alex
 *
 */
public abstract class MapParserer implements DateParserer {
	// Implements not necessary since .parserer() is static, inserted the implements
	// just for clarification reasons.

	@SuppressWarnings("rawtypes")
	static Map<Class, Function> parseFromString = new HashMap<>();

	@SuppressWarnings("unchecked")
	private static void init() {
		parseFromString.put(Double.class, x -> {
			if (x != "") {
				return Double.parseDouble((String) x);
			}
			return 0.0;
		});
		parseFromString.put(Integer.class, x -> {
			if (x != "") {
				return Integer.parseInt((String) x);
			}
			return 0;
		});
		parseFromString.put(LocalDate.class, x -> {
			if (x != "") {
				return DateParserer.parserer().apply(x);
			}
			return null;
		});
		parseFromString.put(String.class, x -> x);
		parseFromString.put(Float.class, x -> {
			if (x != "") {
				return Float.parseFloat((String) x);
			}
			return 0f;
		});
	}

	/**
	 * Gets the Map with the Functions to parse a String to the Class used as key if
	 * the String is "" inserts a default value.
	 *
	 * @return Map<Class,Function>
	 */
	@SuppressWarnings("rawtypes")
	public static Map<Class, Function> getMap() {
		if (parseFromString.values().size() == 0)
			init();
		return parseFromString;
	}
}
