package com.example.outer;

import java.util.BitSet;
import java.util.Hashtable;
import java.util.Stack;
import java.util.Vector;

abstract class Glyph {
    abstract void draw();
    Glyph() {
        System.out.println("Glyph() before draw()");
        draw();
        System.out.println("Glyph() after draw()");
    }
    Glyph(int r){}
}
class RoundGlyph extends Glyph {
    int radius = 1;
    RoundGlyph(int r) {
        radius = r;
        System.out.println(
                "RoundGlyph.RoundGlyph(), radius = "
                        + radius);
    }
    void draw() {
        System.out.println(
                "RoundGlyph.draw(), radius = " + radius);
    }
}
public class PolyConstructors {
    public static void main(String[] args) {
        new RoundGlyph(5);

        BitSet bitSet = new BitSet();
        Vector<Object> objects = new Vector<>();
        Hashtable<Object, Object> objectObjectHashtable = new Hashtable<>();
        Stack stack = new Stack();
    }
}