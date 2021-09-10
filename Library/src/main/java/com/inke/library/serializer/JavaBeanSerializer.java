package com.inke.library.serializer;

import com.inke.library.utils.FieldInfo;
import com.inke.library.utils.TypeUtils;

import java.util.List;

public class JavaBeanSerializer implements ObjectSerializer {

    //收集控件
    private final FieldSerializer[] getters;

    public JavaBeanSerializer(Class<?> clazz) {
        List<FieldInfo> fieldInfoList = TypeUtils.buildBeanInfo(clazz, true);

        getters = new FieldSerializer[fieldInfoList.size()];

        for (int i = 0; i < getters.length; i++) {
            //属性序列化数组，构造方法传入该属性的信息
            getters[i] = new FieldSerializer(fieldInfoList.get(i));
        }
    }

    @Override
    public void write(SerializeConfig config, Object object, StringBuilder out) {
         out.append("{");

         boolean isEnd = true;
         //循环属性的序列化器
        for (FieldSerializer getter : getters) {
            if (!isEnd) {
                out.append(",");
            }

            //完整的"name":"simon","age":35
            String entry = getter.write(config, object);

            out.append(entry);

            isEnd = entry.isEmpty();

        }
         out.append("}");
    }
}
