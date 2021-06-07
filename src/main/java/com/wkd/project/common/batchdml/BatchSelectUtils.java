package com.wkd.project.common.batchdml;

import com.wkd.project.common.constant.Constants;
import com.wkd.project.common.utils.CollectionUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wkd
 * @version 1.0.0
 * @className Constants.java
 * @description 批量查找工具类（注意有异常时，需要抛出异常，以便上层方法进行事务回滚）
 * @createTime 2021-06-07 10:44
 */
public class BatchSelectUtils {

    /**
     * 批量查找方法
     *
     * @param list              要新增的集合
     * @param clazz             Mapper中sqlID的全类名
     * @param sqlSessionFactory
     * @param <T>               传入集合类型
     * @param <U>               返回集合类型
     * @return
     */
    public static <T, U> List<U> batchSelect(List<T> list, String clazz, SqlSessionFactory sqlSessionFactory) {
        return batchSelect(list, clazz, sqlSessionFactory, Constants.BATCH_DEFAULT_COUNT);
    }

    /**
     * 批量查找方法
     *
     * @param list              要新增的集合
     * @param clazz             Mapper中sqlID的全类名
     * @param sqlSessionFactory
     * @param batchCount            一次批量的数量
     * @param <T>               传入集合类型
     * @param <U>               返回集合类型
     * @return
     */
    public static <T, U> List<U> batchSelect(List<T> list, String clazz, SqlSessionFactory sqlSessionFactory, int batchCount) {
        if (CollectionUtils.isEmpty(list) || batchCount <= 0) {
            return new ArrayList<>();
        }
        batchCount = Math.min(batchCount, 2000);
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        List<U> objects = new ArrayList<>();
        try {
            int size = list.size();
            for (int i = 0; i < size; i += batchCount) {
                int end = Math.min(i + batchCount - 1, size - 1);
                List<T> subList = list.subList(i, end + 1);
                objects.addAll(sqlSession.selectList(clazz, subList));
                sqlSession.commit();
            }
            sqlSession.commit();
            sqlSession.clearCache();
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException("批量查找失败，触发回滚");
        } finally {
            sqlSession.close();
        }
        return objects;
    }
}
