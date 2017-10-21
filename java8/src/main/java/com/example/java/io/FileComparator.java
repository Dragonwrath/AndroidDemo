package com.example.java.io;


import java.io.File;
import java.util.Comparator;

class FileComparator implements Comparator<File>{
	@Override
	public int compare(File o1,File o2) {
		int i = o1.isDirectory() ? 1: 0 ;
		int j = o2.isDirectory() ? 1: 0 ;
		if(i != j) {
			return j - i;
		} else {
			return o1.getName().compareTo(o2.getName());
		}
	}
}
