package com.soft1921.maven.collection;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author :Flobby
 * @version :1.0
 * @date :2021/3/11
 * @description :学生类set测试
 */

public class SetTest {
    public static void main(String[] args) {
        System.out.println("\nTreeSet实例");
        Student s1 = Student.builder().name("aaa").age(20).build();
        Student s2 = Student.builder().name("ccc").age(19).build();
        Student s3 = Student.builder().name("bbb").age(17).build();
        Set<Student> studentSet = new HashSet<>();
        studentSet.add(s1);
        studentSet.add(s2);
        studentSet.add(s3);
        System.out.println(studentSet);

        Set<String> set = new TreeSet<>();
        set.add("C");
        set.add("A");
        set.add("B");
        System.out.println(set);

        System.out.println("\nHashSet示例");
        Set<String> hashSet = new HashSet<>();
        hashSet.add("C");
        hashSet.add("A");
        hashSet.add("B");
        hashSet.add("B");
        // 用for循环遍历
        System.out.println("for循环遍历");
        Object[] array = hashSet.toArray();
        for (Object o :array){
            System.out.print(o.toString() + "\t");
        }
        // 使用Iterator遍历
        System.out.println("\n\n迭代器遍历");
        Iterator<String> iterator = hashSet.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + "\t");
        }

        // lambda遍历
        System.out.println("\n\nlambda表达式");
        hashSet.forEach(s -> System.out.print(s + "\t"));

        // 使用方法引用遍历
        System.out.println("\n\n方法引用遍历");
        hashSet.forEach(System.out::println);
    }
}
