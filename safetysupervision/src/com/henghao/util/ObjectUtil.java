package com.henghao.util;

import java.lang.reflect.Field;

public class ObjectUtil {


    /**
     * 将数据库信息同步到新实体类中
     * @param oldEntity 数据库
     * @param newEntity 接收类
     * @return Object
     * @throws Exception
     */
    public static Object updateNew(Object oldEntity, Object newEntity)
            throws Exception {
        Field[] oFields = oldEntity.getClass().getDeclaredFields();
        Field[] nFields = newEntity.getClass().getDeclaredFields();
        for (int i = 0; i < oFields.length; i++) {
            Field of = oFields[i];
            Field nf = nFields[i];

            of.setAccessible(true);
            nf.setAccessible(true);
            if ((of.equals(nf)) && (nf.get(newEntity) != null)) {
                of.set(oldEntity, nf.get(newEntity));
            }
            oFields[i] = of;
        }
        return oldEntity;
    }

    /**
     * 统计对象属性为空的个数
     * @param obj 对象
     * @return int
     */
    public static int countNullProperty(Object obj) {

        int count = 0;
        // 如果obj为空，则默认属性也为空
        if (obj == null)
            return count;
        // 利用反射机制，判断对象的属性是否为空，或者空字符串
        for(Field f : obj.getClass().getDeclaredFields()){
            f.setAccessible(true);
            try {
                if(f.get(obj) == null || "".equals(f.get(obj))){
                    // 有一个为空或空字符串，则+1
                    count++;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return count;
    }
}
