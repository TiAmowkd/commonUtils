package com.wkd.project.common.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wkd
 * @version 1.0.0
 * @className ListUtils.java
 * @description list复制工具类
 * @createTime 2021-06-07 11:29
 */
public class ListUtils {

    /*public static <T, E> List<T> toList(List<E> source, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        source.stream().forEach(x -> {
            try {
                T o1 = (T) Class.forName(clazz.getName()).newInstance();
                BeanUtils.copyProperties(x, o1);
                list.add(o1);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        return list;
    }*/

    /**
     * list深复制
     *
     * @param src
     * @param <T>
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static <T> List<T> deepCopy(List<T> src) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);

        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        @SuppressWarnings("unchecked")
        List<T> dest = (List<T>) in.readObject();
        return dest;
    }
}
