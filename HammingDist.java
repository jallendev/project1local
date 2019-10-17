/**
 * @author Joseph Allen
 * @version 2019-09-08
*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class HammingDist
{
    /**
     * The beginning capacity of the stations array
     */
	private final int INITIAL_STATION_SIZE = 10;
	/**
	 * The filepath for the Mesonet.txt file. May need to be changed depending on relative working directory.
	 */
	private final String FILENAME = "Mesone.txt";
	/**
	 * The fileReader looks for this column header to begin reading in station IDs.
	 */
	private final String STATION_COLUMN_HEADER = "STID";
	/**
	 * Mohammad told me to compare against this station when outputting for zyLabs. See Project 1 canvas discussion.
	 */
	private final String THE_STATION_WE_WERE_TOLD_TO_USE = "NRMN";
	
	/**
	 * An array to hold station IDs read from the Mesonet.txt file. Initialized with a value of 10.
	 */
	private String[] stations = new String[INITIAL_STATION_SIZE];
	
	/**
	 * Holds the station ID of the first station to compare.
	 */
	private String station1;
	/**
	 * Holds the station ID of the second station to compare.
	 */
	private String station2;
	/**
	 * Holds the hamming value of the first station compared to "NRMN".
	 */
	private int hamming1;
	/**
	 * Holds the hamming value of the second station compared to "NRMN".
	 */
	private int hamming2;
	/**
	 * Holds the number of stations which have the same hamming as hamming1.
	 */
	private int hammingStations1;
	/**
	 * Holds the number of stations which have the same hamming as hamming2.
	 */
	private int hammingStations2;
	
	/**
	 * Constructor for the HammingDist class. Initializes the class properties after reading the Mesonet.txt file.
	 * 
	 * @param str1 the first station ID to process.
	 * @param str2 the second station ID to process.
	 * 
	 * @throws IOException if the Mesonet.txt filename is incorrect and the file cannot be found. See the const String FILENAME.
	 */
	public HammingDist(String str1, String str2) throws IOException{
		readFile();
		station1 = str1;
		station2 = str2;
		hamming1 = calcHamming(THE_STATION_WE_WERE_TOLD_TO_USE, station1);
		hamming2 = calcHamming(THE_STATION_WE_WERE_TOLD_TO_USE, station2);
		hammingStations1 = calcStationsWithHamming(station1, hamming1);
		hammingStations2 = calcStationsWithHamming(station2, hamming2);
	}
	
	/**
	 * Returns a string in the format: 
	 * <code>The Hamming Distance between Norman and (first station ID) is (first hamming) and for (second station ID): (second hamming). 
				For (first station ID), number of stations with Hamming Distance (first hamming) is (stations with first hamming), and
				for (second station ID), number of stations with Hamming Distance (second hamming) is (stations with second hamming)."</code>
	 * @return the string representation of Hamming Distance
	 */
	@Override
	public String toString() {
		String output = new String("");
		output += "The Hamming Distance between Norman and " + station1 + " is " + hamming1 + " and for " + station2 + ": " + hamming2 + ".\n" + 
				"For " + station1 + ", number of stations with Hamming Distance " + hamming1 + " is " + hammingStations1 + ", and\n" + 
				"for " + station2 + ", number of stations with Hamming Distance " + hamming2 + " is " + hammingStations2 + ".";
		return output;
	}
	
	/**
	 * Reads the Mesonet.txt file and inputs the results into the stations array. Helper method for the default constructor.
	 * 
	 * @throws IOException if the Mesonet.txt filename is incorrect and the file cannot be found. See the const String FILENAME.
	 */
	private void readFile() throws IOException{
	    //catch IOException if one occurs and print error message
		try {
		    //Declare necessary variables and objects
			BufferedReader fileReader = new BufferedReader(new FileReader(FILENAME));
			String line = new String("");
			String station = new String("");
			Scanner stationFinder;
			boolean startReading = false;
			int index = 0;
			
			//read the file
			line = fileReader.readLine();
			while(line != null) {
			    //get only the first token on each line
				stationFinder = new Scanner(line);
				station = stationFinder.next();
				//triggers upon column header being found by below conditional statement
				if (startReading) {
					if (index >= stations.length) {
						stations = expandStations();
					}
					stations[index] = station;
					++index;
				}
				//test for beginning of station IDs using const station header
				if (station.equals(STATION_COLUMN_HEADER)){
					startReading = true;
				}
				line = fileReader.readLine();
			}
			fileReader.close();
		}
		//An IOException will most likely occur in the case of the FILENAME variable being initialized with an incorrect path.
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Expands the size of the stations array when it becomes full. This helps the fileReader method in filling the array.
	 * 
	 * @return a String[] array containing the values of the original stations array + 10 new null spaces
	 */
	private String[] expandStations() {
		String[] tempStations = new String[stations.length + 10];
		
		//transfer data to temp array
		for (int index = 0; index < stations.length; ++index) {
			tempStations[index] = stations[index];
		}
		
		//resize stations when transfer is complete
		stations = new String[stations.length + 10];
		return tempStations;
	}
	
	/**
	 * Calculates the hamming between the two station IDs given as parameters.
	 * 
	 * @param str1 the first station ID to compare.
	 * @param str2 the second station ID to compare.
	 * 
	 * @return an int with the computed hamming value
	 */
	private int calcHamming(String str1, String str2) {
		int hammingCounter = 0;
		for (int currChar = 0; currChar < str1.length(); ++currChar) {
			if (!str1.substring(currChar, currChar + 1).equals(str2.substring(currChar, currChar + 1))) {
				++hammingCounter;
			}
		}
		return hammingCounter;
	}
	
	/**
	 * Calculates the number of stations with a hamming equal to the given value when compared with the given station ID.
	 * 
	 * @param station the station ID to compare
	 * @param hamming the hamming value to check for
	 * 
	 */
	private int calcStationsWithHamming(String station, int hamming) {
		int matchingStations = 0;
		for (int index = 0; index < stations.length; ++index) {
			if (calcHamming(stations[index], station) == hamming) {
				++matchingStations;
			}
		}
		return matchingStations;
	}
}