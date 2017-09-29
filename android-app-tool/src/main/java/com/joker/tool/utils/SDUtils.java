package com.joker.tool.utils;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取SD卡目录，针对于外部目录，我们可以使用此方法，不过推荐使用内部目录
 */
public final class SDUtils {
    private final static int ALARMS = 0;
    private final static int PICTURES = 1;
    private final static int MUSIC = 2;
    private final static int MOVIES = 3;
    private final static int DOWNLOADS = 4;

    private SDUtils() {
        throw new AssertionError("class do not support initialization");
    }

    /**
     *
     * @param type The type of storage directory to return. Should be one of
     *             {@link #ALARMS} , {@link #PICTURES} , {@link #MUSIC} ,
     *             {@link #MOVIES} or {@link #DOWNLOADS}, May be null.
     * @return the relate dirctory
     */
    public static String getSdCardPath(int type) {
        String typeString = null;
        if (hasSdCard()) {
            switch (type) {
                case ALARMS: typeString = Environment.DIRECTORY_ALARMS; break;
                case PICTURES: typeString = Environment.DIRECTORY_PICTURES; break;
                case MUSIC: typeString = Environment.DIRECTORY_MUSIC; break;
                case MOVIES: typeString = Environment.DIRECTORY_MOVIES; break;
                case DOWNLOADS: typeString = Environment.DIRECTORY_DOWNLOADS; break;
            }
            File sdDir = typeString != null ? Environment.getExternalStoragePublicDirectory(typeString) :
                    Environment.getExternalStorageDirectory();
            return sdDir.getAbsolutePath();
        }

        List<String> devMountList = getDevMountList();
        if (devMountList != null && devMountList.size() > 0)
            for (String devMount : devMountList) {
                File file = new File(devMount);
                if (file.isDirectory() && file.canWrite()) {
                    return file.getAbsolutePath();
                }
            }
        return null;
    }

    private static List<String> getDevMountList() {
        final String s = readFile("/etc/vold.fstab");
        if (s != null) {
            String[] toSearch = s.split(" ");
            List<String> out = new ArrayList<>();
            for (int i = 0; i < toSearch.length; i++) {
                if (toSearch[i].contains("dev_mount")) {
                    if (new File(toSearch[i + 2]).exists()) {
                        out.add(toSearch[i + 2]);
                    }
                }
            }
            return out;
        }
        return  null;
    }

    private static String readFile(String filePath) {
        String fileContent = "";
        File file = new File(filePath);
        if (!file.isFile()) {
            return null;
        }
        BufferedReader reader = null;
        try {
            InputStreamReader is = new InputStreamReader(new FileInputStream(file));
            reader = new BufferedReader(is);
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                fileContent += line + " ";
            }
            reader.close();
            return fileContent;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return fileContent;
    }

    private static boolean hasSdCard() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

}
