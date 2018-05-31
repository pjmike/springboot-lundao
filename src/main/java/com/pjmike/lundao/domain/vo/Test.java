package com.pjmike.lundao.domain.vo;

import java.util.HashMap;
import java.util.*;

/**
 * @author pjmike
 * @create 2018-05-26 10:11
 */
public class Test {
    public static void main(String[] args) {
        HashMap<Integer, Reply> map = new HashMap<>(16);
        Reply reply = new Reply(1, 2, 1, "hi ");
        map.put(1, reply);
        reply = new Reply(2, 1, 2, "hello");
        map.put(2, reply);
        reply = new Reply(3, 1, 3, "hello");
        map.put(3, reply);
        reply = new Reply(4, 2, 1, "world");
        map.put(4, reply);
        reply = new Reply(5, 2, 3, "hello");
        map.put(5, reply);
        reply = new Reply(6, 3, 1, "nihao");
        map.put(6, reply);
        reply = new Reply(7, 1, 2, "hsdfllo");
        map.put(7, reply);


        Test test = new Test();
        HashMap<Integer,Reply> replyHashMap = test.dfs(map,1,2);
        for (int i : replyHashMap.keySet()) {
            System.out.println(replyHashMap.get(i).getContent());
        }
    }

    HashMap<Integer,Reply> dfs(HashMap<Integer,Reply> map, int x, int id) {
        HashMap<Integer, Reply> ans = new HashMap<>(16);
        for (int i : map.keySet()) {
                        if (map.get(i).from_uid == x && map.get(i).to_uid == id) {
                //查找x发给id的回复
                ans.put(i,map.get(i));
            }
            else if (map.get(i).from_uid == id && map.get(i).to_uid == x) {
                //查找id发给x的回复
                ans.put(i,map.get(i));
            }
        }
        return ans;
    }
}
