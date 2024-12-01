package com.psugv.capstone.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * The class provide utility functions that can be used generally.
 * Author: Chuan Wei
 */
public class Utility {

    private static final Logger LOGGER = LoggerFactory.getLogger(Utility.class);

    public static List<Integer> commonIdComparator(List<Integer> list1, List<Integer> list2) {

        List<Integer> result = new LinkedList<>();

        if(list1.isEmpty() || list2.isEmpty()) {
            return result;
        }

        int list1Size;

        Set<Integer> set;

        List<Integer> theList;

        try {
            if (list1.size() > list2.size()) {
                list1Size = list2.size();
                set = new HashSet<>(list1);
                theList = list2;

            } else {
                list1Size = list1.size();
                set = new HashSet<>(list2);
                theList = list1;
            }
            for (int i = 0; i < list1Size; i++) {

                if(set.contains(theList.get(i))){
                    result.add(theList.get(i));
                }
            }
        } catch (Exception e) {
            LOGGER.error("Cannot compare the ID", e);
        }
        return result;
    }
}
