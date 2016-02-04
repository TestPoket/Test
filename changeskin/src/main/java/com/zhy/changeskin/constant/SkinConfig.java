package com.zhy.changeskin.constant;

import android.os.Environment;

import java.io.File;

/**
 * Created by zhy on 15/9/22.
 */
public class SkinConfig
{
    public static final String PREF_NAME = "skin_plugin_pref";
    public static final String KEY_PLUGIN_PATH = "key_plugin_path";
    public static final String KEY_PLUGIN_PKG = "key_plugin_pkg";
    public static final String KEY_PLUGIN_SUFFIX = "key_plugin_suffix";

    public static final String SKIN_PREFIX ="skin:";

    public static final String PLUGIN_NAME ="plugin-debug.apk";

    public static final String PLUGIN_PATH = Environment.getExternalStorageDirectory()
            + File.separator + "skin"+"/"+ SkinConfig.PLUGIN_NAME;

    public static final String PLUGIN_NAME_ZIP ="plugin-debug.zip";

    public static final String PLUGIN_PATH_ZIP = Environment.getExternalStorageDirectory()
            + File.separator + "skin"+"/"+ SkinConfig.PLUGIN_NAME_ZIP;



}
