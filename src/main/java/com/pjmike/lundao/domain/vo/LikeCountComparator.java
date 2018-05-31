package com.pjmike.lundao.domain.vo;

import com.pjmike.lundao.domain.Likecount;

import java.util.Comparator;

/**
 * 对点赞数排序
 *
 * @author pjmike
 * @create 2018-05-31 14:32
 */
public class LikeCountComparator implements Comparator<Likecount> {

    @Override
    public int compare(Likecount o1, Likecount o2) {
        return o1.getCount() < o2.getCount() ? 1 : (o1.getCount().equals(o2.getCount()) ? 0 : -1);
    }
}
