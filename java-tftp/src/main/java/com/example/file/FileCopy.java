package com.example.file;


import java.io.File;
import java.io.IOException;

public class FileCopy {

    @SuppressWarnings("ConstantConditions")
    public static void main(String[] args) throws IOException {


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
        for (File file : files) {
            if (file.getName().contains(".mp4"))
                renameFile(file);
        }
    }

    private static void renameFile(File file) throws IOException {
        String name = file.getName();
        String fileDstName = "";
        final String[] split = name.split("\\.");
        for (int i = 0; i < split.length; i++) {
            if (split[i].contains("S06E")) {
                fileDstName = split[i];
                break;
            }
        }
        String path = file.getAbsolutePath();
        String[] paths = path.split("\\\\");
        File dstFile = new File(paths[0] + File.separator + paths[1] + File.separator + paths[2] + File.separator +fileDstName + ".mp4");
        file.renameTo(dstFile);
    }
}
