package com.example.interview;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 输入n，之后输出1到n位数的最大值，例如输入3，则输出1到999
 */
public class NumberPrint{
  public static void main(String[] args){
    Print stupid=new RecursivePrint();
    stupid.print(2);
  }

  interface Print{
    void print(int n);
  }

  private static class Stupid implements Print{
    @Override
    public void print(int n){
      StringBuilder builder;
      for(int i=1;i<=n;i++) {
        int[] cache=new int[i];
        if(i>=2){
          cache[i-1]=1;
        }
        out:
        while(true){
          reservePrint(i,cache);
          for(int j=0;;) { //始终从最低位加起
            cache[j]+=1;
            if(cache[j]>=10){
              for(int k=0;;) {
                cache[k]=0;
                if(k+1<i){
                  cache[k+1]=cache[k+1]+1;
                  if(cache[k+1]>=10){
                    k++;
                  }else{
                    continue out;
                  }
                }else{
                  break;
                }
              }
              if(cache[i-1]==0){
                break out;
              }
            }else{
              reservePrint(i,cache);
            }
          }
        }
      }
    }

    private static void reservePrint(int i,int[] cache){
      StringBuilder builder;
      builder=new StringBuilder();
      for(int k=i-1;k>=0;k--) {
        builder.append(String.valueOf(cache[k]));
      }
      System.out.println(builder.toString());
    }
  }


  private static class RecursivePrint implements Print{

      public static void main(String[] args) {
        Node node = new Node(-1, null);
        print(5, node);
      }

    @Override
    public void print(int n){
      Node node = new Node(-1, null);
      print(5, node);
    }

    private static void print(int n, Node node) {
      final int decrease = n -1;
      for (int i = 0; i < 10; i++) {
        if (n == 0) {
          ArrayList<Integer> list = new ArrayList<>();
          list.add(i);
          list.add(0,node.value);
          Node head = node.prev;
          while (head != null) {
            if (head.value != -1)
              list.add(0, head.value);
            head = head.prev;
          }
          System.out.println(Arrays.toString(list.toArray()));
        } else {
          print(decrease, new Node(i, node));
        }
      }
    }



    private static class Node {
        private final int value;
        private final Node prev;

        private Node(int value, Node prev) {
          this.value = value;
          this.prev = prev;
        }
      }
    }

}
