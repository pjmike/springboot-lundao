package com.pjmike.lundao.domain.vo;

import com.sun.javafx.collections.MappingChange;
import lombok.Data;

import java.util.LinkedList;
import java.util.*;

/**
 * @author pjmike
 * @create 2018-05-31 10:35
 */
@Data
public class CountVector {
    public Map<Integer, LinkedList<Integer>> map = new HashMap<>();

}
