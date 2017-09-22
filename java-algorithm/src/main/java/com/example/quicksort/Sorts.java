package com.example.quicksort;


import java.util.Arrays;
import java.util.TreeSet;


public class Sorts {
    private static final int[] nums = {3, 12, 22, 34, 58,18, 21, 8, 1};
    public static void main(String[] args) {
        QuickSort.quickSortRecursively(nums,0, nums.length - 1);
        System.out.println(Arrays.toString(nums));

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

        static void quickSortRecursively(int[] array, int left, int right) {
            int pivotKey;
            if (left < right) {
                pivotKey = partitionByPivotValue(array, left, right);
                quickSortRecursively(array, left, pivotKey - 1);
                quickSortRecursively(array, pivotKey + 1, right);
            }
        }

        static void quickSortNonRecursively(int[] nums, int start, int end) {

        }

        static int partitionByPivotValue(int[] array, int left, int right) {
            int pivotValue = array[left];
            while (left < right) {
                while (left < right && array[right] >= pivotValue) {
                    --right;
                }
                array[left] = array[right];
                while (left < right && array[left] <= pivotValue) {
                    ++left;
                }
                array[right] = array[left];
            }
            array[left] = pivotValue;
            return left;
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

    private static class Fibonacci {
        private static int[] result;
        static void fibonacci(int n) {
            int[] nums = new int[n];
            nums[0] = nums[1] = 1;

            for (int i = 2; i < n; i++) {
                nums[i] = nums[i-1] + nums[i-2];
            }

            String string = Arrays.toString(nums);
            System.out.println("Arrays = " + string);
        }

        static void setLength(int length) {
            result = new int[length];
        }

        static void recursiveFibonacci(int n, int length) {
            if (n == 0) {
                result[0] = result[1] = 1;
                recursiveFibonacci(2,length);
            }else if (n < length && n > 0) {
                result[n] = result[n - 1] + result[n - 2];
                recursiveFibonacci(n + 1,length);
            } else if ( n > 0 ){
                String string = Arrays.toString(result);
                System.out.println("Arrays = " + string);
            }
        }
    }

    private static class SelectSort {
        static void selectSortRecursively(int[] source, int length) {
            int max = source[0], index = 0;
            for (int i = 0; i < length; i++) {
                if (max < source[i]) {
                    max = source[i];
                    index = i;
                }

            }
            if (index != length - 1) {
                int temp = source[index];
                source[index] = source[length - 1];
                source[length - 1] = temp;
            }
            if (length > 2)
                selectSortRecursively(source, length - 1);
        }

        static void selectSortNonRecursively(int[] source) {
            int length = source.length, index, min;
            for (int i = 0; i < length - 1; i++) {
                min = source[i];
                index = i;
                for (int j = i + 1; j < length; j++) {
                    if (source[j] < min) {
                        min = source[j];
                        index = j;
                    }
                }
                int temp = source[index];
                source[index] = source[i];
                source[i] = temp;
            }
        }
    }
}
