package com.acuevas.bcnTrees.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

/**
 * This interface creates a new Function interface inserting a formatter inside
 * so it can still receive one parameter. Used in MapParserer. Not inserted
 * inside the Lambda Expression because of IDE crashing.
 *
 * @See MapParserer
 * @author Alex
 */
public interface DateParserer {
	final String pattern = "dd/MM/yyyy";

	// this&MapParserer took a while to do and probably could've been done better
	// but I'm proud of it ^^
	// Solution to Function only accepting one parameter when i need to format the
	// DateTime
	@SuppressWarnings("rawtypes")
	public static <R, T> Function parserer() {
		Function function = new Function<T, R>() {

			@SuppressWarnings("unchecked")
			@Override
			public R apply(T t) {
				final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
				return (R) LocalDate.parse((CharSequence) t, formatter);
			}
		};
		return function;
	}

}
