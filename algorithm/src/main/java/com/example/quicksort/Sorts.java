package com.example.quicksort;


import java.util.Arrays;
import java.util.TreeSet;

public class Sorts {
    private static final int[] nums = {3, 12, 22, 34, 58,18, 21, 8, 1};
    public static void main(String[] args) {
        System.out.println("position= " + BinarySearch.binarySearch1(nums, 60));
    }


    private static class QuickSort {
        static void quickSort1(int[] nums) {
            long t1 = System.nanoTime();
            TreeSet<Integer> set = new TreeSet<>();
            for (int num : nums) {
                set.add(num);
            }
            long t2 = System.nanoTime();

//            System.out.println("(t2-t1) = " + (t2-t1));
//            System.out.println("set = " + set.toString());
        }

        static void quickSort2(int[] nums) {
            int[] ints = Arrays.copyOf(nums, nums.length);
            long t1 = System.nanoTime();
            Arrays.sort(ints);
            long t2 = System.nanoTime();
//            System.out.println("(t2-t1) = " + (t2-t1));
//            System.out.println("ints = " + Arrays.toString(ints));
        }

        static void quickSort3(int[] nums) {
            int[] ints = Arrays.copyOf(nums, nums.length);
            long t1 = System.nanoTime();
            Arrays.sort(ints);
            long t2 = System.nanoTime();
        }

    }

    private static class BubbleSort {
        static int[] bubbleSort1(int[] nums) {
            int[] ints = Arrays.copyOf(nums, nums.length);
            long t1 = System.nanoTime();
            int temp;
            //算法实现
            for (int i = ints.length - 1; i > 0 ; i--) {
                for (int j = 0; j < i ; j++) {
                    if (ints[j] > ints[j+1]){
                        temp = ints[j];
                        ints[j] = ints[j+1];
                        ints[j+1] = temp;
                    }
                }
            }
            long t2 = System.nanoTime();
            System.out.println("(t2-t1) = " + (t2-t1));
            System.out.println("ints = " + Arrays.toString(ints));
            return ints;
        }
    }

    private static class BinarySearch {

        static int binarySearch1(int[] nums, int key) {
            int[] ints = BubbleSort.bubbleSort1(nums);
            int position = -1;
            if (ints == null || ints.length <= 0) {
                return position;
            }
            int start = 0,end = ints.length - 1, center = ints.length >> 1;
            while (end >= start) {
                if (ints[center] < key) {
                    start = center + 1;
                    center = (start + end) >> 1;
                    continue;
                }
                if (ints[center] > key) {
                    end = center - 1;
                    center = (start + end) >> 1;
                    continue;
                }

                if (ints[center] == key) {
                    position = center;
                    break;
                }
            }
            return position;
        }
    }
}
