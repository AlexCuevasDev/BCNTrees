package com.acuevas.bcnTrees.persistence;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.acuevas.bcnTrees.model.Tree;
import com.acuevas.bcnTrees.model.XMLFile;

/**
 * This class overrides the methods from DefaultHandler to handle a specific XML
 * structure using SAX technology
 *
 * @see DefaultHandler
 * @author Alex
 *
 */
public class SaxHandler extends DefaultHandler {

	private int counterOfTrees = 0;
	private int indentation;
	private Map<String, Integer> tags = new LinkedHashMap<>(); // I've never used this one before... extra points?, I
																// needed the Map to be ordered so i could print it.
																// I know I could've used a Tag class but hey, I've
																// learned something and now i love it too much to
																// change it: List+Map fusion!
	private List<Tree> trees = new ArrayList<>();
	private List<String> treeProperties = new ArrayList<>();
	private boolean imInATag = false;
	protected final String TREETAG = "arbre";
	protected final String TREELISTTAG = "llistatArbrat";
	private final String LATITUDETAG = "latitud_WGS84";
	private final String LONGITUDETAG = "longitud_WGS84";
	private Double minLatitude = (double) 1000;
	private Double maxLatitude = (double) 0;
	private Double minLongitude = (double) 1000;
	private Double maxLongitude = (double) 0;
	private String state = "";

	/**
	 * Gets the content of a tag and saves it in a List of Strings. Also gets the
	 * minimum&maximum lat/long for later use on "Show trees near me"
	 */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (isImInATag()) {
			String content = new String(ch).substring(start, start + length).trim();

			if (this.getClass().equals(SaxHandler.class)) { // so it won't execute this again when i call this
															// method from SaxHandlerAux

				if (state.equalsIgnoreCase(LATITUDETAG)) {
					if (getMaxLatitude() < Double.parseDouble(content))
						maxLatitude = Double.parseDouble(content);
					if (getMinLatitude() > Double.parseDouble(content))
						minLatitude = (Double.parseDouble(content));
				}
				if (state.equalsIgnoreCase(LONGITUDETAG)) {
					if (getMaxLongitude() < Double.parseDouble(content))
						maxLongitude = Double.parseDouble(content);
					if (getMinLongitude() > Double.parseDouble(content))
						minLongitude = (Double.parseDouble(content));
				}
			}
			getTreeProperties().add(content);
		}
	}

	/**
	 * Sends the XML structure in the tags Map to the XMLFile object.
	 */
	@Override
	public void endDocument() throws SAXException {
		if (this.getClass().equals(SaxHandler.class))// so it won't execute this again when the program calls this
														// method from SaxHandlerAux
			XMLFile.getInstance().setTags(tags);
	}

	/**
	 * At the end of the element we save the tag and its indentation in the Map tags
	 * if it doesn't exists already. Note: differs from startElement() saving in
	 * that this saving also includes / to mark the end of the tag.
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		indentation--;
		if (!tags.keySet().contains("</" + qName + ">"))
			tags.put("</" + qName + ">", indentation);
		// if the size of our list is 23 (complete tree) saves it
		if (qName.equalsIgnoreCase(TREETAG)) {
			if (getTreeProperties().size() == 23) // This could be also implemented in the Tree class to check if all
													// its fields are filled.
				trees.add(new Tree(getTreeProperties()));
		}
		// to control when the program is in an important tag and not "arbre" nor
		// "llistatArbre" so it doesn't load unnecessary "\n","",etc...
		setImInATag(false);
	}

	/**
	 * This method checks the number of trees by adding one to a counter each time
	 * finds an "arbre" tag also clears the list of properties. Adds the tag and its
	 * indentation in the Map tags if it doesn't exist already
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equalsIgnoreCase(TREETAG)) {
			counterOfTrees++;
			getTreeProperties().clear();
		}
		// when we save properties into treeProperties it doesn't get the content
		// "\n",""...
		// from "arbre","llistatArbres" tags
		if (!qName.equalsIgnoreCase(TREETAG) && !qName.equalsIgnoreCase(TREELISTTAG)) {
			setImInATag(true);
		}

		if (!tags.keySet().contains("<" + qName + ">"))
			tags.put("<" + qName + ">", indentation);

		state = qName; // to know if we're in the lat/long tag in characters

		indentation++; // indentation of the tags
	}

	/**
	 * @return the number of total trees
	 */
	public int getCounterOfTrees() {
		return counterOfTrees;
	}

	/**
	 * @return A List with the Tree(s) which have all the fields filled.
	 */
	public List<Tree> getTrees() {
		return trees;
	}

	/**
	 * @return the treeProperties a List with the fields of Tree in order
	 *
	 */
	public List<String> getTreeProperties() {
		return treeProperties;
	}

	/**
	 * @return the minLat
	 */
	public Double getMinLatitude() {
		return minLatitude;
	}

	/**
	 * @return the minLong
	 */
	public Double getMinLongitude() {
		return minLongitude;
	}

	/**
	 * @return the maxLatitude
	 */
	public Double getMaxLatitude() {
		return maxLatitude;
	}

	/**
	 * @return the maxLongitude
	 */
	public Double getMaxLongitude() {
		return maxLongitude;
	}

	/**
	 * @return if the program is in an actual tag and not "arbre" nor
	 *         "llistatArbres"
	 */
	public boolean isImInATag() {
		return imInATag;
	}

	/**
	 * @param imInATag the imInATag to set: if the program is in an actual tag and
	 *                 not "arbre" nor "llistatArbres"
	 */
	public void setImInATag(boolean imInATag) {
		this.imInATag = imInATag;
	}

}
