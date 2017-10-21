package com.example.java.io;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.example.java.io.FileTest.Year._2015;
import static com.example.java.io.FileTest.Year._2016;
import static com.example.java.io.FileTest.Year._2017;


/**
 * a test class to help find and collect all photos of my baby
 *
 */
@SuppressWarnings("ALL")
public class FileTest{

	enum Year {
		_2015("2015"),
		_2016("2016"),
		_2017("2017");

		private final String year;

		Year(String s) {
			this.year = s;
		}

		public String getYear() {
			return year;
		}
	}

	enum Month {
		_01("00"),
		_02("01"),
		_03("02"),
		_04("03"),
		_05("04"),
		_06("05"),
		_07("06"),
		_08("07"),
		_09("08"),
		_10("09"),
		_11("10"),
		_12("11");

		private final String month;

		Month(String s) {
			month = s;
		}

		public String getMonth() {
			return month;
		}
	}


	public static void main(String[] args) throws Exception{
		final String pathname = "D:\\";
		File file = new File(pathname);
//		step_3(file);
		sortFile(file);
	}

	private static void sortFile(File parent) {
		final List<File> list = Arrays.asList(parent.listFiles());
		list.sort(new FileComparator());
		for(File file : list) {
			System.out.println("directory : " + file.isDirectory() + " file = " + file.getName());
		}
	}

	//print directory
	private static void step_1(File parent, String prefix) {
		if(parent.isDirectory()) {
			final File[] files = parent.listFiles();
			if(files != null && files.length > 0) {
				for(File file : files) {
					step_1(file,"   " + prefix);
				}
			} else {
				System.out.println(prefix + parent.getName());
			}
		} else {
			System.out.println(prefix + parent.getName());
		}
	}

	//sort directory by file's name
	private static void step_2(File  file) {
		Map<String, LinkedList<File>> map = new HashMap<>();
		for(Year year : Year.values()) {
			map.put(year.getYear(), new LinkedList<>());
		}
		final ArrayList<File> doubtList = new ArrayList<>();
		sortInternal(file, map, doubtList);
		for(String key : map.keySet()) {
			System.out.println(key+ "  size() = " + map.get(key).size());
		}
		for(String key : map.keySet()) {
			final LinkedList<File> list = map.get(key);

		}
		System.out.println("doubtList = " + doubtList.size());
	}

	private static void sortInternal(File file,Map<String, LinkedList<File>> map,ArrayList<File> doubtList) {
		if(file.isDirectory()) {
			final File[] files = file.listFiles();
			if(files != null && files.length >0) {
				for(File child : files) {
					sortInternal(child, map,doubtList);
				}
			}
		}
		else {
			for(Year y : Year.values()) {
				if(file.getName().contains(y.getYear())) {
					map.get(y.getYear()).push(file);
					return;
				}
			}
			doubtList.add(file);
		}
	}

	private static void step_3(File parent) {
		if(parent.isDirectory()) {
			HashMap<String, ArrayList<File>> map = new HashMap<>();
			final ArrayList<File> list = new ArrayList<>();
			sortInternalV3(parent, map, list);
			if(map.size() > 0) {
				for(String s : map.keySet()) {
					final ArrayList<File> files = map.get(s);
					System.out.println("key = " + s + " directory =  " + files.size());
//
//					if(files.size() > 0) {
//						for(File file : files) {
//							System.out.println("key = " + s + " directory =  " + file.getName());
//						}
//					}
				}
			}
			if(list.size() > 0) {
				for(File file : list) {
					System.out.println("doubt list directory =  " + file.getName());

				}
			}
		} else {
			throw new IllegalArgumentException("Target file is not directory,and the path of file is:" +
					parent.getAbsolutePath());
		}

	}

	private static void sortInternalV3(File parent,HashMap<String, ArrayList<File>> map, ArrayList<File> doubtList) {
		if(parent.isDirectory()) {
			final File[] files = parent.listFiles();
			if(files != null && files.length > 0){
				for(File file : files) {
					sortInternalV3(file, map, doubtList);
				}
			}
		} else {
			String name = parent.getName();
			String yearPre = null, monPre = null;
			if(name.contains(_2015.getYear())) { //parse year of file's name
				yearPre = _2015.getYear();
			} else if(name.contains(_2016.getYear())) {
				yearPre = _2016.getYear();
			} else if(name.contains(_2017.getYear())) {
				yearPre = _2017.getYear();
			}
			if(!FileTest.isEmpty(yearPre)) {
				monPre = parseFileName(yearPre, parent.getName());
				if(!FileTest.isEmpty(monPre)) {
					String key = yearPre + monPre;
					ArrayList<File> list = map.get(key);
					if(list == null) {
						list = new ArrayList<>();
					}
					synchronized(list) {
						if(list.indexOf(parent) < 0) {
							list.add(parent);
							map.put(key, list);
							return;
						}
					}
				}
			}
			synchronized(doubtList) {
				if(doubtList.indexOf(parent) < 0) {
					doubtList.add(parent);
				}
			}
		}
	}

	private static String parseFileName(String year, String  fileName) {
		final int i = fileName.indexOf(year);
		int startIndex = i + 4, endIndex = i + 6;
		if(startIndex < fileName.length() && endIndex < fileName.length()) {
			String month = fileName.subSequence(startIndex, endIndex).toString();
			switch(month) {
				case "00" : return month;
				case "01" : return month;
				case "02" : return month;
				case "03" : return month;
				case "04" : return month;
				case "05" : return month;
				case "06" : return month;
				case "07" : return month;
				case "08" : return month;
				case "09" : return month;
				case "10" : return month;
				case "11" : return month;
			}
		}
		return null;
	}

	private static void sortInternalByMonth(File file, Map<Month,ArrayList<File>> map,
																					ArrayList<File> doubtList){

	}
	private static void generateFile(File dstFile) throws IOException {
		File newFile;
		for(int i = 0;i < 3;i++) {
			for(int j = 0;j < 13;j++) {
				switch(i) {
					case 0 :
						newFile = new File(dstFile.getAbsolutePath() + File.separator + "2015"+ (j > 10 ? String.valueOf(j) : "0" + j) +"93010");
						newFile.createNewFile();
						break;
					case 1:
						newFile = new File(dstFile.getAbsolutePath() + File.separator + "2016"+ (j > 10 ? String.valueOf(j) : "0" + j) +"93010");
						newFile.createNewFile();
						break;
					case 2:
						newFile = new File(dstFile.getAbsolutePath() + File.separator + "2017"+ (j > 10 ? String.valueOf(j) : "0" + j) +"93010");
						newFile.createNewFile();
						break;
				}
			}
		}
	}

	private static void listFile(File file,File dstFile) {
		final TreeNode parent = new TreeNode(dstFile,"Baby");
		final File[] listFiles = parent.getFile().listFiles();
		for(File listFile : listFiles) {
			final String name = listFile.getName();
			if(name.contains(_2015.getYear())) {
				parent.addPicture(listFile,_2015.getYear());
			} else if(name.contains(_2016.getYear())) {
				parent.addPicture(listFile,_2016.getYear());
			} else if(name.contains(_2017.getYear())) {
				parent.addPicture(listFile,_2017.getYear());
			}
		}
		parent.listNode("");
	}

	public static boolean isEmpty(String s) {
		return s == null || s.equals("");
	}
}

@SuppressWarnings("ALL")
class TreeNode {
	private String name;
	private File file;
	private List<TreeNode> childs;

	public TreeNode(File file, String name) {
		this.file = file;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public File getFile() {
		return file;
	}

	public List<TreeNode> getChilds() {
		return childs;
	}

	public void addPicture(File child,String  tags) {
		synchronized(this) {
			if(childs == null) {
				childs = new ArrayQueue<>(1000);
			}
		}
		int i = -1;
		for(int j = 0;j < childs.size();j++) {
			if(childs.get(j).getName().equals(tags )) {
				i = j;
			}
		}
		if(i == -1 && tags.length() > 3) {
			childs.add(new TreeNode(new File(child.getParent()),tags));
		}
		for(TreeNode treeNode : childs) { //year
			final File file = treeNode.getFile();
			final String name = file.getName();
			if(name.contains(tags ) && tags .length() > 3) {
				final String sux = name.split(tags )[1].substring(0,2);
				treeNode.addPicture(child, sux);
			} else if(FileTest.isEmpty(tags)) {

			} else { //month
				childs.add(new TreeNode(child,tags ));
			}
		}
	}

	public TreeNode getChild(String name) {
		if(childs == null) {
			return null;
		}
		for(TreeNode child : childs) {
			if(child.getFile().getName().contains(name)) {
				return child;
			}
		}
		return null;
	}

	public void listNode(String pre) {
		if(childs != null) {
			for(TreeNode child : childs) {
				child.listNode(pre +"---" + child.getName()+"--");
			}
		} else {
			System.out.println(pre + file.getName());
		}
	}
}

