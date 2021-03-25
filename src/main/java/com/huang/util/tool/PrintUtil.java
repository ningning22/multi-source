package com.huang.util.tool;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

public class PrintUtil {

    public static void printMap(Map<String, ?> map, ObjectUtil objectUtil) {
        for (String key : map.keySet()) {
            String value = objectUtil.obj2String(map.get(key));
            System.out.printf("{%s:%s}\n", key, value);
        }
    }

    public static void printList(List<?> list, ObjectUtil objectUtil) {
        for (Object obj : list) {
            String str = objectUtil.obj2String(obj);
            System.out.printf("%s\n", str);
        }
    }

    public static void printMap(String... strings){
        for(int i=0;i<strings.length;i+=2){
            System.out.printf("%s:%s\n",strings[i],strings[i+1]);
        }
    }

    public static void printJSONObject(String text){
        JSONObject jsonObject=JSONObject.parseObject(text);
        for(String key:jsonObject.keySet()){
            System.out.printf("%s:%s\n",key,jsonObject.get(key).toString());
        }
    }
}
