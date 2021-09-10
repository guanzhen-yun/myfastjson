package com.inke.library.serializer;

public class JSONSerializer {

    private final SerializeConfig config;
    private final StringBuilder out;

    public JSONSerializer(StringBuilder out, SerializeConfig config) {
        this.out = out;
        this.config = config;
    }

    //开始序列化
    public void write(Object object) {
        //健壮性
        if (object == null) {
            out.append("null");
            return;
        }

        //接口序列化 获取接口的实现类
        Class<?> clazz = object.getClass();// JavaBean 对象的class
        ObjectSerializer write = config.getObjectWriter(clazz);

        write.write(config, object, out);
    }
}
