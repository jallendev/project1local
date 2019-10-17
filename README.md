# proj1F19, Joseph Allen

## Problem Solving Approach

For this project, I worked mostly from the output sample specified in the project   requirements. To accomplish the project requirements I split the project into 4 basic tasks, each of which was associated with its own helper method:

 - Read the file, Mesonet.txt.
 - Expand the array of station IDs when necessary.
 - Calculate the hamming between two given station IDs.
 - Calculate the amount of stations that have the same hamming when compared to a given station.

These helper methods are initiated from the constructor. My typical methodology for a project type would be to put the stations array in the Driver class so that a separate array is not associated with each instance of the HammingDist object. Since we were not allowed to edit Driver.java, I used the constructor to initialize the array and read the file, much like main() would have in a typical implementation of this sort of program.

## Description of Methods and Variables

### Variables

- I used a variety of const variables to avoid unneccessary use of "magic" data.
- stations: a String[] to hold all station IDs read from the file.
- station1 and station2: 2 Strings to hold the value of the stations given to the constructor.
- hamming1 and hamming2: 2 ints to hold hamming values of these stations when compared to "NRMN"
- hammingStations1 and hammingStations2: 2 ints to hold the amount of stations with hamming equal to stations 1 and 2

### Methods

#### public HammingDist(String, String)

Calls helper methods to read the Mesonet.txt file and initialize all the variables listed above. The two paramaters are the values of the stations given for station1 and station2.

#### public String toString()

Overrides the Object class toString() method, to return the string representation of HammingDist in the format:
<code>The Hamming Distance between Norman and (first station ID) is (first hamming) and for (second station ID): (second hamming). 
For (first station ID), number of stations with Hamming Distance (first hamming) is (stations with first hamming), and
for (second station ID), number of stations with Hamming Distance (second hamming) is (stations with second hamming)."</code>

#### private void readFile()

Reads the Mesonet.txt file and inputs the results into the stations array. See code comments for detailed implementation explanation. Basic summary:

 - Uses try-catch block to mitigate IOExceptions thrown.
 - Reads file utilizing BufferedReader object.
 - Uses Scanner to acquire the first token from each line.
 - Begins reading upon finding the STID header.

#### private String[] expandStations()

Expands the stations array with 10 extra slots. Returns the expanded array with values copied from the stations array.

#### private int calcHamming(String, String)

Calculates the hamming between the two given strings and returns it as an int value.

#### private int calcStationsWithHamming(String, int)

Loops through the stations array to find the amount of stations with hamming matching the given int hamming when compared with the given String station ID. Returns the result as an int value.

## Code Analysis

I am mostly happy with my current implementation. I feel that my code used in the helper methods was necessarily efficient in the tasks, however there are some things that could be improved if the project description was slightly less restrictive:

 - I would have liked to implement data storage in the main Driver class, but this was not possible given the project requirements. 
 - I felt that hard coding "NRMN" into the software as the station to compare against was a bit odd considering possible uses for a project like this. I feel it would be more useful to query the user for a station to compare against or compare the two stations given in the constructor.

Other than these, It seems I fulfilled the project requirements in full and there is little that could be improved in the current implementation under the given restrictions. 

See HammingDist javadoc and code comments for more implementation details.
