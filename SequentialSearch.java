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
//  All other methods are for devlopment and testing.
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
package SequentialSearch;

import java.util.Arrays;

public class SequentialSearch {
    
//////////////////////////////////////////////////////////////////////////////
//  
//  DISABLE MAIN()
//  
//  FOR TESTING ONLY
//
//  TESTING DATA INPUT: Enter value of class methods variables: 
//  magnitude, search_value
//
//////////////////////////////////////////////////////////////////////////////    
    public static void main(String[] args) {
        
        //ENTER VALUE FOR INPUT
        int magnitude = 10000;
        
        //ENTER VALUE FOR INPUT (integer between 0 and magnitude value)
        int search_value = 0;
        
        //Sets testing array
        int[] linear_search_array = new int[magnitude];
        
        populateArray(linear_search_array, magnitude);
        
        sortArray(linear_search_array);
        
        displayArrayValues(linear_search_array);
        
        search(linear_search_array, search_value);
        
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
    public static int search(int[] partition_integer_array, int value)
    {
        //Presets search condition
        boolean found = false;   
        
        //Let's start at the first index, like any sequential search
        int index = 0;
        
        //System.out.println("index is: " + index + " partition_integer_array.length is: " + partition_integer_array.length);
               
        while(!found && index <= partition_integer_array.length -1 && index >= 0)
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
            //Turn off when implementation is final.
            System.out.println("Value found at index: " + index);
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
    
    
    public static int oldSearch(int[] integer_array, int value)
    {
        boolean found = false;   
        
        int index = 0;
        while(!found)
        {          
            if(integer_array[index] == value)
            {    
                 found = true;
                 
            } else if(integer_array.length -1 == index)
            {
                      
                index = -1; 
                
            } else
                index++;
       
               
        }
        
        
        
        return index;
        
    
              
    }
    
}
