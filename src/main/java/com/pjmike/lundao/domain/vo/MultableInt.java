package com.pjmike.lundao.domain.vo;

import lombok.Data;

/**
 * @author pjmike
 * @create 2018-05-30 10:48
 */
@Data
public class MultableInt {
    private int count = 1;

    public void increment() {
        ++count;
    }

    public int get() {
        return count;
    }
}
