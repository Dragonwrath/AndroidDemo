package com.joke.httpsdemo.config;

import android.os.Environment;

import java.io.File;

/**
 * Created by AdamStone on 2016/6/17.
 */
public class Constants {

    public static final int CODE_SUCCESS = 1;
    public static final int CODE_FAILURE = 0;

    public static final int MODE_TEXT = 0;
    public static final int MODE_VOICE = 1;
    public static final int MODE_IMAGE = 2;
    public static final int MODE_GPS = 3;

    public static final int CHAT_TYPE_GROUP = 1;
    public static final int CHAT_TYPE_PERSON = 0;

    public static final String SSID_FIXED_PREFIX = "wl.";

    public static final String ZERO_AP_ID = "0";
    public static final String ADMIN_GROUP_ID = "0-1";
    public static final String WIDE_AREA_GROUP_ID = "0-2";
    public static final String LOCAL_GROUP_ID = "0-0";

    public static class ConfigName {

        public static final String HAS_LOGGED_IN = "config_has_logged_in";

        public static final String ACCOUNT = "config_login";
        public static final String PWD = "config_pwd";

        public static final String HOST_IP = "config_host_ip";
        public static final String AP_ID = "config_ap_id";
//        public static final String USER_ID = "config_user_id";

        public static final String ROLE = "config_role";

        public static final String GUID = "config_globe_user_id";
        public static final String NICKNAME = "config_user_nickname";
        public static final String AVATAR = "config_user_avatar";

        public static final String HBI = "config_heart_beat_interval";

        public static final String TOKEN = "config_token";

        public static final String IMAGE_DOWNLOADED = "config_image_downloaded";

    }

    public static final String AP_SET_PWD = "20160810";

    public static final int DOWNLOAD_IMAGES_WHAT = 100;
    public static final int DOWNLOAD_FACES_WHAT = 99;
    public static final int DOWNLOAD_KERNEL_WHAT = 101;

    // 默认存放文件路径Parent
    public final static String MICROLINK_WORK_DIR = Environment
            .getExternalStorageDirectory()
            + File.separator
            + "microlink"
            + File.separator;

    // 默认工作目录
    public final static String MICROLINK_WORK_DIR_DOWNLAOD = MICROLINK_WORK_DIR
            + "download"
            + File.separator;

    // 默认存放语音的路径
    public final static String MICROLINK_WORK_DIR_VOICES = MICROLINK_WORK_DIR
            + "voices"
            + File.separator;

    // 默认存放图片的路径
    public final static String MICROLINK_WORK_DIR_IMAGE = MICROLINK_WORK_DIR
            + "images"
            + File.separator;

    // 默认存放表情的路径
    public final static String MICROLINK_WORK_DIR_FACES = MICROLINK_WORK_DIR
            + "faces"
            + File.separator;

    // 默认存放离线地图的路径
    public final static String MICROLINK_WORK_DIR_MAPS = MICROLINK_WORK_DIR
            + "offlinemap"
            + File.separator;


    public final static String PART_FILE_SUFFIX = ".part";
    public final static int PART_FILE_SIZE = 240;

}
