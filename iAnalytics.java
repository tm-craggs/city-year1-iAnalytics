import java.util.Arrays;

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

        if (arr == null || arr.length == 0) {return 0;}

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

        if (arr == null || arr.length == 0) {return 0;}

        int minCount = arr.length + 1;  // larger than array, so any found count will be smaller
        int leastElement = -1;
        int current_count = 1;

        // start loop on second element to avoid out of bounds
        // invariant: 1 ≤ i < arr.length
        for (int i = 1; i < arr.length; i++) {

            // check previous element, if equal, increase the count variable
            if (arr[i] == arr[i - 1]) {
                current_count++;
            }

            // if not equal, check if the current minCount is smaller than currentCount
            else {
                if (current_count < minCount) {
                    // assign new minCount and leastElement
                    minCount = current_count;
                    leastElement = arr[i - 1];
                }
                // reset current_count
                current_count = 1;
            }
        }

        // check for the last element
        if (current_count < minCount) {
            leastElement = arr[arr.length - 1];
        }

        return leastElement;

        // TIME COMPLEXITY O(n)

    }


    // Task 3: Count elements in an ordered array less than num
    public int countLess(int[] arr, int num) {

        // handle empty or null array
        if (arr == null || arr.length == 0) {return 0;}

        int count = 0;  // stores the number of loops

        // invariant: 0 ≤ i < arr.length
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

        // handle empty or null array
        if (arr == null || arr.length == 0) {return 0;}

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

        if (arr == null || arr.length == 0) {return new int[0];}

        // create 2D array. each entry will be [value, count]
        int[][] pairs = new int[arr.length][2];
        int[] topK = new int[k];

        int current_count = 1;
        int index = 0;

        // count all unique values and their frequencies, store in 2D array
        for (int i = 0; i < arr.length - 1; i++) {

            if (arr[i] == arr[i + 1]) {
                current_count++;
            } else {
                pairs[index] = new int[] {arr[i], current_count};
                index++;
                current_count = 1;
            }
        }

        // handle edge case
        pairs[index] = new int[] {arr[arr.length - 1], current_count};


        // sort 2D array by count using bubble sort
        for (int i = 0; i < pairs.length - 1; i++){
            for (int j = 0; j < pairs.length - 1 - i; j++){
                if (pairs[j][1] < pairs[j + 1][1] ||
                        (pairs[j][1] == pairs[j + 1][1] && pairs[j][0] > pairs[j + 1][0])) {
                    int[] temp = pairs[j];
                    pairs[j] = pairs[j+1];
                    pairs[j+1] = temp;
                }
            }
        }

        // return array of the first value of up to k in pairs array
        for (int i = 0; i < k ; i++) {
            topK[i] = pairs[i][0];
        }
        return topK;

        // TIME COMPLEXITY: O(n^2)
        // This is due to the bubble sort implementation having a nested loop.
    }

    // Task 6: Longest contiguous subarray in ascending order
    public int[] longestAscSubarray(int[] arr) {

        // handle empty array
        if (arr == null || arr.length == 0) { return new int[0]; }

        int start = 0; // index of start of current ascending subarray
        int maxStart = 0;  // index of start of largest subarray
        int maxLen = 0; // length of largest subarray

        // invariant: 0 ≤ i < arr.length
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i + 1] < arr[i]) {
                // compare current subarray length
                if (i - start + 1 > maxLen) {
                    maxLen = i - start + 1;
                    maxStart = start;
                }
                start = i + 1; // reset start for next subarray
            }
        }

        // manually check if the end of the array is part of the longest accending subarray
        // as maxLen may not be updated as there is no drop

        if (arr.length - start > maxLen) {
            maxLen = arr.length - start;
            maxStart = start;
        }

        // copy all values between the range into a new array, and return
        int result[] = new int[maxLen];
        for (int i = 0; i < maxLen; i++) {
            result[i] = arr[maxStart + i];
        }


        return result;

        // TIME COMPLEXITY: O(n)
        // The main array is looped through once
    }


    // Task 7: Maximum sum of a contiguous subarray with exactly k elements
    public int maxSubarraySum(int[] arr, int k) {

        // handle cases where the array is empty, or k is greater than the array size
        if (arr == null || arr.length == 0 || arr.length < k) { return 0;}

        int sum = 0;
        int maxSum;

        // calculate sum of first k elements
        // invariant: 0 ≤ i < k
        for (int i = 0; i < k; i++){
            sum += arr[i];
        }
        maxSum = sum;

        // slide window along one space. add the next number in array and minus the last one
        for (int i = k; i < arr.length; i++){
            sum = sum + arr[i] - arr[i - k];
            if (sum > maxSum){
                maxSum = sum;
            }
        }

        return maxSum;

        // TIME COMPLEXITY: O(n)
        // The main array is looped through once

    }


}
