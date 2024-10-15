package com.aakash.dsa.bitwise;

import java.util.ArrayList;
import java.util.List;

public class EqualSetUnsetBits {
    public static void main(String[] args) {
        int[] nums = {5, 10, 15, 7, 6, 9, 3, 12};
        System.out.println(equalSetBits(nums));
    }

    public static List<Integer> equalSetBits(int[] nums) {

        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            int setBits = 0;
            int unsetBits = 0;
//            while (num > 0){
                unsetBits |= (num & 1);
                setBits |= (num >> 1);
//                num >>= 1;
//            }

            if (setBits == unsetBits) {
                list.add(num);
            }
        }

        return list;
    }

}
