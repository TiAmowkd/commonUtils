package com.wkd.project.common.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wkd
 * @version 1.0.0
 * @className AesUtil.java
 * @description 文件相关操作工具类
 * @createTime 2021-06-07 10:44
 */
public class FileUtils {

    /**
     * 判断文件是否存在，如果 file 为 null，则返回 false
     *
     * @param file 文件
     * @return 如果存在返回 true
     */
    public static boolean exist(File file) {
        return (null != file) && file.exists();
    }

    /**
     * 判断文件是否不存在，如果 file 为 null，则返回 true
     *
     * @param file 文件
     * @return 如果不存在返回 true
     */
    public static boolean notExist(File file) {
        return !exist(file);
    }

    /**
     * 创建文件及其父目录，如果这个文件存在，直接返回这个文件
     *
     * @param fullFilePath 文件的全路径
     * @return 文件
     * @throws IOException
     */
    public static File touch(String fullFilePath) throws IOException {
        return touch(new File(fullFilePath));
    }

    /**
     * 创建文件及其父目录，如果这个文件存在，直接返回这个文件
     *
     * @param file 文件对象
     * @return 文件，若路径为 null，返回 null
     * @throws IOException IO异常
     */
    public static File touch(File file) throws IOException {
        if (null == file) {
            return null;
        }
        if (notExist(file)) {
            mkParentDirs(file);
            file.createNewFile();
        }
        return file;
    }

    /**
     * 创建所给文件或目录的父目录
     *
     * @param file 文件或目录
     * @return 父目录
     */
    public static File mkParentDirs(File file) {
        final File parentFile = file.getParentFile();
        if (notExist(parentFile)) {
            parentFile.mkdirs();
        }
        return parentFile;
    }

    /**
     * 获取文件内容
     * @param fileName
     * @return
     */
    public static List<String> getFile(String fileName) {
        List<String> list = new ArrayList<>();
        File file = new File(fileName);
        if (exist(file)) {
            try (InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file));
                 BufferedReader bufferedReader = new BufferedReader(inputStreamReader);) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    //包含该行内容的字符串，不包含任何行终止符，如果已到达流末尾，则返回 null
                    list.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    /**
     * 复制文件
     *
     * @param originalUrl
     * @param targetUrl
     */
    public static void copyFile(String originalUrl, String targetUrl) {
        // 初始化输入/输出流对象
        try (FileWriter fileWriter = new FileWriter(targetUrl);) {
            File file = new File(originalUrl);
            if (exist(file)) {
                try (InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file));
                     BufferedReader bufferedReader = new BufferedReader(inputStreamReader);) {
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        //包含该行内容的字符串，不包含任何行终止符，如果已到达流末尾，则返回 null
                        fileWriter.append(line).append("\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
