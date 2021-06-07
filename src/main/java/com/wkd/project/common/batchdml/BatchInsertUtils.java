package com.wkd.project.common.batchdml;

import com.wkd.project.common.constant.Constants;
import com.wkd.project.common.utils.CollectionUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;
import java.util.function.BiConsumer;

/**
 * @author wkd
 * @version 1.0.0
 * @className Constants.java
 * @description 批量插入工具类（注意有异常时，需要抛出异常，以便上层方法进行事务回滚）
 * @createTime 2021-06-07 10:44
 */
public class BatchInsertUtils {

    /**
     * 批量新增方法 (一条条遍历)
     *
     * @param list              要新增的集合
     * @param clazz             Mapper 类
     * @param biConsumer        对应的单条新增方法
     * @param sqlSessionFactory {@link SqlSessionFactory}
     * @param <M>               mapper 类型
     * @param <T>               集合元素类型
     */
    public static <M, T> void batchInsert(List<T> list, Class<M> clazz, BiConsumer<M, T> biConsumer, SqlSessionFactory sqlSessionFactory) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        try {
            M mapper = sqlSession.getMapper(clazz);
            list.forEach(e -> biConsumer.accept(mapper, e));
            sqlSession.commit();
            sqlSession.clearCache();
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException("批量导入失败，触发回滚");
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 批量新增方法
     *
     * @param list              要新增的集合
     * @param clazz             Mapper中sqlID的全类名
     * @param sqlSessionFactory {@link SqlSessionFactory}
     * @param <T>               集合元素类型
     */
    public static <T> void batchInsert(List<T> list, String clazz, SqlSessionFactory sqlSessionFactory) {
        batchInsert(list, clazz, sqlSessionFactory, Constants.BATCH_DEFAULT_COUNT);
    }

    /**
     * 批量新增方法
     *
     * @param list              要新增的集合
     * @param clazz             Mapper中sqlID的全类名
     * @param sqlSessionFactory {@link SqlSessionFactory}
     * @param <T>               集合元素类型
     */
    public static <T> void batchInsert(List<T> list, String clazz, SqlSessionFactory sqlSessionFactory, int batchCount) {
        if (CollectionUtils.isEmpty(list) || batchCount <= 0) {
            return;
        }
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        try {
            int batchLastIndex = batchCount - 1;
            for (int index = 0; index < list.size(); ) {
                if (batchLastIndex > list.size() - 1) {
                    batchLastIndex = list.size() - 1;
                    sqlSession.insert(clazz, list.subList(index, batchLastIndex + 1));
                    sqlSession.commit();
                    break;
                } else {
                    sqlSession.insert(clazz, list.subList(index, batchLastIndex + 1));
                    sqlSession.commit();
                    index = batchLastIndex + 1;
                    batchLastIndex = index + (batchCount - 1);
                }
            }
            sqlSession.commit();
            sqlSession.clearCache();
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException("批量导入失败，触发回滚");
        } finally {
            sqlSession.close();
        }
    }
}
