package com.inke.library.serializer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 序列化配置
 */
public class SerializeConfig {

    //单例
    public final static SerializeConfig globalInstance  = new SerializeConfig();

    private final Map<Class, ObjectSerializer> serializersCache;

    private SerializeConfig() {
        serializersCache = new HashMap<>();
    }

    //对象序列化接口的实现
    public ObjectSerializer getObjectWriter(Class<?> clazz) {
        // 从缓存中获取序列化接口
        ObjectSerializer write = serializersCache.get(clazz);
        if(write != null) {
            return write;
        }
        if (Map.class.isAssignableFrom(clazz)) {
            throw new RuntimeException("暂未实现Map序列");
        } else if (List.class.isAssignableFrom(clazz)) {
            throw new RuntimeException("暂未实现List序列");
        } else if(clazz.isArray()) {
            throw new RuntimeException("暂未实现数组序列");
        }

        //省略很多判断
        else {
            //接口=接口的实现类
            write = new JavaBeanSerializer(clazz);
        }

        //加入缓存
        serializersCache.put(clazz, write);

        return write;
    }
}
