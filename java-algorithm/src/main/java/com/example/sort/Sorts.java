package com.example.sort;


import java.util.Arrays;
import java.util.TreeSet;


@SuppressWarnings("unused")
public class Sorts{
  private static final int[] nums={15,3,12,22,34,50,18,21,8,1,87,76,45,29,38,42};

  public static void main(String[] args){
    InsertionSort.shellInsertionSort(nums);
  }

  private static class QuickSort{
    static void quickSort1(int[] nums){
      long t1=System.nanoTime();
      TreeSet<Integer> set=new TreeSet<>();
      for(int num : nums) {
        set.add(num);
      }
      long t2=System.nanoTime();

//            System.out.println("(t2-t1) = " + (t2-t1));
//            System.out.println("set = " + set.toString());
    }

    static void quickSort2(int[] nums){
      int[] ints=Arrays.copyOf(nums,nums.length);
      long t1=System.nanoTime();
      Arrays.sort(ints);
      long t2=System.nanoTime();
//            System.out.println("(t2-t1) = " + (t2-t1));
//            System.out.println("ints = " + Arrays.toString(ints));
    }

    static void quickSort3(int[] nums){
      int[] ints=Arrays.copyOf(nums,nums.length);
      long t1=System.nanoTime();
      Arrays.sort(ints);
      long t2=System.nanoTime();
    }

    static void quickSortRecursively(int[] array,int left,int right){
      int pivotKey;
      if(left<right){
        pivotKey=partitionByPivotValue(array,left,right);
        quickSortRecursively(array,left,pivotKey-1);
        quickSortRecursively(array,pivotKey+1,right);
      }
    }

    static void quickSortNonRecursively(int[] nums,int start,int end){

    }

    static int partitionByPivotValue(int[] array,int left,int right){
      int pivotValue=array[left];
      while(left<right){
        while(left<right&&array[right]>=pivotValue){
          --right;
        }
        array[left]=array[right];
        while(left<right&&array[left]<=pivotValue){
          ++left;
        }
        array[right]=array[left];
      }
      array[left]=pivotValue;
      return left;
    }

  }

  private static class BubbleSort{
    static int[] bubbleSort1(int[] nums){
      int[] ints=Arrays.copyOf(nums,nums.length);
      long t1=System.nanoTime();
      int temp;
      //算法实现
      for(int i=ints.length-1;i>0;i--) {
        for(int j=0;j<i;j++) {
          if(ints[j]>ints[j+1]){
            swap(ints,j,j+1);
          }
        }
      }
      long t2=System.nanoTime();
      System.out.println("(t2-t1) = "+(t2-t1));
      System.out.println("ints = "+Arrays.toString(ints));
      return ints;
    }

    /**
     * 鸡尾酒排序- 冒泡排序的优化
     * 该算法分为两部分，
     * 第一部分，从左到右进行最大值冒泡，之后减少右边界值
     * 第二部分，从右到左进行最小值冒泡，之后增加左边界值
     * 之后再循环操作，直到right < left 为止         *
     *
     * @param nums 数组
     */
    static void cocktailSort(int[] nums){
      int left=0, right=nums.length-1;
      long t1=System.nanoTime();

      while(left<right){
        for(int i=left;i<right;i++) {
          if(nums[i]>nums[i+1]){
            swap(nums,i,i+1);
          }
        }
        right--;

        for(int i=right;i>left;i--) {
          if(nums[i]<nums[i-1]){
            swap(nums,i,i-1);
          }
        }
        left++;
      }

      long t2=System.nanoTime();
      System.out.println("(t2-t1) = "+(t2-t1));
      System.out.println("Arrays.toString(nums) = "+Arrays.toString(nums));
    }

    static void swap(int[] nums,int i,int j){
      int temp=nums[i];
      nums[i]=nums[j];
      nums[j]=temp;
    }
  }

  private static class BinarySearch{

    static int binarySearch1(int[] nums,int key){
      int[] ints=BubbleSort.bubbleSort1(nums);
      int position=-1;
      if(ints==null||ints.length<=0){
        return position;
      }
      int start=0, end=ints.length-1, center=ints.length>>1;
      while(end>=start){
        if(ints[center]<key){
          start=center+1;
          center=(start+end)>>1;
          continue;
        }
        if(ints[center]>key){
          end=center-1;
          center=(start+end)>>1;
          continue;
        }

        if(ints[center]==key){
          position=center;
          break;
        }
      }
      return position;
    }
  }

  private static class Fibonacci{
    private static int[] result;

    static void fibonacci(int n){
      int[] nums=new int[n];
      nums[0]=nums[1]=1;

      for(int i=2;i<n;i++) {
        nums[i]=nums[i-1]+nums[i-2];
      }

      String string=Arrays.toString(nums);
      System.out.println("Arrays = "+string);
    }

    static void setLength(int length){
      result=new int[length];
    }

    static void recursiveFibonacci(int n,int length){
      if(n==0){
        result[0]=result[1]=1;
        recursiveFibonacci(2,length);
      }else if(n<length&&n>0){
        result[n]=result[n-1]+result[n-2];
        recursiveFibonacci(n+1,length);
      }else if(n>0){
        String string=Arrays.toString(result);
        System.out.println("Arrays = "+string);
      }
    }
  }

  private static class SelectSort{
    static void selectSortRecursively(int[] source,int length){
      int max=source[0], index=0;
      for(int i=0;i<length;i++) {
        if(max<source[i]){
          max=source[i];
          index=i;
        }

      }
      if(index!=length-1){
        int temp=source[index];
        source[index]=source[length-1];
        source[length-1]=temp;
      }
      if(length>2)
        selectSortRecursively(source,length-1);
    }

    static void selectSortNonRecursively(int[] source){
      int length=source.length, index, min;
      for(int i=0;i<length-1;i++) {
        min=source[i];
        index=i;
        for(int j=i+1;j<length;j++) {
          if(source[j]<min){
            min=source[j];
            index=j;
          }
        }
        int temp=source[index];
        source[index]=source[i];
        source[i]=temp;
      }
    }
  }

  private static class InsertionSort{
    static void straightInsertionSort(int[] nums){
      int temp=0;
      for(int i=1;i<nums.length;i++) {
        for(int j=0;j<i;j++) {
          if(nums[j]>nums[i]){
            temp=nums[i];
            System.arraycopy(nums,j,nums,j+1,i-j);
            nums[j]=temp;
          }
        }
      }
      System.out.println("Arrays.toString(integers) = "+Arrays.toString(nums));
    }

    /**
     * shell 插入排序就是根据相应的gap值，将数组进行分组，可以分组为
     * 0， 0 + gap， 0+ gap *2, 0 + gap *3..
     * 1， 1 + gap， 1+ gap *2, 1 + gap *3..
     * ...
     * gap - 1， gap - 1 + gap， gap - 1+ gap *2, gap - 1 + gap *3..
     * 之后，每个分组再进行插入排序
     * <p>
     * 之后将gap在缩小， 再次进行操作，最后gap 为1 时，也就是直接执行最后的插入排序操作
     * <p>
     * 这样理论上，减少了排序的复杂度，减少了插入次数
     * 该排序
     * 时间复杂度为O(NleN)
     * 空间负载度为O(n)
     *
     * @param nums 相应的数组
     */
    static void shellInsertionSort(int[] nums){
      int gap=0;
      while(gap<nums.length/3)
        gap=gap*3+1;
      do{
        try{
          shellInsertionSortInternal(nums,gap);
        }catch(Exception e){
          e.printStackTrace();
        }
      } while((gap=gap/3)>0);
      System.out.println("Arrays.toString(integers) = "+Arrays.toString(nums));
    }


    private static void shellInsertionSortInternal(int[] nums,int gap) throws Exception{
      int length=nums.length, temp;
      for(int i=0;i<gap;i++) { //shell core 核心算法
        for(int j=i+gap;j<length;j+=gap) { //插入排序
          for(int k=i;k+gap<=j;k+=gap) {
            if(nums[j]<nums[k]){
              temp=nums[j];
              for(int l=j;l-gap>=k;l-=gap) {

                nums[l]=nums[l-gap];
              }
              nums[k]=temp;
            }
          }
        }
      }
    }

    static void shellSort(int[] arr){
      int gap=1, i, j, len=arr.length;
      int temp;
      while(gap<len/3) //calculate the best gap
        gap=gap*3+1; // <O(n^(3/2)) by Knuth,1973>: 1, 4, 13, 40, 121, ...
      for(;gap>0;gap/=3)
        for(i=gap;i<len;i++) {
          temp=arr[i];
          for(j=i-gap;j>=0&&arr[j]>temp;j-=gap) {
            arr[j+gap]=arr[j];
          }
          arr[j+gap]=temp;
        }
      System.out.println("Arrays.toString(integers) = "+Arrays.toString(nums));
    }
  }

  private static class MergeSort {
    static void sort(int[] nums) {

    }
  }
}
