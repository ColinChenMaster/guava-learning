package com.learning.guava;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.ObjectArrays;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class GuavaCollections {
    public static void main(String[] args) {
        Map<String, Map<String, String>> map = Maps.newHashMap();

        List<List<Map<String, String>>> list = Lists.newArrayList();

//1,简化集合的创建
        List<Integer> personList= Lists.newLinkedList();
        Set<Integer> personSet= Sets.newHashSet();
        Map<String,Integer> personMap= Maps.newHashMap();
        Integer[] intArrays= ObjectArrays.newArray(Integer.class,10);
    }
}
