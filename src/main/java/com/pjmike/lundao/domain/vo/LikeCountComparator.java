package com.pjmike.lundao.domain.vo;

import com.pjmike.lundao.domain.Comment;
import com.pjmike.lundao.domain.Likecount;

import java.util.Comparator;

/**
 * 对点赞数排序
 *
 * @author pjmike
 * @create 2018-05-31 14:32
 */
public class LikeCountComparator implements Comparator<Comment> {

    @Override
    public int compare(Comment o1, Comment o2) {
        return o1.getFavour() < o2.getFavour() ? 1 : (o1.getFavour().equals(o2.getFavour()) ? 0 : -1);
    }
}
