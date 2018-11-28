package com.acuevas.bcnTrees.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.acuevas.bcnTrees.model.Request;
import com.acuevas.bcnTrees.model.XMLFile;
import com.acuevas.bcnTrees.persistence.SaxHandler;
import com.acuevas.bcnTrees.persistence.SaxHandlerAux;
import com.acuevas.bcnTrees.view.View;

/**
 * The controller of this application, gathers all the data and manages it
 *
 * @author Alex
 */
public class Manager {
	private static final String FILE = "datos.xml";
	private static Double[] minMaxLatitudes = new Double[2];
	private static Double[] minMaxLongitudes = new Double[2];
	private static SaxHandler handler = new SaxHandler();

	public static void main(String[] args) {
		int option;

		useHandler(handler);
		View.welcome();

		do {
			View.menu();
			option = InputAsker.pedirEntero("");
			switch (option) {
			case 1:
				System.out.println(XMLFile.getInstance());
				break;
			case 2:
				View.totalNumberOfTrees(handler.getCounterOfTrees());
				break;
			case 3:
				View.printCompletedTrees(handler.getTrees());
				break;
			case 4:
				initLatLong();
				View.startRequest(minMaxLongitudes, minMaxLatitudes);
				SaxHandlerAux handlerAux = new SaxHandlerAux(createRequestFromUser());
				useHandler(handlerAux);
				View.treesFound(handlerAux.getTreesInRange().size());
				if (handlerAux.getTreesInRange().size() > 0)
					if (InputAsker.yesOrNo("Do you want to see them?"))
						handlerAux.getTreesInRange().forEach(System.out::println);
				break;
			case 5:
				break;
			default:
				View.error(View.getWrongnumber());
			}
		} while (option != 5);
	}

	/**
	 * Initiates the latitudes & longitudes arrays using the handler
	 */
	private static void initLatLong() {
		minMaxLatitudes[0] = handler.getMinLatitude();
		minMaxLatitudes[1] = handler.getMaxLatitude();

		minMaxLongitudes[0] = handler.getMinLongitude();
		minMaxLongitudes[1] = handler.getMaxLongitude();
	}

	/**
	 * Creates a Request based on the user custom parameters. When the user inputs a
	 * wrong number controls it and sends an error message from the
	 * View.error(error) method
	 *
	 * @See View
	 */
	private static Request createRequestFromUser() {
		boolean error = false;
		Arrays.sort(minMaxLatitudes); // just in case...
		Arrays.sort(minMaxLongitudes); // just in case...
		Double latitude;
		Double longitude;
		do {
			latitude = InputAsker.pedirDouble("please insert your latitude: ");
			if (latitude < minMaxLatitudes[0]) {
				View.error(View.getLessThanMinTag());
				error = true;
			} else if (latitude > minMaxLatitudes[1]) {
				View.error(View.getMoreThanMaxTag());
				error = true;
			} else
				error = false;

		} while (error);

		do {
			longitude = InputAsker.pedirDouble("please insert your longitude: ");
			if (longitude < minMaxLongitudes[0]) {
				View.error(View.getLessThanMinTag());
				error = true;
			} else if (longitude > minMaxLongitudes[1]) {
				View.error(View.getMoreThanMaxTag());
				error = true;
			} else
				error = false;
		} while (error);

		Request request = new Request(latitude, longitude);
		boolean customRange = InputAsker
				.yesOrNo("do you want a custom range? (by default it's " + request.getRange() + ")");
		if (customRange) {

			Double range;
			do {
				range = InputAsker.pedirDouble("now insert the range of search: ");
				if (range >= 1) {
					View.error(View.getMustBeLessThanOneErrorTag());
					error = true;
				} else if (range <= 0) {
					View.error(View.getNegativeErrorTag());
					error = true;
				} else if (range >= request.getRange() * 2) {
					View.error(View.getHugeErrorTag());
					error = !InputAsker.yesOrNo("");
				} else {
					error = false;
				}

			} while (error);
			request.setRange(range);
		}
		return request;
	}

	/**
	 * This method receives a DefaultHandler an initializes it with the FILE
	 * constant.
	 *
	 * @param handler DefaultHandler
	 */
	public static void useHandler(DefaultHandler handler) {
		try {
			SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
			parser.parse(new File(FILE), handler);
		} catch (SAXException | IOException | ParserConfigurationException ex) {
			System.out.println("Error loading/parsing the file. " + ex.getMessage());
		}
	}

}
