package com.soft1921.maven.collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author :Flobby
 * @version :1.0
 * @date :2021/3/11
 * @description :map测试
 */

public class MapTest {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>(8);
        map.put("apple", 12);
        map.put("pear", 8);
        map.put("banana", 24);

        System.out.println("通过Map的KeySet，遍历key和value");
        for (String key : map.keySet()) {
            Integer value = map.get(key);
            System.out.println(key + "单价为: " + value);
        }

        System.out.println("\n\n迭代器遍历");
        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            System.out.println(entry.getKey() + "单价为：" + entry.getValue());
        }

        System.out.println("\n\n通过遍历Map的values，只能遍历value，获取不到key");
        for (Integer value : map.values()) {
            System.out.println(value);
        }

        System.out.println("\n\n通过entrySet，遍历key value -------  推荐");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "单价为：" + entry.getValue());
        }

        System.out.println("\n\nlambda表达式遍历 -----  强烈推荐");
        map.forEach((key, value) -> System.out.println("key: " + key + ", value: " + value));
    }
}
