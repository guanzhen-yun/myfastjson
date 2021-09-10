package com.inke.library.serializer;

/**
 * 对象序列化接口
 */
public interface ObjectSerializer {
    /**
     * 序列化对象
     * @param config 序列化配置(缓存)
     * @param object 需要序列化的JavaBean对象
     * @param out 字符串的拼接
     */
    void write(SerializeConfig config, Object object, StringBuilder out);
}
