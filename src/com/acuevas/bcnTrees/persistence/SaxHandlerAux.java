package com.acuevas.bcnTrees.persistence;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.acuevas.bcnTrees.model.Request;
import com.acuevas.bcnTrees.model.Tree;

/**
 * An auxiliary handler for the XML file using SAX technology, gets all the
 * trees within a search range set by the Request generated from the user.
 *
 * @see DefaultHandler
 * @see Request
 * @author Alex
 */
public class SaxHandlerAux extends SaxHandler {

	private List<Tree> treesInRange = new ArrayList<>();
	Request request;
	boolean emptyCamp;

	/**
	 * Creates the SaxHandlerAux object assigning it a Request
	 *
	 * @param request the Request of the user with its position and search range
	 */
	public SaxHandlerAux(Request request) {
		this.request = request;
	}

	/**
	 * Calls the parent .characters which if it's in a tag will get the content of
	 * it and save it into a List<String>
	 *
	 * @See SaxHandler
	 */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// emptyCamp to control when the program sees a tag with no content in it. with
		// this the List<String> to initialize Tree always performs correctly
		emptyCamp = false;
		super.characters(ch, start, length);
	}

	/**
	 * At the end of the tree element creates a new tree and checks if it's inside
	 * the search parameters, in that case it is added to the List
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// emptyCamp to control when the program sees a tag with no content in it. with
		// this the List<String> to initialize Tree always performs correctly
		if (emptyCamp && isImInATag()) {
			getTreeProperties().add("");
		}
		// if the tag "arbre" ends i check if the tree meets my conditions
		if (qName.equalsIgnoreCase(TREETAG)) {
			Tree tree = new Tree(super.getTreeProperties());
			if (tree.getLatitud_WGS84() < request.getMaxLat() && tree.getLatitud_WGS84() > request.getMinLat())
				if (tree.getLongitud_WGS84() < request.getMaxLong() && tree.getLongitud_WGS84() > request.getMinLong())
					treesInRange.add(tree);
		}
		setImInATag(false);
	}

	/**
	 * At the start of the tree element clears the array
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equalsIgnoreCase(TREETAG))
			super.getTreeProperties().clear();

		if (!qName.equalsIgnoreCase(TREETAG) && !qName.equalsIgnoreCase(TREELISTTAG)) {
			setImInATag(true);
		}
		// emptyCamp to control when the program sees a tag with no content in it and
		// doesn't enter characters. with
		// this the List<String> to initialize Tree always performs correctly
		emptyCamp = true;
	}

	/**
	 * @return the treesInRange
	 */
	public List<Tree> getTreesInRange() {
		return treesInRange;
	}
}
