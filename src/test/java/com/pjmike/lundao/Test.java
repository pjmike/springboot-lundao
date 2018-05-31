package com.pjmike.lundao;

import com.pjmike.lundao.utils.CommonUtil;
import com.sun.javafx.collections.MappingChange;

import java.util.*;

/**
 * @author pjmike
 * @create 2018-05-29 15:49
 */
public class Test {
    private static HashMap<Integer, LinkedList<Integer>> map = new HashMap<>();
    private static List<Comment> comments = new ArrayList<>();
    private static LinkedList<Like> likeLinkedList = new LinkedList<>();
    public static void main(String[] args) {
        int cnt = 1;
        int N = 4;
        String str;
        String to_name;
        String from_name;
        Integer id;
        Integer zan;
        for (int i = 0; i < N; i++) {
            Scanner scanner = new Scanner(System.in);
            from_name = scanner.nextLine();
            to_name = scanner.nextLine();
            str = scanner.nextLine();
            id = scanner.nextInt();
            zan = scanner.nextInt();
            Comment comment = new Comment(id,from_name,to_name,str);
//            map.put(id, new LinkedList<>().add(cnt++));
            LinkedList<Integer> integers = new LinkedList<>();
            integers.add(cnt++);
            if (map.get(id) == null) {
                map.put(id, integers);
            } else {
                integers = map.get(id);
                integers.add(cnt++);
                map.put(id, integers);
            }
            likeLinkedList.add(new Like(cnt - 1, zan));
            System.out.println(String.format("%d", cnt - 1));
        }

    }

    void dfs(int x,int id) {
        String to_name = comments.get(id).toUName;
        String from_name = comments.get(id).fromUName;
        System.out.println(from_name+"say: "+comments.get(id)+to_name);
        for (int i = 0; i < map.get(id).size(); i++) {
            if (comments.get(map.get(id).get(i)).fromUName == to_name) {
                dfs(id,map.get(id).get(i));
            }
        }
    }

    void show(int x) {
        String flag;
        Scanner scanner = new Scanner(System.in);
        flag = scanner.nextLine();
        Iterator iterator = likeLinkedList.listIterator();
        while (iterator.hasNext()) {
            if (flag == "+" || flag == "-") {
                if (flag == "+") {
                    if (iterator.hasNext()) {
                        iterator.next();
                    } else {
                        System.out.println("已经到最后一页");
                        continue;
                    }
                }
                if (flag == "-") {
                    if (((ListIterator) iterator).hasPrevious()) {
                        ((ListIterator) iterator).previous();
                    } else {
                        System.out.println("已经到第一页了");

                    }
                }
                Like like = (Like) iterator.next();
                System.out.println("like id"+like.getId()+"  "+comments.get(like.getId()).content);
            }
            if (flag == "show") {
                Like like = (Like) iterator.next();
                show(like.getId());
            }
            if (flag == "print") {
                Like like = (Like) iterator.next();
                dfs(x,like.getId());
            }
            if (flag == "back") {
                return;
            }
        }
    }
}
