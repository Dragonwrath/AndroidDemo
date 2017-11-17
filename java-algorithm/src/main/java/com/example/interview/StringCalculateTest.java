package com.example.interview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * Valid operators are+,-,*,/. Each operand may be an integer or another expression.
 * Some examples:
 * ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 * ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 *
 */
class StringCalculateTest {
  public static void main(String[] args) {
    Solution solution = new Solution();
    String[] strings ={"3","11","+","5","-"};
    int translate = solution.evalRPN(strings);
    System.out.println("translate = " + translate);
  }
}

class Solution {
   int evalRPN(String[] tokens) {
    ArrayList<String> strings = new ArrayList<>();
    Collections.addAll(strings, tokens);
    return getIndex(strings);
  }

  private int getIndex(List<String> list) {
    for (int i = 0; i < list.size(); ) {
      boolean b = indexOf(list.get(i));
      if (b){
        if (i - 2 >= 0) {
          int result = translateInternal(Integer.parseInt(list.get(i - 2)), Integer.parseInt(list.get(i - 1)), list.get(i));
          list.remove(i);
          list.remove(i - 1);
          list.remove(i - 2);
          list.add(i - 2,Integer.toString(result));
        } else if (i < list.size()) {
          int result = translateInternal(Integer.parseInt(list.get(i - 1)), Integer.parseInt(list.get(i + 1)), list.get(i));
          list.remove(i + 1);
          list.remove(i);
          list.remove(i - 1);
          list.add(Integer.toString(result));
        }
        i = 0;
      }
      i++;
    }
    return Integer.parseInt(list.get(0));
  }

  private boolean indexOf(String sign) {
    switch (sign) {
      case "+":
      case "-":
      case "*":
      case "/":
        return true;
    }
    return false;
  }

  private int translateInternal(int pre, int next, String sign) {
    switch (sign) {
      case "+":
        return pre + next;
      case "-":
        return pre - next;
      case "*":
        return pre * next;
      case "/":
        return pre / next;
    }
    return -1;
  }
}
