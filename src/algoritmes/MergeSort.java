package algoritmes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSort {


    public static <T extends Comparable<T>> T[] sort(T[] data) {
            if(data == null){
                throw new IllegalArgumentException("Array cannot be null");
            }

            int arrayLength = data.length;
            if(arrayLength <= 1){
                return data;
            }


            int middle = arrayLength / 2;

            T[] left = Arrays.copyOfRange(data, 0, middle);
            T[] right = Arrays.copyOfRange(data, middle, arrayLength);

            sort(left);
            sort(right);

            return merge(data ,left, right);
        }

        private static <T extends Comparable<T>> T[] merge(T[] data ,T[] left, T[] right){
            int dataIndex = 0;
            int leftIndex = 0;
            int rightIndex = 0;

            while(leftIndex < left.length && rightIndex < right.length){
                if(left[leftIndex].compareTo(right[rightIndex]) < 0){
                    data[dataIndex] = left[leftIndex];
                    leftIndex++;
                } else{
                    data[dataIndex] = right[rightIndex];
                    rightIndex++;
                }
                ++dataIndex;
            }

            while(leftIndex < left.length){
                data[dataIndex] = left[leftIndex];
                dataIndex++;
                leftIndex++;
            }

            while(rightIndex < right.length){
                data[dataIndex] = right[rightIndex];
                dataIndex++;
                rightIndex++;
            }
            return data;
    }
}
