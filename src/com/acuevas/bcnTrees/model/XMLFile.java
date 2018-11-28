package com.acuevas.bcnTrees.model;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This class manages the structure of the XML file, reading its tags and
 * indentation from SaxHandler
 *
 * @author Alex
 *
 */
public class XMLFile {

	private static XMLFile xmlFile = new XMLFile();
	private Map<String, Integer> tags = new LinkedHashMap<>();

	private XMLFile() {

	}

	/**
	 * Get the instance of XMLFile if exists, else create a new one.
	 *
	 * @return XMLFile object
	 */
	public static XMLFile getInstance() {
		if (xmlFile != null)
			return xmlFile;
		else
			return new XMLFile();
	}

	/**
	 * @return the tag structure ordered hierarchically. Map<tagName,indentation>
	 *         Note: it has both, the start of the element and the end of the
	 *         element in order.
	 */
	public Map<String, Integer> getTags() {
		return tags;
	}

	/**
	 * @param tags the tags to set. It's a Map where the key is the tagname and the
	 *             value its indentation
	 */
	public void setTags(Map<String, Integer> tags) {
		this.tags = tags;
	}

	/**
	 * Override of .toString() so it shows us the XML structure using the tags Map
	 * which we get at the end of SaxHandler.
	 *
	 * @See SaxHandler
	 */
	@Override
	public String toString() {
		String message = "";
		for (String tag : tags.keySet()) {
			String indentation = "";
			for (int i = 0; i < tags.get(tag); i++)
				indentation += "\t ";
			message += indentation + tag + "\n";
		}
		return message;
	}

}
