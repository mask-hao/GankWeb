package com.zhanghao.util;

import java.util.Comparator;
import java.util.Map;

/**
 * Created by zhanghao on 2017/6/22.
 */
public class ValueComparator implements Comparator<Map.Entry<String,Integer>>{

    @Override
    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
        return o2.getValue() - o1.getValue();
    }
}
