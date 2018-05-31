package com.pjmike.lundao.service;

import org.apache.ibatis.exceptions.TooManyResultsException;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

/**
 * service层基础接口
 *
 * @author pjmike
 * @create 2018-05-17 22:27
 */
public interface IService<T> {

    default T save(T model) {
        return null;
    }//持久化

    default List<T> save(List<T> models) {
        return null;
    }//批量持久化

    default void deleteById(Integer id) {
    }//通过主鍵刪除

    default void deleteByIds(String ids) {
    }//批量刪除 eg：ids -> “1,2,3,4”

    default void update(T model) {
    }//更新

    default T findById(Integer id) {
        return null;
    }//通过ID查找

    default T findBy(String fieldName, Object value) throws TooManyResultsException {
        return null;
    } //通过Model中某个成员变量名称（非数据表中column的名称）查找,value需符合unique约束

    default List<T> findByIds(String ids) {
        return null;
    }//通过多个ID查找//eg：ids -> “1,2,3,4”

    default List<T> findByCondition(Condition condition) {
        return null;
    }//根据条件查找

    default List<T> findAll() {
        return null;
    }//获取所有
}
