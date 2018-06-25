package com.thinvent.zhhd.common.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.thinvent.library.dim.PosTypeEnum;
import com.thinvent.library.dim.TvtExceptionEnum;
import com.thinvent.library.exception.OptionException;
import com.thinvent.library.exception.ThinventBaseException;

/**
 * Created by KYe on 2017/6/19.
 */
public class EnumUtil {
    /**
     * 将指定Enum转换为List
     */
    public static List<Map<String, String>> parseToMaps(String className) throws ThinventBaseException {
        String packageName = PosTypeEnum.class.getPackage().getName();
        String fullClassName = packageName + "." + className;
        List list = new ArrayList();
        try {
            Class clazz = Class.forName(fullClassName);
            Method getName = clazz.getMethod("getName");
            Method getIndex = clazz.getMethod("getIndex");
            //得到enum的所有实例
            Object[] objs = clazz.getEnumConstants();
            for (Object obj : objs) {
                LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
                map.put("index", getIndex.invoke(obj).toString());
                map.put("name", (String) getName.invoke(obj));
                list.add(map);
            }
        } catch (Exception e) {
            throw new OptionException(TvtExceptionEnum.OPT_QUERY_FAULSE.getIndex(), TvtExceptionEnum.OPT_QUERY_FAULSE.getName());
        }
        return list;
    }

    // 提供主方法，测试
    public static void main(String args[]) throws ThinventBaseException {
        List list = parseToMaps("PosTypeEnum");
        System.out.println(JSON.toJSON(list));
    }
}
