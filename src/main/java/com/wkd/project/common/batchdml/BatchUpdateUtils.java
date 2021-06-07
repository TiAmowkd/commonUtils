package com.wkd.project.common.batchdml;

import com.wkd.project.common.constant.Constants;
import com.wkd.project.common.utils.CollectionUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author wkd
 * @version 1.0.0
 * @className Constants.java
 * @description 批量更新工具类（注意有异常时，需要抛出异常，以便上层方法进行事务回滚）
 * @createTime 2021-06-07 10:44
 */
public class BatchUpdateUtils {

    /**
     * 批量更新方法
     *
     * @param list              要新增的集合
     * @param clazz             Mapper中sqlID的全类名
     * @param sqlSessionFactory {@link SqlSessionFactory}
     * @param <T>               集合元素类型
     */
    public static <T> void batchUpdate(List<T> list, String clazz, SqlSessionFactory sqlSessionFactory) {
        batchUpdate(list, clazz, sqlSessionFactory, Constants.BATCH_DEFAULT_COUNT);
    }

    /**
     * 批量更新方法
     *
     * @param list
     * @param clazz
     * @param sqlSessionFactory
     * @param batchCount        要新增的数量
     * @param <T>
     */
    public static <T> void batchUpdate(List<T> list, String clazz, SqlSessionFactory sqlSessionFactory, int batchCount) {
        if (CollectionUtils.isEmpty(list) || batchCount <= 0) {
            return;
        }
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        try {
            int batchLastIndex = batchCount - 1;
            for (int index = 0; index < list.size(); ) {
                if (batchLastIndex > list.size() - 1) {
                    batchLastIndex = list.size() - 1;
                    sqlSession.update(clazz, list.subList(index, batchLastIndex + 1));
                    sqlSession.commit();
                    break;
                } else {
                    sqlSession.update(clazz, list.subList(index, batchLastIndex + 1));
                    sqlSession.commit();
                    index = batchLastIndex + 1;
                    batchLastIndex = index + (batchCount - 1);
                }
            }
            sqlSession.commit();
            sqlSession.clearCache();
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException("批量更新失败，触发回滚");
        } finally {
            sqlSession.close();
        }
    }
}
