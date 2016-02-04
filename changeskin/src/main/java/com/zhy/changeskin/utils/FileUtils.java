package com.zhy.changeskin.utils;

import android.content.Context;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Author: Davin
 * Date: 2016/2/2
 * Description:<p>{TODO: 用一句话描述}
 */
public class FileUtils {
    /**
     * 解压到指定路径
     * 参数一为源zip文件的完整路径，参数二为解压缩后存放的文件夹
     *
     * @param zipFile
     * @param targetDir
     */
    public static void Unzip(String zipFile, String targetDir) {
        int BUFFER = 4096; //这里缓冲区我们使用4KB，
        String strEntry; //保存每个zip的条目名称

        try {
            BufferedOutputStream dest = null; //缓冲输出流
            FileInputStream fis = new FileInputStream(zipFile);
            ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
            ZipEntry entry; //每个zip条目的实例

            while ((entry = zis.getNextEntry()) != null) {

                try {
                    Log.i("Unzip: ", "=" + entry);
                    int count;
                    byte data[] = new byte[BUFFER];
                    strEntry = entry.getName();

                    File entryFile = new File(targetDir + strEntry);
                    File entryDir = new File(entryFile.getParent());
                    if (!entryDir.exists()) {
                        entryDir.mkdirs();
                    }

                    FileOutputStream fos = new FileOutputStream(entryFile);
                    dest = new BufferedOutputStream(fos, BUFFER);
                    while ((count = zis.read(data, 0, BUFFER)) != -1) {
                        dest.write(data, 0, count);
                    }
                    dest.flush();
                    dest.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            zis.close();
        } catch (Exception cwj) {
            cwj.printStackTrace();
        }
    }


    /**
     * 解压只有一个文件组成的zip到当前目录，并且给解压出的文件重命名
     *
     * @param zipPath
     * @param name
     * @throws IOException
     */
    public static void unzipSingleFileHereWithFileName(String zipPath, String name) throws IOException {
        File zipFile = new File(zipPath);
        File unzipFile = new File(zipFile.getParent() + "/" + name);
        ZipInputStream zipInStream = null;
        FileOutputStream unzipOutStream = null;
        try {
            zipInStream = new ZipInputStream(new FileInputStream(zipFile));
            ZipEntry zipEntry = zipInStream.getNextEntry();
            if (!zipEntry.isDirectory()) {
                unzipOutStream = new FileOutputStream(unzipFile);
                byte[] buf = new byte[4096];
                int len = -1;
                while ((len = zipInStream.read(buf)) != -1) {
                    unzipOutStream.write(buf, 0, len);
                }
            }
        } finally {
            if (unzipOutStream != null) {
                unzipOutStream.close();
            }
            if (zipInStream != null) {
                zipInStream.close();
            }
        }
    }


    /**
     *      * 从assets中复制apk到sd中
     *      *（为了方便调试，调试阶段可以直接把插件放在assets中，上线取消这功能。备注：待改）
     *      * @param context
     *      * @param filename
     *      * @param path
     *      * @return
     *      
     */
    public static boolean copyApkFromAssets(Context context, String filename, String path) {
        boolean copyIsFinish = false;
        try {
            // 打开assets的输入流
            InputStream is = context.getAssets().open(filename);
            File file = new File(path);
            // 创建一个新的文件
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            byte[] temp = new byte[1024];
            int i = 0;
            while ((i = is.read(temp)) > 0) {
                fos.write(temp, 0, i); // 写入到文件
            }
            fos.close();
            is.close();
            copyIsFinish = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return copyIsFinish;
    }


    /**
     * 删除文件或文件夹
     * @param file
     */
    public static void deleteFile(File file) {
        if (file.exists()) { // 判断文件是否存在
            if (file.isFile()) { // 判断是否是文件
                file.delete(); // delete()方法 你应该知道 是删除的意思;
            }
            else if (file.isDirectory()) { // 否则如果它是一个目录
                File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
                for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
                    deleteFile(files[i]); // 把每个文件 用这个方法进行迭代
                }
            }
            file.delete();
        }
    }
}
