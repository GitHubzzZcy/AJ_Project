package com.henghao.service.impl;

/**
 * @Description:Redis服务
 * @author zcy  
 * @date 2017年11月15日 下午2:38:23
 * @version 1.0
 */
public interface Function<T, E> {

    public T callback(E e);

}
