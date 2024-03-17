import java.util.Arrays;

/**
 * aOrderedList - This class is an aOrderedList object class that is used for storing objects into a sorted array.
 * This whole class pretty much covers the entirety of Step 3: aOrderedList Class, Step 5: Managing Array Size, and Step 6: Completion of aOrderedList Class,
 * and Steps 2-4 of Step 8: Extend our aOrderedList class.
 *
 * CSC 1351 Programming Project No. 1 Part A
 * Section 002
 *
 * @author Madison Harman
 * @since 03/17/2024
 *
 */

class aOrderedList {
	private static final int SIZEINCREMENTS = 20; // Size of increments for increasing ordered list.
	private Comparable[] oList; // The ordered list.
	private int listSize; // The size of the ordered list.
	private int numObjects; // The number of objects in the ordered list. 
	private int curr; // Index of current element accessed via iterator methods.

	/**
	 * aOrderedList - Constructor that sets sets numObjects to 0, sets listSize to the value of
	 * SIZEINCREMENTS, and instantiates the array oList to an array of size
	 * SIZEINCREMENTS.
	 *
	 * CSC 1351 Programming Project No. 1 Part A
	 * Section 002
	 *
	 * @author Madison Harman
	 * @since 03/17/2024
	 *
	 */

	public aOrderedList() {
		numObjects = 0;
		listSize = SIZEINCREMENTS;
		oList = new Car[SIZEINCREMENTS];
	}

	/**
	 * add - Adds the newObject object to the sorted array in the correct position to maintain sorted order.
	 * @param newObject - We need this in order to add the new object to the sorted array.
	 * 
	 * CSC 1351 Programming Project No. 1 Part A
	 * Section 002
	 *
	 * @author Madison Harman
	 * @since 03/17/2024
	 *
	 */

	public void add(Comparable<Car> newObject) {
		int index = 0; // Need to know the index to know where to add the Car.

		// Increases the size of the list if the number of objects and the actual size of the list are equal.
		if (numObjects == listSize) {
			oList = Arrays.copyOf(oList,listSize + SIZEINCREMENTS); // This covers Step 5: Managing Array Size.
			listSize += SIZEINCREMENTS;
		}
		// While the index is less than the number of objects and the comparison of the newObject and a Car at the specified index in the list returns 0...
		while(index < numObjects && newObject.compareTo((Car)oList[index]) > 0) {
			index++; // Increases the value of index by 1.
		}
		// Iterates through the list until the index value.
		for(int i = numObjects - 1; i >= index; i--) {
			oList[i + 1] = oList[i]; // Shifts the elements in the oList.
		}
		oList[index] = newObject; // Inserts newObject into the oList.
		numObjects++; // Updates the number of objects.
	}

	/**
	 * toString - Returns the toString values of the list objects, separated by commas, and delimited by brackets 
	 * (e.g., [Make: Kia, Year: 2007, Price: 4000;]. This method calls the toString of the Car objects in the oList 
	 * array to construct the return value of the method.
	 * @return - Returns the toString values of the list objects in a certain format.
	 * 
	 * CSC 1351 Programming Project No. 1 Part A
	 * Section 002
	 *
	 * @author Madison Harman
	 * @since 03/17/2024
	 *
	 */

	@Override
	public String toString() {
		String stringOutput = ""; // The toString output to be returned later.
		
		// Iterates through the list.
		for(int i = 0; i < numObjects; i++) {
			// This separates the list objects by commas.
			if(i < numObjects - 1) {
				stringOutput += ", ";
			}
			// Else, the toString values are delimited by brackets.
			else {
				stringOutput += "[" + oList[i].toString() + "]";
			}
		}
		return stringOutput; // Returns the stringOutput in the requested format.
	}

	/**
	 * size - Returns the number of elements in this list.
	 * @return - The number of elements in this list.
	 *
	 * CSC 1351 Programming Project No. 1 Part A
	 * Section 002
	 *
	 * @author Madison Harman
	 * @since 03/17/2024
	 *
	 */

	public int size() {
		return numObjects;
	}

	/**
	 * get - Returns the element at the specified position in this list.
	 * @param index - We need to know the index to return the element at that position.
	 * @return - The element at the specified position in this list.
	 *
	 * CSC 1351 Programming Project No. 1 Part A
	 * Section 002
	 *
	 * @author Madison Harman
	 * @since 03/17/2024
	 *
	 */

	public Comparable<Car> get(int index) {
		return oList[index];
	}

	/**
	 * isEmpty - Returns true if the array is empty and false otherwise.
	 * @return - True if the array is empty and false otherwise.
	 *
	 * CSC 1351 Programming Project No. 1 Part A
	 * Section 002
	 *
	 * @author Madison Harman
	 * @since 03/17/2024
	 *
	 */

	public boolean isEmpty() {
		return numObjects == 0;
	}

	/**
	 * remove - Removes the element at the specified position in this list. Remember that elements that follow the 
	 * removed element must “move down” to fill the hole in the array.
	 * @param index -  We need to know the index to remove the element at that position.
	 *
	 * CSC 1351 Programming Project No. 1 Part A
	 * Section 002
	 *
	 * @author Madison Harman
	 * @since 03/17/2024
	 *
	 */

	public void remove(int index) {
		// Iterates through the list in order to find the index to remove at.
		for(int i = index; i < numObjects - 1; i++) {
			oList[i] = oList[i + 1];
		}
		numObjects--; // Updates the number of objects.
	}

	/**
	 * reset - Resets iterator parameters so that the “next” element is the first element in the
	 * array, if any.
	 *
	 * CSC 1351 Programming Project No. 1 Part A
	 * Section 002
	 *
	 * @author Madison Harman
	 * @since 03/17/2024
	 *
	 */

	public void reset() {
		curr = -1;
	}

	/**
	 * next - Returns the next element in the iteration and increments the iterator parameters.
	 * @return - The next element in the iteration and increments the iterator parameters.
	 *
	 * CSC 1351 Programming Project No. 1 Part A
	 * Section 002
	 *
	 * @author Madison Harman
	 * @since 03/17/2024
	 *
	 */

	public Comparable<Car> next() {
		curr += 1; // Increments the iterator parameters.
		return oList[curr]; // Returns the next element in the iteration.
	}

	/**
	 * hasNext - Returns true if the iteration has more elements to iterate through.
	 * @return - True if the iteration has more elements to iterate through.
	 *
	 * CSC 1351 Programming Project No. 1 Part A
	 * Section 002
	 *
	 * @author Madison Harman
	 * @since 03/17/2024
	 *
	 */

	public boolean hasNext() {
		return curr + 1 < numObjects;
	}

	/**
	 * remove - Removes the last element returned by the next() method.
	 *
	 * CSC 1351 Programming Project No. 1 Part A
	 * Section 002
	 *
	 * @author Madison Harman
	 * @since 03/17/2024
	 *
	 */

	public void remove() {
		remove(curr);
	}
}
