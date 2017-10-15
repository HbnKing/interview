package com.interview.bigdata.java;



/*用Java写一个折半查找。
答：折半查找，也称二分查找、二分搜索，是一种在有序数组中查找某一特定元素的搜索算法。
搜素过程从数组的中间元素开始，如果中间元素正好是要查找的元素，则搜素过程结束；如果某一特定元素大于或者小于中间元素
，则在数组大于或小于中间元素的那一半中查找，而且跟开始一样从中间元素开始比较。如果在某一步骤数组已经为空，
则表示找不到指定的元素。这种搜索算法每一次比较都使搜索范围缩小一半，其时间复杂度是O(logN)。
*/
import java.util.Comparator;

public class MyUtil {

public static <T extends Comparable<T>> int binarySearch(T[] x, T key) {
return binarySearch(x, 0, x.length- 1, key);
}

// 使用循环实现的二分查找
public static <T> int binarySearch(T[] x, T key, Comparator<T> comp) {
int low = 0;
int high = x.length - 1;
while (low <= high) {
  int mid = (low + high) >>> 1;
  int cmp = comp.compare(x[mid], key);
  if (cmp < 0) {
    low= mid + 1;
  }
  else if (cmp > 0) {
    high= mid - 1;
  }
  else {
    return mid;
  }
}
return -1;
}

// 使用递归实现的二分查找
private static<T extends Comparable<T>> int binarySearch(T[] x, int low, int high, T key) {
if(low <= high) {
int mid = low + ((high -low) >> 1);
if(key.compareTo(x[mid])== 0) {
   return mid;
}
else if(key.compareTo(x[mid])< 0) {
   return binarySearch(x,low, mid - 1, key);
}
else {
   return binarySearch(x,mid + 1, high, key);
}
}
return -1;
}
}