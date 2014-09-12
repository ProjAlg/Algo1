/////////////////////////////////////////////////////////////////////////////
//  
//  CLASS: JumpSearch
//  
//  NEEDS: Timer and ArrayGenerator classes
//
//  DELIVERY CONDITION: The delivered version is set to run and test with
//  main() methods. Turn this off when the class is implemented.
//
//  CLASS METHODS: Primary method: search(int[] integer_array, int value, int k)
//  When used, call as JumpSearch.search(int[] integer_array, int value, int k).
//  All other methods are for devlopment and testing.
//  
//  METHOD VARIABLES FOR PARAMETERS: 
//  int[] integer_array: provided array
//  int value: value searching for
//  int k: interval length for jumping from one partition to next
//
//  DEVELOPER: Sheldon Schiffer
//  OTHER TEAM MEMBERS: Bryan Oliver, Caynan Sousa
//  
//  COURSE: CS 6520: Design & Analysis of Algorithms
//  INSTRUCTOR: Sushil Prasad
//  SEMESTER: Fall 2014
//////////////////////////////////////////////////////////////////////////////

import java.util.Arrays;

public class JumpSearch {

    static int found_count = 0;


    //////////////////////////////////////////////////////////////////////////////
//  
//  METHOD: search()
//
//  DESCRIPTION: Performs the jump of the jump search
//
//  NEEDS: Array, search_value and k_interval; Returns boolean, true if found
//
//  HELPED BY: partitionSearch(), this helper method does a sequential search
//  within the given interval of k length and start and end values  
//
//////////////////////////////////////////////////////////////////////////////
    public boolean search(int[] integer_array, int value, int k) {
        //presets returned value to -2, indicated value is out of the ranges
        //of any interval k length
        int index_found = -2;

        int partition_length = (int) Math.ceil(integer_array.length / k);

        //Current partition for while loop below.
        //Each loop sends the partition range to the partitionSearch()
        int partition_number = 0;
        boolean found = false;
        while (!found && partition_number < k) {
            //Checks condition if the partition we are look at is any but the
            //last one
            if (partition_number < k - 1) {

                //Check that the give value is greater than the start index of
                //the checked partition, and less than the last index of that
                //partition
                if (value >= integer_array[partition_number * partition_length] && value <= integer_array[((partition_number + 1) * partition_length) - 1]) {


                    //Gathers the returned value of the partitionSearch()
                    index_found = partitionSearch(integer_array, value, partition_number * partition_length, ((partition_number + 1) * partition_length - 1));

                    //Any positive integer and 0 returned indicates the location
                    //of the first array element where the value was found
                    if (index_found >= 0) {
                        found = true;
                    }
                }
            }

            //Checks that we are on the last possible partition, and considers
            //that the last one is shorter than the others, so it bases its
            //end index on the last element in the arrya.
            if (partition_number == k - 1) {

                //Checks value with first element of the last partition and the
                //value of the last element of the array
                if (value > integer_array[partition_number * partition_length] && value < integer_array[integer_array.length - 1]) {
                    //Gathers the returned value of the partitionSearch()
                    index_found = partitionSearch(integer_array, value, partition_number * partition_length, integer_array.length - 1);
                }
            }

            //increments tonext partition number
            partition_number++;

        }

//        //Prints index. Comment out as needed.
//        if (found == true) {
//            System.out.println("Value " + value + " found at index: " + index_found);
//        } else
//            System.out.println("Last index checked. Value " + value + " not found.");

        return found;

    }


    //////////////////////////////////////////////////////////////////////////////
//  
//  METHOD: partitionSearch()
//
//  DESCRIPTION: Performs the sequential search of the interval given 
//  for a jump search
//
//  NEEDS: Array, search_value start_index and end_index; Returns int
//  The int returned is the index number of the array that holds the matching
//  value
//
//  HELPS: search(), this method does a jump search
//  across all intervals of k length  
//
//////////////////////////////////////////////////////////////////////////////
    private static int partitionSearch(int[] partition_integer_array, int value, int start_index, int end_index) {
        //Presets search condition
        boolean found = false;

        //Copies start_index to an incremented index for while loop
        int index = start_index;

        //System.out.println("index is: " + index + " partition_integer_array.length is: " + partition_integer_array.length);

        while (!found && index <= end_index && index >= 0) {
            //System.out.println("index is: " + index);

            //Checks value at given index
            if (partition_integer_array[index] == value) {
                found = true;

                //Turn off at implementation final
                //System.out.println("Value found!");

                //If above check fails, checks if last failed check
                //was the last index of array. Prevents out of bound error.
            } else if ((partition_integer_array.length - 1) == index) {
                //Turn off at implementation is final.
                //System.out.println("Last index checked. Value not found.");
                index = -1;

            } else
                index++;

        }

        if (index >= 0) {
            //System.out.println("Value found at index: " + index);
        }

        return index;
    }
}
    
