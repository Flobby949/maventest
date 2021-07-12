package com.soft1921.maven.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author :Flobby
 * @version :1.0
 * @date :2021/3/11
 * @description :map里嵌套list
 */

public class StudentMapListTest {
    public static void main(String[] args) {
        List<Student> list1 = new ArrayList<>();
        List<Student> list2 = new ArrayList<>();
        list1.add(new Student("aaa", 20));
        list1.add(new Student("bbb", 25));
        list2.add(new Student("ccc", 21));
        list2.add(new Student("ddd", 19));
        Map<String, List<Student>> map = new HashMap<>();
        map.put("男生", list1);
        map.put("女生", list2);
        System.out.println("********按性别输出*********");
        for (Map.Entry<String, List<Student>> entry : map.entrySet()) {
            System.out.println(entry.getKey());
            List<Student> list = entry.getValue();
            // lambda遍历内层循环
            list.forEach(stu -> {
                System.out.println(stu.getName() + "\t" + stu.getAge());
            });
        }
    }
}
