import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Prog01_aOrderedList - Contains the main method that allows for a file to be inputted, read, and outputted
 * in the correct format based on the operations presented in the inputted file.
 *
 * CSC 1351 Programming Project No. 1 Part A
 * Section 002
 *
 * @author Madison Harman
 * @since 03/17/2024
 *
 */

public class Prog01_aOrderedList {
	public static aOrderedList orderedList = new aOrderedList();
	public static void main(String[] args) {
		try {
			Scanner userInputFile = GetInputFile("Enter input filename: "); // Asks user for the input filename and determines if it's in the correct format or not.
			orderedList = readInputFile(userInputFile); // Reads the inputted file's operations and adds it to an orderedList. With this aOrderedList object being added to the program, 
														// this covers Part 1 of Step 4: Populating Ordered List.
			PrintWriter outputFileWriter = GetOutputFile("Enter the output filename: "); // Asks user for the output filename and determines if it's in the correct format or not.
			String formattedOutputFile = outputPrintFormat(orderedList); // Prints the orderedList in the specified format.
			outputFileWriter.println(formattedOutputFile); // Actually writes the correct formatted output file onto the output file.

			// Close both the Scanner and PrintWriter in order to avoid possible resource leaks.
			userInputFile.close();
			outputFileWriter.close();
		} 
		catch (FileNotFoundException e) {
			// Moves on if a FileNotFoundException is thrown.
		}
	}

	/**
	 * GetInputFile - This method will prompt the user for the filename of a text input file and return a 
	 * Scanner object, ready for input. If the input file is in the workspace folder, only the filename is entered. 
	 * If the file is in another folder, the filename must be preceded by the path to the folder from the workspace folder.
	 * This method covers Parts 1-2 of Step 2: Input Car Data.
	 * @param UserPrompt - Prompts the user to enter a filename.
	 * @return - Returns the file's name (as a Scanner object) if it is either in the workspace folder or another folder.
	 * @throws FileNotFoundException - If the file is not found.
	 *
	 * CSC 1351 Programming Project No. 1 Part A
	 * Section 002
	 *
	 * @author Madison Harman
	 * @since 03/17/2024
	 *
	 */

	public static Scanner GetInputFile(String UserPrompt) throws FileNotFoundException {
		System.out.print(UserPrompt); // Asks the user to enter an input filename. This covers a part Part 1 of Step 2: Input Car Data.

		Scanner userInput = new Scanner(System.in); // This Scanner contains the user's inputted filename.
		String fileName = userInput.nextLine(); // The file's name is what is user inputs.
		File actualFile = new File(fileName); // Creates a file with the file's name.
		String userResponse; // User's response to continuing with the program or not.
		boolean shouldContinue; // Based on the user's response, the program continues or not.

		try {
			// Checks to see if the file is in the workspace folder or not. If so, just input "inputfile.txt". 
			// If not, input "./testFolder/inputfile.txt". Then, the file gets created. This covers a part Part 1 of Step 2: Input Car Data.
			if (actualFile.exists()) {
				return new Scanner(new File(fileName));
			}
			// Otherwise, the file does not exist. The rest of this method covers Part 2 of Step 2: Input Car Data.
			else {
				System.out.println("File specified <" + fileName + "> does not exist. Would you like to continue? <Y/N>");
				userResponse = userInput.nextLine();
				do {
					// If the user inputs "Y", continue with the program.
					shouldContinue = false;
					if(userResponse.equalsIgnoreCase("Y")) {
						shouldContinue = true; // User chose to continue.
						return GetInputFile("Enter input filename: "); // Recursively calls GetInputFile to continue program.
					}
					// Otherwise, terminate and throw a FileNotFoundException.
					else if (userResponse.equalsIgnoreCase("N")) {
						System.out.println("The user has canceled the program.");
						shouldContinue = false; // User chose to NOT continue.
						userInput.close(); // Close the Scanner in order avoid  possible resource leaks.
						System.exit(0); // Exit the system to terminate the program.
						throw new FileNotFoundException(); // Since the user inputted "N", a FileNotFoundException is thrown.
					}
				} while(shouldContinue);
			}
		}
		catch(FileNotFoundException e){
			// Moves on if a FileNotFoundException is thrown.
		}
		return null; // If all else fails, return null.
	}


	/**
	 * readInputFile - This helper method reads the inputted file and adds a Car object to the list.
	 * This whole method pretty much covers Part 3 of Step 2: Input Car Data, Part 1 of Step 7: Operational Script, and Part 5 of Step 8: Extend our aOrderedList class.
	 * @param userInput - We need a scanner in order to get user input.
	 * @return - Returns the list.
	 * @throws NoSuchElementException - If a requested element does not exist.
	 * @throws NumberFormatException - If a String is attempted to be converted with an incorrect format to a number.
	 *
	 * CSC 1351 Programming Project No. 1 Part A
	 * Section 002
	 *
	 * @author Madison Harman
	 * @since 03/17/2024
	 *
	 */

	public static aOrderedList readInputFile(Scanner userInput) throws NoSuchElementException, NumberFormatException{
		// While there is another line in the input file...
		while (userInput.hasNextLine()) {
			Scanner nextLineScanner = new Scanner(userInput.nextLine()); // Creates a Scanner that reads the user's input.

			nextLineScanner.useDelimiter(",\\s*"); // Ignores whitespace when reading the input file.

			// While there is another line in the input file...
			while (nextLineScanner.hasNext()) {
				String lineOperation = nextLineScanner.next(); // This string reads whether the operations "A" or "D" is in the list.
				String make = ""; // Assign to null value in order to initialize the car's make.
				int year = 0; // Assign to 0 in order to initialize the car's year.
				int price = 0; // Assign to 0 in order to initialize the car's price.
				
				try {
					make = nextLineScanner.next(); // Assigns the make to the String value in the line.
					year = Integer.valueOf(nextLineScanner.next()); // Assigns the year to the integer value after the make.
					price = Integer.valueOf(nextLineScanner.next());// Assigns the price to the integer value after the year.
				} 
				catch (NoSuchElementException | NumberFormatException e) {
					// Moves on if a NoSuchElementException OR NumberFormatException is thrown.
				} 

				// If the line contains an "A" operation, add that Car object to the orderedList. This covers Part 3 of Step 2: Input Car Data.
				if (lineOperation.equals("A")) {
					Car newCar = new Car(make, year, price);
					orderedList.add(newCar);
				} 
				// If the line contains a "D" operation, delete that Car object in the orderedList. 
				else if (lineOperation.equals("D")) {
					// Checks whether or not the line (or make since it is right after the "D" operation) contains an integer/index to delete in the list. 
					// This covers Part 1 of Step 7: Operational Script. 
					if (make.matches("[-]?[0-9]+")) {
						// If so, find the index and remove the Car object that is there.
						int index = Integer.parseInt(make);
						orderedList.remove(index);
					} 
					// Otherwise, if the year and make are equal, remove that Car from the orderedList.
					else {
						orderedList.reset();
						// While there is another line in the orderedList...
						while (orderedList.hasNext()) {
							Car currentCar = (Car)orderedList.next();
							if (currentCar.getYear() == year && currentCar.getMake().equals(make)) {
								orderedList.remove();
								break; // Exit when finished.
							}
						}
					}
				}
			}
			nextLineScanner.close(); // Close the Scanner in order avoid  possible resource leaks.
		}
		return orderedList; // Returns the orderedList after all of the operations are done.
	}

	/**
	 * GetOutputFile - will prompt the user for the filename of a text output file and return 
	 * a PrintWriter object, ready for output. If the output file is in the workspace folder, 
	 * only the filename is entered. If the file is in another folder, the filename must be 
	 * preceded by the path to the folder from the workspace folder. This method covers Parts 2-3 of Step 4: Populating Ordered List.
	 * @param UserPrompt - Prompts the user to enter a filename.
	 * @return - Returns the file's name if it is either in the workspace folder or another folder.
	 * @throws FileNotFoundException - If the file is not found.
	 *
	 * CSC 1351 Programming Project No. 1 Part A
	 * Section 002
	 *
	 * @author Madison Harman
	 * @since 03/17/2024
	 *
	 */

	public static PrintWriter GetOutputFile(String UserPrompt) throws FileNotFoundException {
		System.out.print(UserPrompt); // Asks the user to enter an output filename. This covers a part Part 2 of Step 4: Populating Ordered List.

		Scanner userInput = new Scanner(System.in); // This Scanner contains the user's entered filename.
		String fileName = userInput.nextLine(); // The file's name is what is user entered.
		File actualFile = new File(fileName); // Creates a file with the file's name.
		String userResponse; // User's response to continuing with the program or not.
		boolean shouldContinue; // Based on the user's response, the program continues or not.

		try {
			// Checks to see if the file is in the workspace folder or not. If so, just input "outputfile.txt". 
			// If not, enter "./testFolder/outputfile.txt". Then, the file gets created. The rest of this method covers Parts 2-3 of Step 4: Populating Ordered List.
			if (actualFile.exists()) {
				return new PrintWriter(new File(fileName));
			} 
			// Otherwise, the file does not exist.
			else {
				System.out.println("File specified <" + fileName + "> is not valid. Would you like to continue? <Y/N>");
				userResponse = userInput.nextLine();
				do {
					// If the user inputs "Y", continue with the program.
					shouldContinue = false;
					if(userResponse.equalsIgnoreCase("Y")) {
						shouldContinue = true; // User chose to continue.
						return GetOutputFile("Enter output filename: "); // Recursively calls GetOutputFile to continue program.
					}
					// Otherwise, terminate and throw a FileNotFoundException.
					else if (userResponse.equalsIgnoreCase("N")) {
						System.out.println("The user has canceled the program.");
						shouldContinue = false; // User chose to NOT continue.
						userInput.close(); // Close the Scanner in order avoid  possible resource leaks.
						System.exit(0); // Exit the system to terminate the program.
						throw new FileNotFoundException(); // Since the user inputted "N", a FileNotFoundException is thrown.
					}
				} while(shouldContinue);
			}
		}
		catch(FileNotFoundException e){
			// Moves on if a FileNotFoundException is thrown.
		}
		return null; // If all else fails, return null.
	}

	/**
	 * outputPrintFormat - This helper method prints the output of the ReadInputFile method into the format specified.
	 * This whole method pretty much covers Part 4 of Step 4: Populating Ordered List and Part 2 of Step 7: Operational Script.
	 * @param orderedList - We need a list in order to format it correctly.
	 * @return - The output of the formatted list.
	 *
	 * CSC 1351 Programming Project No. 1 Part A
	 * Section 002
	 *
	 * @author Madison Harman
	 * @since 03/17/2024
	 *
	 */

	public static String outputPrintFormat(aOrderedList orderedList) {
		String formattedOutput = "Number of cars:\t\t" + String.format("%10d", orderedList.size()) + "\n\n"; // Assigns the number of cars (in the specified format) to a String value 
																											 // to be returned later.

		// Iterates throughout the orderedList, printing the make, year, and price of each car in the specified format.
		for (int i = 0; i < orderedList.size(); i++) {
			Car currentCar = (Car)orderedList.get(i); // Creates a currentCar to add to the orderedList.
			String formattedPrice = String.format("$%,d", currentCar.getPrice()); // Formats the price to have a "$" and ",". We need a separate variable for this so that, 
																				  // no matter the length of the price, they will all be aligned.
			// Prints the make, year, and price into the orderedList in the specified format.
			formattedOutput += String.format("Make:\t\t%10s\nYear:\t\t%10d\nPrice:\t\t%10s\n\n", currentCar.getMake(), currentCar.getYear(), formattedPrice, currentCar.getPrice());
		}
		return formattedOutput; // Returns the formatted output into the output file.
	}
}
