package com.soft1921.maven.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author :Flobby
 * @version :1.0
 * @date :2021/3/11
 * @description :list集合
 */

public class ListTest {
    public static void main(String[] args) {
        // 创建一个list接口的实现类，泛型为string，建议带上初始大小
        List<String> list = new ArrayList<>(8);
        // 增加三个元素
        list.add("java");
        list.add("javaScript");
        list.add("Java");

        System.out.println("用for循环遍历");
        for (String s : list) {
            System.out.print(s + "\t");
        }

        System.out.println("\n\n用Iterator迭代器遍历");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + "\t");
        }

        System.out.println("\n\n用lambda表达式遍历");
        list.forEach(s -> System.out.print(s + "\t"));

        System.out.println("\n\n使用方法引用遍历list，终极简单版本");
        list.forEach(System.out::println);
        System.out.println("\n");
    }
}
