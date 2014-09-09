/////////////////////////////////////////////////////////////////////////////
//  
//  CLASS: SequentialSearch
//  
//  NEEDS: Timer and ArrayGenerator classes
//
//  DELIVERY CONDITION: The delivered version is set to run and test with
//  main() methods. Turn this off when the class is implemented.
//
//  CLASS METHODS: Primary method: search(int[] integer_array, int value, int k)
//  When used, call as SequentialSearch.search(int[] integer_array, int value, int k).
//  All other methods are for development and testing.
//  
//  METHOD VARIABLES FOR PARAMETERS: 
//  int[] integer_array: provided array
//  int value: value searching for
//
//  DEVELOPER: Sheldon Schiffer
//  OTHER TEAM MEMBERS: Bryan Oliver, Caynan Sousa
//  
//  COURSE: CS 6520: Design & Analysis of Algorithms
//  INSTRUCTOR: Sushil Prasad
//  SEMESTER: Fall 2014
//////////////////////////////////////////////////////////////////////////////
import java.util.Arrays;

public class SequentialSearch {
    
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
    public int search(int[] partition_integer_array, int value)
    {
        //Presets search condition
        boolean found = false;   
        
        //Let's start at the first index, like any sequential search
        int index = 0;

        while(!found && index <= partition_integer_array.length -1 && index >= 0)
        {   

            //Checks value at given index
            if(partition_integer_array[index] == value)
            {    
                 found = true;
            
            //If above check fails, checks if last failed check
            //was the last index of array. Prevents out of bound error.
            } else if((partition_integer_array.length -1) == index)
            {
                index = -1; 
                
            } else
                index++;   
               
        }
        
        return index;
    }
    
}
