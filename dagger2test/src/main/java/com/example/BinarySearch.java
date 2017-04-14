package com.example;


import java.util.Arrays;

public class BinarySearch {
    private static int[]  source = {1,2,3,4,5,6,7,8,9,10};
    private static int[] reserSource = {10,19,81,71,16,15,41,13,12,11};
    public static void main(String[] args) {
        int i = binarySearch(source, 10, 9);
        System.out.println("i = " + i);

//        Arrays.sort(reserSource);
        int i1 = binarySearch(reserSource, 10, 81);
        System.out.println("i1 = " + i1);
    }

    private static int binarySearch(int[] source, int size, int value) {
        int low = 0;
        int high = size-1;
        while(low <= high) {
//            System.out.println("low = " + low);
            int mid = (low + high)>>1;
            int midValue = source[mid];

            if (midValue > value){
                high = mid - 1;
            } else if ( midValue < value) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return ~high;
    }
}
