package com.acuevas.bcnTrees.view;

import java.util.Arrays;
import java.util.List;

import com.acuevas.bcnTrees.model.Tree;

/**
 * This class controls what the user sees and how, thus preventing the
 * Controller of doing any sys.out
 *
 * @author Alex
 */
public abstract class View {
	private static final String MUSTBELESSTHANONEERROR = "minusOne";
	private static final String NEGATIVEERROR = "negativeValue";
	private static final String HUGEERROR = "hugeValue";
	private static final String LESSTHANMIN = "lessThanMin";
	private static final String MORETHANMAX = "moreThanMax";
	private static final String WRONGNUMBER = "wrongNumber";

	/**
	 * Welcome
	 */
	public static void welcome() {
		System.out.println("Hello!, welcome to MyTreesBCN.");
	}

	/**
	 * Menu of the application
	 */
	public static void menu() {
		System.out.println("Please, select one of the following options");
		System.out.println("-----------------------------------");
		System.out.println("1. See the XML structure");
		System.out.println("2. Show how many trees are registered");
		System.out.println("3. Show trees without empty properties");
		System.out.println("4. Show trees near me");
		System.out.println("5. Exit");
		System.out.println("------------------------------------");
	}

	/**
	 * Get the which kind of error is and sends the appropriate message
	 *
	 * @param error the kind of error
	 */
	public static void error(String error) {
		switch (error) {
		case MUSTBELESSTHANONEERROR:
			System.out.println("Error, range must be under 1");
			break;
		case NEGATIVEERROR:
			System.out.println("Error, range cannot be negative or 0");
			break;
		case HUGEERROR:
			System.out.println("This is a huge search range, are you sure?");
			break;
		case LESSTHANMIN:
			System.out.println("Error, you cannot insert a value lower than the minimum");
			break;
		case MORETHANMAX:
			System.out.println("Error, you cannot insert a value higher than the maximum");
			break;
		case WRONGNUMBER:
			System.out.println("Wrong number");
			break;
		default:
			throw new Error("Error, wrong state");
		}
	}

	/**
	 * Prints all the trees without any blank camp (completed trees)
	 *
	 * @param trees A List with all the completed trees
	 */
	public static void printCompletedTrees(List<Tree> trees) {
		System.out.println("Here are all the completed trees: (" + trees.size() + ") ");
		trees.forEach(System.out::println);
	}

	/**
	 * Prints the number of trees registered.
	 *
	 * @param treeCount the total of trees
	 */
	public static void totalNumberOfTrees(int treeCount) {
		System.out.println("There's a total of " + treeCount + " trees.");
	}

	/**
	 * Prints the number of trees found within our search range
	 *
	 * @param number the number of trees found
	 */
	public static void treesFound(int number) {
		System.out.println("We've found a total of: " + number + "trees.");
	}

	/**
	 * This method prints on console the min/max range of latitude/longitude in the
	 * XML file.
	 *
	 * @param longitudes a Double[] with the min/max longitudes of the trees
	 * @param latitudes  a Double[] with the min/max latitudes of the trees
	 */
	public static void startRequest(Double[] longitudes, Double[] latitudes) {
		Arrays.sort(longitudes);
		Arrays.sort(latitudes);
		System.out.println("We'll locate trees near you using your position and a custom range of search.");
		System.out.println("All of our trees are within a range of min: " + latitudes[0] + " , " + longitudes[0]
				+ " and max " + latitudes[1] + " , " + longitudes[1]
				+ " latitudes & longitudes, please insert values within that");
	}

	/**
	 * This tag is used in View.error. Use this when the search range is equal or
	 * more than one
	 *
	 * @return the lessThanOne Error tag
	 */
	public static String getMustBeLessThanOneErrorTag() {
		return MUSTBELESSTHANONEERROR;
	}

	/**
	 * This tag is used in View.error. Use this when the search range is equal or
	 * less than 0
	 *
	 * @return the negative Error tag
	 */
	public static String getNegativeErrorTag() {
		return NEGATIVEERROR;
	}

	/**
	 * This tag is used in View.error. Use this to warn the user about using a huge
	 * search radius
	 *
	 * @return the huge Error Tag
	 */
	public static String getHugeErrorTag() {
		return HUGEERROR;
	}

	/**
	 * This tag is used in View.error. Use this when the user inserts a value more
	 * than the maximum
	 *
	 * @return the moreThanMax Error Tag
	 */
	public static String getMoreThanMaxTag() {
		return MORETHANMAX;
	}

	/**
	 * This tag is used in View.error. Use this when the user inserts a value less
	 * than the minimum
	 *
	 * @return the lessThanMin Error Tag
	 */
	public static String getLessThanMinTag() {
		return LESSTHANMIN;
	}

	/**
	 * This tag is used in View.error. Use this when the user inserts a wrong number
	 *
	 * @return the wrongNumber Error Tag
	 */
	public static String getWrongnumber() {
		return WRONGNUMBER;
	}

}
