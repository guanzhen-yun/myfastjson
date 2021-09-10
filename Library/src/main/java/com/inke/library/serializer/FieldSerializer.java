package com.inke.library.serializer;

import com.inke.library.utils.FieldInfo;

import java.lang.reflect.InvocationTargetException;

/**
 * "name":"simon"
 */
public class FieldSerializer {

    //"name":
    private String key;
    private FieldInfo fieldInfo;
    //是否基本数据或者包装类类型
    private boolean isPrimitive;

    public FieldSerializer(FieldInfo fieldInfo) {
        this.fieldInfo = fieldInfo;
        //拼接: "name":
        this.key = '"' + fieldInfo.name + "\":";
        //属性的类型
        Class type = fieldInfo.fieldClass;

        //isPrimitive
        isPrimitive = type.isPrimitive() || isJavaObject(type);
    }

    private boolean isJavaObject(Class type) {
        return type == Integer.class || type == Character.class;//....
    }

    public String write(SerializeConfig config, Object object) {

        try {
            Object o = fieldInfo.get(object);
            StringBuilder out = new StringBuilder();

            if (isPrimitive) {
                //"age":35
                out.append(key);
                out.append(o.toString());
            } else if (isString(fieldInfo.fieldClass)) {
                //"name":"simon"
                out.append(key);
                out.append('"');
                out.append(o.toString());
                out.append('"');
            } else {
                //JavaBean的对象 private UserInfo userInfo; //购买用户
                // 递归 !!!!!!
                if(o == null) return "";
                ObjectSerializer objectWriter = config.getObjectWriter(fieldInfo.fieldClass);
                out.append(key);
                objectWriter.write(config, o, out);
            }
            return out.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private boolean isString(Class<?> fieldClass) {
        return CharSequence.class.isAssignableFrom(fieldClass);
    }
}
