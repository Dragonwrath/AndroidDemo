package com.example.quicksort;


import java.util.Arrays;
import java.util.TreeSet;

public class Sorts {
    private static final int[] nums = {3, 12, 22, 34, 58,18, 21, 8, 1};
    public static void main(String[] args) {
        Fibonacci.fibonacci(10);
        Fibonacci.setLength(10);
        Fibonacci.recursiveFibonacci(0, 10);

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

        static void quickSort4(int[] nums, int start, int end) {
            int begin = nums[start];
            int l,h;
            for (int i = start + 1, j = end;;) {
                if (nums[i] > begin && nums[j] < begin) {
                    swap(nums[i],nums[j]);
                    i++;
                    j--;
                }
                if (nums[i] > begin){
                    j--;
                }
                if (nums[j] <= begin ) {
                    i++;
                }

                if (i >= j) {
                    l = j;
                    h = i;
                    break;
                }
            }




        }

        private static void swap(int a, int b) {
            int temp = b;
            b = a;
            a =temp;
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
}
