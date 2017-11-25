package com.example.interview.leetcode.twenty;

import java.util.Arrays;

public class Exe_20_SurroundedRegions{

  public static void main(String[] args){
    Solution solution=new Solution();
    char[][] board = {{'X','O','X','O','X','O'},{'O','X','O','X','O','X'},{'X','O','X','O','X','O'},{'O','X','O','X','O','X'}};
    solution.solve(board);
  }
  static class Solution{
    public void solve(char[][] board){
      if(board==null) return;
      int row=board.length;
      int column = board[0].length;
      if(row<3) return;
      if(column<3) return;
      boolean[][] boxes=new boolean[row][column];
      for(int i=0;i<row;i++){ //判断边界
        for(int j=0;j<column;j++){
          if(board[i][j]=='O'){
            if(i==0||j==0||i==row-1||j==column-1){
              boxes[i][j]=true;
              board[i][j]='*';
              infectBoxes(board,boxes,i,j,row,column);
            }
          }
        }
      }
      for(int i=0;i<row;i++){
        for(int j=0;j<column;j++){
          if(board[i][j]=='O'){
            board[i][j]='X';
          } else if(board[i][j]=='*'){
            board[i][j]='O';
          }
        }
      }
    }

    private void infectBoxes(char[][] board,boolean[][] boxes,int x,int y,int row,int column){
      if(x-1>=0&&board[x-1][y]=='O'){
        boxes[x-1][y]=true;
        board[x-1][y]='*';
        infectBoxes(board,boxes,x-1,y,row,column);
      }
      if(y-1>=0&&board[x][y-1]=='O'){
        boxes[x][y-1]=true;
        board[x][y-1]='*';
        infectBoxes(board,boxes,x,y-1,row,column);
      }
      if(x+1<row&&board[x+1][y]=='O'){
        boxes[x+1][y]=true;
        board[x+1][y]='*';
        infectBoxes(board,boxes,x+1,y,row,column);
      }
      if(y+1<column&&board[x][y+1]=='O'){
        boxes[x][y+1]=true;
        board[x][y+1]='*';
        infectBoxes(board,boxes,x,y+1,row,column);
      }
    }

    private void print(char[][] board){
      for(char[] chars : board){
        for(char c : chars){
          System.out.print(c +" ");
        }
        System.out.println();
      }
      System.out.println();
    }
  }
}
