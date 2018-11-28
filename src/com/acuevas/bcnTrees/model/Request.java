package com.acuevas.bcnTrees.model;

/**
 * The request of a user for a search with parameters. range by default is 0.03.
 * WARNING, high search parameters will result in troves of trees.
 *
 * @author Alex
 */
public class Request {
	private Double range = 0.03;
	private Double latitude;
	private Double longitude;

	/**
	 * Creates the Request object with a custom latitude and longitude
	 *
	 * @param latitude  Double
	 * @param longitude Double
	 */
	public Request(Double latitude, Double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * Returns the max latitude within the search range
	 *
	 * @return latitude+range
	 */
	public double getMaxLat() {
		return latitude + getRange();
	}

	/**
	 * Returns the max longitude within the search range
	 *
	 * @return longitude+range
	 */
	public double getMaxLong() {
		return longitude + getRange();
	}

	/**
	 * Returns the min latitude within the search range
	 *
	 * @return latitude-range
	 */
	public double getMinLat() {
		return latitude - getRange();
	}

	/**
	 * Returns the min longitude within the search range
	 *
	 * @return latitude-range
	 */
	public double getMinLong() {
		return longitude - getRange();
	}

	/**
	 * @return the range
	 */
	public Double getRange() {
		return range;
	}

	/**
	 * @param range the range to set
	 */
	public void setRange(Double range) {
		this.range = range;
	}
}
