import java.util.HashMap;

/**
 * Class of operations on integer arrays.
 * You MUST NOT change the signatures of the methods supplied. 
 */
 
// IN1002 Introduction to Algorithms
// Coursework 2024/2025
//
// Submission by
// Thomas Craggs
// Thomas.Craggs@city.ac.uk

public class iAnalytics {

    // Task 1: Count unique elements in an ordered array
    public int countUnique(int[] arr) {

        int unique = arr.length; // assume all values in array are unique

        // invariant: 0 ≤ i < arr.length - 1 AND unique ≤ arr.length
        for (int i = 0; i < arr.length - 1; i++) {

            // check if next value is a duplicate of the current
            if (arr[i] == arr[i+1]){
                // decrement unique, as next item is a duplicate
                unique--;
            }
        }

        return unique;

        // TIME COMPLEXITY: O(n)
        // Where n is the input size, as the algorithm loops through the array once.

    }


    // Task 2: Find least frequent value in an ordered array
    public int leastFrequent(int[] arr) {
        // replace the following line with your implementation
        throw new UnsupportedOperationException("Not implemented yet.");
    }


    // Task 3: Count elements in an ordered array less than num
    public int countLess(int[] arr, int num) {
        int count = 0;

        for (int i : arr){
            if(i >= num){
                break;
            }
            count++;
        }
        return count;

        // TIME COMPLEXITY: O(n)
        // Where n is the input size, as the algorithm loops through the array once.

    }
	

    // Task 4: Count elements in an ordered array between low and high
    public int countBetween(int[] arr, int low, int high) {
        int count = 0;
        for (int i : arr){
            if(i >= low && i <= high){
                count++;
            }

            // terminate loop early if i exceeds high
            if(i > high){
                break;
            }
        }
        return count;

        // TIME COMPLEXITY: O(n)
        // Where n is the input size, as the algorithm loops through the array once.

    }
	

    // Task 5: Find top K most frequent elements in an ordered array
    public int[] topKFrequent(int[] arr, int k) {
        // replace the following line with your implementation
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    // Task 6: Longest contiguous subarray in ascending order
    public int[] longestAscSubarray(int[] arr) {
        // replace the following line with your implementation
        throw new UnsupportedOperationException("Not implemented yet.");
    }


    // Task 7: Maximum sum of a contiguous subarray with exactly k elements
    public int maxSubarraySum(int[] arr, int k) {
        // replace the following line with your implementation
        throw new UnsupportedOperationException("Not implemented yet.");
    }


}
