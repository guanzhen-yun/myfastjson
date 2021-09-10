package com.inke.library;

import com.inke.library.serializer.JSONSerializer;
import com.inke.library.serializer.SerializeConfig;

/**
 * 1.2.58
 */
public class JSON {

    /**
     * 序列化对象，将JavaBean转为字符串
     * @param object
     * @return
     */
    public static String toJSONString(Object object) {
        StringBuilder out = new StringBuilder();

        JSONSerializer serializer = new JSONSerializer(out, SerializeConfig.globalInstance);

        serializer.write(object);

        return out.toString();
    }
}
