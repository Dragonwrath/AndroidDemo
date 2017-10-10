package com.example.file;


import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Set;

public class FileCopy {

    @SuppressWarnings("ConstantConditions")
    public static void main(String[] args) throws IOException {
        final Properties properties = System.getProperties();
        final Set<Object> keySet = properties.keySet();
        for (Object o : keySet) {
        }

    }

    static class FileThread extends Thread {
        String pre;

        public FileThread(String pre) {
            this.pre = pre;
        }

    }

    private static void copyToDst(File srcFile,File parent) {
        if (srcFile.isDirectory()) {
            final File[] files = srcFile.listFiles();
            assert files != null;
            for (File file : files) {
                copyToDst(file, srcFile);
            }
            return;
        }
        String name = srcFile.getName();
    }

    private static void testRenameFile()  throws IOException {
        String path = "D:\\downloads\\hbo_got_s6";
        final File grandPath = new File(path);
        final File[] files = grandPath.listFiles();
        assert files != null;
        for (File file : files) {
            if (file.getName().contains(".mp4"))
                renameFile(file);
        }
    }

    private static void renameFile(File file) throws IOException {
        String name = file.getName();
        String fileDstName = "";
        final String[] split = name.split("\\.");
        for (String aSplit : split) {
            if (aSplit.contains("S06E")) {
                fileDstName = aSplit;
                break;
            }
        }
        String path = file.getAbsolutePath();
        String[] paths = "\\\\".split(path);
        File dstFile = new File(paths[0] + File.separator + paths[1] + File.separator + paths[2] + File.separator +fileDstName + ".mp4");
        file.renameTo(dstFile);
    }
}
