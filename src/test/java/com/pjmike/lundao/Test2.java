package com.pjmike.lundao;

import com.pjmike.lundao.domain.Comment;
import com.pjmike.lundao.domain.Likecount;
import com.pjmike.lundao.domain.vo.LikeCountComparator;

import java.util.*;

/**
 * @author pjmike
 * @create 2018-05-31 14:41
 */
public class Test2 {
    public static void main(String[] args) {
        List<Likecount> likecountList = new ArrayList<>();
        Likecount likecount1 = new Likecount(1,3,5);
        Likecount likecount2 = new Likecount(2,3,45);
        Likecount likecount3 = new Likecount(3,3,456);
        Likecount likecount4 = new Likecount(4,3,25);
        Likecount likecount5 = new Likecount(5,3,51);
        Likecount likecount6 = new Likecount(6,3,50);
        likecountList.add(likecount1);
        likecountList.add(likecount2);
        likecountList.add(likecount3);
        likecountList.add(likecount4);
        likecountList.add(likecount5);
        likecountList.add(likecount6);
        LinkedList<Likecount> linkedList = new LinkedList<>(likecountList);
//        Collections.sort(linkedList, new LikeCountComparator());
        System.out.println(linkedList);
        Iterator<Likecount> likecountIterator = linkedList.listIterator();
        if (likecountIterator.hasNext()) {
            System.out.println(likecountIterator.next());
            if (likecountIterator.hasNext()) {
                System.out.println(likecountIterator.next());
            }
        }
    }
}
