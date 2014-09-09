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
package jumpsearch;

//needed for sort method after random number population of array
import java.util.Arrays;

public class JumpSearch {

    static int found_count = 0;
    
//////////////////////////////////////////////////////////////////////////////
//  
//  DISABLE MAIN()
//  
//  FOR TESTING ONLY
//
//  TESTING DATA INPUT: Enter value of class methods variables: 
//  magnitude, search_value, k for number partions to jump
//
//////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        
        //ENTER VALUE FOR INPUT
        int magnitude = 10000;
        
        //ENTER VALUE FOR INPUT (integer between 0 and magnitude value)
        int search_value = 0;
        
        //ENTER VALUE FOR INPUT
        int k = 5; //k intervals to jump through array, k <= magnitude
        
        //Sets size of array. Change size paramter as needed. 
        int[] linear_search_array = new int[magnitude];
        
        //Sends random values from 0 to array size into each array element.
        populateArray(linear_search_array, magnitude);
        
        //Sorts using Arrar.sort()
        sortArray(linear_search_array);
        
        //Displays to console for debugging
        displayArrayValues(linear_search_array);
        
        //Sets the paramters for search(Array, int search_value, int k_partition_size)
        //and returns either index with positive integer, or -2 (no partition held the
        //range for the search_value, or -1 (the partition searched, did not have the
        //value
        boolean result = search(linear_search_array, search_value, k);
        
        //Shows result
        System.out.println(result + "\n");
        
    }
    
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
    public static boolean search(int[] integer_array, int value, int k)
    {
        //presets returned value to -2, indicated value is out of the ranges
        //of any interval k length
        int index_found = -2;
        
        //System.out.println("value is:" + value);
        
        //System.out.println("k is: " + k);
           
        int partition_length = (int) Math.ceil(integer_array.length/k);
        
        //System.out.println("partition_length is: " + partition_length);
        
        //Current partition for while loop below.
        //Each loop sends the partition range to the partitionSearch()
        int partition_number = 0;
        boolean found = false;
        while(!found && partition_number < k)
        {
            //System.out.println("partition_number is: " + partition_number);
            
            //Checks condition if the partition we are look at is any but the
            //last one
            if(partition_number < k-1)
            {
                //System.out.println("from i < k-1, i is: " + partition_number);       
                
                //Check that the give value is greater than the start index of
                //the checked partition, and less than the last index of that
                //partition
                if(value >= integer_array[partition_number*partition_length] && value <= integer_array[((partition_number+1)*partition_length)-1] )
                {
                    
                    //System.out.println("i*partition_length: " + partition_number*partition_length + " (i+1)*partition_length)-1: " + ((partition_number+1)*partition_length-1));
                    
                    //Gathers the returned value of the partitionSearch()
                    index_found = partitionSearch(integer_array, value, partition_number*partition_length, ((partition_number+1)*partition_length-1));
                
                    //Any positive integer and 0 returned indicates the location
                    //of the first array element where the value was found
                    if(index_found >= 0)
                    {
                        found = true;
                    }
                }
            }
            
            //Checks that we are on the last possible partition, and considers
            //that the last one is shorter than the others, so it bases its
            //end index on the last element in the arrya.
            if(partition_number == k-1)
            {
                //System.out.println("from i == k, i is: " + partition_number);
                
                //Checks value with first element of the last partition and the
                //value of the last element of the array
                if(value > integer_array[partition_number*partition_length] && value < integer_array[integer_array.length-1] )
                {
                    //Gathers the returned value of the partitionSearch()
                    index_found = partitionSearch(integer_array, value, partition_number*partition_length, integer_array.length-1);
                }
            }
            
            //increments tonext partition number
            partition_number++;
        
        }
        
        //Prints index. Comment out as needed.
        if(found == true)
        {
            System.out.println("Value " + value + " found at index: " + index_found);
        } else
            System.out.println("Last index checked. Value " + value + " not found.");
       
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
    public static int partitionSearch(int[] partition_integer_array, int value, int start_index, int end_index)
    {
        //Presets search condition
        boolean found = false;   
        
        //Copies start_index to an incremented index for while loop
        int index = start_index;
        
        //System.out.println("index is: " + index + " partition_integer_array.length is: " + partition_integer_array.length);
               
        while(!found && index <= end_index && index >= 0)
        {   
            //System.out.println("index is: " + index);
            
            //Checks value at given index
            if(partition_integer_array[index] == value)
            {    
                 found = true;
                 
                 //Turn off at implementation final
                 System.out.println("Value found!");
            
            //If above check fails, checks if last failed check
            //was the last index of array. Prevents out of bound error.
            } else if((partition_integer_array.length -1) == index)
            {
                //Turn off at implementation is final.
                System.out.println("Last index checked. Value not found.");
                index = -1; 
                
            } else
                index++;   
               
        }
        
        if(index >= 0)
        {
            //System.out.println("Value found at index: " + index);
        } 
        
        return index;
    }
    
//////////////////////////////////////////////////////////////////////////////
//  
//  METHOD: populateArray()
//
//  DESCRIPTION: Copies each returned random number to the provided array
//
//  NEEDS: Array, int m, where m is magnitude of random number
//
//  HELPED BY: generateRandomNumber(), this method generates a number each
//  time called
//
//////////////////////////////////////////////////////////////////////////////    
    public static void populateArray(int[] integer_array, int m)
    {
        for(int i = 0; i < integer_array.length; i++)
        {
            integer_array[i] = generateRandomNumber(m);
        }
    }
     
//////////////////////////////////////////////////////////////////////////////
//  
//  METHOD: sortArray()
//
//  DESCRIPTION: Sorts values in array elements in ascending order
//
//  NEEDS: Array
//
//  HELPS: main()
//
////////////////////////////////////////////////////////////////////////////// 
    public static void sortArray(int[] integer_array)
    {
        Arrays.sort(integer_array);
    }
    
//////////////////////////////////////////////////////////////////////////////
//  
//  METHOD: generateRandomNumber()
//
//  DESCRIPTION: Generates random number of magnitude m
//
//  NEEDS: int m, the magnitude of random number
//
//  HELPS: populateArray()
//
//////////////////////////////////////////////////////////////////////////////    
    public static int generateRandomNumber(int m)
    {
        double random_double = Math.random();
        
        int random = (int) Math.round(random_double * m);
        
        return random;
    }

//////////////////////////////////////////////////////////////////////////////
//  
//  METHOD: displayArrayValues()
//
//  DESCRIPTION: Prints array to output console in readable form
//
//  NEEDS: Array
//
//  HELPS: main()
//
//////////////////////////////////////////////////////////////////////////////     
    public static void displayArrayValues(int[] integer_array)
    {
        for(int i = 0; i < integer_array.length; i++)
        {        
            System.out.print(" | " + integer_array[i]);
        }
        
        System.out.println(" |");
    }
    
    
}

