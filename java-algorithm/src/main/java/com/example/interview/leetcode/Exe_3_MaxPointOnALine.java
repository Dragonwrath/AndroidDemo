package com.example.interview.leetcode;


import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

/**
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 */
public class Exe_3_MaxPointOnALine{

  private static class BestSolution{
    public int maxPoints(Point[] points){
      if(points.length<=2) return points.length;
      int max=0;
      int aDx, aDy;
      int bDx, bDy;
      for(int i=0;i<points.length;i++) {
        int samePoint=0, temp=1;
        for(int j=i+1;j<points.length;j++) {
          aDx=points[i].x-points[j].x;
          aDy=points[i].y-points[j].y;
          if(aDx==0&&aDy==0){
            samePoint++;
          }else{
            temp++;
            for(int k=j+1;k<points.length;k++) {
              bDx=points[k].x-points[i].x;
              bDy=points[k].y-points[i].y;
              if(aDx*bDy==aDy*bDx){
                temp++;
              }
            }
          }
          if(max<(samePoint+temp)){
            max=samePoint+temp;
          }
          temp=1;
        }
      }
      return max;
    }
  }
  private static class MyStupidSolution{
    class Slope implements Comparable<Slope>{
      int x;
      int y;

      private Slope(){
      }

      private Slope(int x,int y){
        this.x=x;
        this.y=y;
      }

      private void setX(int x){
        this.x=x;
      }

      private void setY(int y){
        this.y=y;
      }

      @Override
      public boolean equals(Object obj){
        if(obj==null) return false;
        if(obj instanceof Slope){
          Slope another=(Slope)obj;
          return x==another.x&&y==another.y;
        }else return false;
      }

      @Override
      public int hashCode(){
        int result=31*17+x;
        return result*31+y;
      }

      @Override
      public int compareTo(Slope o){
        if(o==null){
          return 1;
        }
        if(o.x==x){
          return y-o.y;
        }
        if(o.y==y){
          return x-o.x;
        }
        if(o.x!=0&&o.y!=0&&x!=0 && y!=0){
          if(o.x*y==o.y*x){
            return 0;
          }
        }
        return y - o.y > 0? 1:-1;
      }
    }

    public int maxPoints(Point[] points){
      if(points.length<=2){
        return points.length;
      }
      int max=1;
      ArrayList<Point> list=new ArrayList<>();
      Collections.addAll(list,points);
      TreeMap<Slope,ArrayList<Point>> tree=new TreeMap<>();
      for(int i=0, size=list.size();i<size;) {
        final Point original=list.get(i);
        final TreeMap<Slope,ArrayList<Point>> subTree=new TreeMap<>();
        final ArrayList<Point> cachePoint=new ArrayList<>();
        for(int j=i+1;j<size;j++) {
          Point another=list.get(j);
          if(original.x==another.x&&original.y==another.y){
            cachePoint.add(another);
            continue;
          }
          Slope slope=checkSlope(subTree,original,another);

          if(tree.get(slope)!=null){
            continue;
          }
          ArrayList<Point> pointList=subTree.get(slope);
          if(pointList==null){
            pointList=new ArrayList<>();
            pointList.add(another);
            subTree.put(slope,pointList);
          }else{
            pointList.add(another);
          }
        }
        for(ArrayList<Point> arrayList : subTree.values()) {
          arrayList.add(original);
        }
        for(Point point : cachePoint) {
          for(ArrayList<Point> pointList : subTree.values()) {
            pointList.add(point);
          }
        }
        for(ArrayList<Point> arrayList : subTree.values()) {
          max = Math.max(arrayList.size(),max);
        }
        tree.putAll(subTree);

        if(cachePoint.size()>0&&tree.size()==0){ //only cache
          return cachePoint.size()+1;
        }
        list.removeAll(cachePoint);
        size=list.size();
        i++;
      }
      return max;
    }

    private Slope checkSlope(TreeMap<Slope,ArrayList<Point>> tree,Point original,Point another){
      Slope slope=new Slope();
      if(original.x==another.x){ //垂直x的直线
        slope.setX(original.x);
        slope.setY(1);
        return slope;
      }
      if(original.y==another.y){ //垂直y的直线
        slope.setY(original.y);
        slope.setX(1);
        return slope;
      }
      //斜线
      int diffX=another.x-original.x;
      int diffY=another.y-original.y;
      for(Slope key : tree.keySet()) {
        if(key.x!=0&&key.y!=0){
          if(key.x*diffY==key.y*diffX){
            return key;
          }
        }
      }
      slope.setX(diffX);
      slope.setY(diffY);
      return slope;
    }
  }

  private static class Point{
    int x;
    int y;

    Point(){
      x=0;
      y=0;
    }

    Point(int a,int b){
      x=a;
      y=b;
    }
  }

  public static void main(String[] args){
    MyStupidSolution myLowSolution=new MyStupidSolution();
//    Point[] points={new Point(0,0),new Point(1,1),new Point(0,0)};
//    Point[] points={new Point(1,1),new Point(1,1),new Point(1,1)};
//    Point[] points = {new Point(84,250),new Point(0,0),new Point(1,0),
//        new Point(0,-70),new Point(0,-70),new Point(1,-1),
//        new Point(21,10),new Point(42,90),new Point(-42,-230)};

    Point[] points = {
        new Point(0,-12),new Point(5,2),new Point(2,5),new Point(0,-5),
        new Point(1,5),new Point(2,-2),new Point(5,-4),new Point(3,4),
        new Point(-2,4),new Point(-1,4),new Point(0,-5),new Point(0,-8),
        new Point(-2,-1),new Point(0,-11),new Point(0,-9)};
//    Point[] points={
//        new Point(0,9),new Point(138,429),new Point(115,359),new Point(115,359),
//        new Point(-30,-102),new Point(230,709),new Point(-150,-686),
//        new Point(-135,-613),new Point(-60,-248),new Point(-161,-481),
//        new Point(207,639),new Point(23,79),new Point(-230,-691),new Point(-115,-341),
//        new Point(92,289),new Point(60,336),new Point(-105,-467),
//        new Point(135,701),new Point(-90,-394),new Point(-184,-551),
//        new Point(150,774)};
    int max=myLowSolution.maxPoints(points);
    System.out.println(max);
  }

}
