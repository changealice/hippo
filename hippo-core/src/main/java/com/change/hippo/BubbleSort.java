package com.change.hippo;


/**
 *  冒泡排序法
 *  重复遍历列表， 相邻顺序不对比较交换，直到不需要遍历。 时间复杂度 O(n²)
 *  n-1 + n-2 + n-3 = O(n²)
 */
public class BubbleSort {

    public static void main(String[] args) {
        int a[] = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35, 25, 53, 51};


        int temp;
        for (int i = 0, len = a.length; i < len; i++) {
            for (int j = 0, len2 = a.length - 1 - i; j < len2; j++) {
                if (a[j] > a[j + 1]) {
                    temp =  a[j];
                    a[j] = a[j + 1];
                    a[j+1] =  temp;
                }
            }
        }

        for (int i = 0, len = a.length; i < len; i++) {
            System.out.print(a[i] + "   ");
        }

    }
}
