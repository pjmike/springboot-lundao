package com.pjmike.lundao.domain.vo;

import com.sun.javafx.collections.MappingChange;
import lombok.Data;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.*;

/**
 * @author pjmike
 * @create 2018-05-31 10:35
 */
@Data
public class CountVector implements Serializable {
    public static Map<Integer, LinkedList<Integer>> map = new HashMap<>();

    @Override
    public String toString() {
        return "CountVector{" +
                "map=" + map +
                '}';
    }
}
