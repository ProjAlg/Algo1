

////////////////////////////////////////////////////////////
//
// Class: BinarySearch
//   Developer: Bryan Oliver
//   Team Mates: Sheldon Schiffer, Caynan
//   Course: Design and Analysis of Algorithms
//   Semester: Fall 2014
//
////////////////////////////////////////////////////////////


import java.util.List;
import java.util.*;

public class RecursiveSearch {

    //If more than 2 partitions are specified, then this method should get called. Otherwise, just search should be called.

    //Side note: So this program would be fantastic, if we spawned the searching of each partition into seperate threads with a multithread.
    //We could take each partition, and search each one with a binary search, simultaneously. And kill the program when one of the threads
    //discovers the target. Then, it simply communicates the partition number to calculate the actual index, and returns it.
    //Just by itself though, there is really no point to breaking the array into further partitions. TODO: write a proof for that note.
    //Improvement note, pull the last element from the middle array, perform a comparison, and eliminate the out of range partitions.
    //Which is really just a redundant binary search.

    //This method specifies the number of partitions, the target, and the array, as parameters. It breaks the array into sub-arrays, or partitions,
    //with the createPartitions method. Then it searches each partition with recursive binary search, until it finds the target. It then returns
    //the actual target index (it calculates it based on partition level).
    public int searchWithMultiplePartitions(int a[], int targetget, int partitions ) {

        List<int[]> newParts = createPartitions(a, partitions);

        int targetIndex = -1;
        int partitionNumber = 1;

        for (int i = 0; i < newParts.size() && targetIndex == -1; i++ ) {
            targetIndex = search( newParts.get(i), targetget, 0, newParts.get(i).length - 1 );
            partitionNumber = i + 1;
        }

        int indexesToAdd = (newParts.get(0).length) * (partitionNumber - 1);

        return targetIndex + indexesToAdd;
    }


    // Expects an array, a target, the first index, and the last index of the array. Performs binary search, recursively, on the array.
    public int search(int a[], int target, int firstIndex, int lastIndex) {


        if ( firstIndex > lastIndex ) {
            return -1;
        }

        int middleIndex = (firstIndex + lastIndex) / 2;
        if ( a[middleIndex] == target ) {
            System.out.println("match found");
            return middleIndex;
        }

        else if ( a[middleIndex] > target ) {
            return search(a, target, firstIndex, middleIndex - 1);
        }

        else {
            return search(a, target, middleIndex + 1, lastIndex );
        }


    }

    //Method creates the desired number of partition arrays, and indexes them into a list. This list is then returned for searching.
    public List<int[]> createPartitions(int arrayToPartition[], int numberOfPartitions ) {

        List<int[]> ListOfPartitions = new ArrayList<int[]>();
        int size = arrayToPartition.length / numberOfPartitions;
        int first = 0;
        int last = size;
        for( int i = 0; i < numberOfPartitions; i++ ) {

            ListOfPartitions.add( Arrays.copyOfRange( arrayToPartition, first, last ) );
            first = last;
            last += size;

        }
        return ListOfPartitions;
    }

    /*
    //used for testing. Can be deleted safely
    public static void main(String[] args) {
        RecursiveSearch test = new RecursiveSearch();
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        //System.out.println(test.search(array, 3, 0, array.length - 1));
        System.out.println(test.searchWithMultiplePartitions(array, 15, 3));
        //test.createPartitions(array, 3);
    }
    */


}
